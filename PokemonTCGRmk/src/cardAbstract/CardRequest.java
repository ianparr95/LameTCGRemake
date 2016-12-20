package cardAbstract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import misc.CardFilter;

public class CardRequest extends Throwable{

	private boolean self;
	private List<Card> rList;
	private List<Card> fromList;
	private String message;
	private CardFilter cfilter;
	private int num;
	
	
	// TODO: ternary if exactly num, less than, greater than etc..
	// or even if allowzero cards tobe returned etc.
	public CardRequest(boolean self, int num, List<Card> lc, String msg, CardFilter cfilter) {
		rList = new ArrayList<Card>();
		this.self = self;
		fromList = lc;
		message = msg;
		this.cfilter = cfilter;
		this.num = num;
	}
	
	public String abc = "lol";
	
	public int getNumberCardsRequest() {
		return num;
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
	public List<Card> getReturnList() {
		return rList;
	}
	
	/**
	 * The card type that we request
	 */
	public CardFilter getRequestFilter() {
		return cfilter;
	}
	
}
