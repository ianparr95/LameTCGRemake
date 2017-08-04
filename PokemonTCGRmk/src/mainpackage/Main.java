package mainpackage;
import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import arena.GameArena;
import arena.Bench;
import arena.Deck;
import arena.Hand;
import arena.Player;
import arena.Prizes;
import cardAbstract.ActivePokemonCard;
import cardAbstract.Card;
import cardAbstract.CardRequest;
import cardAbstract.EnergyCard;
import cardAbstract.ParsePokemonCardsFile;
import cardAbstract.PokemonCard;
import cardAbstract.PokemonMove;
import cardAbstract.TrainerCard;
import energyCard.DoubleColorlessEnergy;
import energyCard.FireEnergy;
import energyCard.GrassEnergy;
import energyCard.LightningEnergy;
import energyCard.StoneEnergy;
import energyCard.WaterEnergy;
import gui.BattlePanel;
import gui.CardGui;
import gui.CardListGui;
import gui.MainGui;
import misc.CardFilter;
import misc.DebugConsole;
import statuses.*;
import trainerCard.*;
import trainerCard.drawingCards.Bill;
import trainerCard.drawingCards.ProfessorOak;
import trainerCard.otherCards.GustOfWindCard;
import trainerCard.statusCards.DefenderCard;
import trainerCard.statusCards.FullHeal;
import trainerCard.statusCards.PluspowerCard;
import trainerCard.statusCards.Potion;

public class Main {
	
	public static void main(String args[]) throws CardRequest, Exception {
		
		Player playerOne = new Player(new Deck(), new Prizes(6), new Bench(6), null);
		playerOne.getBench().setPlayer(playerOne);
		playerOne.getDeck().setPlayer(playerOne);
		playerOne.getPrizes().setPlayer(playerOne);
		playerOne.getBench().setPlayer(playerOne);

		Player playerTwo = new Player(new Deck(), new Prizes(6), new Bench(6), null);
		playerTwo.getBench().setPlayer(playerTwo);
		playerTwo.getDeck().setPlayer(playerTwo);
		playerTwo.getPrizes().setPlayer(playerTwo);
		playerTwo.getBench().setPlayer(playerTwo);

		GameArena ba = new GameArena(playerOne, playerTwo);
		testDeck(playerOne, ba);
		testDeck(playerTwo, ba);

		playerOne.getPrizes().placePrizes();
		playerTwo.getPrizes().placePrizes();
		System.out.println("------PlayerOne-------");
		drawHandPlaceCards(playerOne, ba);
		System.out.println("------PlayerTwo-------");
		drawHandPlaceCards(playerTwo, ba);
		//debug(cards);
		
		// LOAD GUI
		//MainGui.loadGui(ba);
		// LOAD DEBUG CONSOLE.
		DebugConsole.startDebugConsole(ba);
	}
	
	private static void drawHandPlaceCards(Player player, GameArena ba) {
		System.out.println("Player drew initial hand:\n" + player.getHand().getList());

		CardFilter cf = new CardFilter(player.getHand().getList());
		// Get all the basic pokemon from playerOne's hand.
		List<PokemonCard> ih = cf.filterCardsStage("0");
		// Take the first card from ih and place it for player one's active
		// pokemon.
		PokemonCard p1b = ih.get(0);
		ih.remove(0);
		player.setActivePokemonFromHand(p1b);
		System.out.println("Player's active pokemon is now: " + player.getActivePokemon());
		// Set the rest of cards to be on bench:
		for (PokemonCard pc : ih) {
			player.addToBenchFromHand(pc);
		}
		System.out.println("Player's bench is now:" + player.getBench().getList());
		System.out.println("Hand is now:" + player.getHand().getList());
		// Attach an energy card: TODO: add a method for this
		// In the class player: that way can keep track if attached one during a
		// turn
		// then when ba.nextTurn(), set this to be false. For a pp like rain
		// dance: use attach directly..

		/*List<Card> bllc = cf.filterCardsExact(ProfessorOak.class);
		if (bllc.size() > 0) {
			System.out.println("Got a professor oak");
			System.out.println("Hand is now:" + player.getHand().getHand());
			TrainerCard bl = (TrainerCard) bllc.get(0);
			// When played automatically adds itself to pile.
			bl.whenPlayed();
			System.out.println("Hand after playing oak is: " + player.getHand().getHand());
			System.out.println("Discard pile should contain oak and others: " + player.getDiscardPile().getPile());
		}*/
	}
	
