package cardAbstract;

import java.util.List;

public class NullCard extends Card {

	private static final NullCard _instance = new NullCard();

	private NullCard() {
		super(-1);
	}
	
	public static NullCard getInstance() {
		return _instance; 
	}

	@Override
	public Card copyCard() {
		return this;
	}

	@Override
	public String getName() {
		return "";
	}

	@Override
	public String addInfoName() {
		return "";
	}
	
	public static boolean isNullList(List<Card> cl) {
		return cl.size() == 1 && cl.get(0) == NullCard.getInstance();
	}

}
