package moveEffects;

import arena.Arena;
import statuses.DoubleDamage;

public class SwordsDance extends MoveEffect{

	public SwordsDance(Arena arena) {
		super(arena);
	}

	
	public void whenPlayed() {
		arena.getAttActive().addStatus(new DoubleDamage(arena.getAttActive()));
	}

	
	public boolean requireAdditionalEffects() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public String mEffectName() {
		// TODO Auto-generated method stub
		return "SWORDS_DANCE";
	}

	
	public void setAdditionalEffects(String s) {
		// TODO Auto-generated method stub
		
	}

}
