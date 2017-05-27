package trainerCard.statusCards;

import java.util.ArrayList;
import java.util.List;

import arena.Arena;
import cardAbstract.ActivePokemonCard;
import cardAbstract.Card;
import cardAbstract.CardRequest;
import cardAbstract.PokemonCard;
import cardAbstract.TrainerCard;

public class Potion extends TrainerCard{

	// FIX FULLHEAL, AND OTHER CARDS ALSO WITH THIS NEW CLASS OF CARD REQUEST!

	public Potion(Arena arena, int id) {
		super(arena, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean attachable() {
		return false;
	}

	@Override
	public boolean canPlay() {
		List<ActivePokemonCard> bea = new ArrayList<ActivePokemonCard>();
		bea.add(arena.getAttActive());
		bea.addAll(arena.getPlayerAtt().getBench().getList());
		for (ActivePokemonCard apc : bea) { // check all cards, if damaged, can play.
			if (apc.getDamage() != 0) {
				return true;
			}
		} // else nobody has damage, return false.
		return false;
	}

	@Override
	public void whenPlayed() throws CardRequest {
		//arena.getAtt().addDamage(-20);
		//if (arena.getAtt().getDamage() < 0) arena.getAtt().setDamage(0);
		// For card request:
		// true, cause want to request a card from ourself, num = 1 because
		// we only want one card to be healed by a potion.
		// lc = bench + active, cause we want to choose from these.
		List<Card> displayedCards = new ArrayList<Card>();
		displayedCards.add(arena.getAttActive());
		displayedCards.addAll(arena.getPlayerAtt().getBench().getList());
		List<Card> chooseableCards = new ArrayList<Card>();
		if (arena.getAttActive().getDamage() != 0) chooseableCards.add(arena.getAttActive());
		for (ActivePokemonCard c : arena.getPlayerAtt().getBench().getList()) {
			if (c.getDamage() != 0) {
				chooseableCards.add(c);
			}
		}
		CardRequest pc = new CardRequest(true, 1, displayedCards, chooseableCards
						, "Choose a pokemon to play this potion to.", 0, false);
		pc.requestCard();
	}

	@Override
	public void returnRequestedCards(CardRequest pc) {
		// The card that we want to heal.
		if (pc.getReturnList().size() == 0) {
			System.err.println("Did not specify pokemon to receive potion.");
			return;
		}
		ActivePokemonCard apc = (ActivePokemonCard) pc.getReturnList().get(0);
		if (apc == null) {
			System.err.println("Did not specify pokemon to receive potion.");
			return;
		} else if (apc.getDamage() == 0) {
			System.out.println("Pokemon " + apc + " is at full hp!!!");
			return;
		}
		apc.addDamage(-20);
		if (apc.getDamage() < 0) apc.setDamage(0);
		arena.getPlayerAtt().getHand().removeCardToPile(this);
	}
	
	@Override
	public void turnOppToUs() {
	}

	@Override
	public void turnUsToOpp() {
	}

	
	@Override
	public Card copyCard() {
		return new Potion(arena, getId());
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Potion";
	}

	@Override
	public String addInfoName() {
		// TODO Auto-generated method stub
		return "Remove up to 2 damage counters from any pokemon on your bench or active";
	}
	
	
}
