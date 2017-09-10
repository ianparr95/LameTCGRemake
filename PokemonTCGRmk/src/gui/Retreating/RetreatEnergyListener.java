package gui.Retreating;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import cardAbstract.Card;
import cardAbstract.EnergyCard;
import gui.Selectable.SelectedListener;

public class RetreatEnergyListener extends SelectedListener {

	private boolean selected = false;
	private static List<Card> selectedCards;
	private static JLabel curLab;

	public RetreatEnergyListener(Card c) {
		super(c);
	}
	
	public static void setSelectedEnergies(List<Card> selectedCards) {
		RetreatEnergyListener.selectedCards = selectedCards;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!selected) {
			// TODO: make it so that when select a card,
			// check if it fulfils retreat. if so, and select a new card
			// make sure that can't select a new one without deselect.
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
		String eStr = "";
		for (Card c : selectedCards) {
			eStr += ((EnergyCard) c).energyType();
		}
		curLab.setText("Currently selected energies: " + eStr);
		curLab.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {	}

	public static void setLabel(JLabel curEnergies) {
		curLab = curEnergies;
	}
	
}
