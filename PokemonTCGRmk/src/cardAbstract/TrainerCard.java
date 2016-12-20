package cardAbstract;

import java.util.List;

import arena.Arena;

public abstract class TrainerCard extends Card{
	// make it take ATTacker and DEFENDER?
	// also have ability like gust of wind:
	// let some function like "chooseeEnemeyBenchPokemon" etc..
	// TODO: ALSO WHEN SOMEONE SWITCHES OUT BENCH POKEMON, MAKE SURE MODIFY ATT/DEF
	
	//protected ActivePokemonCard att;
	//protected ActivePokemonCard def;
	protected Arena arena;
	
	public TrainerCard(Arena arena, int id) {
		super(id);
		this.arena = arena;
	}
	
	public Arena getArena(){
		return arena;
	}
	/**
	 * Eg: pluspower/defender is attachable, but bill, prof oak etc is not.
	 * @return true if attachable
	 */
	public abstract boolean attachable();
	
	/**
	 * Returns false eg: if revive is played but no dead pokemon in discard pile.
	 * @return true if can paly this card.
	 */
	public abstract boolean canPlay();
	
	/**
	 * Effects when played.
	 * @throws CardRequest 
	 */
	public abstract void whenPlayed() throws CardRequest;
	
	/**
	 * Does ATTACHABLE CARD still exist from opp to us?
	 * Eg: pluspower, no, but should be removed...
	 * Actually now only defender?
	 */
	public abstract void turnOppToUs();
	/**
	 * Does ATTACHABLE CARD still last from us to opposition?
	 * Eg: defender, yes, pluspower, no.
	 */
	public abstract void turnUsToOpp();
	
	@Override
	public String toString(){
		return "" + this.getClass().getSimpleName();
	}

	/**
	 * When trainer requests a card.
	 * Override when needed.
	 * This is called after whenPlayed(), so for example:
	 * a potion will get card request for who to apply it to.
	 * Then this function will need to remove that card from the hand!
	 * @param pc
	 */
	public void returnRequestedCards(CardRequest pc) {
		// TODO Auto-generated method stub
		System.err.println("returnRequestCards called when not overriden!!!"
				+ "\n" + "This may cause unwanted behavior in game.");
	}
}
