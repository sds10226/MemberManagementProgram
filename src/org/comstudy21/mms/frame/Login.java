package org.comstudy21.mms.frame;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class Login {
	String[] pwinput = { "입 력" };
	String[] logintype = { "일반", "관리자" };
	String[] restarttype = { "비밀번호 재입력", "사용자 재선택" };
	int choice = 0;
	private static final String PASSWORD = "12345";
	JPanel tp = new JPanel();
	JLabel lb = new JLabel("관리자 비밀번호를 입력해주세요.");
	JPasswordField pf = new JPasswordField();

	public Login() {
		pf.setPreferredSize(new Dimension(100, 20));
		tp.setLayout(new GridLayout(2, 1));
		tp.add(lb);
		tp.add(pf);
		choice = JOptionPane.showOptionDialog(null, "사용자를 선택하세요!", "로그인옵션", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, logintype, "일반");
		if (choice == 1) {
			password();
		}
	}
	
	public void reLogin(){
		choice = JOptionPane.showOptionDialog(null, "사용자를 선택하세요!", "로그인옵션", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, logintype, "일반");
		if (choice == 1) {
			password();
		}
	}

	public void password() {
		while (true) {
			int cfm = JOptionPane.showOptionDialog(null, tp, "비밀번호 입력", JOptionPane.OK_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, pwinput, "입 력");
			char[] getpw = pf.getPassword();
			String pw = "";
			for (int i = 0; i < getpw.length; i++) {
				pw += getpw[i];
			}
			if (cfm == 0 && pw.equals(PASSWORD)) {
				break;
			} else if (pw == "") {
				JOptionPane.showMessageDialog(null, "비밀번호가 입력되지 않았습니다.");
			} else if (cfm == 0 && !pw.equals(PASSWORD) && pw != "") {
				int select = JOptionPane.showOptionDialog(null, "비밀번호가 맞지 않습니다. 다시 입력해주세요.", "확 인",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, restarttype, "비밀번호 재입력");
				if (select == 1) {
					choice = JOptionPane.showOptionDialog(null, "사용자를 선택하세요!", "로그인옵션", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, logintype, "일반");
					if (choice == 0) {
						return;
					}
				}
			}
		}
	}
	
}
