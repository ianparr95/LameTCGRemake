package gui.PokemonCard;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import arena.GameArena;
import cardAbstract.ActivePokemonCard;
import cardAbstract.EnergyCard;
import cardAbstract.PokemonCard;
import gui.MainGui;

public class ClickablePokemonInfoListener implements MouseListener {

	private PokemonCard pc;
	private AdditionalPokemonCardInfo clicked;
	
	public ClickablePokemonInfoListener(PokemonCard pc, AdditionalPokemonCardInfo clicked) {
		this.pc = pc;
		this.clicked = clicked;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// set visible when clicked.
		if (pc != null) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				if (clicked.isVisible()) {
					clicked.setVisible(false);
					clicked.closeAllWindows();
					MainGui.removeCurrentlyOpen(clicked);
				} else {
					MainGui.addCurrentlyOpen(clicked);
					clicked.setLocation(e.getLocationOnScreen().x, e.getLocationOnScreen().y - 100);
					clicked.setVisible(true);
				}
			} else {
				// right click:
				if (MainGui.ARENA.getCurStage() == GameArena.GameStage.ATTACHING_ENERGY) {
					ActivePokemonCard selected = (ActivePokemonCard) clicked.getPokemonCard();
					boolean success = MainGui.ARENA.getPlayerAtt().attachEnergyCard(
							(EnergyCard) MainGui.ARENA.getActionObject(), selected);
					if (success) {
						JDialog s = new JDialog(MainGui.MAIN_GUI, true);
						s.add(new JLabel("Sucessfully attached energy card"));
						s.setSize(210, 60);
						s.setLocation(e.getLocationOnScreen());
						s.setVisible(true);
						MainGui.onUpdate();
					} else {	
						// notify fail:
						JDialog s = new JDialog(MainGui.MAIN_GUI, true);
						s.add(new JLabel("Failed to attach energy card"));
						s.setSize(210, 60);
						s.setLocation(e.getLocationOnScreen());
						s.setVisible(true);
					}
				}
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
