package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import cardAbstract.Card;
import cardAbstract.EnergyCard;

public class RetreatListener extends SelectedListener {

	private boolean selected = false;
	private static List<EnergyCard> selectedCards = new ArrayList<EnergyCard>();

	public RetreatListener(Card c) {
		super(c);
		selectedCards.clear();
	}
	
	public static List<EnergyCard> getSelectedEnergies() {
		return selectedCards;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!selected ) {
			selected = true;
			label.setOpaque(true);
			label.setBackground(Color.red);
			label.repaint();
			selectedCards.add((EnergyCard) c);
		} else {
			selected = false;
			label.setOpaque(false);
			label.repaint();
			selectedCards.remove(c);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {	}
	
}
