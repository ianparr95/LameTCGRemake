package gui;

import java.awt.event.MouseListener;

import cardAbstract.Card;

public abstract class ClickableCardLabel extends JMultilineLabel {

	private Card c;
	private MouseListener ml;
	
	public ClickableCardLabel(String s, Card c, MouseListener ml) {
		super(s);
		this.c = c;
		this.addMouseListener(ml);
	}
	
	public ClickableCardLabel(String s, Card c) {
		super(s);
		this.c = c;
	}
	
	public Card getCard() {
		return c;
	}
	
	public MouseListener getMouseListener() {
		return ml;
	}
	
}
