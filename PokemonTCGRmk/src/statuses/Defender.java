package statuses;

import cardAbstract.ActivePokemonCard;

public class Defender extends Status{

	public Defender(ActivePokemonCard apc) {
		super(apc);
	}

	@Override
	public void turnOppToUs() {
		apc.removeStatus(this);
	}

	@Override
	public void turnUsToOpp() {
	}

	@Override
	public boolean canAttack() {
		return true;
	}

	@Override
	public boolean canRetreat() {
		return true;
	}

	@Override
	public boolean canStack(){
		return true;
	}
	
	@Override
	public AttackModifier attackModifiers() {
		return new AttackModifier(20, 1, 0, 1, 2);
	}

	@Override
	public int hashCode() {
		return apc.hashCode() + 351;
	}
	
	@Override
	public boolean curable() {
		return false;
	}
	
}
