package cardgame;

import java.util.ArrayList;
import java.util.List;

/**
 * カードゲームの抽象クラス
 * 
 * @author Hiroki Tanaka
 * @author Shiro TAKATA
 * @version 3.0 2015/11/28
 */
public abstract class CardGame {

	/**
	 * 最初のプレイヤーの座席番号
	 */
	public final static int FIRST_SEAT_NUMBER = 1;

	/**
	 * 最後のプレイヤーの座席番号
	 */
	public final static int PLAYER_NUMBERS = Configuration.getConfiguration()
			.getIntProperty("CardGame.playerNumbers");

	/**
	 * テーブル
	 */
	protected static Table table;
	
	/**
	 * getter
	 * 
	 * @return テーブル
	 */
	public static Table getTable() {
		return table;
	}
	
	/**
	 * setter
	 * 
	 * @param table1 テーブル
	 */
	public static void setTable(Table table1) {
		table = table1;
	}

	/**
	 * カードデッキ
	 */
	protected static Deck deck;

	/**
	 * getter
	 * 
	 * @return カードデック
	 */
	public static Deck getDeck() {
		return deck;
	}
	
	/**
	 * ディーラー
	 */
	protected static Dealer dealer;
	
	/**
	 * getter
	 * 
	 * @return ディーラー
	 */
	public static Dealer getDealer() {
		return dealer;
	}

	/**
	 * 全プレイヤーのリスト
	 */
	public static List<Player> players = new ArrayList<Player>();
	
	/**
	 * getter
	 * 
	 * @return 全プレーヤーのリスト
	 */
	public static List<Player> getPlayers() {
		return players;
	}
	
	/**
	 * カードゲームのコンストラクタ
	 */
	public CardGame() {
	}

	/**
	 * カードゲームの準備を行う
	 */
	public final void setupGame() {
		/* テーブルを準備する */
		table = createTable();
		table.setupTable();
		/* デックを準備する */
		deck = createDeck();
		deck.setupDeck();
		/* プレイヤーを準備する */
		for (int seatNumber = FIRST_SEAT_NUMBER; seatNumber <= PLAYER_NUMBERS; seatNumber++) {
			Player player = createPlayer();
			player.setupPlayer(seatNumber);
			players.add(player);
		}
		dealer = createDealer();
		dealer.setupDealer();
	}

	/**
	 * カードゲームを開始する
	 */
	public void playGame() {
		dealer.playGame();
	}

	/**
	 * テーブルを用意する
	 * 
	 * @return table
	 */
	protected abstract Table createTable();

	/**
	 * デックを用意する
	 * 
	 * @return カードデック
	 */
	protected abstract Deck createDeck();

	/**
	 * ディーラーが登場する
	 * 
	 * @return ディーラー
	 */
	protected abstract Dealer createDealer();

	/**
	 * プレイヤーが着席する
	 * 
	 * @return プレイヤー
	 */
	public abstract Player createPlayer();
}
