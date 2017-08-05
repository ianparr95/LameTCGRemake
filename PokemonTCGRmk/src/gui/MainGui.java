package gui;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import arena.GameArena;
import cardAbstract.ActivePokemonCard;
import cardAbstract.Card;

/**
 * Basic GUI, this is the main gui class which controls all the
 * components and contains the constants and stuff etc.
 */
public class MainGui {
	
	public static final JFrame jf = new JFrame("Pokemon TCG");
	
	// The big frame Constants
	public static final int FRAME_SIZE_X = 700;
	public static final int FRAME_SIZE_Y = 700;

	// Benches
	public static final int BENCH_WIDTH = 400;
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
		// TODO: READ.
		/*
		 * Have some way so that you can only open one type of child for parent
		 * etc. if for an ActivePokemonCard, you want to active pokepower, make sure
		 * to close or disable attacking with a move, otherwise there may be weird stuff.
		 * maybe have some recursive closeWindows function?
		 */
		
		setFonts();
		
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
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	
}
