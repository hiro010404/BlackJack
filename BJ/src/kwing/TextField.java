package kwing;

/**
 * テキストフィールド
 * 
 * @author Tsuyoshi Iwasaki
 * @author Hiroki Tanaka
 * @author Shinji Saito
 * 
 * @version 2.0, 2007/12/05
 */
public abstract class TextField extends Widget {

	/**
	 * テキストを設定する
	 * 
	 * @param text
	 * 			文字列
	 */
	public abstract void setText(String text);

	/**
	 * 設定されたテキストを返す
	 * 
	 * @return 文字列
	 */
	public abstract String getText();

	/**
	 * 列数を設定する
	 * 
	 * @param columns
	 * 			列数
	 */
	public abstract void setColumns(int columns);

	/**
	 * 使用可能か設定する
	 * 
	 * @param isEnabled
	 * 			使用可能（true）・使用不能（false）
	 */
	public abstract void setEnabled(boolean isEnabled);

	/**
	 * 編集可能か設定する
	 * 
	 * @param isEditable
	 * 			編集可能（true）・編集不能（false）
	 */
	public abstract void setEditable(boolean isEditable);

}
