package energyCard;

import arena.GameArena;
import cardAbstract.Card;
import cardAbstract.EnergyCard;

/**
 * Really, a stone energy is just a fighting energy, but since fire also starts with f
 * just call it a stone!.
 */

public class StoneEnergy extends EnergyCard {


	public StoneEnergy(GameArena arena, int id) {
		super(arena, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String energyType() {
		return "s";
	}

	@Override
	protected void otherEnergyEffects() {
		// Do nothing for this since this is a basic energy card.
	}
	
	@Override
	public Card copyCard() {
		return new StoneEnergy(arena, getId());
	}
	
	@Override
	public boolean isBasicEnergy() {
		return true;
	}
	
	@Override
	public String getName() {
		return "Fighting Energy";
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
