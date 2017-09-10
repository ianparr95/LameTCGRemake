package gui.PokemonCard;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import arena.GameArena;
import cardAbstract.Card;
import cardAbstract.EnergyCard;
import gui.ClickableCardLabel;
import gui.JMultilineLabel;
import gui.MainGui;
import pokemonCard.ActivePokemonCard;
import pokemonCard.PokemonCard;

public class PokemonLabelClickable extends ClickableCardLabel{
	
	/**
	 * Card label.
	 */
	private PokemonCard pc;
	private AdditionalPokemonCardInfo clicked;
	
	public PokemonLabelClickable(JFrame parent, ActivePokemonCard c) {
		super((c == null ? "" : (c.getName() + "\n" + 
		(Integer.parseInt(c.getStage()) == 0 ? "Basic" : "Stage: " + c.getStage()) +
		 "\n" + (c.getMaxHp() - c.getDamage()) + "/" + c.getMaxHp())), c);
		this.pc = c;
		if (pc != null) {
			AdditionalPokemonCardInfo clicked = new AdditionalPokemonCardInfo(parent, (ActivePokemonCard) pc);
			this.clicked = clicked;
		}
		this.addMouseListener(new ClickablePokemonInfoListener(pc, clicked));
	}

	public PokemonLabelClickable(JFrame parent, PokemonCard c) {
		super((c == null ? "" : (c.getName() + "\n" + 
		(Integer.parseInt(c.getStage()) == 0 ? "Basic" : "Stage: " + c.getStage()) +
		 "\nHP: " + c.getMaxHp())), c);
		this.pc = c;
		if (pc != null) {
			AdditionalPokemonCardInfo clicked = new AdditionalPokemonCardInfo(parent, pc);
			this.clicked = clicked;
		}
		this.addMouseListener(new ClickablePokemonInfoListener(pc, clicked));
	}
	
	public PokemonLabelClickable(JDialog parent, ActivePokemonCard c) {
		super((c == null ? "" : (c.getName() + "\n" + 
		(Integer.parseInt(c.getStage()) == 0 ? "Basic" : "Stage: " + c.getStage()) +
		 "\n" + (c.getMaxHp() - c.getDamage()) + "/" + c.getMaxHp())), c);
		this.pc = c;
		if (pc != null) {
			AdditionalPokemonCardInfo clicked = new AdditionalPokemonCardInfo(parent, (ActivePokemonCard) pc);
			this.clicked = clicked;
		}
		this.addMouseListener(new ClickablePokemonInfoListener(pc, clicked));
	}

	public PokemonLabelClickable(JDialog parent, PokemonCard c) {
		super((c == null ? "" : (c.getName() + "\n" + 
		(Integer.parseInt(c.getStage()) == 0 ? "Basic" : "Stage: " + c.getStage()) +
		 "\nHP: " + c.getMaxHp())), c);
		this.pc = c;
		if (pc != null) {
			AdditionalPokemonCardInfo clicked = new AdditionalPokemonCardInfo(parent, pc);
			this.clicked = clicked;
		}
		this.addMouseListener(new ClickablePokemonInfoListener(pc, clicked));
	}

	public PokemonLabelClickable(JDialog parent, PokemonCard c, MouseListener selectedListener) {
		super((c == null ? "" : (c.getName() + "\n" + 
		(Integer.parseInt(c.getStage()) == 0 ? "Basic" : "Stage: " + c.getStage()) +
		 "\nHP: " + c.getMaxHp())), c);
		this.pc = c;
		this.addMouseListener(selectedListener);
	}

	public PokemonLabelClickable(JFrame parent, PokemonCard c, MouseListener selectedListener) {
		super((c == null ? "" : (c.getName() + "\n" + 
		(Integer.parseInt(c.getStage()) == 0 ? "Basic" : "Stage: " + c.getStage()) +
		 "\nHP: " + c.getMaxHp())), c);
		this.pc = c;
		this.addMouseListener(selectedListener);
	}
}

