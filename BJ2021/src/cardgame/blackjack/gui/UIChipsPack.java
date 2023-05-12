package cardgame.blackjack.gui;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import kwing.Graphics;
import cardgame.Configuration;
import cardgame.chip.Chip;
import cardgame.chip.ChipsPack;

/**
 * チップの山
 * 
 * @author Yuuki Miyake
 * @author Jun Takata
 * @author Takuma Torii
 * 
 * @version 2.0, 2007/12/05
 * @version 2.1, 2012/12/03 Takuma Torii Use ImageIO instead of
 *          java.awt.Toolkit.getImage().
 * @version 2.1, 2012/12/03 Takuma Torii Append `/` to access to JAR-internal
 *          files.
 */

public class UIChipsPack extends ChipsPack {

	/**
	 * 幅
	 */
	public static final int CHIP_WIDTH = 35;

	/**
	 * 高さ
	 */
	public static final int CHIP_HEIGHT = 35;

	/**
	 * X軸方向にずらす間隔
	 */
	public static final int CHIP_INTERVAL_X = 40;

	/**
	 * Y軸方向にずらす間隔
	 */
	public static final int CHIP_INTERVAL_Y = 0;

	/**
	 * 積み上げるX軸方向の距離
	 */
	public static final int CHIP_DISTANCE_X = 0;

	/**
	 * 積み上げるY軸方向の距離
	 */
	public static final int CHIP_DISTANCE_Y = 3;

	/**
	 * 積み上げられる最大枚数
	 */
	public static final int MAX_CHIP_PILEUP = Configuration.getConfiguration()
			.getIntProperty("UITable.chipPileUp");

	/**
	 * 原点座標
	 */
	protected Point chipOriginPoint;

	/**
	 * 描画を行う
	 */
	protected Graphics graphics;

	/**
	 * 描画した枚数
	 */
	private int currentChipsCount;

	/**
	 * UIChipsPackのコンストラクタ
	 * 
	 * @param graphics 描画を行う
	 * @param originPoint 原点座標
	 */
	public UIChipsPack(Graphics graphics, Point originPoint) {
		this.chipOriginPoint = originPoint;
		this.graphics = graphics;
	}

	/**
	 * チップを加える
	 */
	@Override
	public void addChipsPack(int chipsValue) {
		super.addChipsPack(chipsValue);
		displayChipsPack();
	}

	/**
	 * 表示する
	 */
	public void displayChipsPack() {
		currentChipsCount = 0;
		drawChips(chips100);
		drawChips(chips25);
		drawChips(chips5);
		drawChips(chips1);
	}

	/**
	 * 描画する
	 * 
	 * @param chips チップのリスト
	 */
	private void drawChips(List<Chip> chips) {
		int originX = 0;
		int originY = CHIP_DISTANCE_Y * (MAX_CHIP_PILEUP - 1) + CHIP_HEIGHT;

		for (int chipsCount = 0; chipsCount < chips.size(); chipsCount++) {
			int relativeX = CHIP_INTERVAL_X
					* (currentChipsCount / MAX_CHIP_PILEUP);
			int relativeY = -CHIP_DISTANCE_Y
					* (currentChipsCount++ % MAX_CHIP_PILEUP);

			graphics.draw(readChip(chips.get(chipsCount).getImageFileName()),
					chipOriginPoint.x + originX + relativeX, chipOriginPoint.y
							+ originY + relativeY);

			graphics.repaint();
		}
	}

	/**
	 * 画像を読み込む
	 * 
	 * @param file ファイル名
	 * @return チップの画像
	 */
	protected Image readChip(String file) {
		try {
			return ImageIO.read(Configuration.getConfiguration().
				openResource(file));
		} catch(IOException | IllegalArgumentException error){
			return null;
		}/** @see UITable#readCardDeck() */
	}
}
