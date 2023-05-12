package kwing.swing;

import kwing.FlowLayout;

/**
 * Swingバージョンのフローレイアウト
 * 
 * @author Tsuyoshi Iwasaki
 * @author Hiroki Tanaka
 * @author Shinji Saito
 * 
 * @version 2.0, 2007/12/05
 */
public class SWFlowLayout extends FlowLayout {
	private java.awt.FlowLayout layout;

	public SWFlowLayout() {
		layout = new java.awt.FlowLayout();
	}

	@Override
	public Object getLayout() {
		return layout;
	}

}
