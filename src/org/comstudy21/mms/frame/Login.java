package org.comstudy21.mms.frame;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class Login {
	String[] pwinput = { "�� ��" };
	String[] logintype = { "�Ϲ�", "������" };
	String[] restarttype = { "��й�ȣ ���Է�", "����� �缱��" };
	int choice = 0;
	private static final String PASSWORD = "12345";
	JPanel tp = new JPanel();
	JLabel lb = new JLabel("������ ��й�ȣ�� �Է����ּ���.");
	JPasswordField pf = new JPasswordField();

	public Login() {
		pf.setPreferredSize(new Dimension(100, 20));
		tp.setLayout(new GridLayout(2, 1));
		tp.add(lb);
		tp.add(pf);
		choice = JOptionPane.showOptionDialog(null, "����ڸ� �����ϼ���!", "�α��οɼ�", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, logintype, "�Ϲ�");
		if (choice == 1) {
			password();
		}
	}
	
	public void reLogin(){
		choice = JOptionPane.showOptionDialog(null, "����ڸ� �����ϼ���!", "�α��οɼ�", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, logintype, "�Ϲ�");
		if (choice == 1) {
			password();
		}
	}

	public void password() {
		while (true) {
			int cfm = JOptionPane.showOptionDialog(null, tp, "��й�ȣ �Է�", JOptionPane.OK_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, pwinput, "�� ��");
			char[] getpw = pf.getPassword();
			String pw = "";
			for (int i = 0; i < getpw.length; i++) {
				pw += getpw[i];
			}
			if (cfm == 0 && pw.equals(PASSWORD)) {
				break;
			} else if (pw == "") {
				JOptionPane.showMessageDialog(null, "��й�ȣ�� �Էµ��� �ʾҽ��ϴ�.");
			} else if (cfm == 0 && !pw.equals(PASSWORD) && pw != "") {
				int select = JOptionPane.showOptionDialog(null, "��й�ȣ�� ���� �ʽ��ϴ�. �ٽ� �Է����ּ���.", "Ȯ ��",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, restarttype, "��й�ȣ ���Է�");
				if (select == 1) {
					choice = JOptionPane.showOptionDialog(null, "����ڸ� �����ϼ���!", "�α��οɼ�", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, logintype, "�Ϲ�");
					if (choice == 0) {
						return;
					}
				}
			}
		}
	}
	
}
