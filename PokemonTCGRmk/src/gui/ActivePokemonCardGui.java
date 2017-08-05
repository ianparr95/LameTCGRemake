package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;

import cardAbstract.ActivePokemonCard;
import gui.Clickables.ActivePokemonCardPanel;

public class ActivePokemonCardGui extends JMultilineLabel implements MouseListener{
	
	/**
	 * Card label.
	 */
	private BenchGui activePokemonCardGui;
	ActivePokemonCard pc;
	JDialog clicked;
	
	public ActivePokemonCardGui(BenchGui benchGui, ActivePokemonCard c) {
		super(c == null ? "" : c.getName() + "\n" + (c.getMaxHp() - c.getDamage()) + "/" + c.getMaxHp());
		activePokemonCardGui = benchGui;
		this.pc = c;
		this.addMouseListener(this);
		if (pc != null) {
			JDialog clicked = new JDialog(MainGui.jf, false);
			clicked.setTitle("Bench Pokemon");
			clicked.setResizable(false);
			clicked.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
			ActivePokemonCardPanel ng = new ActivePokemonCardPanel(pc);
			clicked.add(ng);
			ng.setVisible(true);
			clicked.setPreferredSize(ng.getPreferredSize());
			clicked.addWindowListener(new WindowListener(){
				@Override
				public void windowOpened(WindowEvent e) {
				}
				@Override
				public void windowClosing(WindowEvent e) {
					System.out.println("CLOSED");
				}
				@Override
				public void windowClosed(WindowEvent e) {
				}
				@Override
				public void windowIconified(WindowEvent e) {
				}
				@Override
				public void windowDeiconified(WindowEvent e) {
				}
				@Override
				public void windowActivated(WindowEvent e) {
				}
				@Override
				public void windowDeactivated(WindowEvent e) {
				}
				
			});
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