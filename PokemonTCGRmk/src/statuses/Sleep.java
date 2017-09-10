package statuses;
import misc.RNG;
import pokemonCard.ActivePokemonCard;
public class Sleep extends Status{

	public Sleep(ActivePokemonCard apc) {
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
	public int hashCode() {
		return apc.hashCode() + 5;
	}
	@Override
	public boolean equals(Object o){
		return (o instanceof Sleep) && apc.equals(((Status) o).apc);
	}

	@Override
	public void turnOppToUs() {
		inBetweenTurns();
	}

	@Override
	public boolean canStack(){
		return false;
	}
	
	@Override
	public void turnUsToOpp() {
		inBetweenTurns();
	}
	
	private void inBetweenTurns() {
		//TODO HAVE A COIN FLIP THING
		boolean wakeUp = RNG.flipCoin();
		if (wakeUp) {
			apc.removeStatus(this);
		}
	}
	
	@Override
	public AttackModifier attackModifiers() {
		return new AttackModifier(0,1,0,1,0);
	}
	
	@Override
	public boolean curable() {
		return true;
	}
}
