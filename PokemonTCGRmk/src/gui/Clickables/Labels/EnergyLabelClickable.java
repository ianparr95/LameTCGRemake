package gui.Clickables.Labels;

import java.awt.event.MouseEvent;

import cardAbstract.EnergyCard;

public class EnergyLabelClickable extends ClickableCardLabel {
	
	public EnergyLabelClickable(EnergyCard c) {
		super(c.getName());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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
