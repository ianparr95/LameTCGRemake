package trainerCard.drawingCards;

import arena.GameArena;
import cardAbstract.Card;
import cardAbstract.TrainerCard;

public class Bill extends TrainerCard{

		public Bill(GameArena arena, int id) {
			super(arena, id);
			// TODO Auto-generated constructor stub
		}

		@Override
		public boolean attachable() {
			return false;
		}

		// can play bill any time.
		@Override
		public boolean canPlay() {
			return true;
		}

		@Override
		public void whenPlayed() {
			// TODO card = att(or currentPlayer).peekNextCard();
			// att.hand.addCard(card);
			// att.removeDeckCard();
			// THEN DO IT AGAIN FOR THE 2nd CARD!!
			arena.getAttHand().removeCardToPile(this);
			Card c = arena.getAttDeck().drawCard();
			if (c == null) return;
			arena.getAttHand().addCard(c);
			c = arena.getAttDeck().drawCard();
			if (c == null) return;
			arena.getAttHand().addCard(c);
		}

		@Override
		public void turnOppToUs() {
		}

		@Override
		public void turnUsToOpp() {
		}
		
		@Override
		public Card copyCard() {
			return new Bill(arena, getId());
		}

		@Override
		public String getName() {
			return "Bill";
		}

		@Override
		public String addInfoName() {
			return "Draw 2 cards";
		}
	}	