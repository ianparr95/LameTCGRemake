package gui.Panels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
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
public class BenchPanel extends JPanel implements GuiUpdate {
	
	// assumes max bench is 6.
	private List<PokemonLabelClickable> cards = new ArrayList<PokemonLabelClickable>();
	private Bench bench;
	
	public BenchPanel(Bench bench) {
		this.setVisible(true);
		this.bench = bench;
		this.setLayout(null);
		onUpdate();
	}

	@Override
	public void onUpdate() {
		// updated: need fix the bench and cards.
		for (int i = 0 ; i < bench.getCurrentCapacity(); i++) {
			cards.add(new PokemonLabelClickable(bench.getList().get(i)));
		}
		for (int i = bench.getCurrentCapacity(); i < 6; i++) {
			cards.add(new PokemonLabelClickable(null));
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
