package arena;

import cardAbstract.ActivePokemonCard;
import cardAbstract.EnergyCard;
import cardAbstract.PokemonCard;

public class Player {
	private Deck deck;
	private DiscardPile discardPile;
	private Hand hand;
	private Prizes prizes;
	private Bench bench;
	private ActivePokemonCard apc;
	private boolean alreadyAttachedEnergy;
	
	public Player(Deck deck, Prizes prizes, Bench bench, ActivePokemonCard apc){
		this.deck = deck;
		this.discardPile = new DiscardPile(this);
		this.hand = new Hand(this);
		this.prizes = prizes;
		this.bench = bench;
		this.apc = apc;
		alreadyAttachedEnergy = false;
	}
	
	public ActivePokemonCard getActivePokemon(){
		return apc;
	}
	
	public Deck getDeck(){
		return deck;
	}
	
	public DiscardPile getDiscardPile(){
		return discardPile;
	}
	
	public Hand getHand(){
		return hand;
	}
	
	public void setActivePokemon(ActivePokemonCard apc){
		this.apc = apc;
	}
	
	public Prizes getPrizes(){
		return prizes;
	}
	
	public Bench getBench(){
		return bench;
	}
	
	/**
	 * Attachs energy card e to the activepokemoncard pc.
	 * If already attached an energy this turn, does nothing.
	 * Pokemon powers avoid using this function?
	 * Assumes pc is either on bench or active pokemon card.
	 */
	public void attachEnergyCard(EnergyCard e, ActivePokemonCard pc){
		if (alreadyAttachedEnergy) { // if already attached, do nothing.
			return;
		}
		alreadyAttachedEnergy = true;
		pc.attachEnergy(e);
		// Then remove from hand:
		getHand().removeCardToPile(e);
		
		// BELOW: don't actually need to do this?
		/*
		if (pc.equals(apc)) {
			// User chose to attach energy card to current active:
			apc.attachEnergy(e);
		} else {
			// User didnt attach to current active:
			// Search bench.
			for (ActivePokemonCard c : bench.getBench()) {
				if (c.equals(pc)) {
					c.attachEnergy(e);
					break;
				}
			}
		}
		*/
	}
	
	public void setCanAttachEnergy(){
		alreadyAttachedEnergy = false;
	}
	
	/**
	 * Returns false if bench can't fit.
	 */
	public boolean addToBenchFromHand(PokemonCard pc) {
		hand.getHand().remove(pc);
		if (bench.getCurrentCapacity() >= bench.getMaxCapacity()) {
			return false;
		}
		hand.getHand().remove(pc);
		bench.add(pc);
		return true;
	}
	
	/**
	 * Used at beginning when setting up game.
	 * Assumes pc exists in hand: removes pc from hand
	 * and sets apc to be pc.
	 */
	public void setActivePokemonFromHand(PokemonCard pc) {
		hand.getHand().remove(pc);
		this.apc = new ActivePokemonCard(pc, pc.getId());
	}
}
