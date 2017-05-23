package pokepower;

import arena.Arena.GameStage;
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
		if (ba.getCurStage() == GameStage.ATTACH_ENERGY) {
			//if (ba.getPlayerAtt())
			if (c instanceof EnergyCard) {
				if (((EnergyCard) c).energyType().equals("w")) {
					//System.out.println("Rain Dance invoked!!!!");
					ba.getPlayerAtt().setCanAttachEnergy();
				}
			} else {
				System.err.println("Rain Dance called at the wrong stage, because"
						+ " expected object to be an energy card, but it wasn't!");
			}
		}
	}

	@Override
	public boolean affectsEnemies() {
		return false;
	}

	@Override
	public boolean affectsSelf() {
		return true;
	}

}
