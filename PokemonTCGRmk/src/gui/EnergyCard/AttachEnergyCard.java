package gui.EnergyCard;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;
import javax.swing.SwingConstants;

import arena.GameArena.GameStage;
import cardAbstract.EnergyCard;
import gui.MainGui;
import gui.Panels.BenchPanel;
import gui.PokemonCard.ActivePokemonCardPanel;

public class AttachEnergyCard implements MouseListener {

	private EnergyCard c;
	
	private JDialog attEnergyDialog;
	
	public AttachEnergyCard(EnergyCard c) {
		this.c = c;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		MainGui.ARENA.setCurStage(GameStage.ATTACHING_ENERGY);
		MainGui.ARENA.setActionObject(c);
		// Good: want to attempt attaching this energy.
		attEnergyDialog = new JDialog(MainGui.MAIN_GUI, "Attach Energy Card", true);
		MainGui.addCurrentlyOpen(attEnergyDialog);
		attEnergyDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		BenchPanel bp = new BenchPanel(attEnergyDialog, MainGui.ARENA.getPlayerAtt().getBench());
		attEnergyDialog.setLayout(null);
		attEnergyDialog.add(bp);
		bp.setBounds(10, 160, MainGui.BENCH_WIDTH, MainGui.BENCH_HEIGHT);
			
		ActivePokemonCardPanel apc = new ActivePokemonCardPanel(attEnergyDialog, MainGui.ARENA.getAttActive(), false);
		attEnergyDialog.add(apc);
		apc.setBounds(160, 10, MainGui.ACTIVE_WIDTH, MainGui.ACTIVE_HEIGHT);
			
		attEnergyDialog.setPreferredSize(new Dimension(MainGui.BENCH_WIDTH + 30, MainGui.BENCH_HEIGHT + 200));
		attEnergyDialog.pack();
			
		attEnergyDialog.setLocation(e.getLocationOnScreen());
		attEnergyDialog.setResizable(false);
		attEnergyDialog.setVisible(true);

	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
