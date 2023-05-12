package kwing.awt;

import kwing.FlowLayout;

/**
 * AWTバージョンのフローレイアウト
 * 
 * @author Tsuyoshi Iwasaki
 * @author Hiroki Tanaka
 * @author Shinji Saito
 * 
 * @version 2.0, 2007/12/05
 */
public class AWFlowLayout extends FlowLayout {
	private java.awt.FlowLayout layout;

	@Override
	public Object getLayout() {
		return layout;
	}

	public AWFlowLayout() {
		layout = new java.awt.FlowLayout();
	}

}
