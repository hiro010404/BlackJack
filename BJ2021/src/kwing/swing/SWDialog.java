package kwing.swing;

import java.awt.Component;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;

import kwing.Dialog;
import kwing.Panel;

/**
 * Swingバージョンのダイアログ
 * 
 * @author Tsuyoshi Iwasaki
 * @author Hiroki Tanaka
 * @author Shinji Saito
 * 
 * @version 2.0, 2007/12/05
 */
public class SWDialog extends Dialog implements WindowListener {

	JDialog dialog;

	public SWDialog() {
		dialog = new JDialog();
		addWindowListener(this);
		setModal(true);
	}

	@Override
	public void setTitle(String title) {
		dialog.setTitle(title);
	}

	@Override
	public String getTitle() {
		return dialog.getTitle();
	}

	@Override
	public void add(Panel panel) {
		dialog.add((Component) panel.getWidget());
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
	public void setResizable(boolean isResizable) {
		dialog.setResizable(isResizable);
	}

	@Override
	public void setModal(boolean isModal) {
		dialog.setModal(isModal);
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

	@Override
	public void addWindowListener(Object listener) {
		dialog.addWindowListener((WindowListener) listener);
	}

	@Override
	public void windowClosed(WindowEvent arg0) {

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		super.windowClosing(arg0);
	}
}
