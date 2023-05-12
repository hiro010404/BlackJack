package kwing.awt;

import kwing.BorderLayout;

/**
 * AWTバージョンのボーダーレイアウト
 * 
 * @author Tsuyoshi Iwasaki
 * @author Hiroki Tanaka
 * @author Shinji Saito
 * 
 * @version 2.0, 2007/12/05
 */
public class AWBorderLayout extends BorderLayout {
	private java.awt.BorderLayout layout;

	public AWBorderLayout() {
		layout = new java.awt.BorderLayout();
	}

	@Override
	public Object getLayout() {
		return layout;
	}
}
