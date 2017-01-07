package misc;

import cardAbstract.TrainerCard;
import moveEffects.DamageOpp;
import moveEffects.DamageSelf;
import moveEffects.MoveEffect;
import moveEffects.ParOpp;
import moveEffects.PoisonOpp;
import moveEffects.SwordsDance;
import pokepower.PokePower;
import pokepower.RainDance;
import trainerCard.drawingCards.Bill;
import trainerCard.drawingCards.ProfessorOak;
import trainerCard.otherCards.GustOfWindCard;
import trainerCard.statusCards.DefenderCard;
import trainerCard.statusCards.FullHeal;
import trainerCard.statusCards.PluspowerCard;
import trainerCard.statusCards.Potion;

public class CEList {

	@SuppressWarnings("unchecked")
	private static Class<? extends MoveEffect> meSet[] = new Class[]{DamageOpp.class, PoisonOpp.class,
			ParOpp.class, DamageSelf.class, SwordsDance.class};
	@SuppressWarnings("unchecked")
	private static Class<? extends TrainerCard> tcSet[] = new Class[]{Bill.class, ProfessorOak.class,
			DefenderCard.class, FullHeal.class, PluspowerCard.class,
			Potion.class, GustOfWindCard.class};
	@SuppressWarnings("unchecked")
	private static Class<? extends PokePower>[] pSet = new Class[]{RainDance.class};

	public static Class<? extends MoveEffect>[] getMoveEffects(){
		return meSet;
	}
	
	public static Class<? extends PokePower>[] getPokePowers(){
		return pSet;
	}

	public static Class<? extends TrainerCard>[] getTCSet() {
		return tcSet;
	}

}
