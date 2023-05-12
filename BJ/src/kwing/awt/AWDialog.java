package kwing.awt;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import kwing.Panel;

/**
 * AWTバージョンのダイアログ
 * 
 * @author Tsuyoshi Iwasaki
 * @author Hiroki Tanaka
 * @author Shinji Saito
 * 
 * @version 2.0, 2007/12/05
 */
public class AWDialog extends kwing.Dialog implements WindowListener {

	Dialog dialog;

	public AWDialog() {
		dialog = new Dialog(new Frame());

		dialog.addWindowListener(this);
		dialog.setModal(true);
	}

	@Override
	public void setResizable(boolean isResizable) {
		dialog.setResizable(isResizable);
	}

	@Override
	public void add(Panel panel) {
		dialog.add((Component) panel.getWidget());
	}

	@Override
	public void setTitle(String title) {
		dialog.setTitle(title);
	}

	@Override
	public void pack() {
		dialog.pack();
	}

	@Override
	public void setVisible(boolean isVisible) {
		dialog.setVisible(isVisible);
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		super.windowClosing(arg0);
	}

	@Override
	public String getTitle() {
		return dialog.getTitle();
	}

	@Override
	public void setModal(boolean isModal) {
		dialog.setModal(true);
	}

	@Override
	public void addWindowListener(Object listener) {
		dialog.addWindowListener((WindowListener) listener);
	}

	@Override
	public void windowClosed(WindowEvent arg0) {

	}

	@Override
	public void windowActivated(WindowEvent arg0) {

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {

	}

	@Override
	public void windowIconified(WindowEvent arg0) {

	}

	@Override
	public void windowOpened(WindowEvent arg0) {

	}
}
