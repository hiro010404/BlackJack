package cardgame.blackjack.gui;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import kwing.Graphics;
import cardgame.Banner;
import cardgame.Configuration;

/**
 * 各プレイヤーの勝敗を表示する
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
public class UIBanner extends Banner {

	/**
	 * 横幅
	 */
	public static final int BANNER_WIDTH = 150;

	/**
	 * 高さ
	 */
	public static final int BANNER_HEIGHT = 64;

	/**
	 * 原点座標
	 */
	protected Point bannerOriginPoint;

	/**
	 * 描画を行う
	 */
	protected Graphics graphics;

	/**
	 * UIBannerのコンストラクタ
	 * 
	 * @param graphics 描画を行う
	 * @param originPoint 原点座標
	 */
	public UIBanner(Graphics graphics, Point originPoint) {
		this.bannerOriginPoint = originPoint;
		this.graphics = graphics;
	}

	/**
	 * 画像ファイル名を返す
	 * 
	 * @param banner バナーの名前
	 * @return ファイル名
	 */
	protected String getBannerFileName(String banner) {
		return UITable.IMAGE_DIR + "/Banner" + banner + ".png";
	}

	/**
	 * 画像を読み込む
	 * 
	 * @param file ファイル名
	 * @return バナーの画像
	 */
	protected Image readBanner(String file) {
		try {
			return ImageIO.read(Configuration.getConfiguration().
				openResource(file));
		} catch(IOException | IllegalArgumentException error){
			return null;
		}/** @see UITable#readCardDeck() */
	}

	/**
	 * 描画する
	 */
	@Override
	protected void drawBanner() {
		String fName = getBannerFileName(judgment);

		graphics.draw(readBanner(fName), bannerOriginPoint.x,
				bannerOriginPoint.y);
		graphics.repaint();

		UITable.delay(5);
	}

}
