package cardAbstract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import misc.CardFilter;

public class CardRequest extends Throwable{

	private boolean self;
	private List<Card> rList;
	private List<Card> chooseableList;
	private String message;
	private List<Card> displayedList;
	private int num;
	private int mode;
	private boolean canr;
	
	// TODO: ternary if exactly num, less than, greater than etc..
	// or even if allowzero cards tobe returned etc.
	/**
	 * Create a new card request.
	 * @param self if true, then we request a card. Else opponent
	 * @param num number of cards to request
	 * @param displayedList the list of cards to display
	 * @param chooseableList the list of cards that are chooseable
	 * @param msg associated string (can be anything)
	 * @param mode, if 0, then less than equal num cards can be chosen.
	 * 		        if 1, then exactly num cards must be chosen.
	 * 				if 2, then num or more cards may be chosen.
	 * 				!!This may not be important!!
	 * @param canRepeat, if true, user can repeat picked cards. if false, no.
	 */
	public CardRequest(boolean self, int num, List<Card> displayedList, List<Card>
					  chooseableList , String msg, int mode, boolean canRepeat) {
		rList = new ArrayList<Card>();
		this.self = self;
		this.chooseableList = chooseableList;
		this.displayedList = displayedList;
		message = msg;
		this.num = num;
		this.canr = canRepeat;
	}
	
	public String abc = "lol";
	
	public int getNumberCardsRequest() {
		return num;
	}
	
	public void requestCard() throws CardRequest {
		throw this;
	}
	
	public List<Card> getChooseableList() {
		return Collections.unmodifiableList(chooseableList);
	}
	
	public List<Card> getDisplayList() {
		return Collections.unmodifiableList(displayedList);
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
	
	public int getMode(){
		return mode;
	}
	
	public boolean canRepeatPickedCard() {
		return canr;
	}
	
}