	private static void testDeck(Player player, GameArena ba) throws FileNotFoundException {
		PokemonCard p12 = ParsePokemonCardsFile.getPokemonCard("Squirtle", "8");
		PokemonCard w22 = ParsePokemonCardsFile.getPokemonCard("Wartortle", "22");
		PokemonCard blast = ParsePokemonCardsFile.getPokemonCard("Blastoise", "52");
		PokemonCard staryu = ParsePokemonCardsFile.getPokemonCard("Staryu", "15");
		PokemonCard starmie = ParsePokemonCardsFile.getPokemonCard("Starmie", "28");
		/*PokemonCard p12 = ParsePokemonCardsFile.getPokemonCard("Nidoran Male", "20");
		PokemonCard w22 = ParsePokemonCardsFile.getPokemonCard("Nidoran Male", "20");
		PokemonCard blast = ParsePokemonCardsFile.getPokemonCard("Nidoran Male", "20");
		PokemonCard staryu = ParsePokemonCardsFile.getPokemonCard("Nidoran Male", "20");
		PokemonCard starmie = ParsePokemonCardsFile.getPokemonCard("Nidoran Male", "20");*/
		Deck p1d = player.getDeck();
		p1d.addCardFast(p12);
		p1d.addCardFast(p12);
		p1d.addCardFast(p12);
		p1d.addCardFast(p12);
		p1d.addCardFast(w22);
		p1d.addCardFast(w22);
		p1d.addCardFast(w22);
		p1d.addCardFast(w22);
		p1d.addCardFast(blast);
		p1d.addCardFast(blast);
		p1d.addCardFast(blast);
		p1d.addCardFast(blast);
		p1d.addCardFast(staryu);
		p1d.addCardFast(staryu);
		p1d.addCardFast(starmie);		
		TrainerCard bill = new Bill(ba, 0);
		p1d.addCardFast(bill);
		p1d.addCardFast(bill);
		p1d.addCardFast(bill);
		p1d.addCardFast(bill);
		TrainerCard profOak = new ProfessorOak(ba, 0);
		p1d.addCardFast(profOak);
		p1d.addCardFast(profOak);
		p1d.addCardFast(profOak);
		TrainerCard potion = new Potion(ba, 0);
		p1d.addCardFast(potion);
		p1d.addCardFast(potion);
		p1d.addCardFast(potion);
		p1d.addCardFast(potion);
		TrainerCard pluspower = new PluspowerCard(ba, 0);
		p1d.addCardFast(pluspower);
		p1d.addCardFast(pluspower);
		p1d.addCardFast(pluspower);
		p1d.addCardFast(pluspower);
		TrainerCard GOW = new GustOfWindCard(ba, 0);
		p1d.addCardFast(GOW);
		p1d.addCardFast(GOW);
		p1d.addCardFast(GOW);
		p1d.addCardFast(GOW);
		WaterEnergy we = new WaterEnergy(ba , 0);
		for (int i = p1d.getSize(); i < 60; i++) {
			p1d.addCardFast(we);
		}
		p1d.shuffleDeck();
		player.getHand().drawInitialHand();
		//System.out.println(player.getHand().getHand());
	}
	
	private static void testEnergy(){
		StringBuilder mv = new StringBuilder("ce");
		StringBuilder us = new StringBuilder("ef");
		System.out.println(energyCompareTest(mv,us));
	}
	
	private static boolean energyCompareTest(StringBuilder oe, StringBuilder us){
		// Now check energy type:
		if (us.length() < oe.length()) {
			return false; // if we have less energy cards than needed, false
		}
		
		// Check everything except colorless first:
		
		
		int p = 0;
		while(true) {
			if (oe.length() == 0) {
				break;
			}
			// Check if only contains c.
			int ll = 0;
			for (ll = 0 ; ll < oe.length(); ll++) {
				if (oe.charAt(ll) != 'c') break;
			}
			if (ll == oe.length()) {
				break; // only c's left
			}
			lstart:
			if(oe.charAt(p) == 'c') {
				p++;
				continue;
			} else {
				// not colorless: check if us contains that char.
				for (int i = 0 ; i < us.length(); i++) {
					if (us.charAt(i) == oe.charAt(p)) {
						// Good: can remove.
						us.deleteCharAt(i);
						oe.deleteCharAt(p);
						p = 0;
						break lstart;
					}
				}
				return false; // don't contain
			}
		}
		
		// Now check opponents effects: (like tail wag, paralysis, sleep etc...)
		/* CHECKS HERE */
		// TODO
		
		return (us.length() >= oe.length());
	}
	
