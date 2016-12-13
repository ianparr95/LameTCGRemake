package statuses;

import cardAbstract.ActivePokemonCard;

/**
 * This interface represents the statuses that can inflict the active pokemon
 * like sleep, paralysis, poison, toxic poison (20 damage), confusion etc..
 */
public abstract class Status{
	
	protected ActivePokemonCard apc;
	
	public Status(ActivePokemonCard apc) {
		this.apc = apc;
	}
	/**
	 * Does effect still last after Opposition to us?
	 * Eg: paralysis yes, but sleep, we toss a coin
	 */
	public abstract void turnOppToUs();
	/**
	 * Does effect still last from us to opposition?
	 * Eg: defender, yes, pluspower, no.
	 */
	public abstract void turnUsToOpp();
	/**
	 * Can we attack if affected by status? Eg: paralysis no, confused-yes or no.
	 * @return true if can
	 */
	public abstract boolean canAttack();
	/**
	 * Can we retreat if affected by status?
	 * @return true if can retreat.
	 */
	public abstract boolean canRetreat();
	
	/**
	 * Eg: Swords dance, POISON shouldn't give multiple stacks in the status list.
	 * @return true if can stack, like pluspower, false else.
	 */
	public abstract boolean canStack();
	
	/**
	 * Return attackModifier of this effect
	 * @return eg: Poisoned doesn't effect our attack, but PlusPower or swords dance does.
	 */
	public abstract AttackModifier attackModifiers();
	
	/**
	 * Returns if can be cured with a full heal.
	 * @return true if curable
	 */
	public abstract boolean curable();
}
