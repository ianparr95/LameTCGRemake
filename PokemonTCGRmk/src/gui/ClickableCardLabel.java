package gui;

import java.awt.event.MouseListener;

import cardAbstract.Card;

public abstract class ClickableCardLabel extends JMultilineLabel {

	private Card c;
	
	public ClickableCardLabel(String s, Card c) {
		super(s);
		this.c = c;
	}
	
	public Card getCard() {
		return c;
	}
	
}
