package gui;

import java.awt.Color;
import java.awt.Component;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import arena.Hand;
import cardAbstract.Card;
import cardAbstract.ParsePokemonCardsFile;
import cardAbstract.PokemonCard;
import gui.Panels.GenericCardListPanel;

/**
 * Displays so that you can scroll through
 * the cards in your hand, then click on them
 * for more info etc.
 */
public class HandGui extends JDialog {

	private static final int WIDTH = 400;
	private static final int HEIGHT = 150;
	
	public HandGui(Hand attHand) {
		super(MainGui.MAIN_GUI, false);
		this.setResizable(false);
		this.setTitle("Hand");
		this.setLayout(null);
		this.setSize(WIDTH, HEIGHT);
		GenericCardListPanel gcl = new GenericCardListPanel(this, attHand.getList());
		gcl.setBounds(0, 0, WIDTH, HEIGHT);
		this.add(gcl);
	}
	
}
