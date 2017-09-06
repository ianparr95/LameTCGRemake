package gui.Retreating;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JOptionPane;

import cardAbstract.Card;
import gui.MainGui;

public class RetreatOnClick implements MouseListener {
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (MainGui.ARENA.canRetreat()) {
			// Pay retreat cost.
			RetreatPokemonGui jd = new RetreatPokemonGui();
			jd.setLocation(e.getLocationOnScreen());
			jd.setVisible(true);
			
			// here: check jd selected cards.
			List<Card> cards = jd.getSelectedCards();
			System.out.println("GETS EXECUTED AFTER JD CLOSES! We choose: " + cards.size());
			// here: choose pokemon to switch to.
			
		} else {
			JOptionPane.showMessageDialog(null, "Can't retreat.");
		}
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