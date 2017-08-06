package gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;

import cardAbstract.ActivePokemonCard;
import gui.Clickables.PokemonCardInfoPanel;

public class AdditionalPokemonCardInfo extends JDialog{
	
	private PokemonCardInfoPanel ng;
	
	public AdditionalPokemonCardInfo(ActivePokemonCard c) {
		super(MainGui.MAIN_GUI, false);
		this.setTitle(c.getName());
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
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
