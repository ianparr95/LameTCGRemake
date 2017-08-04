package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import cardAbstract.ActivePokemonCard;
import cardAbstract.Card;
import cardAbstract.PokemonCard;

@SuppressWarnings("serial")
@Deprecated
public class CardGui extends JPanel {
	// TODO: DONT USE THIS CLASS, just for reference.
	
	private Card card;
	
	public CardGui(Card c) {
		this.card = c;
		this.setLayout(null);
		JLabel name = new JLabel(card.getName());
		this.add(name);
		name.setBounds(0, 0, 100, 100);
		name.setSize(name.getPreferredSize());
		JMultilineLabel sname = new JMultilineLabel(card.addInfoName());
		if(card instanceof ActivePokemonCard) {
			ActivePokemonCard pc = (ActivePokemonCard) card;
			sname.setText(card.addInfoName() + " HP:" + (pc.getMaxHp() - pc.getDamage()) + "/" + pc.getMaxHp());
			this.add(sname);
			sname.setBounds(0, 30, 110, 50);
			//sname.setSize(sname.getPreferredSize());
			sname.setBorder(new LineBorder(Color.red));
			this.setBorder(new LineBorder(Color.black));
		}
		else if (card instanceof PokemonCard) {
			PokemonCard pc = (PokemonCard) card;
			sname.setText(card.addInfoName() + " HP:" + pc.getMaxHp() + "/" + pc.getMaxHp());
			this.add(sname);
			sname.setBounds(0, 30, 110, 50);
			//sname.setSize(sname.getPreferredSize());
			sname.setBorder(new LineBorder(Color.red));
			this.setBorder(new LineBorder(Color.black));
		} else {
			sname.setText(card.addInfoName());
			this.add(sname);
			sname.setBounds(0, 30, 110, 300);
			//sname.setSize(sname.getPreferredSize());
			sname.setBorder(new LineBorder(Color.red));
			this.setBorder(new LineBorder(Color.black));
			//return;
		}
		this.setSize(110, 300);
			// If not trainer: print out additional info.
			// including pokepower:
			
			/*
			 * POKEPOWER STUFF HERE!
			 */
			
			// Moves:
			
			// Have a move area.
	}
	/*
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}*/
}
