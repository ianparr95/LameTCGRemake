package misc;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import arena.Arena;
import arena.Arena.GameStage;
import arena.Player;
import cardAbstract.ActivePokemonCard;
import cardAbstract.Card;
import cardAbstract.CardRequest;
import cardAbstract.EnergyCard;
import cardAbstract.PokemonCard;
import cardAbstract.PokemonMove;
import cardAbstract.TrainerCard;
import pokepower.PokePower;

/**
 * Basic single player console.
 */
public class DebugConsole {
	
	private static Arena ba;
	private static Scanner f;
	
	public static void startDebugConsole(Arena ba) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, CardRequest, IOException {
		DebugConsole.ba = ba;
		f = new Scanner(System.in);
		displayCommands();
		while (f.hasNext()) {
			// when reset back here, gamestage is nothing
			// cause there is no action yet.
			ba.setCurStage(GameStage.NOTHING);
			String cmd = f.nextLine();
			if (cmd.equals("q")) {
				return;
			} else if (cmd.equals("bench")) {
				displayBenches();
			} else if (cmd.equals("help")) {
				displayCommands();
			} else if (cmd.equals("clear")) {
				clearcons();
			} else if (cmd.equals("hand")) {
				displayHands();
			} else if (cmd.equals("active")) {
				displayActives();
			} else if (cmd.equals("discard")) {
				displayDiscardPile();
			} else if (cmd.equals("endturn")) {
				System.out.print("Ending turn... ");
				// when we end turn, just call nextTurn
				// and then check the game statuss
				ba.nextTurn();
				checkgame();
				// TODO:
				// Check game: should ask to replace dead pokemon
				// draw prizes etc...
				continue;
			} else if (cmd.equals("ov")) {
				// overview of stuff.
				displayActives();
				System.out.println("att moves: " + Arrays.toString(ba.getAttActive().getMoves()));
				System.out.println("def moves: " + Arrays.toString(ba.getDefActive().getMoves()));
				displayBenches();
				displayHands();
				displayDiscardPile();
				System.out.println("att deck size: " + ba.getAttDeck().getSize());
				System.out.println("def deck size: " + ba.getDefDeck().getSize());
			} else if (cmd.equals("decksize")) {
				System.out.println("att deck size: " + ba.getAttDeck().getSize());
				System.out.println("def deck size: " + ba.getDefDeck().getSize());
			} else if (cmd.equals("moves")) {
				System.out.println("att moves: " + Arrays.toString(ba.getAttActive().getMoves()));
				System.out.println("def moves: " + Arrays.toString(ba.getDefActive().getMoves()));
			} else if (cmd.equals("check")) {
				// TODO: check is a debug command?
				// fix the checkArena function also?
				/*ba.checkArena();
				System.out.println("Att dead? " + ba.attDead + " active: " + ba.attActDead);
				System.out.println("Def dead? " + ba.defDead + " active: " + ba.defActDead);
				*/
			}
			// More complex commands: split based on spaces.
			String cmds[] = cmd.split(" ");
			if (cmds.length > 0) {
				// play a trainer card.
				if (cmds[0].equals("playtr") && cmds.length >= 2) {
					int index = getIndex(0, ba.getAttHand().getSize(), 
							"Usage: playtr <card position in hand", cmds);
					if (index == -999) continue;
					if (ba.getAttHand().getList().get(index) instanceof TrainerCard) {
						TrainerCard c = (TrainerCard) ba.getAttHand().getList().get(index);
						if (c.canPlay()) {
							System.out.println("Playing trainer card: " + c.getName());
							catchWhenPlayed(c, index, cmd); 
							continue;
						}
					} else {
						System.out.println(ba.getAttHand().getList().get(index) + " is not a trainer card!");
						continue;
					}
				} else if (cmds[0].equals("atk") && cmds.length >= 2) {
					// attack with index being command
					String[] moves = ba.getAttActive().getMoveNames();
					int index = getIndex(0, moves.length,
							"Usage: atk 0 or atk 1"
								+ " where atk 0 is the first move,"
								+ " atk 1 is the second move", cmds);
					if (index == -999) continue;
					PokemonMove m = ba.getAttActive().getMove(moves[index]);
					if (ba.getAttActive().canPerformMove(m)) {
						//System.out.println("Can play move " + m.getName());
						ba.doMove(m);
						System.out.println("Performed move: ending the turn");
						ba.nextTurn();
						// TODO: checkgame fix it!
						checkgame();
					} else {
						System.out.println("Can't play move " + m.getName());
					}
					// attach energy, first is hand,2nd is bench or active, 0 = active.
				} else if (cmds[0].equals("att") && cmds.length >= 2) {
					ba.setCurStage(GameStage.ATTACH_ENERGY);
					int index = getIndex(0, ba.getAttHand().getSize(), 
							"Usage: att <index in hand for energy card> <0 for "
							+ "active, 1 to 6 for bench>", cmds);
					if (index == -999) continue;
					int bn = 0;
					try { //if bn = 0, then active, else bench from 1 to 7.
						bn = Integer.parseInt(cmds[2]);
					} catch (Exception e) {
						System.out.println("Usage: att <index in hand for energy card> <0 for active, 1 to 6 for bench>");
						continue;
					}
					if (bn < 0 || bn > ba.getPlayerAtt().getBench().getCurrentCapacity()) {
						System.out.println("Invalid index for pokemon");
						continue;
					}
					if (ba.getAttHand().getList().get(index) instanceof EnergyCard) {
						EnergyCard c = (EnergyCard) ba.getAttHand().getList().get(index);
						// Check poke powers: ALTNERATIFVLY: overload checkPowers
						// such that different stage can do like:
						// this stage: pass energycard + arena.
						// attack: pass move + arena etc..
						// endturn: pass arena only
						// TODO: ALSO ADD A CHECK IF POKE POWER AFFECTS OPPOSITION!!
						// EG: RAIN DANCE , NO, BUT PREHISTORIC POWER, YES!
						ba.checkPowers(c);
						if (c.canPlay() && !ba.getPlayerAtt().alreadyAttachedEnergy()) {
							if (bn == 0) {
								System.out.
								println("Playing energy card to active: " + c.getName());
								ba.getPlayerAtt().attachEnergyCard(c, ba.getPlayerAtt().getActivePokemon());
							} else {
								System.out.println("Playing energy card to bench: " + c.getName());
								ba.getPlayerAtt().attachEnergyCard(
										c, ba.getPlayerAtt().getBench().getList().get(bn - 1));
							}
						}
					}
				} else if (cmds[0].equals("evolve") && cmds.length >= 3) { // first bench, 2nd hand
					int bn = 0;
					try { //if bn = 0, then active, else bench from 1 to 7.
						bn = Integer.parseInt(cmds[1]);
					} catch (Exception e) {
						continue;
					}
					int hand_index = 0;
					try { //if bn = 0, then active, else bench from 1 to 7.
						hand_index = Integer.parseInt(cmds[2]);
					} catch (Exception e) {
						continue;
					}
					if (hand_index < 0 || hand_index >= ba.getAttHand().getSize()) continue;
					if (bn < 0 || bn > ba.getPlayerAtt().getBench().getCurrentCapacity()) continue;
					if (bn == 0) {
						if (ba.getAttActive().canEvolve()) {
							Card c = ba.getAttHand().getList().get(hand_index);
							System.out.println("Evolving pokemon to: " + c);
							System.out.println("Making sure we can actually evolve to this... ");
							if (!(c instanceof PokemonCard || c instanceof ActivePokemonCard)) continue;
							if (ba.getAttActive().canEvolveTo((PokemonCard) c)) {
								ActivePokemonCard evl = ba.getAttActive().evolve((PokemonCard) c);
								System.out.println("Evolving to " + evl.getName());
								ba.setAttPokemon(evl);
								ba.getAttHand().removeCardFromHand(hand_index);
							} else {
								System.out.println("Can't evolve to this pokemon");
								continue;
							}
						} else {
							System.out.println("Can't evolve this turn");
						}
					} else { 
						// TODO: when want to evolve not active.
						//System.out.println("TODO!!");
						int bench_index = bn - 1;
						if (bench_index >= ba.getPlayerAtt().getBench().getCurrentCapacity()) continue;
						if (ba.getPlayerAtt().getBench().getList().get(bench_index).canEvolve()) {
							Card c = ba.getAttHand().getList().get(hand_index);
							System.out.println("Evolving pokemon to: " + c);
							System.out.println("Making sure we can actually evolve to this... ");
							if (!(c instanceof PokemonCard || c instanceof ActivePokemonCard)) continue;
							if (ba.getPlayerAtt().getBench().getList().get(bench_index).canEvolveTo((PokemonCard) c)) {
								ActivePokemonCard evl = ba.getPlayerAtt().getBench().getList().get(bench_index).evolve((PokemonCard) c);
								System.out.println("Evolving to " + evl.getName());
								int chp = evl.getDamage();
								ba.getPlayerAtt().getBench().removeCard(bench_index);
								ba.getPlayerAtt().getBench().add(evl);
								ba.getPlayerAtt().getBench().getList().get(bench_index).setDamage(chp);
								ba.getAttHand().removeCardFromHand(hand_index);
							} else {
								System.out.println("Can't evolve to this pokemon");
								continue;
							}
						} else {
							System.out.println("Can't evolve this turn");
						}
						
					}
				} else if (cmds[0].equals("place") && cmds.length >= 2) {
					// TODO: CHECK ADDING ENERGY CARD!! AND EVOLVE!!
					int hand_index = 0;
					try { //if bn = 0, then active, else bench from 1 to 7.
						hand_index = Integer.parseInt(cmds[1]);
					} catch (Exception e) {
						continue;
					}
					if (hand_index < 0 || hand_index >= ba.getAttHand().getSize()) continue;
					Card c = ba.getAttHand().getList().get(hand_index);
					if (c instanceof PokemonCard || c instanceof ActivePokemonCard) {
						// Good:
						PokemonCard cc = (PokemonCard) c;
						if (cc.isBasicPokemon() && 
							ba.getPlayerAtt().getBench().getCurrentCapacity() <
							ba.getPlayerAtt().getBench().getMaxCapacity()) { // good
							ba.getPlayerAtt().getHand().removeCardFromHand(hand_index);
							ba.getPlayerAtt().getBench().add(cc);
						}
					}
				}
			} 
			//displayCommands();
		}
		f.close();
	}

