package gui.PokemonCard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import cardAbstract.ActivePokemonCard;
import cardAbstract.EnergyCard;
import energyCard.DoubleColorlessEnergy;
import energyCard.WaterEnergy;
import gui.JMultilineLabel;
import gui.MainGui;

public class ActivePokemonCardPanel extends JPanel {
	
	private AdditionalPokemonCardInfo clicked;
	
	public ActivePokemonCardPanel(ActivePokemonCard apc, boolean isActive) {
		clicked = new AdditionalPokemonCardInfo(MainGui.MAIN_GUI, apc);
		onUpdate(apc, isActive);
	}

	public ActivePokemonCardPanel(JDialog attEnergy, ActivePokemonCard apc, boolean isActive) {
		clicked = new AdditionalPokemonCardInfo(attEnergy, apc);
		onUpdate(apc, isActive);
	}
	
	private void onUpdate(ActivePokemonCard apc, boolean isActive) {
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		((FlowLayout)this.getLayout()).setVgap(0);
		((FlowLayout)this.getLayout()).setHgap(0);
		JMultilineLabel info = new JMultilineLabel(apc.getName() + 
				"\nHP: " + (apc.getMaxHp() - apc.getDamage()) + "/" + apc.getMaxHp());
		this.add(info);
		info.setBorder(new LineBorder(Color.pink));
		info.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				if (clicked.isVisible()) {
					clicked.setVisible(false);
					clicked.closeAllWindows();
					MainGui.removeCurrentlyOpen(clicked);
				} else {
					MainGui.addCurrentlyOpen(clicked);
					clicked.setLocation(e.getLocationOnScreen().x, e.getLocationOnScreen().y - 100);
					clicked.setVisible(true);
				}
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
		
		if (isActive) {
			JLabel actions = new JLabel("Perform action...");
			this.add(actions);
			actions.setBorder(new LineBorder(Color.black));
			actions.setPreferredSize(new Dimension(MainGui.ACTIVE_WIDTH, 20));
		}
		
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
