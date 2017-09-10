package gui.TrainerCard;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;

import cardAbstract.TrainerCard;
import gui.ClickableCardLabel;
import gui.JMultilineLabel;
import gui.MainGui;
import gui.Panels.BenchPanel;
import gui.PokemonCard.AdditionalPokemonCardInfo;
import pokemonCard.ActivePokemonCard;

public class TrainerLabelClickable extends ClickableCardLabel {
	
	/**
	 * Card label.
	 */
	TrainerCard c;
	
	public TrainerLabelClickable(TrainerCard c) {
		super(c == null ? "" : ("Trainer\n" + c.getName()), c);
		this.c = c;
		//this.addMouseListener(this);
		if (c != null) {
			//AdditionalPokemonCardInfo clicked = new AdditionalPokemonCardInfo(c);
			//this.clicked = clicked;
		}
	}

	public TrainerLabelClickable(TrainerCard c, MouseListener selectedListener) {
		super(c == null ? "" : ("Trainer\n" + c.getName()), c);
		this.c = c;
		//this.addMouseListener(this);
		if (c != null) {
			//AdditionalPokemonCardInfo clicked = new AdditionalPokemonCardInfo(c);
			//this.clicked = clicked;
		}
	}
	
}