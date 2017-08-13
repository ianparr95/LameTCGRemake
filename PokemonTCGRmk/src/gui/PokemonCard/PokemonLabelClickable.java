package gui.PokemonCard;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;

import cardAbstract.ActivePokemonCard;
import cardAbstract.PokemonCard;
import gui.ClickableCardLabel;
import gui.JMultilineLabel;
import gui.MainGui;

public class PokemonLabelClickable extends ClickableCardLabel{
	
	/**
	 * Card label.
	 */
	PokemonCard pc;
	AdditionalPokemonCardInfo clicked;
	
	public PokemonLabelClickable(ActivePokemonCard c) {
		super((c == null ? "" : (c.getName() + "\n" + 
		(Integer.parseInt(c.getStage()) == 0 ? "Basic" : "Stage: " + c.getStage()) +
		 "\n" + (c.getMaxHp() - c.getDamage()) + "/" + c.getMaxHp())));
		this.pc = c;
		this.addMouseListener(this);
		if (pc != null) {
			AdditionalPokemonCardInfo clicked = new AdditionalPokemonCardInfo((ActivePokemonCard) pc);
			this.clicked = clicked;
		}
	}

	public PokemonLabelClickable(PokemonCard c) {
		super((c == null ? "" : (c.getName() + "\n" + 
		(Integer.parseInt(c.getStage()) == 0 ? "Basic" : "Stage: " + c.getStage()) +
		 "\nHP: " + c.getMaxHp())));
		this.pc = c;
		this.addMouseListener(this);
		if (pc != null) {
			AdditionalPokemonCardInfo clicked = new AdditionalPokemonCardInfo(pc);
			this.clicked = clicked;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// create a new card gui when click.
		if (pc != null) {
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