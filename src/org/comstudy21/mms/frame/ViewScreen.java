package org.comstudy21.mms.frame;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ViewScreen extends JFrame {
	JPanel vs = new JPanel();
	CardScreen cs = new CardScreen();
	public static InfoScreen fs = new InfoScreen();
	public ViewScreen() {
		vs.setLayout(new GridLayout(2,1));
			vs.add(cs.cs);
			vs.add(fs.fs);		
	}
}
