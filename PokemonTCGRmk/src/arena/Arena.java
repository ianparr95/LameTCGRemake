package arena;

import java.lang.reflect.InvocationTargetException;
import cardAbstract.ActivePokemonCard;
import cardAbstract.PokemonCard;
import cardAbstract.PokemonMove;
import cardAbstract.TrainerCard;
import misc.CEList;
import misc.RNG;
import moveEffects.MoveEffect;
import pokepower.PokePower;
import statuses.Status;

public class Arena {
	
	private Player att;
	private Player def;
	
	// TODO:
	// Set this to be player instead.
	public Arena(Player att, Player def) {
		this.att = att;
		this.def = def;
	}
	
	public Player getPlayerAtt() {
		return att;
	}
	
	public Player getPlayerDef(){
		return def;
	}
	
	/**
	 * Advances to the next turn such that att becomes def,
	 * and def becomes att. Also applies all status ailments on turn
	 * end and trainer cards also. Sets all can evolve also (IGNORING POKEMON POWER
	 * OF AERODACTYL!!!)
	 */
	public void nextTurn(){
		for (int i = att.getActivePokemon().getStatus().size() - 1; i >= 0; i--) {
			Status s = att.getActivePokemon().getStatus().get(i);
			s.turnUsToOpp();
		}
		for (int i = def.getActivePokemon().getStatus().size() - 1; i >= 0; i--) {
			if (def.getActivePokemon().getStatus().size() == 0) break;
			Status s = def.getActivePokemon().getStatus().get(i);
			s.turnOppToUs();
		}
		for (int i = att.getActivePokemon().getTrainerCards().size() - 1; i >= 0; i--) {
			TrainerCard s = att.getActivePokemon().getTrainerCards().get(i);
			s.turnUsToOpp();
		}
		for (int i = def.getActivePokemon().getTrainerCards().size() - 1; i >= 0; i--) {
			TrainerCard s = def.getActivePokemon().getTrainerCards().get(i);
			s.turnOppToUs();
		}
		// Resets that both can now attach energy cards.
		att.setCanAttachEnergy();
		def.setCanAttachEnergy();
		Player temp = att;
		att = def;
		def = temp;
		att.getHand().addCard(att.getDeck().drawCard());
		// Set all evolve
		att.getActivePokemon().setCanEvolve();
		def.getActivePokemon().setCanEvolve();
		for (ActivePokemonCard apc : att.getBench().getBench()) {
			apc.setCanEvolve();
		}
		for (ActivePokemonCard apc : def.getBench().getBench()) {
			apc.setCanEvolve();
		}
	}
	
	/**
	 * Knocks out the active pokemon:
	 * Sets att to be null.
	 * Adds att and all cards attached to discard pile.
	 * MAYBE ask def to pick out a card.
	 */
	public void knockOutAttPokemon(){
		//att.getDiscardPile().addCard(att.getActivePokemon());
		for (TrainerCard c : att.getActivePokemon().getTrainerCards()) {
			att.getDiscardPile().addCard(c);
		}
		// Now convert att activePokemon to simple a pokemon card, adding it to discard pile.
		PokemonCard p = att.getActivePokemon();
		att.getDiscardPile().addCard(p);
		// Also add preevolutions of that card.
		for (PokemonCard c : att.getActivePokemon().getForms()) {
			att.getDiscardPile().addCard(c);
		}
		
		// ALSO ASK PLAYER TO CHOOSE ACTIVE POKEMON?
		att.setActivePokemon(null);
		// TODO: ASK DEF PICK OUT PRIZE CARD>? HERE OR NOT
	}
	
	// ALSO HAVE KNOCKOUTDEFPOKEMON.
	public void knockOutDefPokemon(){
		//att.getDiscardPile().addCard(att.getActivePokemon());
		for (TrainerCard c : def.getActivePokemon().getTrainerCards()) {
			def.getDiscardPile().addCard(c);
		}
		// Now convert att activePokemon to simple a pokemon card, adding it to discard pile.
		PokemonCard p = def.getActivePokemon();
		def.getDiscardPile().addCard(p);
		// Also add preevolutions of that card.
		for (PokemonCard c : def.getActivePokemon().getForms()) {
			def.getDiscardPile().addCard(c);
		}
		
		// ALSO ASK PLAYER TO CHOOSE ACTIVE POKEMON?
		def.setActivePokemon(null);
		// TODO: ASK DEF PICK OUT PRIZE CARD>? HERE OR NOT
	}
	
	public ActivePokemonCard getAtt(){
		return att.getActivePokemon();
	}
	
	public ActivePokemonCard getDef(){
		return def.getActivePokemon();
	}
	
	public Deck getAttDeck(){
		return att.getDeck();
	}
	
	public Deck getDefDeck(){
		return def.getDeck();
	}
	
	public Hand getAttHand(){
		return att.getHand();
	}
	
	public Hand getDefHand(){
		return def.getHand();
	}
	
	public void setAttPokemon(ActivePokemonCard apc){
		att.setActivePokemon(apc);
	}
	
