package gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.LineBorder;

import cardAbstract.Card;
import cardAbstract.NullCard;
import gui.Panels.GenericCardListPanel;
import gui.Retreating.RetreatEnergyListener;
import gui.Retreating.RetreatPokemonGui;

public class SelectableGui extends JDialog implements WindowListener {
	
	private static final int WIDTH = 400;
	private static final int HEIGHT = 200;
	
	private static final int NEED_X = 0;
	private static final int NEED_Y = 150;
	
	private static final int HAVE_X = 0;
	private static final int HAVE_Y = 130;
	
	private static final int DONE_X = WIDTH - 90;
	private static final int DONE_Y = 140;
	
	private static final int GCL_HEIGHT = 130;
	
	private boolean done;
	
	private List<Card> selectedCards;
	
	/**
	 * Create a new Selectable Gui
	 * @param benchAndActive: create a gui where you can select pokemon
	 * from the bench or active.
	 * @param ourBench: true if our bench/active. else enemy.
	 * @param mode, 0 = must select exact equal to min. 1 = can be from max to min.
	 * 2 = must be atleast max or more.
	 * @param min
	 * @param max
	 * @param cards, The list of cards. benchAndActive will override this.
	 * @param closeable: if true, closing will return null card. Set to false to disable closing
	 * or it will act like clicking done? DECISIONS.
	 * @param title: the title of the gui
	 */
	public SelectableGui(boolean benchAndActive, boolean ourBench, int mode, int min, int max, List<Card> cards,
			boolean closeable, String title) {
		super(MainGui.MAIN_GUI, true);
		if (benchAndActive) {
			createBenchAndActive(ourBench, mode, min, max);
			return;
		}
		// Create the rest of the stuff here:
		this.addWindowListener(this);
		this.setResizable(false);
		this.setTitle(title);
		this.setLayout(null);
		this.setSize(WIDTH, HEIGHT);
		selectedCards = new ArrayList<Card>();
		try {
			GenericCardListPanel gcl = new GenericCardListPanel(this, cards);
			gcl.setBounds(0, 0, WIDTH, GCL_HEIGHT);
			this.add(gcl);
			gcl.setBorder(new LineBorder(Color.black));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		JButton doneButton = new JButton("Done");
		doneButton.setBounds(DONE_X, DONE_Y, 70, 25);
		this.add(doneButton);
		doneButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				done = true;
				SelectableGui.this.dispose();
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
		
	}
	
	public List<Card> getSelectedCards() {
		return null;
	}

	private void createBenchAndActive(boolean ourBench, int mode, int min, int max) {
		System.out.println("NOT YET IMPLEMENTED!");
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {
		// When press close: we didn't select any.
		if (!done) {
			selectedCards.clear();
			selectedCards.add(NullCard.getInstance());
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {	}

	@Override
	public void windowIconified(WindowEvent e) {	}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}
	
	
}
