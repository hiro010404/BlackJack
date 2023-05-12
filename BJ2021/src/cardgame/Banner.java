package cardgame;

/**
 * プレイヤーの勝敗を告知するバナー
 *
 * @author Yuuki Miyake
 *
 * @version 2.0, 2007/12/05
 *
 */
public abstract class Banner {

	/**
	 * 勝ち
	 */
	public static final String WIN = "WIN";

	/**
	 * 負け
	 */
	public static final String LOSE = "LOSE";

	/**
	 * 引き分け
	 */
	public static final String PUSH = "PUSH";

	/**
	 * ディーラが判定したプレイヤーの勝敗
	 *
	 * @see #WIN
	 * @see #LOSE
	 * @see #PUSH
	 * @see #GAMEOVER
	 */
	protected String judgment;

	/**
	 * getter
	 *
	 * @return プレーヤーの勝敗
	 */
	public String getJudgment() {
		return judgment;
	}

	/**
	 * バナーのコンストラクタ
	 */
	public Banner() {
	}

	/**
	 * プレイヤーに勝敗を告知する
	 *
	 * @param judgment 勝敗
	 */
	public void informJudgment(String judgment) {
		this.judgment = judgment;
		drawBanner();
	}

	/**
	 * プレイヤーの勝敗を描画する
	 */
	protected abstract void drawBanner();
}
