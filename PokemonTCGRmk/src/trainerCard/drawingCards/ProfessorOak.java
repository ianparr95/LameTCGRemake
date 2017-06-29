package trainerCard.drawingCards;

import arena.GameArena;
import cardAbstract.Card;
import cardAbstract.TrainerCard;

public class ProfessorOak extends TrainerCard{

		public ProfessorOak(GameArena arena, int id) {
			super(arena, id);
		}

		@Override
		public boolean attachable() {
			return false;
		}

		@Override
		public boolean canPlay() {
			return true;
		}

		@Override
		public void whenPlayed() {
			for (int i = arena.getAttHand().getSize() - 1 ; i >= 0; i--) {
				arena.getAttHand().removeCardToPile(i);
			}
			for (int i = 0 ; i < 7 ; i++) {
				Card c = arena.getAttDeck().drawCard();
				if (c==null) break;
				arena.getAttHand().addCard(c);
			}
			// TODO: already does this automatically? From the above when we remove all the cards.
			//arena.getAttHand().removeCardToPile(this);
		}

		@Override
		public void turnOppToUs() {
		}

		@Override
		public void turnUsToOpp() {
		}
		
		@Override
		public Card copyCard() {
			return new ProfessorOak(arena, getId());
		}
		
		@Override
		public String getName() {
			return "Professor Oak";
		}

		@Override
		public String addInfoName() {
			return "Discard your hand then draw 7 cards";
		}
		
	}