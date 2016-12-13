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
	
	// TODO: IMPLEMENT ATTACHING TRAINER CARDS
	private List<TrainerCard> tCards; // current trainer cards attached
	private List<EnergyCard> eCards; // current energy cards
	
	private List<ActivePokemonCard> forms; // evolved forms.
	
	private Player player;
	
	public ActivePokemonCard(PokemonCard p, int id) {
		super(p.getName(), p.getLevel(), p.desc, id);
		this.curEnergy = new ArrayList<String>();
		statuses = new ArrayList<Status>();
		tCards = new ArrayList<TrainerCard>();
		forms = new ArrayList<ActivePokemonCard>();
		eCards = new ArrayList<EnergyCard>();
		//forms.add(this);
	}
	
	public void setPlayer(Player p) {
		this.player = p;
	}
	
	/**
	 * Returns a new ActivePokemonCard where it is the evolved form.
	 * All statuses are removed, trainers cards are not. forms is also updated and copied over.
	 * HP is also copied over.
	 */
	public ActivePokemonCard evolve(PokemonCard p) {
		ActivePokemonCard newApc = new ActivePokemonCard(p,p.getId());
		newApc.forms = this.forms;
		newApc.forms.add(this);
		newApc.damage = this.damage;
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
	
	public List<ActivePokemonCard> getForms(){
		return forms;
	}
	
	public List<EnergyCard> getEnergyCards(){
		return eCards;
	}
	
	public void addStatus(Status s) {
		// CHECK IF SHOULD STACK.
		
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
	
	public void removeStatus(Status s){
		statuses.remove(s);
	}
	
	public List<Status> getStatus(){
		return statuses;
	}
	
	public void addTrainer(TrainerCard s) {
		if (s.attachable()) {
			tCards.add(s);
			s.whenPlayed();
		} else {
			s.whenPlayed();
		}
	}
	
	public void attachEnergy(EnergyCard e){
		eCards.add(e);
		this.curEnergy.add(e.energyType());
		e.whenPlayed();
	}
	
	// Adds to discard pile.
	public void removeTrainer(TrainerCard s){
		tCards.remove(s);
		player.getDiscardPile().addCard(s);
	}
	
	public List<TrainerCard> getTrainerCards(){
		return tCards;
	}
	
	public boolean isAsleep(){
		for(Status s : statuses) {
			if (s instanceof statuses.Sleep) {
				return true;
			}
		}
		return false;
	}
	
	
	public boolean isDead(){
		return damage >= this.getMaxHp();
	}
	
	public void addEnergyString(String e){
		curEnergy.add(e);
	}
	
	public void addDamage(int damage){
		this.damage += damage;
		if (this.damage < 0) {
			this.damage = 0; 
		}
	}
	
	public void setDamage(int damage){
		this.damage = damage;
		if (this.damage < 0) {
			this.damage = 0; 
		}
	}
	
	public int getDamage(){
		return damage;
	}
	
	public boolean canPerformMove(PokemonMove mv){
		// First check if this card even has that move:
		if (mv == null) {
			return false;
		}
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
			if (s.canAttack() == false) return false;
		}
		//TODO: SINCE CONFUSION CAN KILL: MAKE SURE CHECK
		
		return (us.length() >= oe.length());
	}
	
	public String getEnergyString() {
		String k = "";
		for (String s : curEnergy) {
			k += s;
		}
		return k;
	}
	
	/**
	 * CLears this card of all status, attached cards, hp...
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
	
	
}
