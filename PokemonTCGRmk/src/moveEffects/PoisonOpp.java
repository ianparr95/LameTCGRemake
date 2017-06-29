package moveEffects;

import arena.GameArena;
import statuses.Poisoned;

public class PoisonOpp extends MoveEffect{

	public PoisonOpp(GameArena arena) {
		super(arena);
		// TODO Auto-generated constructor stub
	}

	
	public void whenPlayed() {
		// TODO Auto-generated method stub
		arena.getDefActive().addStatus(new Poisoned(arena.getDefActive()));
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