	public void setDefPokemon(ActivePokemonCard apc){
		def.setActivePokemon(apc);
	}

	/**
	 * APPLIES DAMAGE TO BOTH ATT AND DEF. NEED CHECK AFTER THIS OUTSIDE IF ARE KNOCKED OUT
	 * ALSO CALCULATES COINFLIPS AND APPLIES EFFECTS TO BOTH DEF AND ATT, IF ANY. LIKE POSION< SLEEP ETC.
	 * @return the damage dealt as an int
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public int doMove(PokemonMove m) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		
		System.out.println("-----\n" + att.getActivePokemon() + " is doing move: " + m.getName());
		
		// REMEMBER: DOES NOT DEAL ADAMAGE TO OPP POKEMON YET!! COULD CHANGE?
		
		int baseDamage = m.getMoveDamageOpp();
		// Firstly: if baseDamage = 0, then the move does no damage. So we return 0
		// BUT CHECK: IF M HAS AN DAMAGE_OPP IN MOVE. USUALLY IN COIN FLIPS, BUT ALSO COOD BE ADDITONAL EFFECTS.
		
		/*String aEffects[] = m.getAdditionalEffects();
		for (int i = 0 ; i < aEffects.length; i++) {
			if(aEffects[i].equals("DAMAGE_OPP")) {
				baseDamage += Integer.parseInt(m.getAdditionalEffectsAssoc(i));
			}
		}*/
		
		// Check for COINS.
		int cFlips = m.getCoinFlips();
		System.out.println("There are " + cFlips + " coins to flip");
		// When head flip.
		String ghf[] = m.getHeadFlip();
		String gtf[] = m.getTailFlip();
		// Perform flips. DO TAIL FLIP HERE ALSO!!
		for (int i = 0 ; i < cFlips; i++) {
			boolean heads = RNG.flipCoin();
			if (heads) {
				System.out.println("Flipped heads");
				for (int k = 0 ; k < ghf.length; k++) {
					// Apply effects:
					String eName = ghf[k];
					System.out.println("Doing effect: " + eName);
					System.out.println("Additional head flip effect? " + m.getHeadFlipEffect(k));
					if (eName.equals("DAMAGE_OPP")) {
						// DAMAGE_OPP WE DO SPECIAL HANDLINGHERE.
						baseDamage+=Integer.parseInt(m.getHeadFlipEffect(k));
						continue;
					}
					// HERE WE USE GETHEADFLIPEFFECT: FOR OTHERS USE TAIL EFFECT AND ADDTIONAL EFFECTS
					for (Class<? extends MoveEffect> enl : CEList.getMoveEffects()) {
						MoveEffect me = enl.getDeclaredConstructor(Arena.class).newInstance(this);
						if (me.mEffectName().equals(eName)) {
							// SET ME STUFF HERE, LIKE ATT AND DEF.
							if (me.requireAdditionalEffects()) {
								// Get additional effect association: which is k.
								String addEffect = m.getHeadFlipEffect(k);
								me.setAdditionalEffects(addEffect);
							}
							me.whenPlayed();
						}
					}
				}
			} else {
				System.out.println("Flipped tails");
				for (int k = 0 ; k < gtf.length; k++) {
					// Apply effects:
					String eName = gtf[k];
					System.out.println("Doing effect: " + eName);
					System.out.println("Additional tail flip effect? " + m.getTailFlipEffect(k));
					if (eName.equals("DAMAGE_OPP")) {
						// DAMAGE_OPP WE DO SPECIAL HANDLINGHERE.
						baseDamage+=Integer.parseInt(m.getTailFlipEffect(k));
						continue;
					}
					// HERE WE USE GETHEADFLIPEFFECT: FOR OTHERS USE TAIL EFFECT AND ADDTIONAL EFFECTS
					for (Class<? extends MoveEffect> enl : CEList.getMoveEffects()) {
						MoveEffect me = enl.getDeclaredConstructor(Arena.class).newInstance(this);
						if (me.mEffectName().equals(eName)) {
							// SET ME STUFF HERE, LIKE ATT AND DEF.
							if (me.requireAdditionalEffects()) {
								// Get additional effect association: which is k.
								String addEffect = m.getTailFlipEffect(k);
								me.setAdditionalEffects(addEffect);
							}
							me.whenPlayed();
						}
					}
				}
			}
		}
		
		if (baseDamage == 0) return 0; //TODO: and return a flag? So for example: recover doesn't use pluspower. can check elsewhere?
		
		int totalDamage = baseDamage;
		// Check if att is affected by swords dance? and also stuff like:
		// snivel for example, which reduces damage... or agility etc?
		// FOR PLUSPOWER AND DEFENDER: NEED CHECK AFTER ALL THIS: SO INTRODUCE
		// AN ATT.GETTRAINERCARDSATTACHED ETC..
		// AND WATER GUN ETC MORE ENERGY CARDS>. ERR MA GERD
		// USE MOVE.GETADDITIONALEFFECTS? ETC.. OR ATTACKMODIFIER CAN SPECIFY:
		// APPLY TO BASE, OR AFTER RESISTANCE AND WEAKNESS< OR AT THE END (PLUSPOWER) ETC.
		m.getCoinFlips();
		
