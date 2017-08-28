package gui.PokemonCard;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;

import cardAbstract.ActivePokemonCard;
import gui.MainGui;

public class PerformActionListener implements MouseListener {

	private ActivePokemonCard apc;
	
	public PerformActionListener(ActivePokemonCard apc) {
		this.apc = apc;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			MainGui.ARENA.doMove(apc.getMoves()[0]);
			MainGui.ARENA.nextTurn();
			MainGui.onUpdate();
		} catch (Exception e1) {
		
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
