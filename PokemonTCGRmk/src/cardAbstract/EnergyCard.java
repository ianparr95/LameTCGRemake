package cardAbstract;

import arena.GameArena;

public abstract class EnergyCard extends Card{

	protected GameArena arena;
	
	/**
	 * Constructor for a new EnergyCard
	 * @param arena, the current arena of play.
	 * @param id, the card id.
	 */
	public EnergyCard(GameArena arena, int id) {
		super(id);
		this.arena = arena;
	}
	
	/**
	 * Returns true if the energy card is attachable
	 * @return true, if attachable. Else false.
	 */
	public boolean attachable() {
		return true;
	}
	
	/**
	 * Returns true if this energy can be played. By default, always returns true.
	 * Use Player.setCanAttachEnergy and Player.attachEnergyCard instead.
	 * @return true, if we can attach an energy card.
	 */
	public boolean canPlay() {
		return true;
	}
	
	/**
	 * Called when playing this card.
	 */
	public void whenPlayed() {
		this.otherEnergyEffects();
	}
	
	/**
	 * Called when going from our turn to the opposition.
	 */
	public abstract void turnUsToOpp();
	
	/**
	 * Called when going from the opposition turn to us.
	 */
	public abstract void turnOppToUs();
	
	/**
	 * The String of this energy.
	 * Etc: Grass energy returns "g".
	 * Double colorless returns "cc".
	 * @return the energy string.
	 */
	public abstract String energyType();
	
	/**
	 * Energy effects of this card. Called by
	 * the whenPlayed method.
	 */
	protected abstract void otherEnergyEffects();
	
	/**
	 * Is this card a basic energy?
	 * Ie: grass energy is, double colorless is not.
	 * @return true, if this card is a basic energy card.
	 */
	public abstract boolean isBasicEnergy();
	
	@Override
	public String toString(){
		return "" + this.getClass().getSimpleName();
	}
	
	/**
	 * Additional info about this card.
	 */
	public String addInfoName(){
		return "";
	}


}
