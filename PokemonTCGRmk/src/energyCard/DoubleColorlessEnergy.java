package energyCard;

import java.util.Arrays;

import arena.Arena;
import cardAbstract.ActivePokemonCard;
import cardAbstract.Card;
import cardAbstract.EnergyCard;
import cardAbstract.PokemonCard;

public class DoubleColorlessEnergy extends EnergyCard{


	public DoubleColorlessEnergy(Arena arena, int id) {
		super(arena, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String energyType() {
		return "cc";
	}

	@Override
	public void otherEnergyEffects() {
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

}
