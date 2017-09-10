package gui.PokemonCard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import gui.MainGui;
import gui.Panels.GenericCardListPanel;
import gui.PokemonCard.Moves.MoveOnClick;
import gui.Retreating.RetreatOnClick;
import pokemonCard.ActivePokemonCard;

public class ActionDisplayDialog extends JDialog {

	public static final int ACTION_DIALOG_WIDTH = 300;
	public static final int ACTION_DIALOG_HEIGHT = 130;
	
	private ActivePokemonCard apc;
	
	public ActionDisplayDialog() {
		super(MainGui.MAIN_GUI, "Perform action", true);
		this.apc = MainGui.ARENA.getAttActive();
		this.setSize(ACTION_DIALOG_WIDTH, ACTION_DIALOG_HEIGHT);
		this.setResizable(false);
		this.setVisible(false);
		this.setLayout(new BorderLayout());
		this.add(new JLabel("Perform move..."), BorderLayout.PAGE_START);
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
		
		for (int i = 0; i < apc.getMoveNames().length; i++) {
			JButton mov = new JButton(apc.getMoveNames()[i]);
			leftPanel.add(mov);
			mov.addMouseListener(new MoveOnClick(this, apc.getMoves()[i]));
		}

		leftPanel.setBorder(new LineBorder(Color.blue));
		this.add(leftPanel, BorderLayout.LINE_START);
		
		JButton retreat = new JButton("Retreat");
		this.add(retreat, BorderLayout.LINE_END);
		retreat.addMouseListener(new RetreatOnClick());
		
		this.add(new JButton("Use PokePower"), BorderLayout.CENTER);
	}

}
