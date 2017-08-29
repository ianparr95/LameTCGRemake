package cardAbstract;
import java.util.ArrayList;
import java.util.List;

import arena.Player;
import statuses.Poisoned;
import statuses.Status;
import statuses.Toxic;

public class ActivePokemonCard extends PokemonCard{
	
	private List<String> curEnergy; // current energies attached
	// CURRENT STATUSES INFLICTED ON US.
	private List<Status> statuses;
	private int damage = 0; // current damage dealt to our pokemon
	
	private List<TrainerCard> tCards; // current trainer cards attached
	private List<EnergyCard> eCards; // current energy cards
	
	private List<ActivePokemonCard> forms; // evolved forms.
	
	private Player player;
	
	/**
	 * Creates a new ActivePokemonCard from the specified PokemonCard,
	 * and sets the id and owning player.
	 * @param p, the PokemonCard to be promoted to an ActivePokemonCard
	 * @param id, the id of the card
	 * @param player, the owning player.
	 */
	public ActivePokemonCard(PokemonCard p, int id, Player player) {
		super(p.getName(), p.getLevel(), p.desc, id);
		this.curEnergy = new ArrayList<String>();
		statuses = new ArrayList<Status>();
		tCards = new ArrayList<TrainerCard>();
		forms = new ArrayList<ActivePokemonCard>();
		eCards = new ArrayList<EnergyCard>();
		this.player = player;
		//forms.add(this);
	}
	
	/**
	 * Sets the owning player
	 * @param p, the player to be set as the owner.
	 */
	public void setPlayer(Player p) {
		this.player = p;
	}
	/**
	 * Returns true if this can evolve to p.
	 * @param p, the card to be checked.
	 * @return true, if this card can evolve to pokemon p.
	 */
	public boolean canEvolveTo(PokemonCard p) {
		return p.getEvol().equals(this.getName());
	}
	
	/**
	 * Returns a new ActivePokemonCard where it is the evolved form of this card.
	 * Does not do any checking on whether we can actually evolve to p.
	 * All curable statuses are removed, trainers cards are not. Forms are also updated and copied over.
	 * HP is also copied over. Does not update bench or active pokemon. So the user needs to
	 * remove from the bench/active the current ActivePokemonCard, and replace it with the
	 * ActivePokemonCard returned from here.
	 * Also clears the current card of all statuses/damage/attached cards.
	 * @param p, the card to be evolved to.
	 * @return A new ActivePokemonCard which is the evolved form of this card.
	 */
	public ActivePokemonCard evolve(PokemonCard p) {
		ActivePokemonCard newApc = new ActivePokemonCard(p,p.getId(), player);
		newApc.forms = this.forms;
		newApc.forms.add(this);
		newApc.damage = this.damage;
		newApc.canEvolve = false;
		this.damage = 0;
		for (TrainerCard t : tCards) {
			newApc.tCards.add(t);
		}
		this.tCards.clear();
		for (EnergyCard t : eCards) {
			newApc.eCards.add(t);
		}
		this.eCards.clear();
		for (String e : curEnergy) {
			newApc.addEnergyString(e);
		}
		this.curEnergy.clear();
		//Clear statuses that are clearable?
		for (int i = statuses.size() - 1; i >= 0; i--) {
			if(statuses.get(i).curable()) {
				statuses.remove(i);
			}
		}
		for (Status s : statuses) {
			newApc.statuses.add(s);
		}
		this.statuses.clear();
		return newApc;
	}
	
	/**
	 * Get forms of this card, which are the cards we
	 * evolved from.
	 * @return A list of forms of this card.
	 */
	public List<ActivePokemonCard> getForms(){
		return forms;
	}
	
	
	public List<EnergyCard> getEnergyCards(){
		return eCards;
	}
	
	/**
	 * Adds status s to this card.
	 * Will replace Poisoned with Toxic if found. This is only case
	 * of special handling.
	 * Does not add status if unable to stack.
	 * @param s, status to add.
	 */
	public void addStatus(Status s) {
		// CHECK IF SHOULD STACK.
		// TODO: fix poisoned/toxic in the future.
		//ALSO CHECK IF HAVE POISONED AND TOXIC: ONLY USE TOXIC. POISONED NO!!
		if (s instanceof Poisoned) {
			// Check if toxic exists: if so don't do anything.
			for (Status s2 : statuses) {
				if (s2 instanceof Toxic) {
					return;
				}
			}
		} else if (s instanceof Toxic) {
			// If toxic : remove poison.
			for (int i = statuses.size() - 1; i >= 0; i--) {
				Status s2 = statuses.get(i);
				if (s2 instanceof Poisoned) {
					statuses.remove(s2);
				}
			}
		}
		
		if (s.canStack()) {
			statuses.add(s);
		} else {
			// can't stack: check if statuses contains an instance of s.
			for (Status s2 : statuses) {
				Class<? extends Status> k = s2.getClass();
				if (k.isInstance(s)) {
					return;
				}	
			}
			statuses.add(s);
		}

	}
	
	/**
	 * Removes status s from this card.
	 * Equivalent to getStatus().remove(s).
	 * @param s, the status to be removed.
	 */
	public void removeStatus(Status s){
		statuses.remove(s);
	}
	
	/**
	 * Returns a list of statuses for this card.
	 * @return A list of statuses for this card.
	 */
	public List<Status> getStatus(){
		return statuses;
	}
	
	/**
	 * If the card is attachable, attaches it to this card.
	 * Does not call whenPlayed or any other method.
	 * @param s, the card to attach.
	 */
	public void addTrainer(TrainerCard s) {
		if (s.attachable()) {
			tCards.add(s);
		}
	}
	
