package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import arena.GameArena;
import cardAbstract.Card;
import gui.EnergyCard.AttachEnergyCard;
import gui.EnergyCard.EnergyLabelClickable;
import gui.Panels.BenchPanel;
import gui.PokemonCard.ActivePokemonCardPanel;
import gui.PokemonCard.AdditionalPokemonCardInfo;
import gui.PokemonCard.PokemonCardInfoPanel;
import pokemonCard.ActivePokemonCard;

/**
 * Basic GUI, this is the main gui class which controls all the
 * components and contains the constants and stuff etc.
 */
public class MainGui {
	
	public static final JFrame MAIN_GUI = new JFrame("Pokemon TCG");
	
	public static GameArena ARENA;
	
	private static Set<JDialog> currentlyOpen = new HashSet<JDialog>();
	
	// The big frame Constants
	public static final int FRAME_SIZE_X = 700;
	public static final int FRAME_SIZE_Y = 700;
	
	// The game log
	public static final Rectangle LOG_DIM = new Rectangle(475, 180, 200, 280);

	// Benches
	public static final int BENCH_WIDTH = 390;
	public static final int BENCH_HEIGHT = 50;
	public static final int BENCH_X = 175;
	public static final int BENCH_Y_DEF = 100;
	public static final int BENCH_Y_ATT = 500;
	
	// Active Pokemon
	public static final int ACTIVE_WIDTH = 100;
	public static final int ACTIVE_HEIGHT = 150;
	public static final int ACTIVE_X = 325;
	public static final int ACTIVE_Y_DEF = 150;
	public static final int ACTIVE_Y_ATT = 350;
	
	// View hand/discard buttons.
	public static final int HD_WIDTH = 100;
	public static final int HD_HEIGHT = 20;
	public static final int HD_X = 325;
	public static final int HAND_Y_ATT = 560;
	public static final int DISCARD_Y_ATT = 580;
	public static final int DISCARD_Y_DEF = 70;
	//public static final int HAND_Y_DEF = 0;
	
	// Deck size/prizes/endturn labels.
	public static final Point DECK_SIZE_ATT_BOUNDS = new Point(20,570);
	public static final Point DECK_SIZE_DEF_BOUNDS = new Point(20,10);
	public static final Point PRIZES_LEFT_ATT_BOUNDS = new Point(20,600);
	public static final Point PRIZES_LEFT_DEF_BOUNDS = new Point(20,40);
	public static final Point END_TURN_BOUNDS = new Point(20,520);
	
	public static BenchPanel attbench;
	public static BenchPanel defbench;
	
	public static ActivePokemonCardPanel attAct;
	public static ActivePokemonCardPanel defAct;
	
	public static HandGui attHand;
	
	public static JButton discardAtt;
	public static JButton discardDef;
	
	public static JLabel attPrizesLeft;
	public static JLabel defPrizesLeft;
	
	public static JLabel attDeckSize;
	public static JLabel defDeckSize;
	
	public static JButton endTurnButton;


	public static void loadGui(GameArena ba) {
		// TODO: READ.
		/*
		 * Have some way so that you can only open one type of child for parent
		 * etc. if for an ActivePokemonCard, you want to active pokepower, make sure
		 * to close or disable attacking with a move, otherwise there may be weird stuff.
		 * maybe have some recursive closeWindows function?
		 */
		
		ARENA = ba;
		
		// Handlers for the game arena stuff.
		ARENA.registerPrizeHandler(new DrawPrizesGui());
		
		// Have default arial fonts.
		setFonts();
		MAIN_GUI.setLayout(null);
		
		// set up both benches:
		attbench = new BenchPanel(MAIN_GUI, ba.getPlayerAtt().getBench());
		defbench = new BenchPanel(MAIN_GUI, ba.getPlayerDef().getBench());
		
		MAIN_GUI.add(attbench);
		attbench.setBounds(BENCH_X, BENCH_Y_ATT, BENCH_WIDTH, BENCH_HEIGHT);
		MAIN_GUI.add(defbench);
		defbench.setBounds(BENCH_X, BENCH_Y_DEF, BENCH_WIDTH, BENCH_HEIGHT);
		
		// set up active pokemon.
		attAct = new ActivePokemonCardPanel(ba.getAttActive(), true);
		defAct = new ActivePokemonCardPanel(ba.getDefActive(), false);
		
		MAIN_GUI.add(attAct);
		attAct.setBounds(ACTIVE_X, ACTIVE_Y_ATT, ACTIVE_WIDTH, ACTIVE_HEIGHT);
		MAIN_GUI.add(defAct);
		defAct.setBounds(ACTIVE_X, ACTIVE_Y_DEF, ACTIVE_WIDTH, ACTIVE_HEIGHT);
		
		// set up hand and discard piles.
		attHand = new HandGui(ba.getAttHand());
		JButton handAtt = new JButton("View hand.");
		MAIN_GUI.add(handAtt);
		handAtt.setBounds(HD_X, HAND_Y_ATT, HD_WIDTH, HD_HEIGHT);
		handAtt.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (attHand.isVisible()) {
					attHand.setVisible(false);
				} else {
					attHand.setVisible(true);
					attHand.setLocationRelativeTo(MAIN_GUI);
					attHand.setLocation(BENCH_X, ACTIVE_Y_ATT);
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		});
		
		discardAtt = new JButton("Discard Pile.");
		MAIN_GUI.add(discardAtt);
		discardAtt.setBounds(HD_X - 10, DISCARD_Y_ATT, HD_WIDTH + 20, HD_HEIGHT);
		discardDef = new JButton("Discard Pile.");
		MAIN_GUI.add(discardDef);
		discardDef.setBounds(HD_X - 10, DISCARD_Y_DEF, HD_WIDTH + 20, HD_HEIGHT);
		
