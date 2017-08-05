package gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import arena.Bench;
import cardAbstract.PokemonCard;

/**
 * GUI for the benches: made up of CLabels, which are clickable.
 * When you click on them, it unhides a JDialog with
 * the active pokemon card.
 */
public class BenchGui extends JPanel implements GuiUpdate {
	
	// assumes max bench is 7.
	private List<ActivePokemonCardGui> cards = new ArrayList<ActivePokemonCardGui>();
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
			cards.add(new ActivePokemonCardGui(this, bench.getList().get(i)));
		}
		for (int i = bench.getCurrentCapacity(); i < 7; i++) {
			cards.add(new ActivePokemonCardGui(this, null));
		}
		for (int i = 0 ; i < 7; i++) {
			this.add(cards.get(i));
			cards.get(i).setBounds(i * (MainGui.BENCH_WIDTH / 7), 
						  0, MainGui.BENCH_WIDTH/7, MainGui.BENCH_HEIGHT);
			cards.get(i).setVisible(true);
			cards.get(i).setBorder(BorderFactory.createLineBorder(Color.black));
		}
	}
	

}
