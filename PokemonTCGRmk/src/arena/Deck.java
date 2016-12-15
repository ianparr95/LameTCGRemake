package arena;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import cardAbstract.Card;
import cardAbstract.PokemonCard;


public class Deck {
	// A deck holds 60 cards
	private List<Card> deck; // both trainer and pokemoncard extend this.
	private Player player;
	
	/**
	 * Initializes a new deck.
	 */
	public Deck(){
		deck = new LinkedList<Card>();
	}
	
	/**
	 * Set the owner of this deck
	 * @param player
	 */
	public void setPlayer(Player player){
		this.player = player;
	}
	
	/**
	 * Adds a card to the top of this deck.
	 * @param c
	 */
	public void addCardToTop(Card c){
		deck.add(0, c);
	}

	/**
	 * Quickly add the card to this list at the end.
	 * Used for deck building, not for adding to the top.
	 * Use addCardToTop for that.
	 * Sets the id of the card automatically based on
	 * size of the deck. So when building: first card id is 0
	 * second is 1, third is 2 and so on.
	 * We also copy the card, so it is safe to re-add the same card
	 * using this function.
	 * @param c the card to add.
	 */
	public void addCardFast(Card c){
		// MAYBE OR MAYBE NOT SET ID HERE:
		Card nc = c.copyCard();
		nc.setId(deck.size());
		deck.add(nc);
	}
	
	/**
	 * Get the size of the deck
	 * @return the size of the deck
	 */
	public int getSize(){
		return deck.size();
	}
	
	/**
	 * Returns an unmodifiable list of cards
	 * @return
	 */
	public List<Card> getDeck(){
		return Collections.unmodifiableList(deck);
	}
	
	/**
	 * Shuffles the deck.
	 */
	public void shuffleDeck(){
		Collections.shuffle(deck);
	}
	
	/**
	 * Check if we decked out (deck is empty)
	 * @return true if we decked out.
	 */
	public boolean deckedOut(){
		return deck.size() == 0;
	}
	
	/**
	 * Draw a card
	 * DOES NOT ADD IT TO THE HAND.
	 * REMOVES IT FROM THE TOP OF THE DECK
	 * @return null if deck is empty. Else returns card drawn.
	 */
	public Card drawCard(){
		if (deck.size() == 0) {
			return null;
		}
		return deck.remove(0); // remove the first element
	}
	
	/**
	 * Remove card from index.
	 * Returns the card. Assumes index is valid.
	 */
	public Card removeCardFromDeck(int index){
		return deck.remove(index);
	}
	
	/**
	 * Peek the top of the deck
	 * @return null if deck is empty.
	 */
	public Card peekNextCard(){
		if (deck.size() == 0) {
			return null;
		}
		return deck.get(0);
	}
	
	/**
	 * Checks if have atleast one basic, and not more than 4 types of a card
	 * FOUR TYPES OF A CARD NOT YET IMPLEMENTED. SHOULD ALSO MAKE THIS RETURN
	 * A BOOLEAN.
	 */
	public void checkValid(){
		// TODO: 4 types of a card, use a map?
		boolean foundBasic = false;
		for (int i = 0 ; i < deck.size(); i++) {
			if (deck.get(i) instanceof PokemonCard) {
				PokemonCard c = (PokemonCard) deck.get(i);
				if (c.isBasicPokemon()) {
					foundBasic = true;
				}
			}
			// Check map? for 4 types here.
		}
		if (!foundBasic){
			System.err.println("Not a valid deck!");
		}
	}
	
	/**
	 * Return in an array the next number of cards specified by num.
	 * Does not remove the cards from the deck: just peeks at them.
	 * Returns null entries on cards that don't exist.
	 * Card[0] refers to the top card, Card[1] the next.. and so on.
	 */
	public Card[] peekNextCards(int num){
		Card[] c = new Card[num];
		for (int i = 0 ; i < num; i++) {
			if (i >= deck.size()) {
				for (; i < num; i++) {
					c[i] = null;
				}
				return c;
			}
			c[i] = deck.get(i);
		}
		return c;
	}
}
