package kwing.awt;

import kwing.TextField;

/**
 * AWTバージョンのテキストフィールド
 * 
 * @author Tsuyoshi Iwasaki
 * @author Hiroki Tanaka
 * @author Shinji Saito
 * 
 * @version 2.0, 2007/12/05
 */
public class AWTextField extends TextField {

	private java.awt.TextField textField;

	public AWTextField() {
		textField = new java.awt.TextField();
	}

	@Override
	public void setText(String text) {
		this.textField.setText(text);
	}

	@Override
	public String getText() {
		return textField.getText();
	}

	@Override
	public void setColumns(int columns) {
		textField.setColumns(columns);
	}

	@Override
	public void setEnabled(boolean isEnabled) {
		textField.setEnabled(isEnabled);
	}

	@Override
	public void setEditable(boolean isEditable) {
		textField.setEditable(isEditable);
	}

	@Override
	public Object getWidget() {
		return textField;
	}
}
