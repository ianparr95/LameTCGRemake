package statuses;

import cardAbstract.ActivePokemonCard;

public class Toxic extends Status{

	public Toxic(ActivePokemonCard apc) {
		super(apc);
	}

	@Override
	public void turnOppToUs() {
		apc.addDamage(20);
	}

	@Override
	public void turnUsToOpp() {
		apc.addDamage(20);
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
	public AttackModifier attackModifiers() {
		return new AttackModifier(0,1,0,1,0);
	}
	
	@Override
	public boolean canStack(){
		return false;
	}
	
	@Override
	public int hashCode() {
		return apc.hashCode() + 6;
	}
	@Override
	public boolean equals(Object o){
		return (o instanceof Toxic) && apc.equals(((Status) o).apc);
	}

	@Override
	public boolean curable() {
		return true;
	}
}
