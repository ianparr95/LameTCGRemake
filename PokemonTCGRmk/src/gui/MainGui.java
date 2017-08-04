package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import arena.GameArena;
import cardAbstract.ActivePokemonCard;
import cardAbstract.Card;

public class MainGui {

	public static final int BENCH_WIDTH = 350;
	public static final int BENCH_HEIGHT = 50;
	
	public static final int BENCH_X = 175;
	public static final int BENCH_Y_TOP = 200;
	public static final int BENCH_Y_BOT = 500;
	
	public static void loadGui(GameArena ba) {
		JFrame jf = new JFrame("Game");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		jf.setLayout(null);
		jf.setSize(700, 700);
		//jf.setResizable(false);
		//jf.pack();
		
		// have two benches:
		BenchGui bot = new BenchGui(ba.getPlayerAtt().getBench());
		BenchGui top = new BenchGui(ba.getPlayerDef().getBench());
		jf.add(bot);
		bot.setBounds(BENCH_X, BENCH_Y_BOT, BENCH_WIDTH, BENCH_HEIGHT);
		jf.add(top);
		
	}
	
}