		for (Status s : att.getActivePokemon().getStatus()) {
			if (s.attackModifiers().getLocation() == 0) {
				totalDamage *= s.attackModifiers().getAttackMultiplyer(); // like swords dance
				totalDamage += s.attackModifiers().getAttackIncrease(); //don't think there are really any that has this
				totalDamage /= s.attackModifiers().getDefenseMultiplyer(); // don't think there are any either
				totalDamage -= s.attackModifiers().getDefenseReduction();
			}
		}
		
		for (Status s : def.getActivePokemon().getStatus()) {
			if (s.attackModifiers().getLocation() == 0) {
				totalDamage /= s.attackModifiers().getDefenseMultiplyer(); // don't think there are any either
				totalDamage -= s.attackModifiers().getDefenseReduction();
			}
		}
		
		for (Status s : att.getActivePokemon().getStatus()) {
			if (s.attackModifiers().getLocation() == 1) {
				totalDamage *= s.attackModifiers().getAttackMultiplyer(); // 
				totalDamage += s.attackModifiers().getAttackIncrease(); //
			}
		}
		
		for (Status s : def.getActivePokemon().getStatus()) {
			if (s.attackModifiers().getLocation() == 1) {
				totalDamage /= s.attackModifiers().getDefenseMultiplyer(); // don't think there are any either
				totalDamage -= s.attackModifiers().getDefenseReduction();
			}
		}
		
		String attType = att.getActivePokemon().getType();
		//String defType = def.getType();
		String weak = def.getActivePokemon().getWeakness();
		String resist = def.getActivePokemon().getResistance();
		if (weak != null && weak.equals(attType)) {
			totalDamage = totalDamage*2;
		}
		if (resist != null && resist.equals(attType)) {
			totalDamage = totalDamage - 30;
		}
		if (totalDamage < 0) totalDamage = 0;
		// Apply pluspower or defender here?
		
		for (Status s : att.getActivePokemon().getStatus()) {
			if (s.attackModifiers().getLocation() == 2) {
				totalDamage *= s.attackModifiers().getAttackMultiplyer(); // like swords dance
				totalDamage += s.attackModifiers().getAttackIncrease(); //don't think there are really any that has this
			}
		}
		
		for (Status s : def.getActivePokemon().getStatus()) {
			if (s.attackModifiers().getLocation() == 2) {
				totalDamage /= s.attackModifiers().getDefenseMultiplyer(); // don't think there are any either
				totalDamage -= s.attackModifiers().getDefenseReduction();
			}
		}
		
		def.getActivePokemon().addDamage(totalDamage);
		//checkArena();
		return totalDamage;
	}
	
	public boolean attActDead = false;
	public boolean defActDead = false;
	public int attDead = 0;
	public int defDead = 0;
	
	/**
	 * Checks the arena for any dead pokemon. Sets public variables
	 * attActDead, defActDead, attDead and defDead.
	 */
	public void checkArena() {
		attDead = 0;
		defDead = 0;
		attActDead = false;
		defActDead = false;
		// Check att and def:
		if (att.getActivePokemon().isDead()) {
			attDead++;
			attActDead = true;
		}
		if (def.getActivePokemon().isDead()) {
			defDead++;
			defActDead = true;

		}
		for (ActivePokemonCard apc : att.getBench().getBench()) {
			if (apc.isDead()) {
				attDead++;
			}
		}
		for (ActivePokemonCard apc : def.getBench().getBench()) {
			if (apc.isDead()) {
				defDead++;
			}
		}
	}
	public void clearCheckArena() {
		attDead = 0;
		defDead = 0;
		attActDead = false;
		defActDead = false;
	}
	
	public void setPokePowerStage(PokePower.PowerStage ps) {
		PokePower.curStage = ps;
	}

	public void checkPowers(Object c) throws InstantiationException, IllegalAccessException {
		if (att.getActivePokemon().getPokePowerName() != null) {
			for (Class<? extends PokePower> pp : CEList.getPokePowers()) {
				PokePower p = pp.newInstance();
				if (p.getName().equals(att.getActivePokemon().getPokePowerName())) { 
					if (!p.activated()) {
						// Power doesn't have to be activated for it to work:
						// so we use it now:
						p.effect(c);
					}
				}
			}
		}
		if (def.getActivePokemon().getPokePowerName() != null) {

		}
		for (ActivePokemonCard apc : att.getBench().getBench()) {
			if (apc.getPokePowerName() != null) {
				for (Class<? extends PokePower> pp : CEList.getPokePowers()) {
					PokePower p = pp.newInstance();
					if (p.getName().equals(att.getActivePokemon().getPokePowerName())) { 
						if (!p.activated()) {
							// Power doesn't have to be activated for it to work:
							// so we use it now:
							p.effect(c);
						}
					}
				}
			}
		}
		for (ActivePokemonCard apc : def.getBench().getBench()) {
			if (apc.getPokePowerName() != null) {

			}
		}
	}


}
