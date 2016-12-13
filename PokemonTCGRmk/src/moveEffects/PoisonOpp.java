package moveEffects;

import arena.Arena;
import statuses.Poisoned;

public class PoisonOpp extends MoveEffect{

	public PoisonOpp(Arena arena) {
		super(arena);
		// TODO Auto-generated constructor stub
	}

	
	public void whenPlayed() {
		// TODO Auto-generated method stub
		arena.getDef().addStatus(new Poisoned(arena.getDef()));
	}

	
	public boolean requireAdditionalEffects() {
		return false;
	}

	
	public String mEffectName() {
		return "POI_OPP";
	}

	
	public void setAdditionalEffects(String s) {
	}

}
