package cardAbstract;

import java.util.Collections;
import java.util.List;

import misc.CardFilter;

public class CardRequest extends Throwable{

	private boolean self;
	private Card[] rList;
	private List<Card> fromList;
	private String message;
	private CardFilter cfilter;
	
	public CardRequest(boolean self, int num, List<Card> lc, String msg, CardFilter cfilter) {
		rList = new Card[num];
		this.self = self;
		fromList = lc;
		message = msg;
		this.cfilter = cfilter;
	}
	
	public void requestCard() throws CardRequest {
		throw this;
	}
	
	public List<Card> getRequestList() {
		return Collections.unmodifiableList(fromList);
	}
	
	/**
	 * Returns true if request cards from our self,
	 * false if we need to pass this along to opponent
	 * to handle.
	 */
	public boolean isSelf() {
		return self;
	}
	
	@Override
	public String getMessage(){
		return message;
	}
	
	/**
	 * Returns the list of cards we should fill up.
	 * @return
	 */
	public Card[] getReturnList() {
		return rList;
	}
	
	/**
	 * The card type that we request
	 */
	public CardFilter getRequestFilter() {
		return cfilter;
	}
	
}
