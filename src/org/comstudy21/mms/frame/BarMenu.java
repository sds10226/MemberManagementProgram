package org.comstudy21.mms.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class BarMenu {
	JMenuBar mb = new JMenuBar();
	JMenu mng = new JMenu("관 리");

	JMenuItem[] mnglist = new JMenuItem[3];
	String[] mngstr = { "관리자 로그인", "관리자 로그아웃", "프로그램 종료" };

	public BarMenu() {
		MenuActionListener listener = new MenuActionListener();

		for (int i = 0; i < mngstr.length; i++) {
			if (i == 2) {
				mng.addSeparator();
			}
			mnglist[i] = new JMenuItem(mngstr[i]);
			mng.add(mnglist[i]);
			mnglist[i].addActionListener(listener);
		}
		if (MMFrame.lg.choice == 0) {
			mnglist[1].setEnabled(false);
		} else if (MMFrame.lg.choice == 1) {
			mnglist[0].setEnabled(false);
		}
		mb.add(mng);
	}
}

class MenuActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		if ("프로그램 종료" == e.getActionCommand()) {
			System.exit(0);
		} else if ("관리자 로그인" == e.getActionCommand()) {
			MMFrame.lg.reLogin();
			if (MMFrame.lg.choice == 1) {
				MMFrame.vs.cs.changePane();
				MMFrame.mb.mnglist[0].setEnabled(false);
				MMFrame.mb.mnglist[1].setEnabled(true);
			}
		} else if ("관리자 로그아웃" == e.getActionCommand()) {
			MMFrame.lg.choice = 0;
			JOptionPane.showMessageDialog(null, "관리자 로그아웃 되었습니다.");
			MMFrame.vs.cs.changePane();
			MMFrame.mb.mnglist[0].setEnabled(true);
			MMFrame.mb.mnglist[1].setEnabled(false);
		}
	}
}