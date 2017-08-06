package gui.Clickables;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;

import cardAbstract.ActivePokemonCard;
import gui.AdditionalPokemonCardInfo;
import gui.BenchGui;
import gui.JMultilineLabel;
import gui.MainGui;

public class CardBenchLabelClickable extends JMultilineLabel implements MouseListener{
	
	/**
	 * Card label.
	 */
	private BenchGui activePokemonCardGui;
	ActivePokemonCard pc;
	AdditionalPokemonCardInfo clicked;
	
	public CardBenchLabelClickable(BenchGui benchGui, ActivePokemonCard c) {
		super(c == null ? "" : c.getName() + "\n" + (c.getMaxHp() - c.getDamage()) + "/" + c.getMaxHp());
		activePokemonCardGui = benchGui;
		this.pc = c;
		this.addMouseListener(this);
		if (pc != null) {
			AdditionalPokemonCardInfo clicked = new AdditionalPokemonCardInfo(pc);
			this.clicked = clicked;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// create a new card gui when click.
		if (pc != null) {
			// not null, create a new card gui.
			if (clicked.isVisible()) {
				clicked.setVisible(false);
				clicked.closeAllWindows();
			} else {
				clicked.setLocation(e.getLocationOnScreen());
				clicked.setVisible(true);
			}
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