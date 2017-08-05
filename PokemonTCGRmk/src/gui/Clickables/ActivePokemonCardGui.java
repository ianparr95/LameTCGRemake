package gui.Clickables;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import cardAbstract.ActivePokemonCard;
import cardAbstract.EnergyCard;
import cardAbstract.PokemonMove;
import gui.JMultilineLabel;

/**
 * Active Pokemon Card GUI
 * Shows the Energy/HP/moves/statuses etc.
 */
public class ActivePokemonCardGui extends JPanel {
	
	public static final Dimension ACTIVE_POKEMON_CARD_GUI_BOUNDS = new Dimension(200,300);
	public static final Rectangle SNAME_BOUNDS = new Rectangle(0, 30, 200, 50);

	public ActivePokemonCardGui(ActivePokemonCard card) {
		this.setLayout(null);
		JLabel name = new JLabel(card.getName());
		this.add(name);
		name.setBounds(0, 0, 100, 100);
		name.setSize(name.getPreferredSize());
		String energies = "";
		for (EnergyCard eg : card.getEnergyCards()) {
			energies += eg + " ";
		}
		JMultilineLabel sname = new JMultilineLabel(card.addInfoName());
		sname.setText(card.addInfoName() + " HP:" + (card.getMaxHp() - card.getDamage()) + "/" + card.getMaxHp()
		+ "\nEnergies: " + energies);

		this.add(sname);
		sname.setBounds(SNAME_BOUNDS);
		sname.setBorder(new LineBorder(Color.red));
		this.setBorder(new LineBorder(Color.black));
		this.setSize(ACTIVE_POKEMON_CARD_GUI_BOUNDS);
		
		for (int i = 0; i < card.getMoves().length; i++) {
			ClickableMove cm = new ClickableMove(card.getMoves()[i]);
			this.add(cm);
			cm.setBounds(0,80 + i * 50, 200, 50);
			cm.setBorder(new LineBorder(Color.blue));
		}
	}
}
