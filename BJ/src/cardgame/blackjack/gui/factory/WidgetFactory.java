package cardgame.blackjack.gui.factory;

import java.awt.event.ActionListener;

import kwing.BorderLayout;
import kwing.Button;
import kwing.Dialog;
import kwing.FlowLayout;
import kwing.Frame;
import kwing.Graphics;
import kwing.GridLayout;
import kwing.Label;
import kwing.Panel;
import kwing.RadioButton;
import kwing.TextField;
import kwing.Timer;

/**
 * Kwing Abstract Factory
 * 
 * @author Shiro TAKATA
 * 
 * @version 3.0, 2012/02/15
 */
public interface WidgetFactory {

	/**
	 * タイマーを生成する
	 * @param time 時刻
	 * @param listener アクションリスナー
	 * @return タイマーインスタンス
	 */
	public abstract Timer createTimer(int time, ActionListener listener);

	/**
	 * Graphicsを生成する
	 * 
	 * @param WIDTH パレットの幅
	 * @param HEIGHT パレットの高さ
	 * @return グラフィックス
	 */
	public abstract Graphics createGraphics(int WIDTH, int HEIGHT);

	/**
	 * Frameを生成する
	 * 
	 * @return フレーム
	 */
	public abstract Frame createFrame();

	/**
	 * パネルを生成する
	 * 
	 * @return パネル
	 */
	public abstract Panel createPanel();

	/**
	 * ボーダーレイアウトを生成する
	 * 
	 * @return ボーダーレイアウト
	 * 
	 */
	public abstract BorderLayout createBorderLayout();

	/**
	 * フローレイアウトを生成する
	 * 
	 * @return フローレイアウト
	 */
	public abstract FlowLayout createFlowLayout();

	/**
	 * グリッドレイアウトを生成する
	 * 
	 * @return グリッドレイアウト
	 */
	public abstract GridLayout createGridLayout();

	/**
	 * ラベルを生成する
	 * 
	 * @return ラベル
	 */
	public abstract Label createLabel();

	/**
	 * テキストフィールドを生成する
	 * 
	 * @return テキストフィールド
	 */
	public abstract TextField createTextField();

	/**
	 * ボタンを生成する
	 * 
	 * @return ボタン
	 */
	public abstract Button createButton();

	/**
	 * ダイアログを生成する
	 * 
	 * @return ダイアログ
	 */
	public abstract Dialog createDialog();

	/**
	 * ラジオボタンを生成する
	 * 
	 * @return ラジオボタン
	 */
	public abstract RadioButton createRadioButton();

}