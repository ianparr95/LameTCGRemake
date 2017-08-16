package gui.EnergyCard;

import java.awt.event.MouseEvent;

import javax.swing.JDialog;

import cardAbstract.EnergyCard;
import gui.ClickableCardLabel;
import gui.MainGui;
import gui.PokemonCard.AdditionalPokemonCardInfo;

public class EnergyLabelClickable extends ClickableCardLabel {
		
	// TODO : FIX
	private EnergyCard c;
	private AdditionalEnergyCardInfo clickableEnergyLabel;
	
	public EnergyLabelClickable(EnergyCard c) {
		super(c.getName());
		this.c = c;
		this.addMouseListener(this);
		clickableEnergyLabel = new AdditionalEnergyCardInfo(c);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (c != null) {
			if (clickableEnergyLabel.isVisible()) {
				clickableEnergyLabel.setVisible(false);
				clickableEnergyLabel.closeAllWindows();
				MainGui.removeCurrentlyOpen(clickableEnergyLabel);
			} else {
				clickableEnergyLabel.setLocation(e.getLocationOnScreen().x, e.getLocationOnScreen().y - 100);
				clickableEnergyLabel.setVisible(true);
				MainGui.addCurrentlyOpen(clickableEnergyLabel);
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
