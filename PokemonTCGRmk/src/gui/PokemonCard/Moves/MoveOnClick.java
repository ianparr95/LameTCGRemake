package gui.PokemonCard.Moves;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import gui.MainGui;
import gui.PokemonCard.ActionDisplayDialog;
import pokemonCard.PokemonMove;

public class MoveOnClick implements MouseListener {
	
	/**
	 * 
	 */
	private ActionDisplayDialog actionDisplayDialog;
	public PokemonMove m;
	
	public MoveOnClick(ActionDisplayDialog actionDisplayDialog, PokemonMove m) {
		this.actionDisplayDialog = actionDisplayDialog;
		this.m = m;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (MainGui.ARENA.getAttActive().canPerformMove(m)) {
			try {
				JOptionPane.showMessageDialog(null, "Performed move " + m.getName());
				MainGui.ARENA.doMove(m);
				MainGui.ARENA.nextTurn();
				MainGui.onUpdate();
			} catch (Exception e1) {}
		} else {
			JOptionPane.showMessageDialog(null, "Could not perform move " + m.getName());
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