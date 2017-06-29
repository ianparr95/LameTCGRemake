package moveEffects;

import arena.GameArena;

public class DamageOpp extends MoveEffect{

	//private int damage;
	
	// We do special handling for this in Arena doMove.
	
	public DamageOpp(GameArena arena) {
		super(arena);
	//	damage = 0;
	}

	
	public void whenPlayed() {
		//arena.getDef().addDamage(damage);
		// DAMAGE_OPP: WE CHECK IT BY SELF. IT APPLIES TO WEAKNESS/RESISTANCE.
	}

	
	public boolean requireAdditionalEffects() {
		return false;
	}

	
	public String mEffectName() {
		return "DAMAGE_OPP";
	}

	
	public void setAdditionalEffects(String s) {
		//this.damage = Integer.parseInt(s);
	}

}
