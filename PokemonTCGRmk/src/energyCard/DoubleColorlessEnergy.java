package energyCard;

import arena.GameArena;
import cardAbstract.Card;
import cardAbstract.EnergyCard;

public class DoubleColorlessEnergy extends EnergyCard{


	public DoubleColorlessEnergy(GameArena arena, int id) {
		super(arena, id);
	}

	@Override
	public String energyType() {
		return "cc";
	}

	@Override
	protected void otherEnergyEffects() {
	}
	
	@Override
	public Card copyCard() {
		return new DoubleColorlessEnergy(arena, getId());
	}

	@Override
	public boolean isBasicEnergy() {
		return false;
	}

	@Override
	public String getName() {
		return "Double Colorless Energy";
	}

	@Override
	public void turnUsToOpp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnOppToUs() {
		// TODO Auto-generated method stub
		
	}

}
