package cardgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * カードデック
 *
 * @author Yuuki Miyake
 * @author Takuma Torii
 *
 * @version 2.0, 2007/12/05
 */
public class Deck {

	/**
	 * カードデック
	 */
	private List<Card> deck = new ArrayList<Card>();

	/**
	 * カードの枚数
	 */
	private int cardsNumber;

	/**
	 * getter
	 *
	 * @return カードの枚数
	 */
	public int getCardsNumber() {
		return cardsNumber;
	}

	/**
	 * 配った枚数
	 */
	private int cardsDealtNumber = 0;

	/**
	 * 乱数発生器
	 */
	private Random random = new Random();

	/**
	 * カードデックのコンストラクタ
	 */
	public Deck() {
	}

	/**
	 * デックを生成する
	 */
	public void setupDeck() {
		for (Card.SUIT suit : Card.getSuits()) {
			for (Card.RANK rank : Card.getRanks()) {
				deck.add(new Card(rank, suit));
			}
		}

		cardsNumber = deck.size();
	}

	/**
	 * シャッフル
	 */
	public void shuffle() {
		cardsDealtNumber = 0;

		int HINDOO = random.nextInt(5) + 3;
		for (int i = 0; i < HINDOO; i++) {
			hindoo();
		}

		int RIFFLE = random.nextInt(5) + 3;
		for (int i = 0; i < RIFFLE; i++) {
			riffle();
		}
	}

	/**
	 * ヒンズーシャッフル
	 */
	private void hindoo() {
		int begin = random.nextInt(cardsNumber - cardsDealtNumber)
				+ cardsDealtNumber;
		int end = random.nextInt(cardsNumber - begin) + begin;

		for (int i = begin, j = cardsDealtNumber; i < end; i++, j++) {
			deck.add(j, deck.remove(i));
		}
	}

	/**
	 * リフルシャッフル
	 */
	private void riffle() {
		int center = (cardsNumber - cardsDealtNumber) / 2 + cardsDealtNumber;

		for (int i = center, j = cardsDealtNumber; i < cardsNumber
				&& j < cardsNumber; i++, j += 2) {
			deck.add(j, deck.remove(i));
		}
	}

	/**
	 * カードを引く(指定があれば表にする)
	 *
	 * @param faceUp 表（true）・裏（false）
	 * @return カード
	 */
	public Card takeCard(boolean faceUp) {

		if (cardsDealtNumber >= cardsNumber) {
			shuffle();
		}

		Card card = deck.get(cardsDealtNumber++);
		card.setFaceUp(faceUp);

		return card;
	}

	/**
	 * 残りの枚数を返す
	 *
	 * @return 残りの枚数
	 */
	public int getRemainder() {
		return cardsNumber - cardsDealtNumber;
	}

}