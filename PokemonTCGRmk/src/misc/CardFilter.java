package misc;

import java.util.ArrayList;
import java.util.List;

import cardAbstract.Card;
import cardAbstract.EnergyCard;
import cardAbstract.PokemonCard;

@SuppressWarnings({"rawtypes", "unchecked"})
public class CardFilter {

	// This class allows us to filter cards based on types (if trainer, energy.., pokemon, basic etc)
	
	private List<Card> cards;
	
	public CardFilter(List<Card> cards) {
		this.cards = cards;
	}
	
	/**
	 * Filter pokemon cards based on pokemon name. eg: Blastoise gives all blastoises.
	 */
	public List<PokemonCard> filterCardsName(String name) {
		List<Card> aList = filterCardsExact(PokemonCard.class);
		List rList = fitlerListCond(new Cond(aList) {
			@Override
			public boolean cond(Card c) {
				return ((PokemonCard) c).getName().equals(name);
			}
		});
		return rList;
	}
	
	/**
	 * Returns all basic energy cards: eg: not double colorless, potion energy etc..
	 */
	public List<EnergyCard> filterBasicEnergy(){
		List<Card> aList = filterCardsSub(EnergyCard.class);
		List rList = fitlerListCond(new Cond(aList) {
			@Override
			public boolean cond(Card c) {
				return ((EnergyCard) c).isBasicEnergy();
			}
		});
		return rList;
	}
	
	/**
	 * Returns all non-basic energy cards: eg: not double colorless, potion energy etc..
	 */
	public List<EnergyCard> filterNonBasicEnergy(){
		List<Card> aList = filterCardsSub(EnergyCard.class);
		List rList = fitlerListCond(new Cond(aList) {
			@Override
			public boolean cond(Card c) {
				return !((EnergyCard) c).isBasicEnergy();
			}
		});
		return rList;
	}
	
	/**
	 * Filter cards based on stage... 0 for basic, 1 for stage 1 etc..
	 */
	public List<PokemonCard> filterCardsStage(String stage) {
		List<Card> aList = filterCardsExact(PokemonCard.class);
		List rList = fitlerListCond(new Cond(aList) {
			@Override
			public boolean cond(Card c) {
				return ((PokemonCard) c).getStage().equals(stage);
			}
		});
		return rList;
	}
	
	/**
	 * Filter exact class type (eg: water energy class only does water energy)
	 */
	public List<Card> filterCardsExact(Class k) {
		List<Card> rList = new ArrayList<Card>();
		for (Card c : cards) {
			if (c.getClass().equals(k)) {
				rList.add(c);
			}
		}
		return rList;
	}
	
	/**
	 * Will filter cards until we reach the class Card itself.
	 * So energycard will match all sub types.
	 */
	public List<Card> filterCardsSub(Class k) {
		List<Card> rList = new ArrayList<Card>();
		for (Card c : cards) {
			Class ck = c.getClass();
			while (!ck.equals(Card.class)) {
				if (ck.equals(k)) {
					rList.add(c);
					break;
				} else {
					ck = ck.getSuperclass();
					continue;
				}
			}
		}
		return rList;
	}
	
	private abstract class Cond{
		
		List<Card> lc;
		
		Cond(List<Card> lc) {
			this.lc = lc;
		}

		abstract boolean cond(Card c);
	}
	
	private List<Card> fitlerListCond(Cond sCond){
		List rList = new ArrayList();
		for (Card c : sCond.lc) {
			if (sCond.cond(c)) {
				rList.add(c);
			}
		}
		return rList;
	}
}

