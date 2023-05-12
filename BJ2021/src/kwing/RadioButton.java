package kwing;

/**
 * ラジオボタン
 * 
 * @author Tsuyoshi Iwasaki
 * @author Hiroki Tanaka
 * @author Shinji Saito
 * 
 * @version 2.0, 2007/12/05
 */
public abstract class RadioButton extends Widget {

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

	/**
	 * 選択可能かを設定する
	 * 
	 * @param isEnabled
	 * 			選択可能（true）・選択不能（false）
	 */
	public abstract void setEnabled(boolean isEnabled);

	/**
	 * 選択するか設定する
	 * 
	 * @param isSelected
	 * 			選択（true）・未選択（false）
	 */
	public abstract void setSelected(boolean isSelected);

	/**
	 * 選択されているか
	 * 
	 * @return 選択（true）・未選択（false）
	 */
	public abstract boolean isSelected();

	/**
	 * アクションリスナーを追加する
	 * 
	 * @param listener
	 * 			リスナー
	 */
	public abstract void addActionListener(Object listener);

}
