package arena;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pokemonCard.ActivePokemonCard;
import pokemonCard.PokemonCard;

public class Bench {
	
	private Player player;
	private int capacity;
	//private int curCapacity;
	private List<ActivePokemonCard> bench;
	
	/**
	 * Sets up the bench with capacity.
	 * @param capacity
	 */
	public Bench(int capacity) {
		this.capacity = capacity;
		this.bench = new ArrayList<ActivePokemonCard>();
		//this.curCapacity = 0;
	}
	
	/**
	 * Set player who controls this bench.
	 * @param player
	 */
	public void setPlayer(Player player){
		this.player = player;
	}
	
	/**
	 * Get max capacity of the bench
	 * @return max capacity of the bench
	 */
	public int getMaxCapacity(){
		return capacity;
	}
	
	/**
	 * Get current capacity of the bench
	 * @return the current capacity of the bench
	 */
	public int getCurrentCapacity(){
		return bench.size();
	}
	
	/**
	 * The bench of cards as an unmodifiable list.
	 * @return the bench of cards as a List.
	 */
	public List<ActivePokemonCard> getList(){
		return Collections.unmodifiableList(bench);
	}
	
	/**
	 * Adds the current pokemon card c as an active pokemoncard to the bench.
	 * Assumes current capacity < max capacity.
	 * If the card being added to the bench is an active pokemon card with a
	 * current hp not equal to zero, then we must manually set the hp of that card.
	 * EG: Gust of wind switches active to bench: need to keep track of that HP. 
	 * @param c, the pokemon card to add to the bench.
	 */
	public void add(PokemonCard c) {
		ActivePokemonCard apc = new ActivePokemonCard(c, c.getId(), player);
		bench.add(apc);
		//curCapacity++;
	}
	
	/**
	 * Adds the active pokemon card. Therefore all statuses are set.
	 * So they should be cleared.
	 */
	public void addActive(ActivePokemonCard c) {
		bench.add(c);
		//curCapacity++;
	}
	
	/**
	 * Removes the card at index to the discard pile.
	 * Assumes card at index exists.
	 * @param index
	 */
	public void removeToDiscardPile(int index) {
		PokemonCard rc = bench.get(index);
		player.getDiscardPile().addCard(rc);
		bench.remove(index);
	}
	
	/**
	 * Removes card, returning it.
	 * @param index
	 */
	public ActivePokemonCard removeCard(int index) {
		return bench.remove(index);
	}
	
	
	/**
	 * Removes card
	 * @param apc
	 */
	public boolean removeCard(ActivePokemonCard apc) {
		return bench.remove(apc);
	}
	
	/**
	 * Removes the active pokemon card to the discard pile.
	 * Does nothing if card is not on the bench.
	 * @param c
	 */
	public void removeToDiscardPile(ActivePokemonCard c) {
		for (int i = 0 ; i < capacity; i++) {
			if (c.equals(bench.get(i))) {
				PokemonCard rc = bench.get(i);
				player.getDiscardPile().addCard(rc);
				bench.remove(i);
				return;
			}
		}
	}
	
	/**
	 * Removes the card at index from the bench to the top of the deck.
	 * @param index
	 */
	public void removeToTopDeck(int index) {
		PokemonCard rc = bench.get(index);
		player.getDeck().addCardToTop(rc);
		bench.remove(index);
	}
	
	/**
	 * Removes the card from the bench to the top of the deck.
	 * @param c
	 */
	public void removeToTopDeck(ActivePokemonCard c) {
		for (int i = 0 ; i < capacity; i++) {
			if (c.equals(bench.get(i))) {
				PokemonCard rc = bench.get(i);
				player.getDeck().addCardToTop(rc);
				bench.remove(i);
				return;
			}
		}
	}

}
