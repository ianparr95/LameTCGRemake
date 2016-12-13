package statuses;

import cardAbstract.ActivePokemonCard;

public class Paralysis extends Status{

	public Paralysis(ActivePokemonCard apc) {
		super(apc);
	}

	@Override
	public boolean canAttack() {
		return false;
	}

	@Override
	public boolean canRetreat() {
		return false;
	}

	@Override
	public void turnOppToUs() {
		// Do nothing
	}

	@Override
	public void turnUsToOpp() {
		apc.removeStatus(this);
	}
	
	@Override
	public boolean canStack(){
		return false;
	}

	@Override
	public AttackModifier attackModifiers() {
		return new AttackModifier(0,1,0,1,0);
	}
	
	@Override
	public int hashCode() {
		return apc.hashCode() + 3;
	}
	@Override
	public boolean equals(Object o){
		return (o instanceof Paralysis) && apc.equals(((Status) o).apc);
	}
	
	@Override
	public boolean curable() {
		return true;
	}
	
}