	private static void catchWhenPlayed(TrainerCard c, int index, String cmd) {
		try {
			// ALSO THIS MAY NOT BE CORRECT, EG POTION DONT USE ADDTRAINER.
			// NEED FIND ANOTHER WAY!
			c.whenPlayed();
		} catch (CardRequest e) {
			// TODO: get the cards request from e, display them.
			// return them in rList!
			//System.out.println("Played potion");
			//e.getReturnList().add(ba.getAtt());
			System.out.println("Trainer requested " + e.getNumberCardsRequest() + " cards");
			System.out.println("Chooseable cards: " + e.getChooseableList());
			System.out.println("Displayed cards: " + e.getDisplayList());
			List<Card> cList = new ArrayList<Card>();
			for (Card egc : e.getChooseableList()) {
				cList.add(egc);
			}
			System.out.println("Please type an index for chooseable cards");
			if (e.getChooseableList().size() == 0) {
				System.err.println("Chooseable list was size 0!!!");
				System.err.println("Returning picked list as empty");
				c.returnRequestedCards(e);
				return;
			}
			// getmode may not be important, cause we handle errors in
			// trainer cards themselves
			if (e.getMode() == 0) {
				// num or less cards.
				int picked = 0;
				System.out.println("Type done to finish");
				while (f.hasNext()) {
					if (picked == e.getNumberCardsRequest()) {
						System.out.println("Picked cards up to num");
						break;
					}
					cmd = f.nextLine();
					if (!cmd.equals("done")) {
						try {
							index = Integer.parseInt(cmd);
						} catch (Exception e1) {
							System.err.println("Didn't enter an index, please try again");
							continue;
						}
					} else {
						System.out.println("User was done");
						break;
					}
					if (index < 0 || index >= cList.size()) {
						System.err.println("Bad index, please try again");
						continue;
					}
					// Good index:
					e.getReturnList().add(cList.get(index));
					cList.remove(index);
					picked++;
					if (picked == e.getNumberCardsRequest()) {
						System.out.println("Picked cards up to num");
						break;
					}
				}
			} else if (e.getMode() == 1) {
				// TODO:
			} else if (e.getMode() == 2) {
				// TODO:
			} else {
				System.err.println("Error in mode for e");
				System.err.println("Value was: " + e.getMode());
				System.err.println("Should be 0, 1 or 2.");
			}
			//System.out.println("Size of e:" + e.getReturnList().size());
			c.returnRequestedCards(e);
			System.out.println("Played trainer, may or may not have worked");
			//throw new CardRequestReturn(e);
		}
	}

