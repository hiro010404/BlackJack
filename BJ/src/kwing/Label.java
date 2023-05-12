package kwing;

/**
 * ラベル
 * 
 * @author Tsuyoshi Iwasaki
 * @author Hiroki Tanaka
 * @author Shinji Saito
 * 
 * @version 2.0, 2007/12/05
 */
public abstract class Label extends Widget {

	/**
	 * 文字列を設定する
	 * 
	 * @param text
	 * 			文字列
	 */
	public abstract void setText(String text);

	/**
	 * 設定された文字列を返す
	 * 
	 * @return 文字列
	 */
	public abstract String getText();

}
