package gui.Clickables;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
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
public class ClickableMove extends JMultilineLabel implements MouseListener {

	private JDialog box;
	private boolean in = false;
	
	public ClickableMove(Component parent, PokemonMove mv) {
		this.setText(mv.getName() + "\nDamage: " + mv.getMoveDamageOpp() +
		((mv.getAdditionalEffects().length != 0 || mv.getCoinFlips() != 0) ? "* " : " ")
		+ "\nEnergy cost: " + mv.getEnergyCost());
		this.setVisible(true);
		this.addMouseListener(this);
		box = new JDialog((Frame) parent, false);
		box.setAlwaysOnTop(true);
		box.setResizable(false);
		box.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		
		// TODO:
		String text = "Name: " + mv.getName() + "\n";
		text += "Damage: " + mv.getMoveDamageOpp() + "\n";
		if (mv.getAdditionalEffects().length > 0) {
			text += "Additional effects: " + "\n";
			for (int i = 0; i < mv.getAdditionalEffects().length; i++) {
				text += mv.getAdditionalEffects()[i] + "\n";
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
	
	public void makeInvisible() {
		box.setVisible(false);
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
