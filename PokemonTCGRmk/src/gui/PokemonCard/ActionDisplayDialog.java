package gui.PokemonCard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import cardAbstract.ActivePokemonCard;
import cardAbstract.Card;
import cardAbstract.PokemonMove;
import gui.MainGui;
import gui.Panels.GenericCardListPanel;
import gui.Retreating.RetreatPokemonGui;

public class ActionDisplayDialog extends JDialog {

	public static final int ACTION_DIALOG_WIDTH = 300;
	public static final int ACTION_DIALOG_HEIGHT = 130;
	
	private ActivePokemonCard apc;
	
	public ActionDisplayDialog(ActivePokemonCard apc) {
		super(MainGui.MAIN_GUI, "Perform action", true);
		this.apc = apc;
		this.setSize(ACTION_DIALOG_WIDTH, ACTION_DIALOG_HEIGHT);
		this.setResizable(false);
		this.setVisible(false);
		this.setLayout(new BorderLayout());
		this.add(new JLabel("Perform move..."), BorderLayout.PAGE_START);
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
		
		for (int i = 0; i < apc.getMoveNames().length; i++) {
			JButton mov = new JButton(apc.getMoveNames()[i]);
			leftPanel.add(mov);
			mov.addMouseListener(new MoveOnClick(apc.getMoves()[i]));
		}

		leftPanel.setBorder(new LineBorder(Color.blue));
		this.add(leftPanel, BorderLayout.LINE_START);
		
		JButton retreat = new JButton("Retreat");
		this.add(retreat, BorderLayout.LINE_END);
		retreat.addMouseListener(new RetreatOnClick());
		
		this.add(new JButton("Use PokePower"), BorderLayout.CENTER);

	}
	
	private class RetreatOnClick implements MouseListener {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if (MainGui.ARENA.canRetreat()) {
				// Pay retreat cost.
				RetreatPokemonGui jd = new RetreatPokemonGui();
				jd.setLocation(e.getLocationOnScreen());
				jd.setVisible(true);
				
				// here: check jd selected cards.
				List<Card> cards = jd.getSelectedCards();
				System.out.println("GETS EXECUTED AFTER JD CLOSES! We choose: " + cards.size());
				// here: choose pokemon to switch to.
				
			} else {
				JOptionPane.showMessageDialog(null, "Can't retreat.");
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
		
	}
	
	private class MoveOnClick implements MouseListener {
		
		PokemonMove m;
		
		public MoveOnClick(PokemonMove m) {
			this.m = m;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if (apc.canPerformMove(m)) {
				try {
					JOptionPane.showMessageDialog(null, "Performed move " + m.getName());
					MainGui.ARENA.doMove(m);
					MainGui.ARENA.nextTurn();
					MainGui.onUpdate();
				} catch (Exception e1) {}
			} else {
				JOptionPane.showMessageDialog(null, "Could not perform move " + m.getName());
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
		
	}

}
