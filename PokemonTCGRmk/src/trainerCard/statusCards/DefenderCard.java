package trainerCard.statusCards;

import arena.GameArena;
import cardAbstract.ActivePokemonCard;
import cardAbstract.Card;
import cardAbstract.TrainerCard;

public class DefenderCard extends TrainerCard{


	public DefenderCard(GameArena arena, int id) {
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
		arena.getAttActive().addStatus(new statuses.Defender(arena.getAttActive()));
		// Remove this from hand:
		arena.getAttActive().addTrainer(this);
		arena.getAttHand().removeCardFromHand(this);
	}

	@Override
	public void turnOppToUs() {
		arena.getAttActive().removeTrainerToDiscardPile(this);
	}

	@Override
	public void turnUsToOpp() {
	}
	
	@Override
	public Card copyCard() {
		return new DefenderCard(arena, getId());
	}
	
	@Override
	public String getName() {
		return "Defender";
	}

	@Override
	public String addInfoName() {
		return "Attach Defender to 1 of your Pokémon. At the end of your opponent's next turn, discard Defender. Damage done to that Pokémon by attacks is reduced by 20 (after applying Weakness and Resistance).";
	}

}
