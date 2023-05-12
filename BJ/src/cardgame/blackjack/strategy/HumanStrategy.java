package cardgame.blackjack.strategy;

import cardgame.Player;

/**
 * 人間の戦略(初期値を設定するのみ)
 * 
 * @author Shinji Saito
 * 
 * @version 2.0, 2007/12/05
 */

public class HumanStrategy implements Strategy {

	@Override
	public int decideBetChipsValue(Player player) {
		return player.getBetChips();
	}

	@Override
	public boolean isGettingHit(Player player) {
		return true;
	}

}
