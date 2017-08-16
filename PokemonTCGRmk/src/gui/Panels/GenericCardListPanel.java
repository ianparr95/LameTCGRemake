package gui.Panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import cardAbstract.ActivePokemonCard;
import cardAbstract.Card;
import cardAbstract.EnergyCard;
import cardAbstract.ParsePokemonCardsFile;
import cardAbstract.PokemonCard;
import cardAbstract.TrainerCard;
import gui.ClickableCardLabel;
import gui.EnergyCard.EnergyLabelClickable;
import gui.PokemonCard.PokemonLabelClickable;
import gui.TrainerCard.TrainerLabelClickable;

public class GenericCardListPanel extends JPanel {
	
	private List<Card> clist = new ArrayList<Card>();
	private List<ClickableCardLabel> labelList = new ArrayList<ClickableCardLabel>();
	private List<JPanel> cPanel;
	private JLabel count;
	private int curNum = 1;
	
	private static final int BUTTON_Y = 40;
	private static final int BUTTON_X_LEFT = 10;
	private static final int BUTTON_X_RIGHT = 330;
	private static final int BUTTON_HEIGHT = 20;
	private static final int BUTTON_WIDTH = 40;
	
	private static final int LEFT_PANEL_X = 63;
	private static final int PANEL_Y = 30;
	private static final int PANEL_WIDTH = 65;
	private static final int PANEL_HEIGHT = 60;
	
	private static final int LABEL_X = 300;
	private static final int LABEL_Y = 95;
	
	public GenericCardListPanel(Component parent, List<Card> cards) {
		
		this.setLayout(null);

		for (Card c : cards) {
			this.clist.add(c);
		}
		for (int i = 0; i < clist.size(); i++) {
			ClickableCardLabel cc = null;
			Card c = clist.get(i);
			if (c instanceof TrainerCard) {
				cc = new TrainerLabelClickable((TrainerCard) c);
				labelList.add(cc);
			} else if (c instanceof PokemonCard) {
				if (parent instanceof JDialog) {
					cc = new PokemonLabelClickable((JDialog) parent, (PokemonCard) c);
					labelList.add(cc);
				} else {
					cc = new PokemonLabelClickable((JFrame) parent, (PokemonCard) c);
					labelList.add(cc);
				}
			} else if (c instanceof EnergyCard) {
				cc = new EnergyLabelClickable((EnergyCard) c);
				labelList.add(cc);
			} else {
				System.err.println("ERROR: Clickable Card is not a known type.");
			}
		}
		
		JLabel left = new JLabel("<---");
		left.setOpaque(true);
		left.setBackground(Color.yellow);
		left.setBounds(BUTTON_X_LEFT, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		left.setBorder(new LineBorder(Color.RED));
		this.add(left);
		
		JLabel right = new JLabel("--->");
		right.setHorizontalAlignment(SwingConstants.RIGHT);
		right.setOpaque(true);
		right.setBackground(Color.yellow);
		right.setBounds(BUTTON_X_RIGHT, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		right.setBorder(new LineBorder(Color.RED));
		
		this.add(right);
		count = new JLabel("1/" + cards.size());
		count.setBounds(LABEL_X, LABEL_Y, 
		(int)count.getPreferredSize().getWidth(), (int)count.getPreferredSize().getHeight());
		this.add(count);
		
		// have 5 panels?
		cPanel = new ArrayList<JPanel>();
		for (int i = 0; i < 4; i++) {
			JPanel pn = new JPanel();
			pn.setBounds(LEFT_PANEL_X + i * PANEL_WIDTH, PANEL_Y, PANEL_WIDTH - 2, PANEL_HEIGHT);
			pn.setLayout(null);
			pn.setBorder(new LineBorder(Color.black));
			this.add(pn);
			cPanel.add(pn);
		}
		
		right.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Collections.rotate(clist, -1);
				Collections.rotate(labelList, -1);
				int s = clist.size();
				if (s >= 4) {
					curNum = ((curNum + 1) % s + s) % s;
					GenericCardListPanel.this.remove(count);
					count = new JLabel(curNum + "/" + cards.size());
					GenericCardListPanel.this.add(count);
					count.setBounds(LABEL_X, LABEL_Y, 
							(int)count.getPreferredSize().getWidth(), (int)count.getPreferredSize().getHeight());
					GenericCardListPanel.this.repaint();
				}
				onUpdate();
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}	
		});
		left.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Collections.rotate(clist, 1);
				Collections.rotate(labelList, 1);
				int s = clist.size();
				if (s >= 4) {
					curNum = ((curNum - 1) % s + s) % s;
					GenericCardListPanel.this.remove(count);
					count = new JLabel(curNum + "/" + cards.size());
					GenericCardListPanel.this.add(count);
					count.setBounds(LABEL_X, LABEL_Y, 
							(int)count.getPreferredSize().getWidth(), (int)count.getPreferredSize().getHeight());
					GenericCardListPanel.this.repaint();
				}
				onUpdate();
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}	
		});
		
		onUpdate();
	}

	private void onUpdate() {
		int indx = 4;
		if (clist.size() < 4) {
			indx = clist.size();
		}
		for (int i = 0; i < indx; i++) {
			JPanel curPan = cPanel.get(i);
			curPan.removeAll();
			Card c = clist.get(i);
			ClickableCardLabel cc = labelList.get(i);
			if (cc != null) {
				cc.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
				curPan.add(cc);
				curPan.repaint();
			}
		}

	}

}
