package pokepower;

import arena.Arena;

public abstract class PokePower {

	public static Arena ba;
	
	
	
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
	 * Whether this power affects enemies.
	 * IE: Rain Dance doesn't. Prehistoric power does. 
	 */
	public abstract boolean affectsEnemies();
	
	/**
	 * Effects of this power.
	 * Object can be anything!
	 * But should be relevant. IE: Rain Dance passed in a energy card.
	 */
	public abstract void effect(Object c);
	
	public String getName() {
		return pname;
	}
	
	public String getType() {
		return ptype;
	}


}
