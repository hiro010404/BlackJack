package cardgame.blackjack.gui.factory;

import java.awt.event.ActionListener;

import kwing.*;
import kwing.awt.*;

/**
 * Swingバージョンの部品を生成する工場
 * 
 * @author Tsuyoshi Iwasaki
 * @author Takuma Torii
 * @author Shiro TAKATA
 * 
 * @version 3.0, 2012/02/16
 */
public class AWWidgetFactory implements WidgetFactory {

	/**
	 * SWFactoryクラスの1つのインスタンスを保持するスタティック変数
	 */
	private static WidgetFactory factory = new AWWidgetFactory();

	/**
	 * 他のクラスからは参照できないコンストラクタを定義する
	 */
	private AWWidgetFactory() {
	}

	/**
	 * SWFactoryクラスの唯一のインスタンスの参照変数を返す
	 * 
	 * @return ウィジィット工場インスタンス
	 */
	public static WidgetFactory getInstance() {
		return factory;
	}

	@Override
	public Timer createTimer(int time, ActionListener listener) {
		return new AWTimer(time, listener);
	}

	@Override
	public Graphics createGraphics(int WIDTH, int HEIGHT) {
		return new AWGraphics(WIDTH, HEIGHT);
	}

	@Override
	public Frame createFrame() {
		return new AWFrame();
	}

	@Override
	public Panel createPanel() {
		return new AWPanel();
	}

	@Override
	public BorderLayout createBorderLayout() {
		return new AWBorderLayout();
	}

	@Override
	public FlowLayout createFlowLayout() {
		return new AWFlowLayout();
	}

	@Override
	public GridLayout createGridLayout() {
		return new AWGridLayout();
	}

	@Override
	public Label createLabel() {
		return new AWLabel();
	}

	@Override
	public TextField createTextField() {
		return new AWTextField();
	}

	@Override
	public Button createButton() {
		return new AWButton();
	}

	@Override
	public Dialog createDialog() {
		return new AWDialog();
	}

	@Override
	public RadioButton createRadioButton() {
		return new AWRadioButton();
	}

}
