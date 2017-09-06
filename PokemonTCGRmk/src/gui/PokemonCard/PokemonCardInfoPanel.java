package gui.PokemonCard;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import cardAbstract.ActivePokemonCard;
import cardAbstract.EnergyCard;
import cardAbstract.PokemonCard;
import cardAbstract.PokemonMove;
import gui.JMultilineLabel;
import gui.PokemonCard.Moves.ClickableMove;

/**
 * Active Pokemon Card GUI
 * Shows the Energy/HP/moves/statuses etc.
 */
public class PokemonCardInfoPanel extends JPanel {
	
	public static final Dimension NAME_AND_EFFECT_BOUNDS = new Dimension(200, 30);
	public static final Dimension ACTIVE_POKEMON_CARD_GUI_BOUNDS = new Dimension(200,320);
	public static final Rectangle INFO_BOUNDS = new Rectangle(0, 30, 200, 80);
	public static final Rectangle CLICKABLE_TRAINER_BOUNDS = new Rectangle(0, 110, 200, 30);
	public static final Rectangle CLICKABLE_POKEPOWER_BOUNDS = new Rectangle(0, 140, 200, 30);
	public static final int CLICKABLE_MOVE_Y_START = 170;
	public static final int CLICKABLE_MOVE_SPACE = 60;

	private PokemonCard card;
	private List<ClickableMove> moves = new ArrayList<ClickableMove>();
	
	public PokemonCardInfoPanel(PokemonCard card) {
		this.card = card;
		this.setLayout(null);
		// TODO: fix this.
		JMultilineLabel name = new JMultilineLabel(card.getName() + "\nClick to view afflicted statuses.");
		this.add(name);
		name.setLocation(0, 0);
		name.setSize(NAME_AND_EFFECT_BOUNDS);
		name.setBorder(new LineBorder(Color.magenta));
		JMultilineLabel cardInfo = new JMultilineLabel(card.addInfoName());
		if (card instanceof ActivePokemonCard) {
		
		cardInfo.setText(card.addInfoName() + " HP:" + (card.getMaxHp() - ((ActivePokemonCard) card).getDamage()) + "/" + card.getMaxHp()
		+ "\nEnergies: " + ((ActivePokemonCard) card).getEnergyString() + "\nRetreat Cost: " + card.getRCost() + "\nResistance: "
		+ ((card.getResistance() == null ? "none" : card.getResistance().toUpperCase()) + "\nWeakness: "
		+ ((card.getWeakness() == null ? "none" : card.getWeakness().toUpperCase()))));
		
		} else {
		
		cardInfo.setText(card.addInfoName() + " HP:" + (card.getMaxHp())
		+ "\nEnergies: " + "" + "\nRetreat Cost: " + card.getRCost() + "\nResistance: "
		+ ((card.getResistance() == null ? "none" : card.getResistance().toUpperCase()) + "\nWeakness: "
		+ ((card.getWeakness() == null ? "none" : card.getWeakness().toUpperCase()))));
		}

		this.add(cardInfo);
		cardInfo.setBounds(INFO_BOUNDS);
		cardInfo.setBorder(new LineBorder(Color.red));
		this.setBorder(new LineBorder(Color.black));
		this.setSize(ACTIVE_POKEMON_CARD_GUI_BOUNDS);
		
		// make this something else. TODO
		JLabel trainers = new JLabel("Attached Trainers");
		this.add(trainers);
		trainers.setBounds(CLICKABLE_TRAINER_BOUNDS);
		trainers.setBorder(new LineBorder(Color.green));
		
		// make this something else. TODO
		JLabel pokepower = new JLabel("PokePower: " + ((card.getPokePowerName() == null) ? "none" : card.getPokePowerName()));
		this.add(pokepower);
		pokepower.setBounds(CLICKABLE_POKEPOWER_BOUNDS);
		pokepower.setBorder(new LineBorder(Color.black));
		
		for (int i = 0; i < card.getMoves().length; i++) {
			ClickableMove cm = new ClickableMove(this.getParent(), card.getMoves()[i]);
			this.add(cm);
			cm.setBounds(0,CLICKABLE_MOVE_Y_START + i * CLICKABLE_MOVE_SPACE, 200, CLICKABLE_MOVE_SPACE);
			cm.setBorder(new LineBorder(Color.blue));
			moves.add(cm);
		}
	}

	public void closeAllWindows() {
		for (ClickableMove cm : moves) {
			cm.makeInvisible();
		}
	}
}
