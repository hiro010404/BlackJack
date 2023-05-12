package kwing.awt;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * AWTバージョンのフレーム
 * 
 * @author Yuuki Miyake
 * 
 * @version 2.0, 2007/12/05
 */
public class AWFrame extends kwing.Frame {

	/**
	 * フレーム
	 */
	private Frame frame;

	@Override
	public Object getFrame() {
		return frame;
	}

	public AWFrame() {
		frame = new Frame();
		frame.addWindowListener(new AWWindowAdapter());
	}

	@Override
	public void add(Object o) {
		frame.add((Canvas) o);

	}

	@Override
	public void setResizable(boolean flag) {
		frame.pack();
		frame.setResizable(flag);
		frame.setVisible(true);

	}

	@Override
	public void setTitle(String title) {
		frame.setTitle(title);
	}

	/**
	 * ウィンドウイベントに関する内部クラス
	 * 
	 * @author Yuuki Miyake
	 * @version 1, 2007/11/25
	 */
	private class AWWindowAdapter extends WindowAdapter {

		/**
		 * クローズボタンが押されたら終了する
		 */
		@Override
		public void windowClosing(WindowEvent event) {
			super.windowClosing(event);
			System.exit(1);

		}

	}

}
