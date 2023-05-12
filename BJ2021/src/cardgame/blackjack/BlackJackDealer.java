package cardgame.blackjack;

import cardgame.Banner;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Dealer;
import cardgame.Player;
import cardgame.Table;
import cardgame.blackjack.gui.BlackJackDialog;
import cardgame.blackjack.gui.ChoiceDialog;
import cardgame.blackjack.gui.UITable;
import cardgame.blackjack.gui.factory.AWWidgetFactory;
import cardgame.blackjack.gui.factory.SWWidgetFactory;
import cardgame.blackjack.gui.factory.WidgetFactory;

/**
 * ブラックジャックディーラー
 *
 * @author Hiroki Tanaka
 * @author Shinji Saito
 * @author Shiro Takata
 *
 * @version 3.0, 2015/11/28
 * @version 3.1, 2018/12/06
 */
public class BlackJackDealer extends Dealer {

	/**
	 * ゲームが成立する最低枚数
	 */
	private final int CARD_LIMIT = (CardGame.PLAYER_NUMBERS + 1) * 5;

	/**
	 * ゲーム終了
	 */
	private final static boolean GAME_OVER = true;

	/**
	 * ヒットまたはスタンドのダイアログ
	 */
	private BlackJackDialog choiceDialog;

	/**
	 * BlackJackDealerのコンストラクタ
	 */
	public BlackJackDealer() {
	}

	/**
	 * ゲームを進行する
	 */
	@Override
	public void playGame() {
		prepareGame();

		do {
			calledBets();
			dealCard();
			makeGame();
			payInAndOut();
			clearTable();
		} while (!isGameOver());

		wrapupGame();
	}

	/**
	 * ゲームの準備を行う
	 */
	public void prepareGame() {
		choiceDialog = createChoiceDialog();
		choiceDialog.displayDialog(this);
		deck.shuffle();
	}

	/**
	 * チップを賭けるよう促す
	 */
	public void calledBets() {
		for (Player player : players) {
			if (player.getChipsValue() > 0) {
				player.betChips();
			}
		}
	}

	/**
	 * テーブル上にベッドした全プレーヤーのカードを配る
	 */
	public void dealCard() {
		for (Player player : players) {
			int playerSeatNumber = player.getSeatNumber();
			int playerChipsValue = table.getBetChips(playerSeatNumber);

			if (playerChipsValue > 0) {
				dealCard(player);
				dealCard(player);
			}
		}

		dealCard(Card.FACE_UP);
		dealCard(!Card.FACE_UP);
	}


	private void dealCard(Player player) {
		Card card = deck.takeCard(Card.FACE_UP);

		table.putCard(card, player.getSeatNumber());
	}

	/**
	 * テーブル上にディーラーのカードを表（true）または裏（false）で配る
	 *
	 * @param FaceUp  表（true）または裏（false）
	 */
	public void dealCard(boolean faceUp) {
		table.putCard(deck.takeCard(faceUp), Table.DEALER_SEAT_NUMBER);
	}

	/**
	 * プレーヤーと対戦する
	 */
	public void makeGame() {
		makePlayerTurn();
		playRole();
	}

	/**
	 * プレイヤーのターンを進める
	 */
	private void makePlayerTurn() {
		for (Player player : players) {
			int playerSeatNumber = player.getSeatNumber();
			int playerChipsValue = table.getBetChips(playerSeatNumber);

			if (playerChipsValue > 0) {
				while (!BlackJackRule.isBust(table.getHand(playerSeatNumber))
						&& ((BlackJackPlayer) player).isHitSelected()) {
					dealCard(player);
				}
			}

		}

	}

	/**
	 * 自身のターンを進める
	 */
	private void playRole() {
		turnUpDealerHoldCard();

		while (!BlackJackRule.isBust(table.getHand(Table.DEALER_SEAT_NUMBER)) && isGettingHit()) {
			dealCard(Card.FACE_UP);
		}
	}

	/**
	 * 自身のカードを表にする
	 */
	private void turnUpDealerHoldCard() {
		table.faceUpCard();
	}

	/**
	 * ヒットまたはスタンドを尋ねる
	 *
	 * @return ヒット : 真、スタンド : 偽
	 */
	private boolean isGettingHit() {
		choice = BlackJackRule.decideDealerIsHit(table
				.getHand(Table.DEALER_SEAT_NUMBER));
		choiceDialog.redisplayDialog();
		return choice;
	}

	/**
	 * 賭けチップを精算する
	 */
	public void payInAndOut() {

		for (Player player : players) {
			int playerSeatNumber = player.getSeatNumber();
			int playerChipsValue = table.getBetChips(playerSeatNumber);
			if (playerChipsValue > 0) {
				double rate = BlackJackRule.judgeGame(
						table.getHand(Table.DEALER_SEAT_NUMBER),
						table.getHand(playerSeatNumber));
				adjustChips(player, rate);
			}
		}

		for (Player player : players) {
			player.takeChips();
		}
	}

	/**
	 * チップの倍率に基づいてプレーヤーの勝敗を決めチップを精算する
	 *
	 * @param player プレーヤー
	 * @param rate チップの倍率
	 */
	private void adjustChips(Player player, double rate) {
		int playerSeatNumber = player.getSeatNumber();

		if (rate == 0.0) {// 勝ちもしくは、ブラックジャック
			player.setJudgement(Banner.LOSE);
			table.takeBetChips(playerSeatNumber);
			table.informJudgment(Banner.LOSE, playerSeatNumber);
		} else if (rate == 1.0) {// 引き分け
			player.setJudgement(Banner.PUSH);
			table.informJudgment(Banner.PUSH, playerSeatNumber);
		} else {// 負け
			int rewardChipsValue = (int) (table.getBetChips(playerSeatNumber) * (rate - 1.0));
			player.setJudgement(Banner.WIN);
			table.informJudgment(Banner.WIN, playerSeatNumber);
			table.putRewardChips(rewardChipsValue, playerSeatNumber);
		}
	}

	/**
	 * テーブルを片付ける
	 */
	public void clearTable() {
		table.clearObject();

		for (Player player : players) {
			player.displayMyName();
		}

		shuffleCardDeck();
	}

	/**
	 * カードデッキをシャッフルする
	 */
	private void shuffleCardDeck() {

		if (deck.getRemainder() < CARD_LIMIT) {
			deck.shuffle();
			notifyCardDeckObservers();
		}
	}

	/**
	 * ゲーム終了の判定を行う
	 *
	 * @return 真偽
	 */
	public boolean isGameOver() {
		boolean gameOver = GAME_OVER;

		for (Player player : players) {
			if (player.getChipsValue() > 0) {
				gameOver = !GAME_OVER;
			}
		}

		return gameOver;
	}

	/**
	 * ゲームの終了処理を行う
	 */
	public void wrapupGame() {
		System.out.println("********** Gameover **********");

		UITable.delay(5);
	}

	/**
	 * Widgetの部品を生成する工場
	 */
	public WidgetFactory factory; {
		String GUI = BlackJack.getGUI();
		if (GUI.equals(BlackJack.AWT)) {
			factory = AWWidgetFactory.getInstance();
		} else if (GUI.equals(BlackJack.SWING)) {
			factory = SWWidgetFactory.getInstance();
		}
	}

	/**
	 * ヒットまたはスタンドのダイアログを生成する
	 *
	 * @return チョイスダイヤログのインスタンス
	 */
	protected BlackJackDialog createChoiceDialog() {
		return new ChoiceDialog(factory);
	}
}