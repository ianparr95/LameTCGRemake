package cardAbstract;

/**
 * All cards have an unique id.
 * Initial value of a card is zero.
 */
public abstract class Card {
	
	private int id;
	
	public Card(int id) {
		this.id = id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
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
	
	public abstract Card copyCard();
	
	public abstract String getName();
	
	// additional info about the card.
	public abstract String addInfoName();
	
}
