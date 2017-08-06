package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import cardAbstract.ActivePokemonCard;
import cardAbstract.EnergyCard;
import energyCard.DoubleColorlessEnergy;
import energyCard.WaterEnergy;
import gui.Clickables.PokemonCardInfoPanel;

public class ActivePokemonCardPanel extends JPanel {
	
	private AdditionalPokemonCardInfo clicked;
	
	public ActivePokemonCardPanel(ActivePokemonCard apc) {
		clicked = new AdditionalPokemonCardInfo(apc);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		((FlowLayout)this.getLayout()).setVgap(0);
		((FlowLayout)this.getLayout()).setHgap(0);
		JMultilineLabel info = new JMultilineLabel(apc.getName() + 
				"\nHP: " + (apc.getMaxHp() - apc.getDamage()) + "/" + apc.getMaxHp());
		this.add(info);
		info.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				if (clicked.isVisible()) {
					clicked.setVisible(false);
					clicked.closeAllWindows();
				} else {
					clicked.setLocation(e.getLocationOnScreen());
					clicked.setVisible(true);
				}
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
		// want to have gaps between energies:
		JTextArea energies = new JTextArea();
		energies.setWrapStyleWord(true);
		energies.setLineWrap(true);
		energies.setOpaque(false);
		energies.setEditable(false);
		energies.setFocusable(false);
		String energy = "Energies:\n";
		for (EnergyCard ec : apc.getEnergyCards()) {
			energy += ec.energyType() + " ";
		}
		energies.setText(energy);
		energies.setBorder(new LineBorder(Color.green));
		JTextArea moves = new JTextArea();
		moves.setWrapStyleWord(true);
		moves.setLineWrap(true);
		moves.setOpaque(false);
		moves.setEditable(false);
		moves.setFocusable(false);
		String moveNames = "Moves:\n";
		if (apc.getMoves().length == 0) {
			moveNames += "Card has no moves.";
		} else {
			for (int i = 0 ; i < apc.getMoveNames().length; i++) {
				moveNames += apc.getMoveNames()[i] + "\n";
			}
		}
		moves.setText(moveNames);
		moves.setBorder(new LineBorder(Color.red));
		this.add(energies);
		this.add(moves);
		this.setBorder(new LineBorder(Color.blue));
	}

}
