package gui.EnergyCard;

import java.awt.event.MouseEvent;

import javax.swing.JDialog;

import cardAbstract.EnergyCard;
import gui.ClickableCardLabel;
import gui.PokemonCard.AdditionalPokemonCardInfo;

public class EnergyLabelClickable extends ClickableCardLabel {
		
	// TODO : FIX
	private EnergyCard c;
	AdditionalEnergyCardInfo clicked;
	
	public EnergyLabelClickable(EnergyCard c) {
		super(c.getName());
		this.c = c;
		this.addMouseListener(this);
		clicked = new AdditionalEnergyCardInfo(c);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// create a new card gui when click.
		if (c != null) {
			// not null, create a new card gui.
			if (clicked.isVisible()) {
				clicked.setVisible(false);
				clicked.closeAllWindows();
			} else {
				clicked.setLocation(e.getLocationOnScreen().x, e.getLocationOnScreen().y - 100);
				clicked.setVisible(true);
			}
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
