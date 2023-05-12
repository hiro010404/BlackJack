package cardgame.blackjack.strategy;

import cardgame.Hand;
import cardgame.Player;
import cardgame.blackjack.BlackJackRule;

/**
 * ディーラーと同じ戦略
 * 
 * @author Shinji Saito
 * 
 * @version 2.0, 2007/12/05
 */
public class SimpleStrategy implements Strategy {

	public SimpleStrategy() {
	}

	@Override
	public int decideBetChipsValue(Player player) {
		int chipsValue = player.getChipsValue();
		int betChips = chipsValue / 5;

		if (betChips <= 0) {
			betChips = chipsValue;
		}

		return betChips;
	}

	@Override
	public boolean isGettingHit(Player player) {
		boolean isHit = false;
		Hand hand = player.getTable().getHand(player.getSeatNumber());

		if (BlackJackRule.totalHand(hand) < 16) {
			isHit = true;
		}

		return isHit;
	}

}