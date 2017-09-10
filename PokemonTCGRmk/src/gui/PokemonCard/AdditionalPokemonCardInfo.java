package gui.PokemonCard;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import gui.MainGui;
import pokemonCard.ActivePokemonCard;
import pokemonCard.PokemonCard;

public class AdditionalPokemonCardInfo extends JDialog{
	
	private PokemonCardInfoPanel ng;
	private PokemonCard c;
	
	public AdditionalPokemonCardInfo(JDialog parent, PokemonCard c) {
		super(parent, false);
		setUp(c);
	}
	
	public AdditionalPokemonCardInfo(JFrame parent, PokemonCard c) {
		super(parent, false);
		setUp(c);
	}
	
	public PokemonCard getPokemonCard() {
		return c;
	}
	
	private void setUp(PokemonCard c) {
		this.c = c;
		this.setTitle(c.getName());
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		PokemonCardInfoPanel ng = new PokemonCardInfoPanel(c);
		this.ng = ng;
		this.add(ng);
		ng.setVisible(true);
		this.setPreferredSize(ng.getPreferredSize());
		this.addWindowListener(new WindowListener(){
			@Override
			public void windowOpened(WindowEvent e) {
			}
			@Override
			public void windowClosing(WindowEvent e) {
				ng.closeAllWindows();
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
		this.pack();
	}
	
	public void closeAllWindows() {
		ng.closeAllWindows();
	}
}
