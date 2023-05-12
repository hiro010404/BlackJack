package kwing.swing;

import kwing.BorderLayout;

/**
 * Swingバージョンのボーダーレイアウト
 * 
 * @author Tsuyoshi Iwasaki
 * @author Hiroki Tanaka
 * @author Shinji Saito
 * 
 * @version 2.0, 2007/12/05
 */
public class SWBorderLayout extends BorderLayout {
	private java.awt.BorderLayout layout;

	@Override
	public Object getLayout() {
		return layout;
	}

	public SWBorderLayout() {
		layout = new java.awt.BorderLayout();
	}
}
