package gui.Retreating;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import cardAbstract.Card;
import gui.MainGui;
import gui.Panels.GenericCardListPanel;

public class RetreatPokemonGui extends JDialog implements WindowListener {
	private static final int WIDTH = 400;
	private static final int HEIGHT = 200;
	
	private static final int NEED_X = 0;
	private static final int NEED_Y = 150;
	
	private static final int HAVE_X = 0;
	private static final int HAVE_Y = 130;
	
	
	private static final int GCL_HEIGHT = 130;
	
	private static List<Card> selectedCards;
	
	public RetreatPokemonGui() {
		super(MainGui.MAIN_GUI, true);
		this.addWindowListener(this);
		this.setResizable(false);
		this.setTitle("Choose energy cards");
		this.setLayout(null);
		this.setSize(WIDTH, HEIGHT);
		selectedCards = new ArrayList<Card>();
		List<Card> cl = new ArrayList<Card>();
		cl.addAll(MainGui.ARENA.getAttActive().getEnergyCards());
		try {
			GenericCardListPanel gcl = new GenericCardListPanel(this, cl, RetreatEnergyListener.class);
			gcl.setBounds(0, 0, WIDTH, GCL_HEIGHT);
			this.add(gcl);
			gcl.setBorder(new LineBorder(Color.black));
		} catch (Exception e) {
			e.printStackTrace();
		}

		JLabel neededEnergies = new JLabel("Needed energies: " + MainGui.ARENA.getAttActive().getRCost());
		neededEnergies.setBounds(NEED_X, NEED_Y, WIDTH, 20);
		this.add(neededEnergies);
		
		JLabel curEnergies = new JLabel("Currently selected energies: ");
		curEnergies.setBounds(HAVE_X, HAVE_Y, WIDTH, 20);
		this.add(curEnergies);
		
		RetreatEnergyListener.setSelectedEnergies(selectedCards);
		RetreatEnergyListener.setLabel(curEnergies);

	}

	public List<Card> getSelectedCards() {
		return selectedCards;
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {
		// When press close: we didn't select any.
		selectedCards.clear();
	}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}
}
