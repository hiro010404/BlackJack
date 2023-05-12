package kwing.awt;

import java.awt.Button;
import java.awt.event.ActionListener;

/**
 * AWTバージョンのボタン
 * 
 * @author Tsuyoshi Iwasaki
 * @author Hiroki Tanaka
 * @author Shinji Saito
 * 
 * @version 2.0, 2007/12/05
 */
public class AWButton extends kwing.Button {

	private Button button;

	public AWButton() {
		button = new Button();
	}

	@Override
	public void addActionListener(Object listener) {
		button.addActionListener((ActionListener) listener);
	}

	@Override
	public void setText(String text) {
		button.setLabel(text);
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
	public String getText() {
		return button.getLabel();
	}
}