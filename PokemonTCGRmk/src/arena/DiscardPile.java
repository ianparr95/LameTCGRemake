package arena;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cardAbstract.Card;

public class DiscardPile {

	private Player player;
	private List<Card> pile;
	
	/**
	 * Initializes discard pile with the owning player.
	 * @param player
	 */
	public DiscardPile(Player player) {
		this.player = player;
		this.pile = new ArrayList<Card>();
	}
	
	/**
	 * Adds a card to the pile
	 * @param c
	 */
	public void addCard(Card c) {
		pile.add(c);
	}
	
	/**
	 * Returns the pile as an unmodifiable list
	 * @return
	 */
	public List<Card> getPile(){
		return Collections.unmodifiableList(pile);
	}
	
	/**
	 * Returns false if card doesn't exist in pile.
	 * Adds this card to the top of the deck.
	 * This can be used for like item finder.
	 * This removes the card from the pile.
	 */
	public boolean removeCardToTopOfDeck(Card c){
		for (int i = 0 ; i < pile.size(); i++) {
			if (c.getId() == pile.get(i).getId()) {
				Card d = pile.remove(i);
				player.getDeck().addCardToTop(d);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns false if card doesn't exist in pile
	 * Adds this card to the hand.
	 * This removes the card from the pile.
	 */
	public boolean removeCardToHand(Card c){
		for (int i = 0 ; i < pile.size(); i++) {
			if (c.getId() == pile.get(i).getId()) {
				Card d = pile.remove(i);
				player.getHand().addCard(d);
				return true;
			}
		}
		return false;
	}
	
	
}
