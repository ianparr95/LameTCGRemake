package trainerCard.otherCards;

import java.util.ArrayList;
import java.util.List;

import arena.GameArena;
import cardAbstract.Card;
import cardAbstract.CardRequest;
import cardAbstract.TrainerCard;
import pokemonCard.ActivePokemonCard;
import trainerCard.statusCards.Potion;

public class GustOfWindCard extends TrainerCard {

	public GustOfWindCard(GameArena arena, int id) {
		super(arena, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean attachable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlay() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void whenPlayed() throws CardRequest {
		// TODO Auto-generated method stub
		List<Card> pList = new ArrayList<Card>();
		for (Card c : arena.getPlayerDef().getBench().getList()) {
			pList.add(c);
		}
		CardRequest pc = new CardRequest(true, 1, pList, pList
				, "Choose a benched pokemon to switch with the active.", 0, false);
		pc.requestCard();
	}
	
	@Override
	public void returnRequestedCards(CardRequest pc) {
		// Swap out with it:
		if (pc.getReturnList().size() == 0) {
			System.err.println("Did not specify pokemon to switch.");
			return;
		}
		ActivePokemonCard apc = (ActivePokemonCard) pc.getReturnList().get(0);
		if (apc == null) {
			System.err.println("Did not specify pokemon to switch with Gust of Wind.");
			return;
		} else {
			// Good: can swap
			ActivePokemonCard curAct = arena.getDefActive();
			System.out.println("Swapping to " + apc + " with energy string: " + apc.getEnergyString());
			System.out.println("Swapping from " + curAct + " with energy string: " + curAct.getEnergyString());
			arena.setDefPokemon(apc);
			arena.getPlayerDef().getBench().removeCard(apc);
			//int chp = curAct.getDamage();
			arena.getPlayerDef().getBench().addActive(curAct);
			/*for (ActivePokemonCard aa : arena.getPlayerDef().getBench().getList()) {
				if (aa.equals(curAct)) {
					aa.setDamage(chp);
					aa.getStatus().clear();
					//System.out.println("Set damage");
					break;
				}
			}*/
			curAct.getStatus().clear();
			arena.getPlayerAtt().getHand().removeCardToPile(this);
			return;
		}
	}

	@Override
	public void turnOppToUs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnUsToOpp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Card copyCard() {
		// TODO Auto-generated method stub
		return new GustOfWindCard(arena, getId());
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Gust of Wind";
	}

	@Override
	public String addInfoName() {
		// TODO Auto-generated method stub
		return "Choose 1 of your opponent's Benched Pokémon and switch it with his or her Active Pokémon.";
	}

}
