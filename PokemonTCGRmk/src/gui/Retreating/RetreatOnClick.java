package gui.Retreating;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import cardAbstract.Card;
import cardAbstract.EnergyCard;
import cardAbstract.NullCard;
import gui.MainGui;
import gui.SelectableGui;
import pokemonCard.ActivePokemonCard;

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
			if (NullCard.isNullList(cards)) {
				System.out.println("Did not press done to retreat!");
				return;
			}
			
			if (MainGui.ARENA.canRetreat(cards)) {
				// Good: we now let user choose pokemon to retreat to.
				List<Card> cList = new ArrayList<Card>();
				for (ActivePokemonCard c : MainGui.ARENA.getAttBench().getList()) {
					cList.add(c);
				}
				SelectableGui pd = new SelectableGui(false, false, 0, 1, 1, cList, true, "Choose pokemon to switch with.");
				pd.setLocation(e.getLocationOnScreen());
				pd.setVisible(true);
				
				// TODO: STUFF
				
				
			} else {
				String gotCards = "";
				for (Card c : cards) {
					gotCards += ((EnergyCard) c).energyType();
				}
				JOptionPane.showMessageDialog(null, "Did not select enough cards:\n"
						+ "Require: " + MainGui.ARENA.getAttActive().getRCost() +"\n"
						+ "Got: " + gotCards);
			}
			
			
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