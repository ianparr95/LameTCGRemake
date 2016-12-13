package energyCard;

import arena.Arena;
import cardAbstract.ActivePokemonCard;
import cardAbstract.Card;
import cardAbstract.EnergyCard;

/**
 * Really, a stone energy is just a fighting energy, but since fire also starts with f
 * just call it a stone!.
 */

public class StoneEnergy extends EnergyCard {


	public StoneEnergy(Arena arena, int id) {
		super(arena, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String energyType() {
		return "s";
	}

	@Override
	public void otherEnergyEffects() {
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

}
