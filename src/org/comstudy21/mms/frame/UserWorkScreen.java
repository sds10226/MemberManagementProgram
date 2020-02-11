package org.comstudy21.mms.frame;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class UserWorkScreen extends JFrame {
	JTabbedPane ws = new JTabbedPane();
	InputScreen is = new InputScreen();
	OutputScreen os = new OutputScreen();
	SearchScreen ss = new SearchScreen();
	ModifyScreen mos = new ModifyScreen();
	DeleteScreen ds = new DeleteScreen();

	public UserWorkScreen() {
		ws.addTab("�� ��", os.os);
		ws.addTab("�� ��", ss.ss);
	}
}
