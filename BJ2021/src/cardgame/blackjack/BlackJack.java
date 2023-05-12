package cardgame.blackjack;

import cardgame.CardGame;
import cardgame.Dealer;
import cardgame.Deck;
import cardgame.Player;
import cardgame.Table;
import cardgame.blackjack.gui.UITable;

/**
 * ブラックジャック
 *
 * @author 池田美保
 * @author 岩崎章彦
 * @author 榎本悠希
 * @author 川上泰明
 * @author 川西 貢
 * @author Hiroki Tanaka
 * @author Shiro TAKATA
 * @version 3.0 2015/12/02
 */
public class BlackJack extends CardGame {

	/**
	 * GUIモード
	 */
	public static final String SWING = "Swing";
	public static final String AWT = "AWT";
	private static String GUI = AWT;

	/**
	 * getter
	 *
	 * @return GUIモード
	 */
	public static String getGUI() {
		return GUI;
	}

	/**
	 * setter
	 *
	 * @param gui GUIモード
	 */
	public static void setGUI(String gui) {
		GUI = gui;
	}

	@Override
	protected Table createTable() {
		return new UITable();
	}

	@Override
	protected Deck createDeck() {
		return new Deck();
	}

	@Override
	protected Dealer createDealer() {
		return new BlackJackDealer();
	}

	@Override
	public Player createPlayer() {
		return new BlackJackPlayer();
	}

	/**
	 * ブラックジャックゲームを実行する
	 *
	 * @param args 実行時パラメーター
	 */
	public static void main(String[] args) {

		CardGame cardGame;

		if (args.length >= 1) {
			/* 実行時パラメータの１番目はGUIのモード指定 */
			GUI = args[0];
		}

		if (args.length != 1 || (!GUI.equals(SWING) && !GUI.equals(AWT))) {
			System.err.println("起動方法 : java BlackJack " + SWING + " または " + AWT);
			System.exit(1);
		}

		cardGame = new BlackJack();
		cardGame.setupGame();
		cardGame.playGame();
		System.exit(0);
	}

}