package arena;

import java.util.ArrayList;
import java.util.List;

import cardAbstract.ActivePokemonCard;
import cardAbstract.PokemonCard;

public class Bench {
	
	private Player player;
	private int capacity;
	private int curCapacity;
	private List<ActivePokemonCard> bench;
	
	public Bench(int capacity) {
		this.capacity = capacity;
		this.bench = new ArrayList<ActivePokemonCard>();
		this.curCapacity = 0;
	}
	
	public void setPlayer(Player player){
		this.player = player;
	}
	
	public int getMaxCapacity(){
		return capacity;
	}
	
	public int getCurrentCapacity(){
		return curCapacity;
	}
	
	public List<ActivePokemonCard> getBench(){
		return bench;
	}
	
	/**
	 * Assumes that curCapacity < maxCapacity
	 */
	public void add(PokemonCard c) {
		ActivePokemonCard apc = new ActivePokemonCard(c, c.getId());
		bench.add(apc);
		curCapacity++;
	}
	
	public void removeToDiscardPile(int index) {
		PokemonCard rc = bench.get(index);
		player.getDiscardPile().addCard(rc);
		bench.remove(index);
	}
	
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
	
	public void removeToTopDeck(int index) {
		PokemonCard rc = bench.get(index);
		player.getDeck().addCardToTop(rc);
		bench.remove(index);
	}
	
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
