package trainerCard.statusCards;

import arena.GameArena;
import cardAbstract.Card;
import cardAbstract.CardRequest;
import cardAbstract.TrainerCard;
import pokemonCard.ActivePokemonCard;

public class PluspowerCard extends TrainerCard{


	public PluspowerCard(GameArena arena, int id) {
		super(arena, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean attachable() {
		return true;
	}

	@Override
	public boolean canPlay() {
		return true;
	}

	@Override
	public void whenPlayed() {
		arena.getAttActive().addStatus(new statuses.Pluspower(arena.getAttActive()));
		arena.getAttActive().addTrainer(this);
		arena.getAttHand().removeCardFromHand(this);
	}

	@Override
	public void turnOppToUs() {
	}

	@Override
	public void turnUsToOpp() {
		arena.getAttActive().removeTrainerToDiscardPile(this);
	}

	@Override
	public Card copyCard() {
		return new PluspowerCard(arena, getId());
	}
	
	@Override
	public String getName(){
		return "Pluspower";
	}

	@Override
	public String addInfoName() {
		// TODO Auto-generated method stub
		return "Attach PlusPower to your Active Pok�mon. At the end of your turn, discard PlusPower. If this Pok�mon's attack does damage to the Defending Pok�mon (after applying Weakness and Resistance), the attack does 10 more damage to the Defending Pok�mon.";
	}
	
}
