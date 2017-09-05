package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import cardAbstract.Card;

public class SelectedListener implements MouseListener {

	private ClickableCardLabel label;
	private Card c;
	
	private static List<Card> selectedCards;
	
	public SelectedListener(Card c, ClickableCardLabel label) {
		this.c = c;
		this.label = label;
	}
	
	public static List<Card> getCardList() {
		return selectedCards;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Card id is: " + c.getId());
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
