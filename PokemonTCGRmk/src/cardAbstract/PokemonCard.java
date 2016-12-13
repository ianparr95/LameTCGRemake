package cardAbstract;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PokemonCard extends Card{

	/**
	 * A pokemon card is associated via its:
	 * name, level and description.
	 * The description is read in from the pokemon.txt file and
	 * contains all the data about the card via tags like:
	 * [name]Pikachu
	 * [level]12
	 * [mname]Gnaw
	 * etc..
	 * 
	 * Note that pokemon cards are different from energy and trainer cards.
	 */
	
	private String name;
	private String level;
	protected String desc;
	
	private String[] moveNames;
	private PokemonMove[] moves;
	
	private int hp = -1;
	
	// This initially is set at false cause when you place a card on the bench
	// can't evolve it immediately. 
	protected boolean canEvolve = false; 
	
	/**
	 * Should be used using ParsePokemonCardsFile's function: getPokemonCard
	 * This creates a pokemon card with the associated card description string
	 * @param name
	 * @param level
	 * @param cardDes: cardDescription, should be read in from ParsePokemonCardsFile
	 */
	public PokemonCard(String name, String level, String cardDes, int id) {
		super(id);
		this.name = name;
		this.level = level;
		this.desc = cardDes;
	}
	
	/**
	 * Get the name of the card
	 * @return the name of the card
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the level of the card
	 * @return the level of the card, as a string.
	 */
	public String getLevel() {
		return level;
	}
	
	/**
	 * Get the list of moves this card has.
	 * @return string[] of names of moves this card has.
	 */
	public String[] getMoveNames() {
		// First get number of [mname] there are.
		if (moveNames == null) {
			String dMoves = desc;
			int loc = dMoves.indexOf("[mname]");
			List<String> lList = new ArrayList<String>(2);
			while (loc >= 0) {
				dMoves = dMoves.substring(loc + 7);
				loc = dMoves.indexOf("[mname]");
				lList.add(dMoves.substring(0, dMoves.indexOf("[")));
			}
			String[] r = new String[lList.size()];
			int j = 0;
			for (String k : lList) {
				r[j] = k;
				j++;
			}
			this.moveNames = r;
			return r;
		} else {
			return moveNames;
		}
	}
	
	/**
	 * Get the max hp of this card.
	 * @return the maximum hp of this card.
	 */
	public int getMaxHp() {
		if (hp == -1) {
			hp = Integer.parseInt(getAssoc("[hp]"));
		}
		return hp;
	}
	
	/**
	 * Get the image string associated with card
	 * eg: pikachu level 12 could be pik12.jpg.
	 * @return the image associated with card
	 */
	public String getImage(){
		return getAssoc("[img]");
	}
	
	/**
	 * Get the evolution this pokemon is from
	 * @return the name of the pokemon it evolves from,
	 * or b, for base.
	 */
	public String getEvol(){
		return getAssoc("[evol]");
	}
	
	/**
	 * Get the stage of the pokemon
	 * @return the stage of the pokemon
	 */
	public String getStage(){
		return getAssoc("[stage]");
	}
	
	/**
	 * Get the type of the pokemon
	 * @return the type of the pokemon
	 */
	public String getType(){
		return getAssoc("[type]");
	}
	
	/**
	 * Get the resistance of the pokemon
	 * @return the resistance of the pokemon
	 */
	public String getResistance(){
		return getAssoc("[resist]");
	}
	
	/**
	 * Get the weakness of the pokemon
	 * @return the weakness of the pokemon
	 */
	public String getWeakness(){
		return getAssoc("[weak]");
	}
	
	/**
	 * Get the retreat cost of the pokemon
	 * @return the retreat cost of the pokemon
	 */
	public String getRCost(){
		return getAssoc("[rcost]");
	}
	
	public String getPokePowerName(){
		return getAssoc("[powername]");
	}
	
	public String getPokePowerType(){
		return getAssoc("[ptype]");
	}
	
	public boolean isBasicPokemon(){
		String stage = getAssoc("[evol]");
		return stage.equals("b");
	}
	
	public PokemonMove getMove(String moveName) {
		PokemonMove r = null;
		if (moves == null) {
			String[] mn = getMoveNames();
			moves = new PokemonMove[mn.length];
			for (int i = 0 ; i < moves.length; i++) {
				moves[i] = new PokemonMove(mn[i], getMoveBlock(mn[i]));
				if (moves[i].getName().equals(moveName)) {
					r = moves[i];
				}
			}
		} else {
			for (int i = 0 ; i < moves.length; i++) {
				if (moves[i].getName().equals(moveName)) {
					r = moves[i];
				}
			}
		}
		
		return r;
	}
	
	
	/**
	 * Get the moveBlock associated with the specified move.
	 * The moveBlock is basically all the tags about effects of the move.
	 * @param moveName the name of the move
	 * @return the string (move block) representing the move.
	 */
	private String getMoveBlock(String moveName) {
		if (!desc.contains("[mname]" + moveName + "[")) {
			return null;
		} else {
			String nDesc = desc.substring(desc.indexOf("[mname]" + moveName) + ("[mname]" + moveName).length());
			int next = nDesc.indexOf("[mname]");
			// get the substring of the move, then check if there is a another move.
			// if not, then return, else substring.
			if (next == -1) {
				return nDesc;
			} else {
				return nDesc.substring(0, next);
			}
		}
	}
	
	private String getAssoc(String tag) {
		int tLoc = desc.indexOf(tag);
		if (tLoc < 0) {
			return null;
		}
		int mDamageLoc = tLoc + tag.length();
		String rs = desc.substring(mDamageLoc);
		int nextStop = rs.indexOf("[");
		if (nextStop == -1) {
			return rs;
		} else {
			return rs.substring(0, nextStop);
		}
	}
	
	public boolean canEvolve(){
		return canEvolve;
	}
	
	public void setCanEvolve(){
		canEvolve = true;
	}
	
	/**
	 * Returns true if the card specified is an evolution card
	 * of this pokemon.
	 */
	public boolean isEvolutionCard(PokemonCard c){
		String cEvol = c.getAssoc("[evol]");
		return cEvol.equals(this.name);
	}
	
	@Override
	public String toString() {
		return name + " level: " + level;
	}

	@Override
	public Card copyCard() {
		PokemonCard c = new PokemonCard(name, level, desc, this.getId());
		c.hp = hp;
		c.canEvolve = canEvolve;
		if (c.moveNames!=null) {
			c.moveNames = Arrays.copyOf(moveNames, moveNames.length);
		}
		if (c.moves != null) {
			c.moves = Arrays.copyOf(moves, moves.length);
		}
		return c;
	}

	@Override
	public String addInfoName() {
		return "Level: " + level;
	}

}
