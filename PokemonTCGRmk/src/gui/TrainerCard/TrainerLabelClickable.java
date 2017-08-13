package gui.TrainerCard;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;

import cardAbstract.ActivePokemonCard;
import cardAbstract.TrainerCard;
import gui.ClickableCardLabel;
import gui.JMultilineLabel;
import gui.MainGui;
import gui.Panels.BenchPanel;
import gui.PokemonCard.AdditionalPokemonCardInfo;

public class TrainerLabelClickable extends ClickableCardLabel{
	
	/**
	 * Card label.
	 */
	TrainerCard c;
	
	public TrainerLabelClickable(TrainerCard c) {
		super(c == null ? "" : ("Trainer\n" + c.getName()));
		this.c = c;
		this.addMouseListener(this);
		if (c != null) {
			//AdditionalPokemonCardInfo clicked = new AdditionalPokemonCardInfo(c);
			//this.clicked = clicked;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	/*	// create a new card gui when click.
		if (pc != null) {
			// not null, create a new card gui.
			if (clicked.isVisible()) {
				clicked.setVisible(false);
				clicked.closeAllWindows();
			} else {
				clicked.setLocation(e.getLocationOnScreen().x, e.getLocationOnScreen().y - 100);
				clicked.setVisible(true);
			}
		}*/
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