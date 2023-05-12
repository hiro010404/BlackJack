package kwing.awt;

import java.awt.Label;

/**
 * AWTバージョンのラベル
 * 
 * @author Tsuyoshi Iwasaki
 * @author Hiroki Tanaka
 * @author Shinji Saito
 * 
 * @version 2.0, 2007/12/05
 */
public class AWLabel extends kwing.Label {

	Label label;

	public AWLabel() {
		label = new Label();
	}

	@Override
	public void setText(String text) {
		label.setText(text);
	}

	@Override
	public String getText() {
		return label.getText();
	}

	@Override
	public Object getWidget() {
		return label;
	}
}