	/**
	 * Attaches the energy to this pokemon.
	 * Does not call whenPlayed or any other method.
	 * @param e, the energy card to attach.
	 */
	public void attachEnergy(EnergyCard e){
		eCards.add(e);
		this.curEnergy.add(e.energyType());
	}
	
	/**
	 * Removes the specified TrainerCard from this card.
	 * Adds this card to the discard pile.
	 * @param s, the card to remove to the discard pile.
	 */
	public void removeTrainerToDiscardPile(TrainerCard s){
		tCards.remove(s);
		player.getDiscardPile().addCard(s);
	}
	
	/**
	 * Returns a list of attached trainer cards.
	 * @return a list of attached trainer cards.
	 */
	public List<TrainerCard> getTrainerCards(){
		return tCards;
	}
	
	/**
	 * Returns true if this card is affected by sleep
	 * @return True, if this card is affected by sleep.
	 */
	public boolean isAsleep(){
		for(Status s : statuses) {
			if (s instanceof statuses.Sleep) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns true if this card is affected by confusion
	 * @return True, if this card is affected by confusion.
	 */
	public boolean isConfused(){
		for(Status s : statuses) {
			if (s instanceof statuses.Confused) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns true if this card is dead
	 * @return True, if this is dead.
	 */
	public boolean isDead(){
		return damage >= this.getMaxHp();
	}
	
	/**
	 * Adds the energy string to this card
	 * @param e, the energy string to add.
	 */
	public void addEnergyString(String e){
		curEnergy.add(e);
	}
	
	/**
	 * Adds the specified damage to this card.
	 * HP does not go below 0 or above max hp.
	 * @param damage, the damage to add.
	 */
	public void addDamage(int damage){
		this.damage += damage;
		if (this.damage < 0) {
			this.damage = 0; 
		}
		if (this.damage > this.getMaxHp()) {
			this.damage = this.getMaxHp();
		}
	}
	
	/**
	 * Sets the current damage on this card.
	 * Damage does not go below zero or above
	 * the max hp of this card.
	 * @param damage, the amount to set damage as.
	 */
	public void setDamage(int damage){
		this.damage = damage;
		if (this.damage < 0) {
			this.damage = 0; 
		}
		if (this.damage > this.getMaxHp()) {
			this.damage = this.getMaxHp();
		}
	}
	
	/**
	 * Gets the damage on this card.
	 * @return the amount of damage on this card.
	 */
	public int getDamage(){
		return damage;
	}
	
	/**
	 * Checks if we can perform the specified move.
	 * Does the following checks:
	 * 1. Is mv null?
	 * 2. Is mv's energycost not null?
	 * 3. Are we affected by a status so that we can't perform the move?
	 * 4. Does this card have the correct energy cards to perform the move?
	 * Does not do confusion checking. That should be done elsewhere,
	 * since confusion always returns true, so we should check if this card
	 * is confused.
	 * @param mv, the move to check if this card can perform.
	 * @return True, if this card can perform the move.
	 */
	public boolean canPerformMove(PokemonMove mv){
		if (mv == null) {
			return false;
		}
		
		// Assume that the card has the move... MAYBE FIX? TODO
		
		// We have the move: check energy cards.
		if (mv.getEnergyCost() == null) {
			System.err.println("ERROR WITH ENERGY COST FOR MOVE " + mv.getName() + " FOR POKEMON: " + this.getName() + " LEVEL: " + this.getLevel());
			return false;
		}
		// Now check energy type:
		StringBuilder us = new StringBuilder("");
		for (String s : this.curEnergy) {
			us.append(s);
		}
		if (us.length() < mv.getEnergyCost().length()) {
			return false; // if we have less energy cards than needed, false
		}
		
		// Check everything except colorless first:
		StringBuilder oe = new StringBuilder(mv.getEnergyCost());
		
		int p = 0;
		while(true) {
			if (oe.length() == 0) {
				break;
			}
			// Check if only contains c.
			int ll = 0;
			for (ll = 0 ; ll < oe.length(); ll++) {
				if (oe.charAt(ll) != 'c') break;
			}
			if (ll == oe.length()) {
				break; // only c's left. We know our energy >= move energy. So good to break.
			}
			lstart:
			if(oe.charAt(p) == 'c') {
				p++;
				continue;
			} else {
				// not colorless: check if us contains that char.
				for (int i = 0 ; i < us.length(); i++) {
					if (us.charAt(i) == oe.charAt(p)) {
						// Good: can remove.
						us.deleteCharAt(i);
						oe.deleteCharAt(p);
						p = 0;
						break lstart;
					}
				}
				return false; // don't contain
			}
		}
		
		// Now check opponents effects: (like tail wag, paralysis, sleep etc...)
		/* CHECKS HERE */
		// TODO
		for (Status s : statuses) {
			if (s.canAttack() == false) {
				return false;
			}
		}
		//TODO: SINCE CONFUSION CAN KILL: MAKE SURE CHECK
		
		return (us.length() >= oe.length());
	}
	
	/**
	 * Get the energy string of this card.
	 * @return the energy string of this card.
	 */
	public String getEnergyString() {
		String k = "";
		for (String s : curEnergy) {
			k += s;
		}
		return k;
	}
	
	/**
	 * Clears this card of all statuses, attached cards, health etc.
	 * Sets the card to be unable to evolve.
	 */
	public void clearCard(){
		this.curEnergy = new ArrayList<String>();
		statuses = new ArrayList<Status>();
		tCards = new ArrayList<TrainerCard>();
		forms = new ArrayList<ActivePokemonCard>();
		eCards = new ArrayList<EnergyCard>();
		canEvolve = false;
		this.damage = 0;
	}

	public void clearStatuses() {
		statuses.clear();
	}
	
	
}
