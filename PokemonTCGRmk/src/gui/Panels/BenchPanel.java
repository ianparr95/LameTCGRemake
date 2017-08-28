package gui.Panels;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import arena.Bench;
import cardAbstract.PokemonCard;
import gui.GuiUpdate;
import gui.MainGui;
import gui.PokemonCard.PokemonLabelClickable;

/**
 * GUI for the benches: made up of CLabels, which are clickable.
 * When you click on them, it unhides a JDialog with
 * the active pokemon card.
 */
public class BenchPanel extends JPanel {
	
	// assumes max bench is 6.
	private List<PokemonLabelClickable> cards = new ArrayList<PokemonLabelClickable>();
	private Bench bench;
	
	public BenchPanel(JFrame parent, Bench bench) {
		this.setVisible(true);
		this.bench = bench;
		this.setLayout(null);
		onUpdate(parent);
	}
	
	public BenchPanel(JDialog parent, Bench bench) {
		this.setVisible(true);
		this.bench = bench;
		this.setLayout(null);
		onUpdate(parent);
	}
	
	public void setBench(Bench bench) {
		this.bench = bench;
	}

	public void onUpdate(Component parent) {
		this.cards.clear();
		this.removeAll();
		// updated: need fix the bench and cards.
		for (int i = 0 ; i < bench.getCurrentCapacity(); i++) {
			if (parent instanceof JDialog) {
				cards.add(new PokemonLabelClickable((JDialog) parent, bench.getList().get(i)));
			} else {
				cards.add(new PokemonLabelClickable((JFrame) parent, bench.getList().get(i)));
			}
		}
		for (int i = bench.getCurrentCapacity(); i < 6; i++) {
			if (parent instanceof JDialog) {
				cards.add(new PokemonLabelClickable((JDialog) parent, null));
			} else {
				cards.add(new PokemonLabelClickable((JFrame) parent, null));
			}
		}
		for (int i = 0 ; i < 6; i++) {
			this.add(cards.get(i));
			cards.get(i).setBounds(i * (MainGui.BENCH_WIDTH / 6), 
						  0, MainGui.BENCH_WIDTH/6, MainGui.BENCH_HEIGHT);
			cards.get(i).setVisible(true);
			cards.get(i).setBorder(BorderFactory.createLineBorder(Color.red));
			cards.get(i).setOpaque(true);
			cards.get(i).setBackground(Color.pink);
			cards.get(i).setForeground(Color.blue);
		}
		
	}
	

}
