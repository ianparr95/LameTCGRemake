package cardAbstract;

import arena.Arena;

public abstract class EnergyCard extends Card{

	protected Arena arena;
	
	public EnergyCard(Arena arena, int id) {
		super(id);
		this.arena = arena;
	}
	
	public boolean attachable(){
		return true;
	}
	
	// TODO:
	public boolean canPlay(){
		// SOME VARIABLE LIKE Arena.alreadyPlayedEnergy
		// BUT BEWARE OF POKEPOWERS!!
		return true;
	}
	
	public void whenPlayed(){
		//arena.getAtt().addEnergyString(this.energyType());
		this.otherEnergyEffects(); //like potion energy.. maybe we won't implement it now
		arena.getAttHand().removeCardFromHand(this);
	}
	
	public void turnUsToOpp(){};
	
	public void turnOppToUs(){};
	
	public abstract String energyType();
	
	public abstract void otherEnergyEffects();
	
	public abstract boolean isBasicEnergy();
	
	@Override
	public String toString(){
		return "" + this.getClass().getSimpleName();
	}
	
	public String addInfoName(){
		return "";
	}


}
