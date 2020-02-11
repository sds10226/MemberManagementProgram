package org.comstudy21.mms.frame;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.comstudy21.mms.jdbc.JdbcUtil;
import org.comstudy21.mms.mdao.MemberDao;

@SuppressWarnings("serial")
public class ModifyScreen extends JFrame implements ActionListener{
	Connection conn = JdbcUtil.getConnection();
	MemberDao dao = new MemberDao(conn);
	JPanel mos = (JPanel) getContentPane();
	JPanel g1 = new JPanel();
	JPanel g2 = new JPanel();
	JLabel[] lb = new JLabel[4];
	private static final String[] istr = {"�̸�", "����", "����", "��ȭ��ȣ", "�ּ�", "�̸���", "SNS", "�������", "������","�������ϸ�"};
	private static final String[] istrEng = {"name", "age", "sex", "phone", "address", "email", "sns", "birth", "joindate", "picture"};
	JComboBox<String> cb= new JComboBox<>(istr);
	JTextField[] tf = new JTextField[2];
	
	Button edt= new Button("�����ϱ�");
	
	public ModifyScreen() {
		mos.setLayout(new GridLayout(10,1));
		g1.setLayout(new FlowLayout());
		lb[0] = new JLabel("������ ����� ��ȣ ��  �׸��� �����ϰ� ���氪�� �Է����ּ���");
		lb[1] = new JLabel("�� ȣ");
		lb[2] = new JLabel("�� ��");
		lb[3] = new JLabel("���氪");
		tf[0] = new JTextField();
		tf[1] = new JTextField();
		tf[0].setPreferredSize(new Dimension(100,20));
		tf[1].setPreferredSize(new Dimension(100,20));
		edt.addActionListener(this);
		cb.setLightWeightPopupEnabled(false);
		
		mos.add(lb[0],-1);
		g1.add(lb[1]);
		g1.add(tf[0]);
		g2.add(lb[2]);
		g2.add(cb);
		g2.add(lb[3]);
		g2.add(tf[1]);
		mos.add(g1, -1);
		mos.add(g2, -1);
		mos.add(edt, -1);
	}
	
	public void modifyData(){
		String num = tf[0].getText();
		int no=0;
		try {
			no = Integer.parseInt(num);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "���� �� ����� ȸ����ȣ�� �Է� �����մϴ�.");
			return;
		}
		int itemN = cb.getSelectedIndex();
		String val = tf[1].getText();
		dao.modifyData(no, istrEng[itemN], val);
		for (int i = 0; i < tf.length; i++) {
			tf[i].setText("");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(edt==e.getSource()){
			modifyData();
			
		}
	}
}
