package energyCard;

import arena.Arena;
import cardAbstract.Card;
import cardAbstract.EnergyCard;

public class WaterEnergy extends EnergyCard{


	public WaterEnergy(Arena arena, int id) {
		super(arena, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String energyType() {
		return "w";
	}

	@Override
	protected void otherEnergyEffects() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Card copyCard() {
		return new WaterEnergy(arena, getId());
	}
	
	@Override
	public boolean isBasicEnergy() {
		return true;
	}
	
	@Override
	public String getName() {
		return "Water Energy";
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
