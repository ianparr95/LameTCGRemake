package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cardAbstract.Card;

public class CardListGui extends JPanel {
	
	private List<Card> cards;
	
	private CardGui left;
	private CardGui mid;
	private CardGui right;
	
	private JButton lb;
	private JButton rb;
	private JLabel txt;
	
	int curIndex;
	
	// Other fields: Like card filter for selection?
	// private CardFilter
	// private bool view or select?
	// return selected cards?
	
	public CardListGui(List<Card> cards) {
		this.cards = cards;
		
		lb = new JButton("<==");
		rb = new JButton("==>");
		txt = new JLabel("1/" + cards.size());
		int size = cards.size();
		if (size == 0) return;
		if (size >= 1) {
			curIndex = 0;
			left = new CardGui(cards.get(0));
			if (size >= 2) {
				mid = new CardGui(cards.get(1));
				if (size >= 3) {
					right = new CardGui(cards.get(2));
				}
			}
		}
		this.add(lb);
		this.add(Box.createRigidArea(new Dimension(10,0)));
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.add(left);
		this.add(Box.createRigidArea(new Dimension(10,0)));
		if (mid != null) {
			this.add(mid);
			this.add(Box.createRigidArea(new Dimension(10,0)));
			if (right != null) {
				this.add(right);
				this.add(Box.createRigidArea(new Dimension(10,0)));
				this.add(rb);
			}
		}
		this.add(txt);
		this.setSize(500, 300);
		
		lb.addActionListener(new ActionListener(){
			CardListGui cls = CardListGui.this;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (curIndex == 0) return;
				curIndex--;
				cls.remove(9);
				cls.remove(8);
				cls.remove(7);
				cls.remove(6);
				cls.remove(5);
				cls.remove(4);
				cls.remove(3);
				cls.remove(2);
				cls.add(Box.createRigidArea(new Dimension(10,0)));
				if (cards.get(curIndex) != null) {
					//curIndex = 0;
					left = new CardGui(cards.get(curIndex));
					if (cards.get(curIndex + 1) != null) {
						mid = new CardGui(cards.get(curIndex+1));
						if (cards.get(curIndex + 2) != null) {
							right = new CardGui(cards.get(curIndex+2));
						}
					}
				}
				cls.add(left);
				cls.add(Box.createRigidArea(new Dimension(10,0)));
				cls.add(mid);
				cls.add(Box.createRigidArea(new Dimension(10,0)));
				cls.add(right);
				cls.add(Box.createRigidArea(new Dimension(10,0)));
				cls.add(rb);
				txt.setText(curIndex+1 + "/" + cards.size());
				cls.add(txt);
				
				cls.repaint();
				//left = new CardGui(cards.get(5));
			}
		});
		rb.addActionListener(new ActionListener(){
			CardListGui cls = CardListGui.this;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (curIndex >= cards.size() - 3) return;
				curIndex++;
				cls.remove(9);
				cls.remove(8);
				cls.remove(7);
				cls.remove(6);
				cls.remove(5);
				cls.remove(4);
				cls.remove(3);
				cls.remove(2);
				cls.add(Box.createRigidArea(new Dimension(10,0)));
				if (cards.get(curIndex) != null) {
					//curIndex = 0;
					left = new CardGui(cards.get(curIndex));
					if (cards.get(curIndex + 1) != null) {
						mid = new CardGui(cards.get(curIndex+1));
						if (cards.get(curIndex + 2) != null) {
							right = new CardGui(cards.get(curIndex+2));
						}
					}
				}
				cls.add(left);
				cls.add(Box.createRigidArea(new Dimension(10,0)));
				cls.add(mid);
				cls.add(Box.createRigidArea(new Dimension(10,0)));
				cls.add(right);
				cls.add(Box.createRigidArea(new Dimension(10,0)));
				cls.add(rb);
				txt.setText(curIndex+1 + "/" + cards.size());
				cls.add(txt);
				
				cls.repaint();
			}
		});
	}
	
	
	
}
