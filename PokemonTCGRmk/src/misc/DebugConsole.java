package misc;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Scanner;

import arena.Arena;
import cardAbstract.Card;
import cardAbstract.EnergyCard;
import cardAbstract.PokemonMove;
import cardAbstract.TrainerCard;

public class DebugConsole {
	
	private static Arena ba;
	
	public static void startDebugConsole(Arena ba) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		DebugConsole.ba = ba;
		Scanner f = new Scanner(System.in);
		while (f.hasNext()) {
			String cmd = f.nextLine();
			if (cmd.equals("q")) {
				return;
			} else if (cmd.equals("bench")) {
				displayBenches();
			} else if (cmd.equals("hand")) {
				displayHands();
			} else if (cmd.equals("active")) {
				displayActives();
			} else if (cmd.equals("discard")) {
				displayDiscardPile();
			} else if (cmd.equals("endturn")) {
				System.out.print("Ending turn... ");
				ba.nextTurn();
				System.out.println("Done");
			}
			// None of these: try splitting
			String cmds[] = cmd.split(" ");
			if (cmds.length > 0) {
				if (cmds[0].equals("playtr") && cmds.length >= 2) {
					int index = 0;
					try {
						index = Integer.parseInt(cmds[1]);
					} catch (Exception e) {
						continue;
					}
					if (index < 0 || index >= ba.getAttHand().getSize()) continue;
					if (ba.getAttHand().getHand().get(index) instanceof TrainerCard) {
						TrainerCard c = (TrainerCard) ba.getAttHand().getHand().get(index);
						if (c.canPlay()) {
							System.out.println("Playing trainer card: " + c.getName());
							ba.getAtt().addTrainer(c);
						}
					}
				} else if (cmds[0].equals("atk") && cmds.length >= 2) {
					// attack with index being command
					int index = 0;
					try {
						index = Integer.parseInt(cmds[1]);
					} catch (Exception e) {
						continue;
					}
					String[] moves = ba.getAtt().getMoveNames();
					if (index >= moves.length) continue;
					PokemonMove m = ba.getAtt().getMove(moves[index]);
					if (ba.getAtt().canPerformMove(m)) {
						//System.out.println("Can play move " + m.getName());
						ba.doMove(m);
					} else {
						System.out.println("Can't play move " + m.getName());
					}
					// attach energy
				} else if (cmds[0].equals("att") && cmds.length >= 2) {
					int index = 0;
					try {
						index = Integer.parseInt(cmds[1]);
					} catch (Exception e) {
						continue;
					}
					if (index < 0 || index >= ba.getAttHand().getSize()) continue;
					if (ba.getAttHand().getHand().get(index) instanceof EnergyCard) {
						EnergyCard c = (EnergyCard) ba.getAttHand().getHand().get(index);
						if (c.canPlay()) {
							System.out.println("Playing energy card: " + c.getName());
							ba.getAtt().attachEnergy(c);
						}
					}
				}
			}
		}
		f.close();
	}

	private static void displayBenches() {
		System.out.println("Att bench: " + ba.getPlayerAtt().getBench().getBench());
		System.out.println("Def bench: " + ba.getPlayerDef().getBench().getBench());
	}
	
	private static void displayHands() {
		System.out.println("Att hand: " + ba.getAttHand().getHand());
		System.out.println("Def hand: " + ba.getDefHand().getHand());
	}
	
	private static void displayDiscardPile() {
		System.out.println("Att pile: " + ba.getPlayerAtt().getDiscardPile().getPile());
		System.out.println("Def pile: " + ba.getPlayerDef().getDiscardPile().getPile());
	}
	
	private static void displayActives() {
		System.out.println("Att active: " + ba.getAtt().getName() + " hp: "
				+ (ba.getAtt().getMaxHp() - ba.getAtt().getDamage()) + "/" + ba.getAtt().getMaxHp()
				+ " Cards attached: " + ba.getAtt().getEnergyCards() + " " + ba.getAtt().getTrainerCards()
				+ " Statuses: " + ba.getAtt().getStatus());
		System.out.println("Def active: " + ba.getDef().getName() + " hp: "
				+ (ba.getDef().getMaxHp() - ba.getDef().getDamage()) + "/" + ba.getDef().getMaxHp()
				+ " Cards attached: " + ba.getDef().getEnergyCards() + " " + ba.getDef().getTrainerCards()
				+ " Statuses: " + ba.getDef().getStatus());
	}
}
