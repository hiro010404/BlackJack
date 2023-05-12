package cardgame.blackjack.strategy;

import cardgame.Hand;
import cardgame.Player;
import cardgame.blackjack.BlackJackRule;

/**
 * 強気な戦略
 * 
 * @author Shinji Saito
 * 
 * @version 2.0, 2007/12/05
 */
public class BullStrategy implements Strategy {

	public BullStrategy() {
	}

	@Override
	public int decideBetChipsValue(Player player) {
		int chipsValue = player.getChipsValue();
		int betChips = chipsValue / 4;

		if (betChips == 0 && chipsValue > 0) {
			betChips = chipsValue;
		}

		return betChips;
	}

	@Override
	public boolean isGettingHit(Player player) {
		boolean isHit = false;
		Hand hand = player.getTable().getHand(player.getSeatNumber());

		if (BlackJackRule.totalHand(hand) < 18) {
			isHit = true;
		}

		return isHit;
	}

}
