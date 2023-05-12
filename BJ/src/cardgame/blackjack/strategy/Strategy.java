package cardgame.blackjack.strategy;

import cardgame.Player;

/**
 * 戦略
 *
 * @author Takuma Torii
 * @author Shiro Takata
 *
 * @version 2.0, 2007/12/05
 * @version 3.0, 2015/12/01
 */
public interface Strategy {

	/**
	 * 戦略の集合
	 */
	public enum STRATEGY {
		BasicStrategy, BearStrategy, BullStrategy, SimpleStrategy, RandomStrategy;

		/**
		 * getter
		 *
		 * @return STRATEGYの配列
		 */
		public static STRATEGY[] getStrategies() {
			return STRATEGY.values();
		}
	}

	/**
	 * 賭けチップを決定する
	 *
	 * @param player プレーヤー
	 * @return 賭けチップ
	 */
	public int decideBetChipsValue(Player player);

	/**
	 * ヒットまたはスタンドを決定する
	 *
	 * @param player プレーヤー
	 * @return ヒット：真、スタンド：偽
	 */
	public boolean isGettingHit(Player player);
}