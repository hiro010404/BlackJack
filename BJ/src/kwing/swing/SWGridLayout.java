package kwing.swing;

import kwing.GridLayout;

/**
 * Swingバージョンのグリッドレイアウト
 * 
 * @author Tsuyoshi Iwasaki
 * @author Hiroki Tanaka
 * @author Shinji Saito
 * 
 * @version 2.0, 2007/12/05
 */
public class SWGridLayout extends GridLayout {
	private java.awt.GridLayout layout;

	public SWGridLayout() {
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

	@Override
	public Object getLayout() {
		return layout;
	}
}
