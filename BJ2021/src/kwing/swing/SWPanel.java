package kwing.swing;

import java.awt.Component;
import java.awt.LayoutManager;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import kwing.BorderLayout;
import kwing.Layout;
import kwing.Panel;
import kwing.RadioButton;
import kwing.Widget;

/**
 * Swingバージョンのパネル
 * 
 * @author Tsuyoshi Iwasaki
 * @author Hiroki Tanaka
 * @author Shinji Saito
 * 
 * @version 2.0, 2007/12/05
 */
public class SWPanel extends Panel {
	private JPanel panel;

	private ButtonGroup buttonGroup;

	public SWPanel() {
		panel = new JPanel();
	}

	@Override
	public void setLayout(Layout layout) {
		panel.setLayout((LayoutManager) layout.getLayout());
	}

	@Override
	public void add(Widget widget) {
		panel.add((Component) widget.getWidget());
	}

	@Override
	public void add(RadioButton radioButton) {
		if (buttonGroup == null) {
			buttonGroup = new ButtonGroup();
		}
		buttonGroup.add((JRadioButton) radioButton.getWidget());
		panel.add((Component) radioButton.getWidget());
	}

	@Override
	public void add(Widget widget, String direction) {
		if (direction.equals(BorderLayout.NORTH)) {
			direction = java.awt.BorderLayout.NORTH;
		} else if (direction.equals(BorderLayout.SOUTH)) {
			direction = java.awt.BorderLayout.SOUTH;
		} else {
			direction = java.awt.BorderLayout.CENTER;
		}

		panel.add((Component) widget.getWidget(), direction);
	}

	@Override
	public Object getWidget() {
		return panel;
	}
}
