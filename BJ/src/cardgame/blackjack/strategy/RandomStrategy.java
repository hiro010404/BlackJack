package cardgame.blackjack.strategy;

import java.util.Random;

import cardgame.Hand;
import cardgame.Player;
import cardgame.blackjack.BlackJackRule;

/**
 * 気分屋な戦略
 * 
 * @author Shinji Saito
 * @version 1., 2007/10/21
 */
public class RandomStrategy implements Strategy {

	/**
	 * 乱数発生
	 */
	private Random random;

	public RandomStrategy() {
		random = new Random();
	}

	@Override
	public int decideBetChipsValue(Player player) {
		int chipsValue = player.getChipsValue();

		int betChips = chipsValue / (2 + random.nextInt(3));

		if (betChips == 0 && chipsValue > 0) {
			betChips = chipsValue;
		}

		return betChips;
	}

	@Override
	public boolean isGettingHit(Player player) {
		boolean isHit = false;
		Hand hand = player.getTable().getHand(player.getSeatNumber());

		if (BlackJackRule.totalHand(hand) < 15 + random.nextInt(6)) {
			isHit = true;
		}

		return isHit;
	}

}
