package statuses;

import pokemonCard.ActivePokemonCard;

public class DoubleDamage extends Status{

	// For stuff like swords dance that doubles your base damage
	
	public DoubleDamage(ActivePokemonCard apc) {
		super(apc);
	}

	@Override
	public void turnOppToUs() {
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
		return false;
	}
	
	@Override
	public AttackModifier attackModifiers() {
		return new AttackModifier(0, 1, 0, 2, 0);
	}
	
	@Override
	public int hashCode() {
		return apc.hashCode() + 2;
	}
	@Override
	public boolean equals(Object o){
		return (o instanceof DoubleDamage) && apc.equals(((Status) o).apc);
	}

	
	@Override
	public boolean curable() {
		return false;
	}
}
