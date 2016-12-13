package statuses;

import cardAbstract.ActivePokemonCard;

public class Poisoned extends Status{

	public Poisoned(ActivePokemonCard apc) {
		super(apc);
	}

	@Override
	public void turnOppToUs() {
		apc.addDamage(10);
	}

	@Override
	public void turnUsToOpp() {
		apc.addDamage(10);
	}

	@Override
	public boolean canAttack() {
		return true;
	}

	@Override
	public boolean canStack(){
		return false;
	}
	
	@Override
	public boolean canRetreat() {
		return true;
	}
	
	@Override
	public AttackModifier attackModifiers() {
		return new AttackModifier(0,1,0,1,0);
	}
	
	@Override
	public int hashCode() {
		return apc.hashCode() + 4;
	}
	@Override
	public boolean equals(Object o){
		return (o instanceof Poisoned) && apc.equals(((Status) o).apc);
	}
	
	@Override
	public boolean curable() {
		return true;
	}
}