	private static void testGui(GameArena ba) throws FileNotFoundException{
		JFrame jf = new JFrame("Battle!");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		/*BattlePanel opponentPanel = new BattlePanel(false);
		BattlePanel ourPanel = new BattlePanel(true);
		jf.setLayout(null);
		jf.setSize(500, 530);
		jf.setResizable(false);
		jf.add(opponentPanel);
		jf.add(ourPanel);
		ourPanel.setSize(500, 250);
		ourPanel.setLocation(0, 250);
		opponentPanel.setLocation(0,0);
		opponentPanel.setSize(500, 250);
		ourPanel.setBorder(BorderFactory.createLineBorder(Color.black));*/
		//PokemonCard p12 = ParsePokemonCardsFile.getPokemonCard("Squirtle", "8");
		//Card po = new DefenderCard(null, 0);
		//CardGui g = new CardGui(p12);
		//g.setVisible(true);
		List<ActivePokemonCard> benchba = ba.getPlayerDef().getBench().getList();
		List<Card> benchfix = new ArrayList<Card>();
		for (Card c : benchba){
			benchfix.add(c);
		}
		CardListGui g = new CardListGui(ba.getDefDeck().getList());
		g.setVisible(true);
		jf.setLayout(null);
		jf.setSize(700, 700);
		//jf.setResizable(false);
		jf.add(g, 0, 0);
		//jf.pack();

	}
	
	private static void debug(String cards[][]) throws FileNotFoundException{

		System.out.println("There are: " + cards.length + " cards currently loaded.");
		for (int cn = 0; cn < cards.length; cn++) {
			PokemonCard card = ParsePokemonCardsFile.getPokemonCard(cards[cn][0], cards[cn][1]);
			System.out.println(card);
			String mNames[] = card.getMoveNames();
			System.out.println(card.getMaxHp() + "<<HP");
			System.out.println("---------------------------------");
			System.out.println("Type is:" + card.getType());
			System.out.println("Retreat cost is:" + card.getRCost());
			System.out.println("Weakness is:" + card.getWeakness());
			System.out.println("Resistance is:" +  card.getResistance());
			System.out.println("Pokepower is:" + card.getPokePowerName());
			System.out.println("Stage is:" + card.getStage());
			System.out.println("Evolves from:" + card.getEvol());
			System.out.println("--------------------------");
			System.out.println(card.getName() + "'s moves are:" + mNames.length);
			for (int i = 0; i < mNames.length; i++) {
				System.out.println("--------------------------");
				System.out.println(mNames[i]);
				System.out.println("Move block for: " + mNames[i]);
				//PokemonMove move = new PokemonMove(mNames[i], card.getMoveBlock(mNames[i]));
				PokemonMove move = card.getMove(mNames[i]);
				System.out.println("Damage dealt:" + move.getMoveDamageOpp());
				System.out.println("Energy cost:" + move.getEnergyCost());
				System.out.println("Coins to flip:" + move.getCoinFlips());
				System.out.println("Coin flip effects on heads:");
				for (int k = 0; k < move.getHeadFlip().length; k++) {
					System.out.println(move.getHeadFlip()[k]);
					System.out.println("Effect does: " + move.getHeadFlipEffect(k));
				}
				System.out.println("Coin flip effects on tails:");
				for (int k = 0; k < move.getTailFlip().length; k++) {
					System.out.println(move.getTailFlip()[k]);
					System.out.println("Effect does: " + move.getTailFlipEffect(k));
				}
				System.out.println("Additional effects:");
				for (int k = 0 ; k < move.getAdditionalEffects().length; k++){
					System.out.println(move.getAdditionalEffects()[k]);
					System.out.println("Effect does: " + move.getAdditionalEffectsAssoc(k));
				}
			}
			System.out.println("<<<<<<<<<<<<<>>>>>>>>>>>>>>>");
		}
	}
	
}
