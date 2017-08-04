package gui.Clickables;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import cardAbstract.PokemonMove;

/**
 * Displays information about a move.
 * A label that can be clicked.
 */
public class ClickableMove extends JLabel implements MouseListener {

	private PokemonMove mv;
	
	public ClickableMove(PokemonMove mv) {
		this.mv = mv;
		this.setText(mv.getName());
		this.setVisible(true);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
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
