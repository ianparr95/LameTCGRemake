package arena;

import java.util.ArrayList;
import java.util.List;

import cardAbstract.ActivePokemonCard;
import cardAbstract.Card;
import cardAbstract.PokemonCard;

public class Hand {
	// This class represents the hand of a player.
	
	private List<Card> hand;
	private Player player;
	
	public Hand(Player player){
		this.player = player;
		this.hand = new ArrayList<Card>();
	}
	
	/**
	 * Fills up the hand initially with 7 cards, making sure
	 * that it has a basic pokemon in it.
	 * Assumes deck already has 60 cards in it.
	 */
	public void drawInitialHand(){
		Deck d = player.getDeck();
		Card[] iHand = d.peekNextCards(7);
		for (Card c : iHand) {
			if (c instanceof PokemonCard) {
				if (((PokemonCard) c).isBasicPokemon()){
					// Good: can use iHand.
					for (int i = 0 ; i < 7 ; i++) {
						Card drawn = d.drawCard();
						this.addCard(drawn);
					}
					return;
				}
			}
		}
		// If reached here: hand is not good.
		d.shuffleDeck();
		drawInitialHand();
	}
	
	public List<Card> getHand(){
		return hand;
	}
	
	public void addCard(Card c){
		hand.add(c);
	}
	
	public void removeCardToPile(int index){
		if (index >= hand.size()) return;
		Card c = hand.remove(index);
		player.getDiscardPile().addCard(c);
	}
	
	/**
	 * Removes the card from the hand.
	 */
	public void removeCardToPile(Card c){
		hand.remove(c);
		player.getDiscardPile().addCard(c);
	}
	
	/**
	 * Returns false if card doesn't exist in this hand.
	 * Adds this card to the top of the deck.
	 */
	public boolean removeCardToTopOfDeck(Card c){
		for (int i = 0 ; i < hand.size(); i++) {
			if (c.getId() == hand.get(i).getId()) {
				Card d = hand.remove(i);
				player.getDeck().addCardToTop(d);
				return true;
			}
		}
		return false;
	}
	
	public int getSize(){
		return hand.size();
	}
	
}
