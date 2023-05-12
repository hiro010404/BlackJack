package kwing;

/**
 * ゲームの進行状況を描画する
 * 
 * @author Yuuki Miyake
 * 
 * @version 2.0, 2007/12/05
 */
public abstract class Graphics {

	/**
	 * 描画色を設定する
	 * 
	 *  @param color
	 *  			描画色 
	 */
	public abstract void setColor(String color);

	/**
	 * 描画する線の太さを設定する
	 * 
	 * @param size
	 * 			線の太さ
	 */
	public abstract void setStroke(float size);

	/**
	 * 始点(x,y)からオブジェクトを描画する
	 * 
	 * @param Image オブジェクト
	 * @param x 始点のx座標
	 * @param y 始点のy座標
	 */
	public abstract void draw(Object Image, int x, int y);

	/**
	 * 始点(x,y)からテキストを描画する
	 * 
	 * @param text テキスト
	 * @param x 始点のx座標
	 * @param y 始点のy座標
	 */
	public abstract void drawText(String text, int x, int y);

	/**
	 * 正方形(塗りつぶし可能)を描画する
	 * 
	 * @param x 始点のx座標
	 * @param y 始点のy座標
	 * @param width 正方形の幅
	 * @param height　正方形の高さ
	 */
	public abstract void fillRect(int x, int y, int width, int height);

	/**
	 * 長方形(丸角)を描画する
	 * 
	 * @param x 始点のx座標
	 * @param y 始点のy座標
	 * @param width 長方形の幅
	 * @param height 長方形の高さ
	 */
	public abstract void drawRoundRect(int x, int y, int width, int height);

	/**
	 * 弧を描画する
	 * 
	 * @param x 楕円の中心x座標
	 * @param y 楕円の中心y座標
	 * @param width 楕円の幅
	 * @param height 楕円の高さ
	 * @param startAngle 描画開始角度
	 * @param drawAngle 描画終了角度
	 */
	public abstract void drawArc(int x, int y, int width, int height,
			int startAngle, int drawAngle);

	/**
	 * 再描画を行う
	 */
	public abstract void repaint();

	/**
	 * キャンバスを返す
	 * 
	 * @return キャンバス
	 */
	public abstract Object getCanvas();

	/**
	 * グラフィックを返す
	 * 
	 * @return グラフィック
	 */
	public abstract Object getGraphics();

}
