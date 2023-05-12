package kwing.swing;

import javax.swing.JTextField;

import kwing.TextField;

/**
 * Swingバージョンのテキストフィールド
 * 
 * @author Tsuyoshi Iwasaki
 * @author Hiroki Tanaka
 * @author Shinji Saito
 * 
 * @version 2.0, 2007/12/05
 */
public class SWTextField extends TextField {

	private JTextField textField;

	public SWTextField() {
		textField = new JTextField();
	}

	@Override
	public void setText(String text) {
		textField.setText(text);
	}

	@Override
	public String getText() {
		return textField.getText();
	}

	@Override
	public void setEditable(boolean isEditable) {
		textField.setEditable(isEditable);
	}

	@Override
	public void setEnabled(boolean isEnabled) {
		textField.setEnabled(isEnabled);
	}

	@Override
	public void setColumns(int columns) {
		textField.setColumns(columns);
	}

	@Override
	public Object getWidget() {
		return textField;
	}
}
