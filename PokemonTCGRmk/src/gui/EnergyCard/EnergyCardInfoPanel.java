package gui.EnergyCard;

import javax.swing.JPanel;

import cardAbstract.EnergyCard;
import gui.JMultilineLabel;
import gui.PokemonCard.ClickableMove;

public class EnergyCardInfoPanel extends JPanel {
	
	private EnergyCard c;
	
	public EnergyCardInfoPanel(EnergyCard c) {
		this.c = c;
		this.setLayout(null);
		this.add(new JMultilineLabel("TEST"));
		
	}
	
	public void closeAllWindows() {
	}
	
}
