package gui.Clickables;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import cardAbstract.PokemonMove;
import gui.JMultilineLabel;

/**
 * Displays information about a move.
 * A label that can be clicked.
 */
public class ClickableMove extends JLabel implements MouseListener {

	private JDialog box;
	private boolean in = false;
	
	public ClickableMove(PokemonMove mv) {
		this.setText(mv.getName());
		this.setVisible(true);
		this.addMouseListener(this);
		box = new JDialog();
		box.setResizable(false);
		box.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		
		// TODO:
		String text = "Name: " + mv.getName() + "\n";
		text += "Damage: " + mv.getMoveDamageOpp() + "\n";
		if (mv.getAdditionalEffects().length > 0) {
			text += "Additional effects: " + "\n";
			for (int i = 0; i < mv.getAdditionalEffects().length; i++) {
				text += i + ". " + mv.getAdditionalEffects()[i] + "\n";
			}
		}
		if (mv.getCoinFlips() > 0) {
			text += "Number of coin flips: " + mv.getCoinFlips() + "\n";
			if (mv.getHeadFlip().length > 0) {
				text += "Head flip effects: " + "\n";
			}
		}

		JMultilineLabel info = new JMultilineLabel(text);
		box.add(info);
		box.pack();
		//box.setPreferredSize(info.getPreferredSize());
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (in == false) {
			box.setLocation(e.getLocationOnScreen());
			box.setVisible(true);
			in = true;
		} else {
			in = false;
			box.setVisible(false);
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
