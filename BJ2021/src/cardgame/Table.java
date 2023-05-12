package cardgame;

import java.util.ArrayList;
import java.util.List;

import cardgame.chip.ChipsPack;
import cardgame.observer.HandObserver;
import cardgame.observer.HandSubject;

/**
 * テーブル
 * 
 * @author Yuuki Miyake
 * @author Tsuyoshi Iwasaki
 * 
 * @version 2.0, 2007/12/05
 */
public abstract class Table implements HandSubject {

	/**
	 * 席数
	 */
	public static final int SEAT_NUMBERS = CardGame.PLAYER_NUMBERS;

	/**
	 * ディーラーの座席番号
	 */
	public static final int DEALER_SEAT_NUMBER = 0;

	/**
	 * 参加プレイヤーの賭けチップ
	 */
	private List<ChipsPack> betChipsPacks = new ArrayList<ChipsPack>();

	/**
	 * 参加プレイヤーの報酬
	 */
	private List<ChipsPack> rewardChipsPacks = new ArrayList<ChipsPack>();

	/**
	 * 参加プレイヤーの手札
	 */
	private List<Hand> playerHands = new ArrayList<Hand>();

	/**
	 * 参加プレイヤーのバナー
	 */
	private List<Banner> banners = new ArrayList<Banner>();

	/**
	 * 参加プレイヤーの手札の観察者
	 */
	private List<HandObserver> observers = new ArrayList<HandObserver>();

	/**
	 * テーブルのコンストラクター
	 */
	public Table() {
	}

	/**
	 * テーブル上の各種オブジェクトを準備する
	 */
	public void setupTable() {
		for (int i = DEALER_SEAT_NUMBER; i <= SEAT_NUMBERS; i++) {
			betChipsPacks.add(createBetChipsPack(i));
			rewardChipsPacks.add(createRewardChipsPack(i));
			playerHands.add(createHand(i));
			banners.add(createBanner(i));
		}
	}

	/**
	 * 手札の観察者を追加する
	 * 
	 * @param observer 手札の観察者
	 */
	@Override
	public void registerHandObserver(HandObserver observer) {
		observers.add(observer);
	}

	/**
	 * 手札の観察者に変更を知らせる
	 * 
	 * @param seatNumber 座席番号
	 */
	@Override
	public void notifyHandObservers(int seatNumber) {
		for (HandObserver observer : observers) {
			observer.lookAtHand(seatNumber);
		}
	}

	/**
	 * プレイヤー名を表示する
	 * 
	 * @param name 名前
	 * @param seatNumber 座席番号
	 */
	public void displayPlayerName(String name, int seatNumber) {
		showPlayerName(name, seatNumber);
	}

	/**
	 * 賭けチップを置く
	 * 
	 * @param betChips 賭けチップ
	 * @param seatNumber 置く場所
	 */
	public void putBetChips(int betChips, int seatNumber) {
		betChipsPacks.get(seatNumber).addChipsPack(betChips);
	}

	/**
	 * 指定された座席の賭けチップを得る
	 * 
	 * @param seatNumber 座席番号
	 * @return 賭けチップ
	 */
	public int getBetChips(int seatNumber) {
		return betChipsPacks.get(seatNumber).getChipsValue();
	}

	/**
	 * カードを置き, 手札の観察者に知らせる
	 * 
	 * @param card カード
	 * @param seatNumber 座席番号
	 */
	public void putCard(Card card, int seatNumber) {
		playerHands.get(seatNumber).putCard(card);

		notifyHandObservers(seatNumber);
	}

	/**
	 * 指定されたプレイヤーの手札を得る
	 * 
	 * @param seatNumber 座席番号
	 * @return 手札
	 */
	public Hand getHand(int seatNumber) {
		return playerHands.get(seatNumber);
	}

	/**
	 * ディーラーのホールドカードを表にする
	 */
	public void faceUpCard() {
		playerHands.get(DEALER_SEAT_NUMBER).faceUpCard();
	}

	/**
	 * バナーに判定を知らせる
	 * 
	 * @param judgment 表示するバナーの種類（"Win", "Push", "Lose", "ゲームオーバー"）
	 * @param seatNumber 座席番号
	 */
	public void informJudgment(String judgment, int seatNumber) {
		banners.get(seatNumber).informJudgment(judgment);
	}

	/**
	 * 報酬を置く
	 * 
	 * @param rewardChips 報酬チップ数
	 * @param seatNumber 座席番号
	 */
	public void putRewardChips(int rewardChips, int seatNumber) {
		rewardChipsPacks.get(seatNumber).addChipsPack(rewardChips);
	}

	/**
	 * 指定された座席の賭けチップを取り去る
	 * 
	 * @param seatNumber 座席番号
	 * @return 賭けチップ数
	 */
	public int takeBetChips(int seatNumber) {
		ChipsPack chips = betChipsPacks.get(seatNumber);
		int betChips = chips.getChipsValue();
		chips.clearChipsPack();

		return betChips;
	}

	/**
	 * 指定された座席の報酬チップを取り去る
	 * 
	 * @param seatNumber 座席番号
	 * @return 報酬チップ
	 */
	public int takeRewardChips(int seatNumber) {
		ChipsPack chips = rewardChipsPacks.get(seatNumber);
		int rewardChips = chips.getChipsValue();
		chips.clearChipsPack();

		return rewardChips;
	}

	/**
	 * テーブル上を片付ける
	 * 
	 */
	public void clearObject() {
		clearHand();

		drawTable();
	}

	/**
	 * プレイヤーの手札を片付ける
	 * 
	 */
	private void clearHand() {
		for (Hand hand : playerHands) {
			hand.clearHand();
		}
	}

	/**
	 * テーブルを描画する
	 */
	protected abstract void drawTable();

	/**
	 * プレイヤー名を表示する
	 * 
	 * @param name 名前
	 * @param seatNumber 座席番号
	 */
	protected abstract void showPlayerName(String name, int seatNumber);

	/**
	 * 賭けチップのチップスパックを生成する
	 * 
	 * @param seatNumber 座席番号
	 * @return 賭けチップのチップスパック
	 */
	public abstract ChipsPack createBetChipsPack(int seatNumber);

	/**
	 * 手札を生成する
	 * 
	 * @param seatNumber 座席番号
	 * @return 手札
	 */
	protected abstract Hand createHand(int seatNumber);

	/**
	 * バナーを生成する
	 * 
	 * @param seatNumber 座席番号
	 * @return バナー
	 */
	protected abstract Banner createBanner(int seatNumber);

	/**
	 * 報酬チップのチップスパックを生成する
	 * 
	 * @param seatNumber 座席番号
	 * @return 報酬チップのチップスパック
	 */
	public abstract ChipsPack createRewardChipsPack(int seatNumber);
}
