package energyCard;

import arena.GameArena;
import cardAbstract.Card;
import cardAbstract.EnergyCard;

public class PsychicEnergy extends EnergyCard{



	public PsychicEnergy(GameArena arena, int id) {
		super(arena, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String energyType() {
		return "p";
	}

	@Override
	protected void otherEnergyEffects() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Card copyCard() {
		return new PsychicEnergy(arena, getId());
	}
	
	@Override
	public boolean isBasicEnergy() {
		return true;
	}
	
	@Override
	public String getName() {
		return "Psychic Energy";
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
