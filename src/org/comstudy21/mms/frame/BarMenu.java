package org.comstudy21.mms.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class BarMenu {
	JMenuBar mb = new JMenuBar();
	JMenu mng = new JMenu("�� ��");

	JMenuItem[] mnglist = new JMenuItem[3];
	String[] mngstr = { "������ �α���", "������ �α׾ƿ�", "���α׷� ����" };

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
		if ("���α׷� ����" == e.getActionCommand()) {
			System.exit(0);
		} else if ("������ �α���" == e.getActionCommand()) {
			MMFrame.lg.reLogin();
			if (MMFrame.lg.choice == 1) {
				MMFrame.vs.cs.changePane();
				MMFrame.mb.mnglist[0].setEnabled(false);
				MMFrame.mb.mnglist[1].setEnabled(true);
			}
		} else if ("������ �α׾ƿ�" == e.getActionCommand()) {
			MMFrame.lg.choice = 0;
			JOptionPane.showMessageDialog(null, "������ �α׾ƿ� �Ǿ����ϴ�.");
			MMFrame.vs.cs.changePane();
			MMFrame.mb.mnglist[0].setEnabled(true);
			MMFrame.mb.mnglist[1].setEnabled(false);
		}
	}
}