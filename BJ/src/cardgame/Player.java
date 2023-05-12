package cardgame;

import cardgame.blackjack.strategy.Strategy;
import cardgame.chip.ChipsPack;
import cardgame.observer.CardDeckObserver;
import cardgame.observer.HandObserver;

/**
 * プレイヤー
 * 
 * @author Hiroki Tanaka
 * @author Shinji Saito
 * @author Shiro Takata
 * 
 * @version 3.0, 2015/11/28
 */
public abstract class Player implements HandObserver, CardDeckObserver {

	/**
	 * ゲーム開始時
	 */
	public static final String READY = "READY";

	/**
	 * 座席番号
	 */
	protected int seatNumber;

	/**
	 * getter
	 * 
	 * @return 座席番号
	 */
	public int getSeatNumber() {
		return seatNumber;
	}
	
	/**
	 * setter
	 * 
	 * @param seatNumber 座席番号
	 */
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	
	/**
	 * プレーヤの名前
	 */
	protected String name;
	
	/**
	 * getter
	 * 
	 * @return プレーヤの名前
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * setter
	 * 
	 * @param name プレーヤの名前
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 人間またはエージェント(人間 : 真、エージェント : 偽)
	 */
	protected boolean human;
	
	/**
	 * getter
	 * 
	 * @return human
	 */
	public boolean isHuman() {
		return human;
	}

	/**
	 * setter
	 * 
	 * @param human 人間（true）・エージェント（false）
	 */
	public void setHuman(boolean human) {
		this.human = human;
	}

	/**
	 * 戦略
	 */
	protected Strategy strategy;

	/**
	 * getter
	 * 
	 * @return 戦略
	 */
	public Strategy getStrategy() {
		return strategy;
	}

	/**
	 * setter
	 * 
	 * @param strategy 戦略
	 */
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * 戦略名
	 */
	protected String strategyName;

	/**
	 * getter
	 * 
	 * @return 戦略名
	 */
	public String getStrategyName() {
		return strategyName;
	}
	
	/**
	 * setter
	 * 
	 * @param strategyName 戦略名
	 */
	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}

	/**
	 * 購入チップ数
	 */
	protected int exchangeChips;

	/**
	 * 購入チップ数を返す
	 * 
	 * @return 購入チップ数
	 */
	public int getExchangeChips() {
		return exchangeChips;
	}
	
	/**
	 * 購入チップ数をセットする
	 * 
	 * @param  exchangeChips 購入チップ数
	 */
	public void setExchangeChips(int exchangeChips) {
		this.exchangeChips = exchangeChips;
	}

	/**
	 * 所持チップ
	 */
	protected ChipsPack chipsValue = new ChipsPack();

	/**
	 * 所持チップの合計を返す
	 * 
	 * @return 所持チップの合計
	 */
	public int getChipsValue() {
		return chipsValue.getChipsValue();
	}

	/**
	 * 所持チップをセット
	 * 
	 * @param chipsValue 所持チップの合計
	 */
	public void setChipsValue(int chipsValue) {
		this.chipsValue.setChipsValue(chipsValue);
	}

	/**
	 * 賭けチップ
	 */
	protected int betChips = 0;

	/**
	 * getter
	 * 
	 * @return 賭けチップ
	 */
	public int getBetChips() {
		return betChips;
	}

	/**
	 * setter
	 * 
	 * @param betChips 賭けチップ
	 */
	public void setBetChips(int betChips) {
		this.betChips = betChips;
	}

	/**
	 * 選択(ヒット : 真、スタンド : 偽)
	 */
	protected boolean choice;

	/**
	 * getter
	 * 
	 * @return choice(ヒット : 真、スタンド : 偽)
	 */
	public boolean getChoice() {
		return choice;
	}
	
	/**
	 * setter
	 * 
	 * @param choice 真：ヒット、偽：スタンド
	 */
	public void setChoice(boolean choice) {
		this.choice = choice;
	}

	/**
	 * gameの勝敗
	 * プレイヤーの勝敗
	 * 
	 * @see #READY
	 */
	protected String judgment = READY;

	/**
	 * getter
	 * 
	 * @return gameの勝敗
	 */
	public String getJudgment() {
		return judgment;
	}

	/**
	 * setter
	 * 
	 * 	 @param judgement gameの勝敗
	 */
	public void setJudgement(String judgement) {
		this.judgment = judgement;
	}

	/**
	 * テーブル
	 */
	protected Table table;
	
	/**
	 * getter
	 * 
	 * @return Tableのインスタンス
	 */
	public Table getTable() {
		return table;
	}
	
	/**
	 * setter
	 * 
	 * @param table Tableのインスタンス
	 */
	public void setTable(Table table) {
		this.table = table;
	}


	

	/**
	 * Playerのコンストラクタ
	 */
	public Player() {		
	}

	/**
	 *Playerをセットアップする
	 *
	 * @param seatNumber 座席番号
	 */
	public void setupPlayer(int seatNumber){
		this.seatNumber = seatNumber;
		table = CardGame.getTable();
		table.registerHandObserver(this);
	}

	/**
	 * テーブルに名前を表示する
	 */
	public void displayMyName() {
		table.displayPlayerName(name, seatNumber);
	}

	/**
	 * 手札を見る
	 * 
	 */
	@Override
	public void lookAtHand(int seatNumber) {
		watchHand(seatNumber);
	}

	/**
	 * カードデッキを見る
	 */
	@Override
	public void lookAtCardDeck() {
		watchCardDeck();
	}
	
	/**
	 * 掛けチップ数を決める
	 */
	public void decideBetChipsValue(){
		betChips = strategy.decideBetChipsValue(this);
	}

	/**
	 * 戦略を決定する
	 */
	protected abstract void createStrategy();

	/**
	 * チップを賭ける
	 */
	public abstract void betChips();

	/**
	 * 手札を見る
	 * 
	 * @param seatNumber 座席番号
	 */
	protected abstract void watchHand(int seatNumber);

	/**
	 * カードデッキを見る
	 */
	protected abstract void watchCardDeck();

	/**
	 * 報酬チップを手に入れる
	 */
	public abstract void takeChips();

}
