package kwing;

/**
 * グリッドレイアウト
 * 
 * @author Tsuyoshi Iwasaki
 * @author Hiroki Tanaka
 * @author Shinji Saito
 * 
 * @version 2.0, 2007/12/05
 */
public abstract class GridLayout extends Layout {

	/**
	 * 行数を設定する
	 * 
	 * @param rows
	 * 			行数
	 */
	public abstract void setRows(int rows);

	/**
	 * 列数を設定する
	 * 
	 * @param columns
	 * 			列数
	 */
	public abstract void setColumns(int columns);
}
