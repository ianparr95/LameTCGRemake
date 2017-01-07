package pokepower;

import arena.Arena;

public abstract class PokePower {

	// Way to make them work:
	// Maybe before doing any move, if not activated
	// then we have to check its effect.
	// OR Arena has a list of current poke powers?

	public static Arena ba;
	// When this skill takes effect.
	public static enum PowerStage{
		ATTACK, ATTACH_ENERGY, ATTACH_TRAINER,
		RECEIVE_ATTACK, RECEIVE_DAMAGE, NOTHING
	}
	
	public static PowerStage curStage;
	
	private String pname;
	private String ptype;

	/**
	 * name and type, name should be used for like:
	 * "Rain Dance". type would be like "rain_dance"
	 * @param name: eg: "Rain Dance"
	 * @param type: eg: "energy_burn"
	 */
	public PokePower(String name, String type) {
		pname = name;
		ptype = type;
	}
	
	/**
	 * Whether or not this power needs to be selected
	 * IE: Rain dance / energy burn don't have to be selected.
	 * Damage swap does.
	 */
	public abstract boolean activated();
	
	/**
	 * Effects of this power.
	 * Object can be anything!
	 */
	public abstract void effect(Object c);
	
	public String getName() {
		return pname;
	}
	
	public String getType() {
		return ptype;
	}
}
