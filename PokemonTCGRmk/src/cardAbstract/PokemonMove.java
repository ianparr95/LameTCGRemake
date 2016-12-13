package cardAbstract;

public class PokemonMove {
	
	/**
	 * A pokemon move is represented by a name and a move block.
	 */
	
	private String name;
	private String moveBlock;
	
	public PokemonMove(String name, String moveBlock) {
		this.name = name;
		this.moveBlock = moveBlock;
	}
	
	public String getName() {
		return name;
	}
	
	/*
	private String getMoveBlock() {
		return moveBlock;
	}
	*/
	
	/**
	 * Get how much damage this move does
	 * @return an int, how much damage this move does.
	 */
	public int getMoveDamageOpp() {
		String dmg = getMoveTagAssoc("[mdamage]");
		if (dmg == null) {
			return 0;
		} else {
			return Integer.parseInt(dmg);
		}
	}
	
	/**
	 * Get how many coin flips this move does.
	 * @return the number of coin flips this move does.
	 */
	public int getCoinFlips() {
		String dmg = getMoveTagAssoc("[mcoin]");
		if (dmg == null) {
			return 0;
		} else {
			return Integer.parseInt(dmg);
		}
	}
	
	/**
	 * Get the list of effects for a Head Flip
	 * @return the list of effects for a head flip, in a String[]
	 */
	public String[] getHeadFlip(){
		String erg = getMoveTagAssoc("[mcoinheads]");
		if (erg == null) {
			return new String[0];
		} else {
			String[] ll = erg.split(",");
			return ll;
		}
	}

	/**
	 * Get the list of effects for a tail Flip
	 * @return the list of effects for a tail flip, in a String[]
	 */
	public String[] getTailFlip() {
		String erg = getMoveTagAssoc("[mcointails]");
		if (erg == null) {
			return new String[0];
		} else {
			String[] ll = erg.split(",");
			return ll;
		}
	}
	
	/**
	 * Get what is associated with the specific head flip effect number.
	 * eg: getHeadFlip[0] = DAMAGE_SELF
	 * then getHeadFlipEffect(0) returns 10 if that is the amount associated.
	 * @param effectNumber, should be 0 for getHeadFlip[0], 1 for getHeadFlip[1] etc..
	 * @return as a String, the associated effect
	 */
	public String getHeadFlipEffect(int effectNumber) {
		return getMoveTagAssoc("[mcoinheads_" + (effectNumber+1) + "]");
	}
	
	/**
	 * Get what is associated with the specific tail flip effect number.
	 * eg: getTailFlip[0] = DAMAGE_SELF
	 * then getTailFlipEffect(0) returns 10 if that is the amount associated.
	 * @param effectNumber, should be 0 for getTailFlip[0], 1 for getTailFlip[1] etc..
	 * @return as a String, the associated effect
	 */
	public String getTailFlipEffect(int effectNumber) {
		return getMoveTagAssoc("[mcointails_" + (effectNumber+1) + "]");
	}
	
	/**
	 * Get any additional effects of the move
	 * eg: Haunter can sleep an opponent without coinflip.
	 * eg: Some moves require you to discard energy cards.
	 * @return as an array, any additional effects of the move.
	 */
	public String[] getAdditionalEffects() {
		String eff = getMoveTagAssoc("[meffect]");
		if (eff==null) {
			return new String[0];
		}
		return eff.split(",");
	}
	
	/**
	 * Get what is associated with the specific tail flip effect number.
	 * eg: getAdditionalEffects[0] = DAMAGE_SELF
	 * then getAdditionalEffects(0) returns 10 if that is the amount associated.
	 * @param effectNumber, should be 0 for getAdditionalEffects[0], 1 for getAdditionalEffects[1] etc..
	 * @return as a String, the associated effect
	 */
	public String getAdditionalEffectsAssoc(int effectNumber) {
		return getMoveTagAssoc("[meffect_" + (effectNumber+1) + "]");
	}
	
	/**
	 * Get energy cost of the move
	 * @return the energy cost of the move
	 */
	public String getEnergyCost() {
		return getMoveTagAssoc("[menergy]");
	}
	
	/**
	 * Get what is associated with a tag.
	 * @param tag
	 * @return what is associated with a tag.
	 */
	private String getMoveTagAssoc(String tag) {
		int tLoc = moveBlock.indexOf(tag);
		if (tLoc < 0) {
			return null;
		}
		int mDamageLoc = tLoc + tag.length();
		String rs = moveBlock.substring(mDamageLoc);
		int nextStop = rs.indexOf("[");
		if (nextStop == -1) {
			return rs;
		} else {
			return rs.substring(0, nextStop);
		}
	}

}
