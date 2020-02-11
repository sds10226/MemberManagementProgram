package org.comstudy21.mms.frame;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class CardScreen {
	JPanel cs = new JPanel();
	CardLayout cards = new CardLayout();
	UserWorkScreen uws = new UserWorkScreen();
	ManagerWorkScreen mws = new ManagerWorkScreen();
	public CardScreen() {
		cs.setLayout(cards);
		cs.add("user", uws.ws);
		cs.add("manager", mws.ws);
		if(MMFrame.lg.choice == 0){
			cards.show(cs, "user");
		}else if(MMFrame.lg.choice == 1){
			cards.show(cs,"manager");
		}
	}
	
	public void changePane(){
		if(MMFrame.lg.choice == 0){
			cards.show(cs, "user");
		}else if(MMFrame.lg.choice == 1){
			cards.show(cs,"manager");
		}
	}
}
