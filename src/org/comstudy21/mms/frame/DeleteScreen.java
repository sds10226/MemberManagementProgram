package org.comstudy21.mms.frame;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.comstudy21.mms.jdbc.JdbcUtil;
import org.comstudy21.mms.mdao.MemberDao;

@SuppressWarnings("serial")
public class DeleteScreen extends JFrame implements ActionListener{
	Connection conn = JdbcUtil.getConnection();
	MemberDao dao = new MemberDao(conn);
	JPanel ds = (JPanel) getContentPane();
	JPanel g1 = new JPanel();
	JPanel g2 = new JPanel();
	JPanel g3 = new JPanel();
	JPanel g4 = new JPanel();
	JPanel g5 = new JPanel();
	JLabel lb = new JLabel("���� �� ȸ����ȣ �Է�");
	JTextField tf = new JTextField();
	
	Button deleteN = new Button("�Է¹�ȣ �����");
	Button deleteAll = new Button("��ü ���̺� �����");
	
	public DeleteScreen() {
		ds.setLayout(new GridLayout(10,1));
		g1.setLayout(new FlowLayout());
		tf.setPreferredSize(new Dimension(100,20));
		deleteN.addActionListener(this);
		deleteAll.addActionListener(this);
		g1.add(lb);
		g1.add(tf);
		ds.add(g1);
		ds.add(deleteN);
		ds.add(deleteAll);
		
	}
	
	public void deleteData(){
		String num = tf.getText();
		int no=0;
		try {
			no = Integer.parseInt(num);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "���� �� ����� ȸ����ȣ�� �Է� �����մϴ�.");
			return;
		}
		dao.deleteData(no);
		tf.setText("");
	}
	
	public void deleteAll(){
		dao.deleteAll();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(deleteN==e.getSource()){
			deleteData();
			
		}else if(deleteAll==e.getSource()){
			int cfm = JOptionPane.showConfirmDialog(null, "���� DB�� �ִ� ���̺��� ����ðڽ��ϱ�?", "Ȯ ��", JOptionPane.YES_NO_OPTION);
			if(cfm==0){
				deleteAll();	
			}else{
				return;
			}
		}
		
		
	}
}
