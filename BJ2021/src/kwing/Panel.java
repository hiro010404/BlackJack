package kwing;

/**
 * パネル
 * 
 * @author Tsuyoshi Iwasaki
 * @author Hiroki Tanaka
 * @author Shinji Saito
 * 
 * @version 2.0, 2007/12/05
 */

public abstract class Panel extends Widget {

	/**
	 * ウィジェットを追加する
	 * 
	 * @param widget
	 * 			ウィジェット
	 */
	public abstract void add(Widget widget);

	/**
	 * ウィジェットを指定された位置に追加する
	 * 
	 * @param widget ウィジェット
	 * @param index 指定位置
	 */
	public abstract void add(Widget widget, String index);

	/**
	 * ラジオボタンを追加する
	 * 
	 * @param radioButton
	 * 			ラジオボタン
	 */
	public abstract void add(RadioButton radioButton);

	/**
	 * レイアウトを設定する
	 * 
	 * @param layout
	 * 			レイアウト
	 */
	public abstract void setLayout(Layout layout);
}
