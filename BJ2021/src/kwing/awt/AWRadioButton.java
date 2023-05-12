package kwing.awt;

import java.awt.Checkbox;
import java.awt.event.ItemListener;

import kwing.RadioButton;

/**
 * AWTバージョンのラジオボタン
 * 
 * @author Tsuyoshi Iwasaki
 * @author Hiroki Tanaka
 * @author Shinji Saito
 * 
 * @version 2.0, 2007/12/05
 */
public class AWRadioButton extends RadioButton {

	private Checkbox radioButton;

	public AWRadioButton() {
		radioButton = new Checkbox();
	}

	@Override
	public void setText(String text) {
		radioButton.setLabel(text);
	}

	@Override
	public String getText() {
		return radioButton.getLabel();
	}

	@Override
	public void setSelected(boolean isSelected) {
		radioButton.setState(isSelected);
	}

	@Override
	public void setEnabled(boolean isEnabled) {
		radioButton.setEnabled(isEnabled);
	}

	@Override
	public Object getWidget() {
		return radioButton;
	}

	@Override
	public boolean isSelected() {
		return radioButton.getState();
	}

	@Override
	public void addActionListener(Object listener) {
		radioButton.addItemListener((ItemListener) listener);
	}

}
