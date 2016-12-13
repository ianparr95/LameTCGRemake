package statuses;

import cardAbstract.ActivePokemonCard;

public class Pluspower extends Status{

	public Pluspower(ActivePokemonCard apc) {
		super(apc);
	}

	@Override
	public void turnOppToUs() {}

	@Override
	public void turnUsToOpp() {
		apc.removeStatus(this);
	}

	@Override
	public boolean canAttack() {
		return true;
	}

	@Override
	public boolean canStack(){
		return true;
	}
	
	@Override
	public boolean canRetreat() {
		return true;
	}

	@Override
	public AttackModifier attackModifiers() {
		return new AttackModifier(0, 1, 10, 1, 2);
	}

	@Override
	public int hashCode() {
		return apc.hashCode() + 137;
	}
	
	@Override
	public boolean curable() {
		return false;
	}
	
}
