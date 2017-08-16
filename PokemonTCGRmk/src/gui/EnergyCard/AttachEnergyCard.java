package gui.EnergyCard;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;

import arena.GameArena.GameStage;
import cardAbstract.EnergyCard;
import gui.MainGui;
import gui.Panels.BenchPanel;
import gui.PokemonCard.ActivePokemonCardPanel;

public class AttachEnergyCard implements MouseListener {

	private EnergyCard c;
	
	public AttachEnergyCard(EnergyCard c) {
		this.c = c;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// can't attach energy.
		MainGui.ARENA.setCurStage(GameStage.ATTACHING_ENERGY);
		MainGui.ARENA.setActionObject(c);
		/*try {
			MainGui.ARENA.checkPowers(c);
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}*/
		// Good: want to attempt attaching this energy.
		JDialog attEnergy = new JDialog(MainGui.MAIN_GUI, "Attach Energy Card", true);
		BenchPanel bp = new BenchPanel(attEnergy, MainGui.ARENA.getPlayerAtt().getBench());
		attEnergy.setLayout(null);
		attEnergy.add(bp);
		bp.setBounds(10, 160, MainGui.BENCH_WIDTH, MainGui.BENCH_HEIGHT);
			
		ActivePokemonCardPanel apc = new ActivePokemonCardPanel(attEnergy, MainGui.ARENA.getAttActive(), false);
		attEnergy.add(apc);
		apc.setBounds(160, 10, MainGui.ACTIVE_WIDTH, MainGui.ACTIVE_HEIGHT);
			
		attEnergy.setPreferredSize(new Dimension(MainGui.BENCH_WIDTH + 30, MainGui.BENCH_HEIGHT + 200));
		attEnergy.pack();
			
		attEnergy.setLocation(e.getLocationOnScreen());
		attEnergy.setResizable(false);
		attEnergy.setVisible(true);
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
