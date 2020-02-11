package org.comstudy21.mms.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import org.comstudy21.mms.jdbc.JdbcUtil;
import org.comstudy21.mms.mdao.MemberDao;

@SuppressWarnings("serial")
public class InfoScreen extends JFrame{
	Connection conn = JdbcUtil.getConnection();
	JPanel fs = new JPanel();
	TitledBorder tb = new TitledBorder("�� ����");
	MemberDao dao = new MemberDao(conn);
	JLabel imglabel = new JLabel();
	JPanel g1 = new JPanel();
	JPanel g2 = new JPanel();
	JPanel g3 = new JPanel();
	JPanel g4 = new JPanel();
	JPanel g5 = new JPanel();
	JPanel cntp = new JPanel();
	JLabel[] lb = new JLabel[10];
	private static final String[] istr = { "ȸ����ȣ", "��         ��", "��         ��", "��         ��", "��ȭ��ȣ", "��         ��", "��  ��  ��", "S     N     S", "�������", "��  ��  ��" };
	JTextField[] tf = new JTextField[10];

	public InfoScreen() {
		fs.setLayout(new BorderLayout());
		fs.setBorder(tb);
		// -----------------�̹���---------------------------
		imglabel.setIcon(new ImageIcon("image/null.jpg"));
		imglabel.setPreferredSize(new Dimension(200, 200));
		imglabel.setHorizontalAlignment(JLabel.CENTER);
		fs.add(imglabel, BorderLayout.NORTH);
		// -----------------�� �� �ؽ�Ʈ�ʵ� ����-------------------
		for (int i = 0; i < istr.length; i++) {
			lb[i] = new JLabel(istr[i]);
			lb[i].setHorizontalAlignment(JLabel.LEFT);
			tf[i] = new JTextField();
			tf[i].setHorizontalAlignment(JLabel.LEFT);
		}
		// ----------------���� �г� ����--------------------------
		cntp.setLayout(new GridLayout(6, 1));
		fs.add(cntp, BorderLayout.CENTER);
		// -----------���� �гο� ��ȣ �� �̸� �߰� -----------------
		tf[0].setPreferredSize(new Dimension(100, 20));
		tf[1].setPreferredSize(new Dimension(100, 20));
		g1.setLayout(new FlowLayout(FlowLayout.CENTER));
		g1.add(lb[0]);
		g1.add(tf[0]);
		g1.add(lb[1]);
		g1.add(tf[1]);
		cntp.add(g1);
		// -----------���� �гο� ���� ���� �߰� -----------------
		tf[2].setPreferredSize(new Dimension(100, 20));
		tf[3].setPreferredSize(new Dimension(100, 20));
		g2.setLayout(new FlowLayout(FlowLayout.CENTER));
		g2.add(lb[2]);
		g2.add(tf[2]);
		g2.add(lb[3]);
		g2.add(tf[3]);
		cntp.add(g2);
		// -----------���� �гο� ��ȭ��ȣ �ּ��߰� -----------------
		tf[4].setPreferredSize(new Dimension(100, 20));
		tf[5].setPreferredSize(new Dimension(100, 20));
		g3.setLayout(new FlowLayout(FlowLayout.CENTER));
		g3.add(lb[4]);
		g3.add(tf[4]);
		g3.add(lb[5]);
		g3.add(tf[5]);
		cntp.add(g3);
		// -----------���� �гο� �̸��� SNS �߰� -----------------
		tf[6].setPreferredSize(new Dimension(100, 20));
		tf[7].setPreferredSize(new Dimension(100, 20));
		g4.setLayout(new FlowLayout(FlowLayout.CENTER));
		g4.add(lb[6]);
		g4.add(tf[6]);
		g4.add(lb[7]);
		g4.add(tf[7]);
		cntp.add(g4);
		// -----------���� �гο� ���� ���� �߰� -----------------
		tf[8].setPreferredSize(new Dimension(100, 20));
		tf[9].setPreferredSize(new Dimension(100, 20));
		g5.setLayout(new FlowLayout(FlowLayout.CENTER));
		g5.add(lb[8]);
		g5.add(tf[8]);
		g5.add(lb[9]);
		g5.add(tf[9]);
		cntp.add(g5);
	}

	public void showData(int no) {
		String mno = Integer.toString(no);
		Vector<Vector<String>> data = dao.select("mno", mno);
		for (int i = 0; i < data.size(); i++) {
			for (int j = 0; j < data.get(i).size(); j++) {
				if(j==10){
					String path = "image/"+(String) data.get(i).get(j);
					imglabel.setIcon(new ImageIcon(path));
					imglabel.setToolTipText((String) data.get(i).get(j));
					
				}else{
					tf[j].setText((String) data.get(i).get(j));	
				}
			}
		}
		imglabel.setPreferredSize(new Dimension(200, 200));
		fs.invalidate();
		fs.repaint();
	}
}
