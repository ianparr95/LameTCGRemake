package arena;

import cardAbstract.Card;

public class Prizes {

	private Player player;
	private Card[] prizes;
	private int numPrizes;
	
	public Prizes(int numPrizes){
		prizes = new Card[numPrizes];
		this.numPrizes = numPrizes;
	}
	
	public void setPlayer(Player player){
		this.player = player;
	}
	
	/**
	 * Returns null if prize already taken!!!
	 * This can be used for that mankey pokemon power?
	 */
	public Card peekPrize(int index) {
		if (index >= prizes.length || index < 0) {
			return null;
		}
		return prizes[index];
	}
	
	public int numPrizesLeft(){
		return numPrizes;
	}
	
	/**
	 * Returns null if prize at index already taken!!
	 * So should make sure we can pick a different index.
	 * If numPrizesLeft > 0.
	 * MAKE SURE TO CALL DECREASE PRIZE COUNT!!
	 */
	public Card removePrize(int index){
		if (index >= prizes.length || index < 0) {
			return null;
		}
		if (prizes[index] != null) {
			Card p = prizes[index];
			prizes[index] = null;
			return p;
		}
		return null;
	}
	
	/**
	 * Call this when we remove a prize that is not
	 * when we use that mankey pokepower.
	 */
	public void decreasePrizeCount(){
		numPrizes--;
	}
	
	/**
	 * Add to the index the card c.
	 * This is for when using mankey's poke power to swap stuff.
	 */
	public void addPrize(int index, Card c) {
		prizes[index] = c;
	}
	
	/**
	 * Place prizes initially at start of the game.
	 */
	public void placePrizes(){
		for (int i = 0 ; i < numPrizes; i++) {
			prizes[i] = player.getDeck().drawCard();
		}
	}
	
}
