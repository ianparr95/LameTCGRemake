package moveEffects;

import arena.Arena;

public class DamageSelf extends MoveEffect{

	private int damage;
	
	public DamageSelf(Arena arena) {
		super(arena);
		damage = 0;
	}


	public void whenPlayed() {
		// Damage self doesn't apply resis/whatever. So can do it here.
		arena.getAtt().addDamage(damage);
	}

	
	public boolean requireAdditionalEffects() {
		return true;
	}

	
	public String mEffectName() {
		return "DAMAGE_SELF";
	}

	@Override
	public void setAdditionalEffects(String s) {
		damage = Integer.parseInt(s);
	}

}
