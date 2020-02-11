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
		ws.addTab("입 력", is.is);
		ws.addTab("출 력", os.os);
		ws.addTab("검 색", ss.ss);
		ws.addTab("수 정", mos.mos);
		ws.addTab("삭 제", ds.ds);
		
	}
}
