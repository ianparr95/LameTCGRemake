package misc;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import arena.Arena;
import cardAbstract.ActivePokemonCard;
import cardAbstract.Card;
import cardAbstract.CardRequest;
import cardAbstract.EnergyCard;
import cardAbstract.PokemonCard;
import cardAbstract.PokemonMove;
import cardAbstract.TrainerCard;

public class DebugConsole {
	
	private static Arena ba;
	
	public static void startDebugConsole(Arena ba) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, CardRequest {
		DebugConsole.ba = ba;
		@SuppressWarnings("resource")
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
			} else if (cmd.equals("decksize")) {
				System.out.println("att deck size: " + ba.getAttDeck().getSize());
				System.out.println("def deck size: " + ba.getDefDeck().getSize());
			} else if (cmd.equals("moves")) {
				System.out.println("att moves: " + Arrays.toString(ba.getAtt().getMoves()));
				System.out.println("def moves: " + Arrays.toString(ba.getDef().getMoves()));
			} else if (cmd.equals("check")) {
				ba.checkArena();
				System.out.println("Att dead? " + ba.attDead + " active: " + ba.attActDead);
				System.out.println("Def dead? " + ba.defDead + " active: " + ba.defActDead);
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
									
								} else if (e.getMode() == 2) {
									
								} else {
									System.err.println("Error in mode for e");
									System.err.println("Value was: " + e.getMode());
									System.err.println("Should be 0, 1 or 2.");
								}
								//System.out.println("Size of e:" + e.getReturnList().size());
								c.returnRequestedCards(e);
								System.out.println("Played trainer, may or may not have worked");
								continue;
								//throw new CardRequestReturn(e);
							}
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
						System.out.println("Performed move: ending the turn");
						ba.nextTurn();
					} else {
						System.out.println("Can't play move " + m.getName());
					}
					// attach energy, first is hand,2nd is bench or active, 0 = active.
				} else if (cmds[0].equals("att") && cmds.length >= 2) {
					int index = 0;
					try {
						index = Integer.parseInt(cmds[1]);
					} catch (Exception e) {
						continue;
					}
					if (index < 0 || index >= ba.getAttHand().getSize()) continue;
					int bn = 0;
					try { //if bn = 0, then active, else bench from 1 to 7.
						bn = Integer.parseInt(cmds[2]);
					} catch (Exception e) {
						continue;
					}
					if (bn < 0 || bn > ba.getPlayerAtt().getBench().getCurrentCapacity()) continue;
					if (ba.getAttHand().getHand().get(index) instanceof EnergyCard) {
						EnergyCard c = (EnergyCard) ba.getAttHand().getHand().get(index);
						if (c.canPlay() && !ba.getPlayerAtt().alreadyAttachedEnergy()) {
							if (bn == 0) {
								System.out.println("Playing energy card to active: " + c.getName());
								ba.getPlayerAtt().attachEnergyCard(c, ba.getPlayerAtt().getActivePokemon());
							} else {
								System.out.println("Playing energy card to bench: " + c.getName());
								ba.getPlayerAtt().attachEnergyCard(
										c, ba.getPlayerAtt().getBench().getBench().get(bn - 1));
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
						if (ba.getAtt().canEvolve()) {
							Card c = ba.getAttHand().getHand().get(hand_index);
							System.out.println("Evolving pokemon to: " + c);
							System.out.println("Making sure we can actually evolve to this... ");
							if (!(c instanceof PokemonCard || c instanceof ActivePokemonCard)) continue;
							if (ba.getAtt().canEvolveTo((PokemonCard) c)) {
								ActivePokemonCard evl = ba.getAtt().evolve((PokemonCard) c);
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
						System.out.println("TODO!!");
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
					Card c = ba.getAttHand().getHand().get(hand_index);
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
		}
		f.close();
	}

	private static void displayBenches() {
		System.out.println("Att bench: ");
		for (ActivePokemonCard c : ba.getPlayerAtt().getBench().getBench()) {
			System.out.print("[" + c.getName() + " energies: " + c.getEnergyString());
		}
		System.out.println();
		System.out.println("Def bench: ");
		for (ActivePokemonCard c : ba.getPlayerDef().getBench().getBench()) {
			System.out.print("[" + c.getName() + " energies: " + c.getEnergyString());
		}
		System.out.println();
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
