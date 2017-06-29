package trainerCard.statusCards;

import arena.GameArena;
import cardAbstract.ActivePokemonCard;
import cardAbstract.Card;
import cardAbstract.TrainerCard;
import statuses.Status;

public class FullHeal extends TrainerCard{


	public FullHeal(GameArena arena, int id) {
		super(arena, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean attachable() {
		return false;
	}

	@Override
	public boolean canPlay() {
		for (Status s : arena.getAttActive().getStatus()) {
			if (s.curable()) {
				// if exists a status that can be cured, can use full heal.
				return true;
			}
		}
		return false;
	}

	@Override
	public void whenPlayed() {
		for (int i = arena.getAttActive().getStatus().size() - 1 ; i >= 0 ; i--) {
			Status s = arena.getAttActive().getStatus().get(i);
			if (s.curable()) {
				arena.getAttActive().removeStatus(s);
			}
		}
		arena.getAttHand().removeCardToPile(this);
	}

	@Override
	public void turnOppToUs() {
	}

	@Override
	public void turnUsToOpp() {
	}

	
	@Override
	public Card copyCard() {
		return new FullHeal(arena, getId());
	}
	
	@Override
	public String getName(){
		return "Full Heal";
	}

	@Override
	public String addInfoName() {
		// TODO Auto-generated method stub
		return "Your Active Pokémon is no longer Asleep, Confused, Paralyzed, or Poisoned.";
	}
	
}
