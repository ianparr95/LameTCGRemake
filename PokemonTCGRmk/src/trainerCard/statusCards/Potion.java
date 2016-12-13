package trainerCard.statusCards;

import arena.Arena;
import cardAbstract.ActivePokemonCard;
import cardAbstract.Card;
import cardAbstract.TrainerCard;

public class Potion extends TrainerCard{



	public Potion(Arena arena, int id) {
		super(arena, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean attachable() {
		return false;
	}

	@Override
	public boolean canPlay() {
		return !(arena.getAtt().getDamage() == 0) ;
	}

	@Override
	public void whenPlayed() {
		arena.getAtt().addDamage(-20);
		if (arena.getAtt().getDamage() < 0) arena.getAtt().setDamage(0);
		arena.getPlayerAtt().getHand().removeCardToPile(this);
	}

	@Override
	public void turnOppToUs() {
	}

	@Override
	public void turnUsToOpp() {
	}

	
	@Override
	public Card copyCard() {
		return new Potion(arena, getId());
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Potion";
	}

	@Override
	public String addInfoName() {
		// TODO Auto-generated method stub
		return "Remove up to 2 damage counters from any pokemon on your bench or active";
	}
	
	
}