		defPrizesLeft = new JLabel("Prizes: 6/6");
		MAIN_GUI.add(defPrizesLeft);
		defPrizesLeft.setBounds(PRIZES_LEFT_DEF_BOUNDS.x, PRIZES_LEFT_DEF_BOUNDS.y, 100, 20);
		defDeckSize = new JLabel("Deck size: " + ARENA.getDefDeck().getSize() + "/60");
		MAIN_GUI.add(defDeckSize);
		defDeckSize.setBounds(DECK_SIZE_DEF_BOUNDS.x, DECK_SIZE_DEF_BOUNDS.y, 100, 20);
		
		attPrizesLeft = new JLabel("Prizes: 6/6");
		MAIN_GUI.add(attPrizesLeft);
		attPrizesLeft.setBounds(PRIZES_LEFT_ATT_BOUNDS.x, PRIZES_LEFT_ATT_BOUNDS.y, 100, 20);
		attDeckSize = new JLabel("Deck size: " + ARENA.getAttDeck().getSize() + "/60");
		MAIN_GUI.add(attDeckSize);
		attDeckSize.setBounds(DECK_SIZE_ATT_BOUNDS.x, DECK_SIZE_ATT_BOUNDS.y, 100, 20);
		
		endTurnButton = new JButton("End turn");
		MAIN_GUI.add(endTurnButton);
		endTurnButton.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				MainGui.ARENA.nextTurn();
				MainGui.onUpdate();
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		});
		endTurnButton.setBounds(END_TURN_BOUNDS.x, END_TURN_BOUNDS.y, 100, 20);
		
		JTextArea log = new JTextArea(5, 40);
		log.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(log);

        MAIN_GUI.add(scrollPane);
        scrollPane.setBounds(LOG_DIM);
        
        PrintStream printStream = new PrintStream(new CustomOutput(log));
        System.setOut(printStream);
        System.out.println("Game log:");
		
		// final set up
		MAIN_GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MAIN_GUI.setSize(FRAME_SIZE_X, FRAME_SIZE_Y);
		MAIN_GUI.getContentPane().setBackground(new Color(0, 20, 100, 80));
		MAIN_GUI.setVisible(true);

	}

	public static void onUpdate() {
		// Firstly: close all windows
		for (JDialog jd : currentlyOpen) {
			if (jd != null) {
				jd.dispose();
			}
		}
		currentlyOpen.clear();
		
		// Then do rest of stuff.
		attbench.setBench(ARENA.getAttBench());
		defbench.setBench(ARENA.getDefBench());
		attbench.onUpdate(MAIN_GUI);
		defbench.onUpdate(MAIN_GUI);
		
		attHand.setVisible(false);
		attHand = new HandGui(ARENA.getAttHand());
		
		MAIN_GUI.remove(attAct);
		MAIN_GUI.remove(defAct);
		attAct = new ActivePokemonCardPanel(ARENA.getAttActive(), true);
		defAct = new ActivePokemonCardPanel(ARENA.getDefActive(), false);
		MAIN_GUI.add(attAct);
		attAct.setBounds(ACTIVE_X, ACTIVE_Y_ATT, ACTIVE_WIDTH, ACTIVE_HEIGHT);
		MAIN_GUI.add(defAct);
		defAct.setBounds(ACTIVE_X, ACTIVE_Y_DEF, ACTIVE_WIDTH, ACTIVE_HEIGHT);
		
		attDeckSize.setText("Deck size: " + ARENA.getAttDeck().getSize() + "/60");
		defDeckSize.setText("Deck size: " + ARENA.getDefDeck().getSize() + "/60");
		
		attPrizesLeft.setText("Prizes: " + ARENA.getPlayerAtt().getPrizes().numPrizesLeft() + "/6");
		defPrizesLeft.setText("Prizes: " + ARENA.getPlayerDef().getPrizes().numPrizesLeft() + "/6");

		MAIN_GUI.repaint();
	}
	
	public static void addCurrentlyOpen(JDialog jd) {
		currentlyOpen.add(jd);
	}

	public static void removeCurrentlyOpen(JDialog jd) {
		currentlyOpen.remove(jd);
		
	}
	
	private static void setFonts() {
		Font f = new Font("Arial", Font.PLAIN, 12);
		UIManager.put("Button.font", f);
		UIManager.put("ToggleButton.font", f);
		UIManager.put("RadioButton.font", f);
		UIManager.put("CheckBox.font", f);
		UIManager.put("ColorChooser.font", f);
		UIManager.put("ComboBox.font", f);
		UIManager.put("Label.font", f);
		UIManager.put("List.font", f);
		UIManager.put("MenuBar.font", f);
		UIManager.put("MenuItem.font", f);
		UIManager.put("RadioButtonMenuItem.font", f);
		UIManager.put("CheckBoxMenuItem.font", f);
		UIManager.put("Menu.font", f);
		UIManager.put("PopupMenu.font", f);
		UIManager.put("OptionPane.font", f);
		UIManager.put("Panel.font", f);
		UIManager.put("ProgressBar.font", f);
		UIManager.put("ScrollPane.font", f);
		UIManager.put("Viewport.font", f);
		UIManager.put("TabbedPane.font", f);
		UIManager.put("Table.font", f);
		UIManager.put("TableHeader.font", f);
		UIManager.put("TextField.font", f);
		UIManager.put("PasswordField.font", f);
		UIManager.put("TextArea.font", f);
		UIManager.put("TextPane.font", f);
		UIManager.put("EditorPane.font", f);
		UIManager.put("TitledBorder.font", f);
		UIManager.put("ToolBar.font", f);
		UIManager.put("ToolTip.font", f);
		UIManager.put("Tree.font", f);
	}
	
}

