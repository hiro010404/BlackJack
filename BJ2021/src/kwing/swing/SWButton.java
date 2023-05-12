package kwing.swing;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import kwing.Button;

/**
 * Swingバージョンのボタン
 * 
 * @author Tsuyoshi Iwasaki
 * @author Hiroki Tanaka
 * @author Shinji Saito
 * 
 * @version 2.0, 2007/12/05
 */
public class SWButton extends Button {

	private JButton button;

	public SWButton() {
		button = new JButton();
	}

	@Override
	public void setText(String text) {
		button.setText(text);
	}

	@Override
	public String getText() {
		return button.getText();
	}

	@Override
	public void setEnabled(boolean isEnabled) {
		button.setEnabled(isEnabled);
	}

	@Override
	public Object getWidget() {
		return button;
	}

	@Override
	public void addActionListener(Object listener) {
		button.addActionListener((ActionListener) listener);
	}
}