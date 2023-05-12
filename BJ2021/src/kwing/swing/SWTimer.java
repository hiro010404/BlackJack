package kwing.swing;

import java.awt.event.ActionListener;

import kwing.Timer;

/**
 * Swingバージョンのタイマー
 * 
 * @author Tsuyoshi Iwasaki
 * @author Hiroki Tanaka
 * @author Shinji Saito
 * 
 * @version 2.0, 2007/12/05
 */
public class SWTimer extends Timer {
	javax.swing.Timer timer;

	public SWTimer(int time, ActionListener listener) {
		timer = new javax.swing.Timer(time, listener);
	}

	@Override
	public void start() {
		timer.start();
	}

	@Override
	public void stop() {
		timer.stop();
	}

	@Override
	public Object getWidget() {
		return timer;
	}

}
