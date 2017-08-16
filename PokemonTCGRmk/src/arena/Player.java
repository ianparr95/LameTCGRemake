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
	private GameArena arena;
	
	/**
	 * Initializes player with the deck, prizes, and bench.
	 * Automatically sets owner of deck, prizes and bench, BUT NOT APC.
	 * @param deck
	 * @param prizes
	 * @param bench
	 * @param apc
	 */
	public Player(Deck deck, Prizes prizes, Bench bench, ActivePokemonCard apc){
		this.deck = deck;
		this.discardPile = new DiscardPile(this);
		this.hand = new Hand(this);
		this.prizes = prizes;
		this.bench = bench;
		this.apc = apc;
		this.arena = arena;
		alreadyAttachedEnergy = false;
		deck.setPlayer(this);
		prizes.setPlayer(this);
		bench.setPlayer(this);
		//apc.setPlayer(this);
	}
	
	/**
	 * Returns the current active pokemon
	 * @return
	 */
	public ActivePokemonCard getActivePokemon(){
		return apc;
	}
	
	/**
	 * Returns the Deck.
	 * @return
	 */
	public Deck getDeck(){
		return deck;
	}
	
	/**
	 * Returns the discard pile.
	 * @return
	 */
	public DiscardPile getDiscardPile(){
		return discardPile;
	}
	
	/**
	 * Returns the hand.
	 * @return
	 */
	public Hand getHand(){
		return hand;
	}
	
	/**
	 * Sets the active pokemon to the specified APC.
	 * @param apc
	 */
	public void setActivePokemon(ActivePokemonCard apc){
		this.apc = apc;
	}
	
	/**
	 * Returns the prizes.
	 * @return
	 */
	public Prizes getPrizes(){
		return prizes;
	}
	
	/**
	 * Returns the bench.
	 * @return
	 */
	public Bench getBench(){
		return bench;
	}
	
	/**
	 * returns if already attached energy
	 */
	public boolean alreadyAttachedEnergy(){
		return alreadyAttachedEnergy;
	}
	
	/**
	 * Attachs energy card e to the activepokemoncard pc.
	 * If already attached an energy this turn, does nothing.
	 * FOR BLASTOISES RAINDANCE, IGNORE THIS FUNCTION.
	 * Assumes pc is either on bench or active pokemon card.
	 * REMOVES THE CARD FROM THE HAND
	 * @return False if didn't attach, true if did.
	 */
	public boolean attachEnergyCard(EnergyCard e, ActivePokemonCard pc){
		arena.setCurStage(GameArena.GameStage.ATTEMPTED_ATTACHED_ENERGY);
		try {
			arena.checkPowers(e);
		} catch (InstantiationException | IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (alreadyAttachedEnergy) { // if already attached, do nothing.
			return false;
		}
		alreadyAttachedEnergy = true;
		pc.attachEnergy(e);
		// Then now call energy's effects.
		e.whenPlayed();
		// now remove it from the hand.
		hand.removeCardFromHand(e);
		return true;
		
	}
	
	/**
	 * Set if can attach an energy.
	 */
	public void setCanAttachEnergy(){
		alreadyAttachedEnergy = false;
	}
	
	/**
	 * Adds to bench a pokemon card. Returns false if can't fit in bench.
	 * DOES NOT CHECK IF POKEMON CARD CAN BE PLAYED.
	 */
	public boolean addToBenchFromHand(PokemonCard pc) {
		hand.removeCardFromHand(pc);
		if (bench.getCurrentCapacity() >= bench.getMaxCapacity()) {
			return false;
		}
		hand.removeCardFromHand(pc);
		bench.add(pc);
		return true;
	}
	
	/**
	 * Used at beginning when setting up game.
	 * Assumes pc exists in hand: removes pc from hand
	 * and sets apc to be pc.
	 */
	public void setActivePokemonFromHand(PokemonCard pc) {
		//hand.getHand().remove(pc);
		hand.removeCardFromHand(pc);
		this.apc = new ActivePokemonCard(pc, pc.getId(), this);
	}

	public void setArena(GameArena ba) {
		this.arena = ba;
	}
}
