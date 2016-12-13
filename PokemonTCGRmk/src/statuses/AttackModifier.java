package statuses;

public class AttackModifier {
	// Could be used for pluspower, defender, swords dance.. etc
	
	private int defenseReduction;
	private int defenseMultiplyer; // reduce damage by half etc..
	private int attackIncrease;
	private int attackMultiplyer;
	private int location; // 0 = apply this to base damage, 1 = after base damage, before weakness/resistance 
						  // 2 = after weakness/resistance
	
	// EG: pluspower does: 0 defense reduction, 1 defense multiplyer(no effect), 10 damage increase, 1 attack multiplyer(no effect)
	// and location is 2: applied after weaknness/resistance.
	// swords dance is 0,1,0,2,1 ETC.
	public AttackModifier(int defenseReduction, int defenseMultiplyer, int attackIncrease, int attackMultiplyer, int location) {
		this.setDefenseReduction(defenseReduction);
		this.setDefenseMultiplyer(defenseMultiplyer);
		this.setAttackIncrease(attackIncrease);
		this.setAttackMultiplyer(attackMultiplyer);
		this.location = location;
	}

	public int getDefenseReduction() {
		return defenseReduction;
	}

	public void setDefenseReduction(int defenseReduction) {
		this.defenseReduction = defenseReduction;
	}

	public int getDefenseMultiplyer() {
		return defenseMultiplyer;
	}

	public void setDefenseMultiplyer(int defenseMultiplyer) {
		this.defenseMultiplyer = defenseMultiplyer;
	}

	public int getAttackIncrease() {
		return attackIncrease;
	}

	public void setAttackIncrease(int attackIncrease) {
		this.attackIncrease = attackIncrease;
	}

	public int getAttackMultiplyer() {
		return attackMultiplyer;
	}

	public void setAttackMultiplyer(int attackMultiplyer) {
		this.attackMultiplyer = attackMultiplyer;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}
	 
}
