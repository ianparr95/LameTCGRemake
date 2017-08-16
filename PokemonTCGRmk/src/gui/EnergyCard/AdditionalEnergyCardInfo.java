package gui.EnergyCard;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;

import cardAbstract.EnergyCard;
import cardAbstract.PokemonCard;
import gui.MainGui;
import gui.PokemonCard.PokemonCardInfoPanel;

public class AdditionalEnergyCardInfo extends JDialog {
	private EnergyCardInfoPanel ng;
	
	public AdditionalEnergyCardInfo(EnergyCard c) {
		super(MainGui.MAIN_GUI, true);
		MainGui.addCurrentlyOpen(this);
		this.setTitle(c.getName());
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		EnergyCardInfoPanel ng = new EnergyCardInfoPanel(c);
		this.ng = ng;
		this.add(ng);
		ng.setVisible(true);
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
