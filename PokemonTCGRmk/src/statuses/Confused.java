package statuses;

import cardAbstract.ActivePokemonCard;
import misc.RNG;

/**
 * Do special handling for this class!
 */
public class Confused extends Status{

	public Confused(ActivePokemonCard apc) {
		super(apc);
	}

	@Override
	public void turnOppToUs() {
		// nothing: stay confused
	}

	@Override
	public void turnUsToOpp() {
		// nothing: stay confused
		
	}

	@Override
	public boolean canAttack() {
		/*boolean heads = RNG.flipCoin();
		if (heads) return true;
		apc.addDamage(30);*/
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
		return apc.hashCode() + 1;
	}
	@Override
	public boolean equals(Object o){
		return (o instanceof Confused) && apc.equals(((Status) o).apc);
	}

	@Override
	public boolean curable() {
		return true;
	}

	
}
