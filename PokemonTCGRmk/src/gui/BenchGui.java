package gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import arena.Bench;
import cardAbstract.ActivePokemonCard;
import cardAbstract.PokemonCard;
import gui.Clickables.ActivePokemonCardGui;

/**
 * GUI for the benches: made up of CLabels, which are clickable.
 * When you click on them, it unhides a JDialog with
 * the active pokemon card.
 */
public class BenchGui extends JPanel implements GuiUpdate {
	
	// assumes max bench is 7.
	private List<CLabel> cards = new ArrayList<CLabel>();
	private Bench bench;
	
	public BenchGui(Bench bench) {
		this.setVisible(true);
		this.bench = bench;
		this.setLayout(null);
		onUpdate();
	}

	@Override
	public void onUpdate() {
		// updated: need fix the bench and cards.
		for (int i = 0 ; i < bench.getCurrentCapacity(); i++) {
			cards.add(new CLabel(bench.getList().get(i)));
		}
		for (int i = bench.getCurrentCapacity(); i < 7; i++) {
			cards.add(new CLabel(null));
		}
		for (int i = 0 ; i < 7; i++) {
			this.add(cards.get(i));
			cards.get(i).setBounds(i * (MainGui.BENCH_WIDTH / 7), 
						  0, MainGui.BENCH_WIDTH/7, MainGui.BENCH_HEIGHT);
			cards.get(i).setVisible(true);
			cards.get(i).setBorder(BorderFactory.createLineBorder(Color.black));
		}
	}
	
	private class CLabel extends JLabel implements MouseListener{
		
		ActivePokemonCard pc;
		JDialog clicked;
		
		CLabel(ActivePokemonCard c) {
			super(c == null ? "" : c.getName());
			this.pc = c;
			this.addMouseListener(this);
			if (pc != null) {
				JDialog clicked = new JDialog();
				clicked.setResizable(false);
				clicked.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
				ActivePokemonCardGui ng = new ActivePokemonCardGui(pc);
				clicked.add(ng);
				ng.setVisible(true);
				clicked.setPreferredSize(ng.getPreferredSize());
				clicked.pack();
				this.clicked = clicked;
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// create a new card gui when click.
			if (pc != null) {
				// not null, create a new card gui.
				clicked.setLocation(e.getLocationOnScreen().x, e.getLocationOnScreen().y - 40);
				clicked.setVisible(true);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
		
	}
	

}
