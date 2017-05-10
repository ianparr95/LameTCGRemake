package cardAbstract;

/**
 * All cards have an unique id.
 * Initial value of a card is zero.
 */
public abstract class Card {
	
	private int id;
	
	/**
	 * Initializes a card with specified id
	 * @param id, the unique id of the card
	 */
	public Card(int id) {
		this.id = id;
	}
	
	/**
	 * Sets the id of this card
	 * @param id, the unique id of the card
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Get the id of this card
	 * @return the unique id of this card
	 */
	public int getId(){
		return id;
	}
	
	public int hashCode(){
		return id;
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof Card)) return false;
		Card c = (Card) o;
		return (c.id == this.id);
	}
	
	/**
	 * Copy the current card.
	 * @return a copy this card
	 */
	public abstract Card copyCard();
	
	/**
	 * Get the name of this card.
	 * @return the name of this card.
	 */
	public abstract String getName();
	
	/**
	 * Get additional info about the card.
	 * Etc: information about what potion does, for example.
	 * @return a String about additional info of this card.
	 */
	public abstract String addInfoName();
	
}
