package kwing.swing;

import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

import kwing.RadioButton;

/**
 * Swingバージョンのラジオボタン
 * 
 * @author Tsuyoshi Iwasaki
 * @author Hiroki Tanaka
 * @author Shinji Saito
 * 
 * @version 12.0, 2007/12/05
 */
public class SWRadioButton extends RadioButton {

	private JRadioButton radioButton;

	public SWRadioButton() {
		radioButton = new JRadioButton();
	}

	@Override
	public void setText(String text) {
		radioButton.setText(text);
	}

	@Override
	public String getText() {
		return radioButton.getText();
	}

	@Override
	public void setSelected(boolean isSelected) {
		radioButton.setSelected(isSelected);
	}

	@Override
	public void setEnabled(boolean isEnabled) {
		radioButton.setEnabled(isEnabled);
	}

	@Override
	public void addActionListener(Object listener) {
		radioButton.addActionListener((ActionListener) listener);
	}

	@Override
	public boolean isSelected() {
		return radioButton.isSelected();
	}

	@Override
	public Object getWidget() {
		return radioButton;
	}

}
