package cardgame;

import java.util.ArrayList;
import java.util.List;

/**
 * 手札
 * 
 * @author Yuuki Miyake
 * 
 * @version 2.0, 2007/12/05
 */
public abstract class Hand {

	/**
	 * 手札
	 */
	protected List<Card> hand = new ArrayList<Card>();

	/**
	 * getter
	 * 
	 * @return hand 手札
	 */
	public List<Card> getHand() {
		return hand;
	}

	/**
	 * 手札にカードを追加する
	 * 
	 * @param card カード
	 */
	public void putCard(Card card) {
		hand.add(card);
		drawHand();
	}

	/**
	 * 手札の枚数を返す
	 * 
	 * @return 手札の枚数
	 */
	public int getCardsNumber() {
		return hand.size();
	}

	/**
	 * 最後に配られたカードを返す
	 * 
	 * @return 最後に配られたカード
	 */
	protected Card getLastCard() {
		Card card = null;

		if (hand.size() >= 1) {
			card = hand.get(hand.size() - 1);
		}

		return card;
	}

	/**
	 * 指定されたカードの番号を返す
	 * 
	 * @param index 指定番号
	 * @return カードの番号
	 */
	public int getCardNumber(int index) {
		int cardNumber = 0;
		Card card = hand.get(index);

		if (index < hand.size() && card.isFaceUp()) {
			cardNumber = card.getCardNumber();
		}

		return cardNumber;
	}

	/**
	 * カードを表にする
	 */
	public void faceUpCard() {
		if (hand.size() >= 2) {
			hand.get(1).setFaceUp(Card.FACE_UP);
			drawHand();
		}
	}

	/**
	 * 手札を片付ける
	 */
	public void clearHand() {
		hand.clear();
	}

	/**
	 * 手札を描画する
	 */
	protected abstract void drawHand();
}
