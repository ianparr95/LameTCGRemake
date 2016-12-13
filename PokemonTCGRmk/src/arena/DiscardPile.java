package arena;

import java.util.ArrayList;
import java.util.List;

import cardAbstract.Card;

public class DiscardPile {

	private Player player;
	private List<Card> pile;
	
	public DiscardPile(Player player) {
		this.player = player;
		this.pile = new ArrayList<Card>();
	}
	
	public void addCard(Card c) {
		pile.add(c);
	}
	
	public List<Card> getPile(){
		return pile;
	}
	
	/**
	 * Returns false if card doesn't exist in pile.
	 * Adds this card to the top of the deck.
	 * This can be used for like item finder.
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
