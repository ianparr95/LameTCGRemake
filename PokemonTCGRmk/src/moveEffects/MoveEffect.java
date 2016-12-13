package moveEffects;

import arena.Arena;

public abstract class MoveEffect {

	// This class is for stuff like: [mcoinheads]DAMAGE_OPP etc...
	
	// The name: like DAMAGE_OPP etc...
/*	public static String mEffectName() {
		return getName();
	}
	*/
	
	protected Arena arena;
	
	public MoveEffect(Arena arena) {
		this.arena = arena;
	}
	
	// Effect when played: like [meffect] or [mcoinhead] if we got a heads.
	public abstract void whenPlayed();
	
	/**
	 * True for example: DAMAGE_OPP needs additional info on how much damage.
	 * But POI_OPP doesn't.
	 */
	public abstract boolean requireAdditionalEffects();
	
	public abstract String mEffectName();
	
	/**
	 * Set additional effects etc: DAMAGE_OPP additional is an int
	 */
	public abstract void setAdditionalEffects(String s);
	
}
