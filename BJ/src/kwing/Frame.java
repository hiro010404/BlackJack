package kwing;

/**
 * フレーム
 * 
 * @author Yuuki Miyake
 * 
 * @version 2.0, 2007/12/05
 */
public abstract class Frame {

	/**
	 * ゲームのタイトルを設定する
	 * 
	 * @param title
	 * 			タイトル
	 */
	public abstract void setTitle(String title);

	/**
	 * サイズを変更可能にするか設定する
	 * 
	 * @param isResizable
	 * 			変更可能（true)・変更不能（false）
	 */
	public abstract void setResizable(boolean isResizable);

	/**
	 * オブジェクトを追加する
	 * 
	 * @param object
	 * 			オブジェクト
	 */
	public abstract void add(Object object);

	/**
	 * フレームを返す
	 * 
	 * @return フレーム
	 */
	public abstract Object getFrame();
}
