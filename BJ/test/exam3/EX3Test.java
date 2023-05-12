package exam3;

import org.junit.Test;

/**
 * Dealer変更時のJUnit
 */

import cardgame.CardGame;
import cardgame.Dealer;
import cardgame.Deck;
import cardgame.Player;
import cardgame.Table;
import cardgame.blackjack.BlackJackDealer;
import cardgame.blackjack.ExtendedBlackJackPlayer;
import cardgame.blackjack.gui.UITable; 

public class EX3Test extends CardGame {

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
		return new ExtendedBlackJackPlayer();
	}

	/**
	 * Dealer変更時のJUnit
	 */
	@Test
	public void Dealerテストプログラム() {

		CardGame cardGame;

		cardGame = new EX3Test();
		cardGame.setupGame();
		cardGame.playGame();
		System.exit(0);
	}

}