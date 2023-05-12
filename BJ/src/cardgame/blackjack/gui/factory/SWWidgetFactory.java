package cardgame.blackjack.gui.factory;

import java.awt.event.ActionListener;

import kwing.*;
import kwing.swing.*;

/**
 * Swingバージョンの部品を生成する工場
 * 
 * @author Tsuyoshi Iwasaki
 * @author Takuma Torii
 * @author Shiro TAKATA
 * 
 * @version 3.0, 2012/02/16
 */
public class SWWidgetFactory implements WidgetFactory {

	/**
	 * SWFactoryクラスの1つのインスタンスを保持するスタティック変数
	 */
	private static WidgetFactory factory = new SWWidgetFactory();

	/**
	 * 他のクラスからは参照できないコンストラクタを定義する
	 */
	private SWWidgetFactory() {
	}

	/**
	 * SWFactoryクラスの唯一のインスタンスの参照変数を返す
	 * 
	 * @return ウィジィットファクトリインスタンス
	 */
	public static WidgetFactory getInstance() {
		return factory;
	}

	@Override
	public Timer createTimer(int time, ActionListener listener) {
		return new SWTimer(time, listener);
	}

	@Override
	public Graphics createGraphics(int WIDTH, int HEIGHT) {
		return new SWGraphics(WIDTH, HEIGHT);
	}

	@Override
	public Frame createFrame() {
		return new SWFrame();
	}

	@Override
	public Panel createPanel() {
		return new SWPanel();
	}

	@Override
	public BorderLayout createBorderLayout() {
		return new SWBorderLayout();
	}

	@Override
	public FlowLayout createFlowLayout() {
		return new SWFlowLayout();
	}

	@Override
	public GridLayout createGridLayout() {
		return new SWGridLayout();
	}

	@Override
	public Label createLabel() {
		return new SWLabel();
	}

	@Override
	public TextField createTextField() {
		return new SWTextField();
	}

	@Override
	public Button createButton() {
		return new SWButton();
	}

	@Override
	public Dialog createDialog() {
		return new SWDialog();
	}

	@Override
	public RadioButton createRadioButton() {
		return new SWRadioButton();
	}

}
