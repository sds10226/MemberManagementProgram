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
	String[] wrapoption = { "�ߺ����� �׸� �߰��ϱ�", "��ü�����" };
	{
		String[] columnNames = { "ȸ����ȣ", "�̸�", "����", "����", "��ȭ��ȣ", "�ּ�", "�̸���", "SNS", "�������", "������", "�������ϸ�" };
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
		TitledBorder bd = new TitledBorder("���");
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
		int result = JOptionPane.showConfirmDialog(null, "���������� ���̺��� DB�� �����ðڽ��ϱ�?", "Ȯ ��", JOptionPane.YES_NO_OPTION);
		if (result == 0) {
			int wrap = JOptionPane.showOptionDialog(null, "DB�� ����� �ɼ��� �������ּ���.", "Ȯ ��", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, wrapoption, "�ߺ����� �׸��߰�");
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
		// e.getButton ��ȯ�� 1=Ŭ��, 2=����Ŭ��, 3=������Ŭ��
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