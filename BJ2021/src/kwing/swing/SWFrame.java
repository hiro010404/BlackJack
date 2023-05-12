package kwing.swing;

import javax.swing.JFrame;
import javax.swing.JPanel;

import kwing.Frame;

/**
 * Swingバージョンのフレーム
 * 
 * @author Yuuki Miyake
 * 
 * @version 2.0, 2007/12/05
 */
public class SWFrame extends Frame {

	private JFrame window;

	public SWFrame() {
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void setTitle(String title) {
		window.setTitle(title);

	}

	@Override
	public void setResizable(boolean flag) {
		window.pack();
		window.setResizable(flag);
		window.setVisible(true);

	}

	@Override
	public void add(Object o) {
		window.getContentPane().add((JPanel) o);

	}

	@Override
	public Object getFrame() {
		return window;
	}

}
