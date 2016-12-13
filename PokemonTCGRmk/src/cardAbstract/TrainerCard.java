package cardAbstract;

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
	 */
	public abstract void whenPlayed();
	
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
}
