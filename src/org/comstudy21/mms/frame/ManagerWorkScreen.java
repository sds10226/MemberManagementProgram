package org.comstudy21.mms.frame;

import javax.swing.JTabbedPane;

public class ManagerWorkScreen {
	JTabbedPane ws = new JTabbedPane();
	InputScreen is = new InputScreen();
	OutputScreen os = new OutputScreen();
	SearchScreen ss = new SearchScreen();
	ModifyScreen mos = new ModifyScreen();
	DeleteScreen ds = new DeleteScreen();

	public ManagerWorkScreen() {
		ws.addTab("�� ��", is.is);
		ws.addTab("�� ��", os.os);
		ws.addTab("�� ��", ss.ss);
		ws.addTab("�� ��", mos.mos);
		ws.addTab("�� ��", ds.ds);
		
	}
}
