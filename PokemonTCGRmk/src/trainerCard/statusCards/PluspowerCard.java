package trainerCard.statusCards;

import arena.Arena;
import cardAbstract.ActivePokemonCard;
import cardAbstract.Card;
import cardAbstract.TrainerCard;

public class PluspowerCard extends TrainerCard{


	public PluspowerCard(Arena arena, int id) {
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
		arena.getAtt().addStatus(new statuses.Pluspower(arena.getAtt()));
		// Remove this from hand:
		//arena.getAtt().addTrainer(this);
		arena.getAttHand().removeCardFromHand(this);
	}

	@Override
	public void turnOppToUs() {
	}

	@Override
	public void turnUsToOpp() {
		arena.getAtt().removeTrainer(this);
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
		return "Attach PlusPower to your Active Pokémon. At the end of your turn, discard PlusPower. If this Pokémon's attack does damage to the Defending Pokémon (after applying Weakness and Resistance), the attack does 10 more damage to the Defending Pokémon.";
	}
	
}
