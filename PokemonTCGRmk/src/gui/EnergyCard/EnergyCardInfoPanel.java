package gui.EnergyCard;

import javax.swing.JButton;
import javax.swing.JPanel;

import cardAbstract.EnergyCard;
import gui.JMultilineLabel;
import gui.PokemonCard.Moves.ClickableMove;

public class EnergyCardInfoPanel extends JPanel {
	
	private EnergyCard c;
	
	public EnergyCardInfoPanel(EnergyCard c) {
		this.c = c;
		this.add(new JMultilineLabel(c.getName() + "\n" + c.addInfoName()));
		JButton attach = new JButton("Attach");
		attach.addMouseListener(new AttachEnergyCard(c));
		this.add(attach);
	}
	
	public void closeAllWindows() {
	}
	
}
