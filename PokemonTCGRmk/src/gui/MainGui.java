package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import arena.GameArena;
import cardAbstract.ActivePokemonCard;
import cardAbstract.Card;

/**
 * Basic GUI, this is the main gui class which controls all the
 * components and contains the constants and stuff etc.
 */
public class MainGui {
	
	// The big frame Constants
	public static final int FRAME_SIZE_X = 700;
	public static final int FRAME_SIZE_Y = 700;

	// Benches
	public static final int BENCH_WIDTH = 350;
	public static final int BENCH_HEIGHT = 50;
	public static final int BENCH_X = 175;
	public static final int BENCH_Y_DEF = 100;
	public static final int BENCH_Y_ATT = 500;
	
	// Active Pokemon
	public static final int ACTIVE_WIDTH = 200;
	public static final int ACTIVE_HEIGHT = 300;
	public static final int ACTIVE_X = 175;
	public static final int ACTIVE_X_Y_DEF = 200;
	public static final int ACTIVE_X_Y_ATT = 400;
	
	public static void loadGui(GameArena ba) {
		JFrame jf = new JFrame("Pokemon TCG");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		jf.setLayout(null);
		jf.setSize(FRAME_SIZE_X, FRAME_SIZE_Y);

		// set up both benches:
		BenchGui attbench = new BenchGui(ba.getPlayerAtt().getBench());
		BenchGui defbench = new BenchGui(ba.getPlayerDef().getBench());
		jf.add(attbench);
		attbench.setBounds(BENCH_X, BENCH_Y_ATT, BENCH_WIDTH, BENCH_HEIGHT);
		jf.add(defbench);
		defbench.setBounds(BENCH_X, BENCH_Y_DEF, BENCH_WIDTH, BENCH_HEIGHT);
		
	}
	
}
