package energyCard;

import arena.Arena;
import cardAbstract.ActivePokemonCard;
import cardAbstract.Card;
import cardAbstract.EnergyCard;

public class GrassEnergy extends EnergyCard{



	public GrassEnergy(Arena arena, int id) {
		super(arena, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String energyType() {
		return "g";
	}

	@Override
	protected void otherEnergyEffects() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Card copyCard() {
		return new GrassEnergy(arena, getId());
	}
	
	@Override
	public boolean isBasicEnergy() {
		return true;
	}
	
	@Override
	public String getName() {
		return "Grass Energy";
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
