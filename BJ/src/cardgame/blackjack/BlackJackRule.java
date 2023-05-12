package cardgame.blackjack;

import cardgame.Card;
import cardgame.Hand;

/**
 * ブラックジャックルール
 * 
 * @author Hiroki Tanaka
 * @author Shinji Saito
 * @author Shiro Takata
 * 
 * @version 2.0, 2007/12/05
 * @version 3.0, 2015/12/18 soft hand のバグを修正
 */
public class BlackJackRule {

	/**
	 * ディーラーがヒットできる手札の上限
	 */
	private static final int DEALER_HIT_HAND_TOTAL_UPPER = 17;

	/**
	 * バースト
	 */
	private static final boolean IS_BUST = true;

	/**
	 * ペア
	 */
	private static final boolean IS_PAIR = true;

	/**
	 * 勝ちのときの倍率
	 */
	private static final double WIN_RATE = 2.0;

	/**
	 * 負けのときの倍率
	 */
	private static final double LOSE_RATE = 0.0;

	/**
	 * 引き分けのときの倍率
	 */
	private static final double PUSH_RATE = 1.0;

	/**
	 * ブラックジャックで勝ったときの倍率
	 */
	private static final double BJ_RATE = 2.5;

	/**
	 * 倍率の決定表
	 */
	private static double decision[][] = {
			{ LOSE_RATE, LOSE_RATE, LOSE_RATE, LOSE_RATE },
			{ WIN_RATE, PUSH_RATE, LOSE_RATE, LOSE_RATE },
			{ WIN_RATE, WIN_RATE, PUSH_RATE, LOSE_RATE },
			{ BJ_RATE, BJ_RATE, BJ_RATE, PUSH_RATE } };

	/**
	 * BlackJackRuleのコンストラクタ
	 */
	protected BlackJackRule() {
	}
	
	/**
	 * バーストの判定をする
	 * 
	 * @param hand 手札
	 * @return バースト（true)
	 */
	public static boolean isBust(Hand hand) {
		boolean isBust = !IS_BUST;

		if (totalHand(hand) > 21) {
			isBust = IS_BUST;
		}

		return isBust;
	}

	// Hard handと考えた場合の手札の合計(Aceは1、絵札は10と数える)を計算
	private static int totalHandAsHard(Hand hand) {
		int cardTotal = 0;
		
		for (Card card : hand.getHand()) {
			int cardNumber = card.getCardNumber();
			// Aceも常に1とカウント
			cardTotal += (cardNumber >= 10 ? 10 : cardNumber);
		}
		return cardTotal;
	}
	// 手札がsoft handか判断する。引数は手札と、hard handと考えた場合の
	// 手札の合計
	private static boolean isSoft(Hand hand, int totalHandAsHard) {
		// Aceがあり、Softと考えてもcardTotalが21を超えないならSoft。
		// なお、Aceが2枚以上あってもcardTotalが21以下でAceを11と
		// カウントするのは1回のみ。
		for (Card card : hand.getHand()) {
			if(card.getCardNumber() == 1)
				return totalHandAsHard + 10 <= 21;
		}
		return false;
	}

	/**
	 * 手札がsoft handかどうか判断する
	 * 
	 * @param hand 手札
	 * @return 手札がsoft handならtrue、でなければfalse
	 */
	public static boolean isSoft(Hand hand) {
		return isSoft(hand, totalHandAsHard(hand));
	}

	/**
	 * 手札の合計を計算する
	 * 
	 * @param hand 手札
	 * @return 手札の合計
	 */
	public static int totalHand(Hand hand) {
		int cardTotal = totalHandAsHard(hand);
		return isSoft(hand, cardTotal) ? cardTotal + 10 : cardTotal;
	}

	/**
	 * ディーラーに配られた1枚目の数字を返す
	 * 
	 * @param hand 手札
	 * @return 数字
	 */
	public static int getDealerFaceUpCard(Hand hand) {
		int cardNumber = 0;

		if (hand.getCardsNumber() >= 1) {
			cardNumber = hand.getCardNumber(0);
		}
		return cardNumber;
	}

	/**
	 * ディーラーがヒットできるか判定する
	 * 
	 * @param hand 手札
	 * @return ヒット（true）・スタンド（false）
	 */
	public static boolean decideDealerIsHit(Hand hand) {
		boolean isHit = false;

		if (BlackJackRule.totalHand(hand) < DEALER_HIT_HAND_TOTAL_UPPER) {
			isHit = true;
		}

		return isHit;
	}

	/**
	 * ディーラーとプレイヤーの手札を比較する
	 * 
	 * @param dealerHand ディーラーの手札
	 * @param playerHand プレーヤーの手札
	 * @return 清算倍率
	 */
	public static double judgeGame(Hand dealerHand, Hand playerHand) {
		int dealerValue = 0;
		int dealerCardsTotal = totalHand(dealerHand);

		int playerValue = 0;
		int playerCardsTotal = totalHand(playerHand);

		/* ディーラーの評価 */
		if (dealerCardsTotal > 21) {
			dealerValue = 0;
		} else if (dealerHand.getCardsNumber() == 2 && dealerCardsTotal == 21) {
			dealerValue = 3;
		} else if (dealerCardsTotal > playerCardsTotal) {
			dealerValue = 2;
		} else {
			dealerValue = 1;
		}

		/* プレイヤーの評価 */
		if (playerCardsTotal > 21) {
			playerValue = 0;
		} else if (playerHand.getHand().size() == 2 && playerCardsTotal == 21) {
			playerValue = 3;
		} else if (dealerCardsTotal < playerCardsTotal) {
			playerValue = 2;
		} else {
			playerValue = 1;
		}

		return decision[playerValue][dealerValue];
	}

	/**
	 * 手札がペアか判定する
	 * 
	 * @param hand 手札
	 * @return ペア（true）
	 */
	public static boolean isPair(Hand hand) {
		boolean isPair = !IS_PAIR;

		if (hand.getCardsNumber() == 2
				&& (hand.getCardNumber(0) == hand.getCardNumber(1))) {
			isPair = IS_PAIR;
		}

		return isPair;
	}
}
