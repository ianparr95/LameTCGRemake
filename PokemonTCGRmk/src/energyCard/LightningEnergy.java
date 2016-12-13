package energyCard;

import arena.Arena;
import cardAbstract.ActivePokemonCard;
import cardAbstract.Card;
import cardAbstract.EnergyCard;

public class LightningEnergy extends EnergyCard{


	public LightningEnergy(Arena arena, int id) {
		super(arena, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String energyType() {
		return "l";
	}

	@Override
	public void otherEnergyEffects() {
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

}
