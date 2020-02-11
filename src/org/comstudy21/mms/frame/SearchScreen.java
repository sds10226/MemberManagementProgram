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
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.comstudy21.mms.jdbc.JdbcUtil;
import org.comstudy21.mms.mdao.MemberDao;

@SuppressWarnings("serial")
public class SearchScreen extends JFrame implements ActionListener{
	Connection conn = JdbcUtil.getConnection();
	MemberDao dao = new MemberDao(conn);
	JPanel ss = (JPanel) getContentPane();
	JPanel g1 = new JPanel();
	JPanel g2 = new JPanel();
	JLabel[] lb = new JLabel[3];
	private static final String[] istr = {"이름", "나이", "성별", "전화번호", "주소", "이메일", "SNS", "생년월일", "가입일", "사진파일명"};
	private static final String[] istrEng = {"name", "age", "sex", "phone", "address", "email", "sns", "birth", "joindate", "picture"};
	JComboBox<String> cb= new JComboBox<>(istr);
	JTextField tf = new JTextField();
	
	Button srch= new Button("검색하기");
	
	public SearchScreen() {
		ss.setLayout(new GridLayout(10,1));
		g1.setLayout(new FlowLayout());
		lb[0] = new JLabel("검색할 항목과 단어를 입력해주세요");
		lb[1] = new JLabel("항 목");
		lb[2] = new JLabel("검색값");
		tf = new JTextField();
		tf.setPreferredSize(new Dimension(100,20));
		srch.addActionListener(this);
		cb.setLightWeightPopupEnabled(false);
		
		ss.add(lb[0],-1);
		g1.add(lb[1]);
		g1.add(cb);
		g1.add(lb[2]);
		g1.add(tf);
		ss.add(g1, -1);
		ss.add(srch,-1);
	}

	
	public Object[] searchData(){
		Object[] obj = new Object[2]; 
		obj[0] = istrEng[cb.getSelectedIndex()];
		obj[1] = tf.getText();
		return obj;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(srch==e.getSource()){
			Object[] obj = searchData();
			String itemName = (String) obj[0];
			String val = (String) obj[1];		
			OutputScreen.ls.changeTableSelectData(itemName, val);
			tf.setText("");
			
		}
	}
}
