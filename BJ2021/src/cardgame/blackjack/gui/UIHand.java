package cardgame.blackjack.gui;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import kwing.Graphics;
import cardgame.Configuration;
import cardgame.Card;
import cardgame.Hand;

/**
 * 手札を表示する
 * 
 * @author Yuuki Miyake
 * @author Takuma Torii
 * 
 * @version 2.0, 2007/12/05
 * @version 2.1, 2012/12/03 Takuma Torii Use ImageIO instead of
 *          java.awt.Toolkit.getImage().
 * @version 2.1, 2012/12/03 Takuma Torii Append `/` to access to JAR-internal
 *          files.
 */
public class UIHand extends Hand {

	/**
	 * 幅
	 */
	public static final int CARD_WIDTH = 65;

	/**
	 * 高さ
	 */
	public static final int CARD_HEIGHT = 103;

	/**
	 * X軸方向にずらす間隔(1枚ずつ)
	 */
	public static final int CARD_DISTANCE_X = 20;

	/**
	 * Y軸方向にずらす間隔(1枚ずつ)
	 */
	public static final int CARD_DISTANCE_Y = 20;

	/**
	 * X軸方向にずらす間隔(4枚ずつ : グループ)
	 */
	public static final int CARD_INTERVAL_X = 20;

	/**
	 * Y軸方向にずらす間隔(4枚ずつ : グループ)
	 */
	public static final int CARD_INTERVAL_Y = 20;

	/**
	 * 重ねられる最大枚数
	 */
	public static final int MAX_CARD_LINEUP = 4;

	/**
	 * バーストするカードの最大枚数 (デッキ＝1セットのとき：).
	 */
	public static final int LEAST_TOTAL_CARD_COUNT = 12;

	/**
	 * 描画を行う
	 */
	protected Graphics graphics;

	/**
	 * 原点座標
	 */
	protected Point cardOriginPoint;

	/**
	 * 描画するための情報を初期化する
	 * 
	 * @param graphics 描画を行う
	 * @param cardOriginPoint 原点座標
	 */
	public UIHand(Graphics graphics, Point cardOriginPoint) {
		this.cardOriginPoint = cardOriginPoint;
		this.graphics = graphics;
	}

	/**
	 * 描画する
	 */
	@Override
	protected void drawHand() {

		Point originPoint = cardOriginPoint;
		Point relativePoint = getCardPoint();

		Card card = getLastCard();

		graphics.draw(readCard(getCardFileName(card)), relativePoint.x
				+ originPoint.x, relativePoint.y + originPoint.y
				- CARD_INTERVAL_Y * (MAX_CARD_LINEUP - 1));

		graphics.repaint();

		UITable.delay(1);

	}

	/**
	 * 描画するための相対座標を返す
	 * 
	 * @return カードを描画する座標
	 */
	protected Point getCardPoint() {
		int currentCardsNumber = getCardsNumber() - 1;

		int originX = 0;
		int originY = (MAX_CARD_LINEUP - 1) * CARD_DISTANCE_Y;

		int relativeX = CARD_DISTANCE_X
				* (currentCardsNumber % MAX_CARD_LINEUP) + CARD_INTERVAL_X
				* (currentCardsNumber / MAX_CARD_LINEUP);
		int relativeY = -CARD_DISTANCE_Y
				* (currentCardsNumber % MAX_CARD_LINEUP) + CARD_INTERVAL_Y
				* (currentCardsNumber / MAX_CARD_LINEUP);

		return new Point(originX + relativeX, originY + relativeY);
	}

	/**
	 * 画像ファイル名を返す
	 * 
	 * @param card
	 *            カード
	 * @return ファイル名
	 */
	protected String getCardFileName(Card card) {

		String CardFileName = Card.FACE_DOWN_IMAGE_FILE;

		if (card.isFaceUp()) {
			CardFileName = UITable.IMAGE_DIR + "/" + card.getImageFileName();
		}
		return CardFileName;
	}

	/**
	 * 画像を読み込む
	 * 
	 * @param file
	 *            ファイル名
	 * @return カードの画像
	 */
	protected Image readCard(String file) {
		try {
			return ImageIO.read(Configuration.getConfiguration().
				openResource(file));
		} catch(IOException | IllegalArgumentException error){
			return null;
		}/** @see UITable#readCardDeck() */
	}
}
