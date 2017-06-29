package energyCard;

import arena.GameArena;
import cardAbstract.Card;
import cardAbstract.EnergyCard;

public class LightningEnergy extends EnergyCard{


	public LightningEnergy(GameArena arena, int id) {
		super(arena, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String energyType() {
		return "l";
	}

	@Override
	protected void otherEnergyEffects() {
		// Do nothing for this since this is a basic energy card.
	}
	
	@Override
	public Card copyCard() {
		return new LightningEnergy(arena, getId());
	}
	
	@Override
	public boolean isBasicEnergy() {
		return true;
	}
	
	@Override
	public String getName() {
		return "Lightning Energy";
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
