package org.comstudy21.mms.frame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import org.comstudy21.mms.jdbc.JdbcUtil;
import org.comstudy21.mms.mdao.MemberDao;

@SuppressWarnings("serial")
public class ListScreen extends JFrame implements MouseListener {
	Vector<String> columnNameV = new Vector<>();
	public static int no = 0;
	String[] wrapoption = { "중복제외 항목 추가하기", "전체덮어쓰기" };
	{
		String[] columnNames = { "회원번호", "이름", "나이", "성별", "전화번호", "주소", "이메일", "SNS", "생년월일", "가입일", "사진파일명" };
		for (int i = 0; i < columnNames.length; i++) {
			columnNameV.add(i, columnNames[i]);
		}
	}
	Connection conn = JdbcUtil.getConnection();
	JPanel ls = (JPanel) getContentPane();
	public static JTable table;
	DefaultTableModel tableModel = new DefaultTableModel();
	JScrollPane sp = new JScrollPane(table);
	MemberDao dao = new MemberDao(conn);
	Vector<Vector<String>> data = dao.selectAll();

	public ListScreen() {
		TitledBorder bd = new TitledBorder("목록");
		ls.setBorder(bd);
		tableModel = new DefaultTableModel(data, columnNameV);
		table = new JTable(tableModel);
		table.addMouseListener(this);
		JScrollPane sp = new JScrollPane(table);
		ls.add(sp);
	}

	public void changeTableData() {
		Vector<Vector<String>> data = dao.selectAll();
		tableModel.setDataVector(data, columnNameV);
		tableModel.fireTableDataChanged();
	}

	public void changeTableSelectData(String itemName, String val) {
		Vector<Vector<String>> data = dao.select(itemName, val);
		tableModel.setDataVector(data, columnNameV);
		tableModel.fireTableDataChanged();
	}

	public void showTableImData(Vector<Vector<String>> list) {
		tableModel.setDataVector(list, columnNameV);
		tableModel.fireTableDataChanged();
		int result = JOptionPane.showConfirmDialog(null, "가져오기한 테이블을 DB에 넣으시겠습니까?", "확 인", JOptionPane.YES_NO_OPTION);
		if (result == 0) {
			int wrap = JOptionPane.showOptionDialog(null, "DB에 덮어쓸때 옵션을 선택해주세요.", "확 인", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, wrapoption, "중복제외 항목추가");
			if(wrap==0){
				dao.insertCsv(list);
			}else if(wrap==1){
				dao.deleteAll();
				dao.insertCsv(list);
			}
		} else {}
		changeTableData();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// e.getButton 반환값 1=클릭, 2=더블클릭, 3=오른쪽클릭
		if (e.getButton() == 1) {
			int row = table.getSelectedRow();
			no = Integer.parseInt((String) table.getValueAt(row, 0));
			ViewScreen.fs.showData(no);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	// getSelectedColumn(), getSelectedrow()
}