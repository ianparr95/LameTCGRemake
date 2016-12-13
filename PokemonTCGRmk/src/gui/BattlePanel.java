package gui;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BattlePanel extends JPanel{

	private JButton card;
	private JButton deck;
	
	public BattlePanel(boolean us){
		super();
		GridLayout lo = new GridLayout(3, 3);
		this.setLayout(lo);
		card = new JButton();
		card.setBounds(175, 10, 125, 170);
		deck = new JButton();
		deck.setLocation(10, 50);
		deck.setText("deck");
		deck.setSize(deck.getPreferredSize());
		// if this is our self:
		if (us) {
			this.add(new JPanel());
			this.add(new JPanel());
			this.add(new JPanel());
			this.add(deck);
			this.add(card);
			this.add(new JLabel("Prizes"));
			this.add(new JLabel("discard pile"));
			this.add(new JLabel("bench"));
			this.add(new JLabel("hand"));
		} else {
			// not us, opponent:
			this.add(new JLabel("hand"));
			this.add(new JLabel("bench"));
			this.add(new JLabel("discard pile"));
			this.add(new JLabel("Prizes"));
			this.add(card);
			this.add(deck);
			this.add(new JPanel());
			this.add(new JPanel());
			this.add(new JPanel());
		}
	}
	/*
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.drawRect(175, 10, 125, 170);
	}*/

}
