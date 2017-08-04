package gui.Clickables;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import cardAbstract.ActivePokemonCard;
import cardAbstract.EnergyCard;
import gui.JMultilineLabel;

/**
 * Active Pokemon Card GUI
 * Shows the Energy/HP/moves/statuses etc.
 */
public class ActivePokemonCardGui extends JPanel {
	
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
		sname.setBounds(0, 30, 200, 50);
		//sname.setSize(sname.getPreferredSize());
		sname.setBorder(new LineBorder(Color.red));
		this.setBorder(new LineBorder(Color.black));
		this.setSize(200, 300);
		
		ClickableMove moveOne = new ClickableMove(card.getMoves()[0]);
		this.add(moveOne);
		moveOne.setBounds(0,200, 200, 50);
	}
}
