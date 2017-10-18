package BlackJack.model.rules;

import BlackJack.model.Card;
import BlackJack.model.Player;

public class Soft17HitStrategy implements IHitStrategy {

	private final int g_hitLimit = 17;

	public boolean DoHit(Player a_dealer) {

		int currentScore = a_dealer.CalcScore();

		if (currentScore < g_hitLimit) {
			return true;
		} else if (currentScore == g_hitLimit) {
			int aces = 0;
			Iterable<Card> currentHand = a_dealer.GetHand();
			for (Card theCard : currentHand) {
				if (theCard.GetValue() == Card.Value.Ace)
					aces++;
			}
			if (aces < 0) {
				return true;
			}

		}
		return false;

	}

}
