package pokepower;

import cardAbstract.EnergyCard;

public class RainDance extends PokePower{

	/**
	 * Blastoise's Rain Dance
	 */
	public RainDance() {
		super("Rain Dance", "RAIN_DANCE");
	}

	@Override
	public boolean activated() {
		return false;
	}

	@Override
	public void effect(Object c) {
		if (curStage == PowerStage.ATTACH_ENERGY) {
			//if (ba.getPlayerAtt())
			if (c instanceof EnergyCard) {
				if (((EnergyCard) c).energyType().equals("w")) {
					System.out.println("Rain Dance invoked!!!!");
					// STILL NEED TO TEST WITH OTHER ENERGY TYPES
					ba.getPlayerAtt().setCanAttachEnergy();
				}
			} else {
				System.err.println("Rain Dance called at the wrong stage, because"
						+ " expected c to be an energy card, but it wasn't!");
			}
		}
	}

	@Override
	public boolean affectsEnemies() {
		return false;
	}

}
