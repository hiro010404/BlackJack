package kwing;

import java.awt.event.WindowEvent;

/**
 * ダイアログ
 * 
 * @author Tsuyoshi Iwasaki
 * @author Hiroki Tanaka
 * @author Shinji Saito
 * 
 * @version 2.0, 2007/12/05
 */
public abstract class Dialog {

	/**
	 * タイトルに文字列を設定する
	 * 
	 * @param title
	 * 			文字列
	 */
	public abstract void setTitle(String title);

	/**
	 * タイトルの文字列を返す
	 * 
	 * @return title
	 * 			タイトル
	 */
	public abstract String getTitle();

	/**
	 * 可視・不可視を設定する
	 * 
	 * @param isVisible
	 * 			可視(true)・不可視(false)
	 */
	public abstract void setVisible(boolean isVisible);

	/**
	 * サイズを変更可能にするか設定する
	 * 
	 * @param isResizable
	 * 			変更可能(true)・変更不可(false)
	 */
	public abstract void setResizable(boolean isResizable);

	/**
	 * モーダルか設定する
	 * 
	 * @param isModal
	 * 			設定(true)・未設定（false）
	 */
	public abstract void setModal(boolean isModal);

	/**
	 * パネルを追加する
	 * 
	 * @param panel
	 * 			パネル
	 */
	public abstract void add(Panel panel);

	/**
	 * ウィンドウ内のレイアウトを実行する
	 */
	public abstract void pack();

	/**
	 * ウィンドウリスナーを追加する
	 * 
	 * @param listener
	 * 			リスナー
	 */
	public abstract void addWindowListener(Object listener);

	/**
	 * ウィンドウを閉じたときの処理を行う
	 * 
	 * @param arg0
	 * 			ウィンドウイベント
	 */
	public void windowClosing(WindowEvent arg0) {
		System.exit(0);
	}
}