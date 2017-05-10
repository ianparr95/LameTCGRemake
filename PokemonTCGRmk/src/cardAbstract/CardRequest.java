package cardAbstract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import misc.CardFilter;

@SuppressWarnings("serial")
public class CardRequest extends Throwable{

	private boolean self;
	private List<Card> rList;
	private List<Card> chooseableList;
	private String message;
	private List<Card> displayedList;
	private int num;
	private int mode;
	private boolean canr;
	
	/**
	 * Create a new card request.
	 * @param self, if true, then we request a card. Else opponent
	 * @param num, number of cards to request
	 * @param displayedList, the list of cards to display eg: entire deck.
	 * @param chooseableList, the list of cards that are chooseable eg: only basic pokemon.
	 * @param msg associated string (can be anything)
	 * @param mode, if 0, then less than equal num cards can be chosen.
	 * 		        if 1, then exactly num cards must be chosen.
	 * 				if 2, then num or more cards may be chosen.
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
	
	/**
	 * Get the number of cards requested
	 * @return the number of cards requested.
	 */
	public int getNumberCardsRequest() {
		return num;
	}
	
	/**
	 * Request a card. We throw this, and the catcher should
	 * call this class's methods to get information about
	 * the card we request.
	 * @throws CardRequest, throws this.
	 */
	public void requestCard() throws CardRequest {
		throw this;
	}
	
	/**
	 * Get the chooseable card list.
	 * @return an unmodifiable list of chooseable cards.
	 */
	public List<Card> getChooseableList() {
		return Collections.unmodifiableList(chooseableList);
	}
	
	/**
	 * Get the displayed card list.
	 * @return an unmodifiable list of displayed cards.
	 */
	public List<Card> getDisplayList() {
		return Collections.unmodifiableList(displayedList);
	}
	
	/**
	 * Returns if we request cards from ourself.
	 * @return True, if we request cards for ourself. False if from the opponent.
	 */
	public boolean isSelf() {
		return self;
	}
	
	@Override
	public String getMessage(){
		return message;
	}
	
	/**
	 * The return list, we should use this list to return cards requested.
	 * @return a modifiable list, that we should use to return cards.
	 */
	public List<Card> getReturnList() {
		return rList;
	}
	
	/**
	 * Get the mode of this card request.
	 * @return mode of this card request.
	 */
	public int getMode(){
		return mode;
	}
	
	/**
	 * Returns true if we can pick the same card multiple times.
	 * @return true if we can repick the same card.
	 */
	public boolean canRepeatPickedCard() {
		return canr;
	}
	
}
