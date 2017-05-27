package moveEffects;

import arena.Arena;
import statuses.Paralysis;

public class ParOpp extends MoveEffect{

	public ParOpp(Arena arena) {
		super(arena);
		// TODO Auto-generated constructor stub
	}


	public void whenPlayed() {
		arena.getDefActive().addStatus(new Paralysis(arena.getDefActive()));
	}

	
	public boolean requireAdditionalEffects() {
		return false;
	}

	
	public String mEffectName() {
		return "PAR_OPP";
	}

	
	public void setAdditionalEffects(String s) {
		
	}

}
