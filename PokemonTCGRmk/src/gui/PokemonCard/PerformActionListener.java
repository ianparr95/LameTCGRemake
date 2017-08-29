package gui.PokemonCard;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JDialog;

import cardAbstract.ActivePokemonCard;
import gui.MainGui;

public class PerformActionListener implements MouseListener {

	private ActivePokemonCard apc;
	private JDialog ActionDisplay;
	
	public PerformActionListener(ActivePokemonCard apc) {
		this.apc = apc;
		ActionDisplay = new ActionDisplayDialog(apc);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		MainGui.addCurrentlyOpen(ActionDisplay);
		ActionDisplay.setLocation(e.getLocationOnScreen().x - 70, e.getLocationOnScreen().y - 70);
		ActionDisplay.setVisible(true);
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
