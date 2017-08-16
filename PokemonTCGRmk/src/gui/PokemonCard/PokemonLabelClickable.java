package gui.PokemonCard;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import arena.GameArena;
import cardAbstract.ActivePokemonCard;
import cardAbstract.Card;
import cardAbstract.EnergyCard;
import cardAbstract.PokemonCard;
import gui.ClickableCardLabel;
import gui.JMultilineLabel;
import gui.MainGui;

public class PokemonLabelClickable extends ClickableCardLabel{
	
	/**
	 * Card label.
	 */
	private PokemonCard pc;
	private AdditionalPokemonCardInfo clicked;
	
	public PokemonLabelClickable(JFrame parent, ActivePokemonCard c) {
		super((c == null ? "" : (c.getName() + "\n" + 
		(Integer.parseInt(c.getStage()) == 0 ? "Basic" : "Stage: " + c.getStage()) +
		 "\n" + (c.getMaxHp() - c.getDamage()) + "/" + c.getMaxHp())));
		this.pc = c;
		this.addMouseListener(this);
		if (pc != null) {
			AdditionalPokemonCardInfo clicked = new AdditionalPokemonCardInfo(parent, (ActivePokemonCard) pc);
			this.clicked = clicked;
		}
	}

	public PokemonLabelClickable(JFrame parent, PokemonCard c) {
		super((c == null ? "" : (c.getName() + "\n" + 
		(Integer.parseInt(c.getStage()) == 0 ? "Basic" : "Stage: " + c.getStage()) +
		 "\nHP: " + c.getMaxHp())));
		this.pc = c;
		this.addMouseListener(this);
		if (pc != null) {
			AdditionalPokemonCardInfo clicked = new AdditionalPokemonCardInfo(parent, pc);
			this.clicked = clicked;
		}
	}
	
	public PokemonLabelClickable(JDialog parent, ActivePokemonCard c) {
		super((c == null ? "" : (c.getName() + "\n" + 
		(Integer.parseInt(c.getStage()) == 0 ? "Basic" : "Stage: " + c.getStage()) +
		 "\n" + (c.getMaxHp() - c.getDamage()) + "/" + c.getMaxHp())));
		this.pc = c;
		this.addMouseListener(this);
		if (pc != null) {
			AdditionalPokemonCardInfo clicked = new AdditionalPokemonCardInfo(parent, (ActivePokemonCard) pc);
			this.clicked = clicked;
		}
	}

	public PokemonLabelClickable(JDialog parent, PokemonCard c) {
		super((c == null ? "" : (c.getName() + "\n" + 
		(Integer.parseInt(c.getStage()) == 0 ? "Basic" : "Stage: " + c.getStage()) +
		 "\nHP: " + c.getMaxHp())));
		this.pc = c;
		this.addMouseListener(this);
		if (pc != null) {
			AdditionalPokemonCardInfo clicked = new AdditionalPokemonCardInfo(parent, pc);
			this.clicked = clicked;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// set visible when clicked.
		if (pc != null) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				if (clicked.isVisible()) {
					clicked.setVisible(false);
					clicked.closeAllWindows();
				} else {
					clicked.setLocation(e.getLocationOnScreen().x, e.getLocationOnScreen().y - 100);
					clicked.setVisible(true);
				}
			} else {
				// right click:
				if (MainGui.ARENA.getCurStage() == GameArena.GameStage.ATTACHING_ENERGY) {
					ActivePokemonCard selected = (ActivePokemonCard) clicked.getPokemonCard();
					boolean success = MainGui.ARENA.getPlayerAtt().attachEnergyCard(
							(EnergyCard) MainGui.ARENA.getActionObject(), selected);
					if (success) {
						MainGui.onUpdate();
					}
				}
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