	private static int getIndex(int min, int max, String string, String[] cmds) {
		int index = 0;
		try {
			index = Integer.parseInt(cmds[1]);
		} catch (Exception e) {
			System.out.println("Didn't enter a number! Try again");
			return -999;
		}
		if (index < min || index >= max) {
			System.out.println("Entered a bad index");
			return -999;
		}
		return index;
	}

	private static void displayCommands() {
		System.out.println("Command :   Description");
		System.out.println("help    :   display this!");
		System.out.println("clear   :   clear the console");
		System.out.println("ov      :   overview of all cards");
		System.out.println("active  :   display active pokemon cards");
		System.out.println("bench   :   display benched pokemon");
		System.out.println("hand    :   display your hand");
		System.out.println("discard :   discard the discard piles");
		System.out.println("endturn :   ends your turn without attacking");
		System.out.println("decksize:   displays how many cards are left in each deck");
		System.out.println("moves   :   displays moves of active pokemon");
	}

	private static void displayBenches() {
		System.out.println("Att bench: ");
		for (ActivePokemonCard c : ba.getPlayerAtt().getBench().getList()) {
			System.out.println(c.getName() + " energies: " + c.getEnergyString()
			+ " Hp: " + (c.getMaxHp() - c.getDamage()) + "/" + c.getMaxHp());
		}
		System.out.println();
		System.out.println("Def bench: ");
		for (ActivePokemonCard c : ba.getPlayerDef().getBench().getList()) {
			System.out.println(c.getName() + " energies: " + c.getEnergyString()
			+ " Hp: " + (c.getMaxHp() - c.getDamage()) + "/" + c.getMaxHp());
		}
		System.out.println();
	}
	
