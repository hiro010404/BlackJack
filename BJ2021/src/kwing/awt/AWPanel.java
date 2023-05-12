package kwing.awt;

import java.awt.Checkbox;
import java.awt.Component;
import java.awt.LayoutManager;

import kwing.BorderLayout;
import kwing.Layout;
import kwing.Panel;
import kwing.RadioButton;
import kwing.Widget;

/**
 * AWTバージョンのパネル
 * 
 * @author Tsuyoshi Iwasaki
 * @author Hiroki Tanaka
 * @author Shinji Saito
 * 
 * @version 2.0, 2007/12/05
 */
public class AWPanel extends Panel {
	private java.awt.Panel panel;

	private java.awt.CheckboxGroup checkboxGroup;

	public AWPanel() {
		panel = new java.awt.Panel();
	}

	@Override
	public void setLayout(Layout layout) {
		panel.setLayout((LayoutManager) layout.getLayout());
	}

	@Override
	public void add(RadioButton radioButton) {
		if (checkboxGroup == null) {
			checkboxGroup = new java.awt.CheckboxGroup();
		}

		Checkbox check = (Checkbox) radioButton.getWidget();
		check.setCheckboxGroup(checkboxGroup);

		panel.add(check);
	}

	@Override
	public void add(Widget widget) {
		panel.add((Component) widget.getWidget());
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
