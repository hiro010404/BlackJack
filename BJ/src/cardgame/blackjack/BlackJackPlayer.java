package cardgame.blackjack;

import cardgame.Player;
import cardgame.blackjack.gui.BetDialog;
import cardgame.blackjack.gui.BlackJackDialog;
import cardgame.blackjack.gui.ChoiceDialog;
import cardgame.blackjack.gui.JoinDialog;
import cardgame.blackjack.gui.factory.AWWidgetFactory;
import cardgame.blackjack.gui.factory.SWWidgetFactory;
import cardgame.blackjack.gui.factory.WidgetFactory;
import cardgame.blackjack.strategy.BasicStrategy;
import cardgame.blackjack.strategy.BearStrategy;
import cardgame.blackjack.strategy.BullStrategy;
import cardgame.blackjack.strategy.HumanStrategy;
import cardgame.blackjack.strategy.RandomStrategy;
import cardgame.blackjack.strategy.SimpleStrategy;
import cardgame.blackjack.strategy.Strategy;

/**
 * ブラックジャックプレイヤー
 *
 * @author Shinji Saito
 * @author Shiro Takata
 *
 * @version 3.0, 2015/12/01
 */
public class BlackJackPlayer extends Player {

	/**
	 * プレイヤー情報のダイアログ
	 */
	protected BlackJackDialog joinDialog;

	/**
	 * 賭けチップのダイアログ
	 */
	protected BlackJackDialog betDialog;

	/**
	 * ヒットまたはスタンド選択に関するダイアログ
	 */
	protected BlackJackDialog choiceDialog;

	/**
	 * BlackJackPlayerのコンストラクタ
	 */
	public BlackJackPlayer() {
	}

	/**
	 * BlackJackPlayerをセットアップする
	 */
	@Override
	public void setupPlayer(int seatNumber) {
		super.setupPlayer(seatNumber);
		createDialogs();
		setupBlackJackPlayer();
		displayMyName();
		createStrategy();
	}

	/**
	 * 各種ダイアログを生成する
	 */
	public void createDialogs() {
		joinDialog = createJoinDialog();
		betDialog = createBetDialog();
		choiceDialog = createChoiceDialog();
	}

	/**
	 * JoinDialogを表示して、名前、人間かエージェントか、購入チップ数、戦略などをセットアップする
	 */
	private void setupBlackJackPlayer() {
		joinDialog.displayDialog(this);
		chipsValue.addChipsPack(exchangeChips);
		betDialog.displayDialog(this);
		choiceDialog.displayDialog(this);
	}

	/**
	 * チップを賭ける
	 */
	@Override
	public void betChips() {
		decideBetChipsValue();
		betDialog.redisplayDialog();
		chipsValue.subtractChipsValue(betChips);
		table.putBetChips(betChips, getSeatNumber());
	}

	/**
	 * Hitすると思案しているか
	 */
	public void isGettingHit(){
		choice = strategy.isGettingHit(this);
	}

	/**
	 * ヒットまたはスタンドを返す
	 *
	 * @return ヒット（true）・スタンド（false）
	 */
	public boolean isHitSelected() {
		isGettingHit();
		choiceDialog.redisplayDialog();
		return choice = ((ChoiceDialog)choiceDialog).isHitSelected();
	}

	@Override
	public void takeChips() {
		chipsValue.addChipsPack(table.takeBetChips(getSeatNumber()));
		chipsValue.addChipsPack(table.takeRewardChips(getSeatNumber()));
	}

	@Override
	protected void watchHand(int seatNumber) {
	}

	@Override
	protected void watchCardDeck() {
		;
	}

	@Override
	protected void createStrategy() {

		if (human) {
			strategy = new HumanStrategy();
		} else if (strategyName.equals(Strategy.STRATEGY.BasicStrategy.name())) {
			strategy = new BasicStrategy();
		} else if (strategyName.equals(Strategy.STRATEGY.BearStrategy.name())) {
			strategy = new BearStrategy();
		} else if (strategyName.equals(Strategy.STRATEGY.BullStrategy.name())) {
			strategy = new BullStrategy();
		} else if (strategyName.equals(Strategy.STRATEGY.SimpleStrategy.name())) {
			strategy = new SimpleStrategy();
		} else if (strategyName.equals(Strategy.STRATEGY.RandomStrategy.name())) {
			strategy = new RandomStrategy();
		} else {
			strategy = null;
			System.err.println(strategyName + "が存在しません");
			System.exit(1);
		}
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

	public BlackJackDialog createJoinDialog() {
		return new JoinDialog(factory);
	}

	public BlackJackDialog createBetDialog() {
		return new BetDialog(factory);
	}

	public BlackJackDialog createChoiceDialog() {
		return new ChoiceDialog(factory);
	}

}