	private static void displayHands() {
		/*try {
			Runtime.getRuntime().exec("cls");
		} catch (IOException e) {
			System.err.println("Didn't clear the console for some reason!!!");
		}*/
		//clearcons();
		System.out.println("Att hand: ");
		int i = 0;
		for (Card c : ba.getAttHand().getList()) {
			System.out.println("[" + i + "] Card: " + c);
			i++;
		}
		/*i = 0;
		System.out.println("Def hand: ");
		for (Card c : ba.getDefHand().getHand()) {
			System.out.println("Index: " + i + " Card: " + c);
		}*/
	}
	
	private static void displayDiscardPile() {
		System.out.println("Att pile: " + ba.getPlayerAtt().getDiscardPile().getList());
		System.out.println("Def pile: " + ba.getPlayerDef().getDiscardPile().getList());
	}
	
	private static void displayActives() {
		System.out.println("Att active: " + ba.getAttActive().getName() + " hp: "
				+ (ba.getAttActive().getMaxHp() - ba.getAttActive().getDamage()) + "/" + ba.getAttActive().getMaxHp()
				+ " Cards attached: " + ba.getAttActive().getEnergyCards() + " " + ba.getAttActive().getTrainerCards()
				+ " Statuses: " + ba.getAttActive().getStatus());
		System.out.println("Def active: " + ba.getDefActive().getName() + " hp: "
				+ (ba.getDefActive().getMaxHp() - ba.getDefActive().getDamage()) + "/" + ba.getDefActive().getMaxHp()
				+ " Cards attached: " + ba.getDefActive().getEnergyCards() + " " + ba.getDefActive().getTrainerCards()
				+ " Statuses: " + ba.getDefActive().getStatus());
	}
	
