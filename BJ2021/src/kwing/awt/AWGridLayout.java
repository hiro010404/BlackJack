package kwing.awt;

import kwing.GridLayout;

/**
 * AWTバージョンのグリッドレイアウト
 * 
 * @author Tsuyoshi Iwasaki
 * @author Hiroki Tanaka
 * @author Shinji Saito
 * 
 * @version 2.0, 2007/12/05
 */
public class AWGridLayout extends GridLayout {
	private java.awt.GridLayout layout;

	@Override
	public Object getLayout() {
		return layout;
	}

	public AWGridLayout() {
		layout = new java.awt.GridLayout();
	}

	@Override
	public void setRows(int rows) {
		layout.setRows(rows);
	}

	@Override
	public void setColumns(int columns) {
		layout.setColumns(columns);
	}
}
