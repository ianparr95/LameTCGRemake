package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;

import cardAbstract.Card;
import gui.Panels.GenericCardListPanel;

public class RetreatPokemonGui extends JDialog {
	private static final int WIDTH = 400;
	private static final int HEIGHT = 150;
	
	public RetreatPokemonGui() {
		super(MainGui.MAIN_GUI, true);
		this.setResizable(false);
		this.setTitle("Choose energy cards");
		this.setLayout(null);
		this.setSize(WIDTH, HEIGHT);
		List<Card> cl = new ArrayList<Card>();
		cl.addAll(MainGui.ARENA.getAttActive().getEnergyCards());
		GenericCardListPanel gcl = new GenericCardListPanel(this, cl);
		gcl.setBounds(0, 0, WIDTH, HEIGHT);
		this.add(gcl);
	}
}