	/**
	 * CALLED BEFORE OR AFTER ENDTURN?
	 * Prompts user to get prizes.
	 */
	private static void checkgame() {
		System.out.println("Done, now checking arena");
		System.out.println("Att act dead? " + ba.attActDead());
		System.out.println("Def act dead? " + ba.defActDead());
		System.out.println("Att prizes to draw: " + ba.numPrizesAttDraw());
		System.out.println("Def prizes to draw: " + ba.numPrizesDefDraw());
		// TODO: check prizes is zero. also need to check if decked out
		
		// Now ask to draw prizes:
		if (ba.numPrizesAttDraw() != 0) {
			drawPrizes(ba.getPlayerAtt(), ba.numPrizesAttDraw());
		}
		if (ba.numPrizesDefDraw() != 0) {
			drawPrizes(ba.getPlayerDef(), ba.numPrizesDefDraw());
		}
		System.out.println("Att has: " + ba.getPlayerAtt().getPrizes().numPrizesLeft() + " prizes left");
		System.out.println("Def has: " + ba.getPlayerDef().getPrizes().numPrizesLeft() + " prizes left");
		
		// if att dead, ask them to replace.
		if (ba.attActDead()) {
			ba.knockOutAttPokemon();
			replaceAct(ba.getPlayerAtt(),true);
		}
		if (ba.defActDead()) {
			ba.knockOutDefPokemon();
			replaceAct(ba.getPlayerDef(),false);
		}
	}
	
	
	
	private static void drawPrizes(Player player, int numDraw) {
		System.out.println("There are: " + player.getPrizes().numPrizesLeft() + " to pick from.");
		for (int i = 0; i < numDraw; i++) {
			System.out.println("Type an index to draw a prize from.");
			System.out.println("Possible indexes are:");
			for (int j = 0; j < player.getPrizes().getTotalNumPrizes(); j++) {
				if (player.getPrizes().peekPrize(j) != null) {
					System.out.print(j + " ");
				}
			}
			System.out.println();
			while (f.hasNext()) {
				String s = f.next();
				int ind;
				try {
					ind = Integer.parseInt(s);
				} catch (Exception e) {
					System.out.println("Please type a number");
					continue;
				}
				if (ind < 0 || ind >= player.getPrizes().getTotalNumPrizes()) {
					System.out.println("Please type a valid index");
					continue;
				}
				if (player.getPrizes().peekPrize(ind) == null) {
					System.out.println("Prize was already taken, please try again");
					continue;
				}
				Card c = player.getPrizes().removePrize(ind);
				player.getPrizes().decreasePrizeCount();
				System.out.println("Prize was a: " + c);
				player.getHand().addCard(c);
				break;
			}
			if (player.getPrizes().numPrizesLeft() == 0) {
				return;
			}
		}
		
	}

	private static void replaceAct(Player p, boolean att) {
		if (p.getBench().getCurrentCapacity() == 0) {
			// att lost
			System.out.println("a player lost due to having no benched pokemon to replace!");
			// TODO: EXIT?
		} else {
			// ask to replace
			System.out.println("Replace active. Current pokemon in bench are: " + p.getBench().getList());
			System.out.println("Type an index: ");
			while (f.hasNext()) {
				String s = f.next();
				int ind;
				try {
					ind = Integer.parseInt(s);
				} catch (Exception e) {
					System.out.println("Please type a number");
					continue;
				}
				if (ind < 0 || ind >= p.getBench().getCurrentCapacity()) {
					System.out.println("Please type a valid index");
					continue;
				}
				// now replace:
				if (att) {
					ba.setAttPokemon(p.getBench().removeCard(ind));
				} else {
					ba.setDefPokemon(p.getBench().removeCard(ind));
				}
				break;
			}
		}
	}
	
	private static void clearcons() {
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (IOException e) {
			System.err.println("Couldn't clear console for some reason");
		} catch (InterruptedException e) {
			System.err.println("Couldn't clear console for some reason");
		}
	}
}
