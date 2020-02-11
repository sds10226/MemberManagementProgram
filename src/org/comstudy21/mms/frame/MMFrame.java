package org.comstudy21.mms.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.JFrame;

import org.comstudy21.mms.jdbc.JdbcUtil;
import org.comstudy21.mms.mdao.MemberDao;

@SuppressWarnings("serial")
public class MMFrame extends JFrame{
	Dimension sc = Toolkit.getDefaultToolkit().getScreenSize();
	Connection conn = JdbcUtil.getConnection(); 
	public static Login lg= new Login();
	public static BarMenu mb = new BarMenu();
	public static ViewScreen vs = new ViewScreen();
	public MMFrame() {
		this("Member Management Program", 150,150,1280,960);
	}
	
	public MMFrame(String title, int x, int y, int w, int h) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(title);
		setLocation((int)((sc.getWidth()-w)/2), (int)((sc.getHeight()-h)/2));
		new MemberDao().createTable();
		setSize(w,h);
		setJMenuBar(mb.mb);
		setLayout(new BorderLayout());
		add(OutputScreen.ls.ls, BorderLayout.CENTER);
		add(vs.vs, BorderLayout.EAST);
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
			dispose();
			System.exit(0);}
		});
	}
	
}
