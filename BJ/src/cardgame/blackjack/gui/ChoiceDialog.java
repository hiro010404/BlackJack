package cardgame.blackjack.gui;

import cardgame.Dealer;
import cardgame.Player;
import cardgame.Table;
import cardgame.blackjack.gui.factory.WidgetFactory;

/**
 * ヒットまたは、スタンドを選択するダイアログ
 * 
 * @author Shinji Saito
 * @author Shiro Takata
 * 
 * @version 2.0, 2007/12/05
 * @version 3.0, 2015/12/02
 * @version 3.2, 2019/12/06
 */
public class ChoiceDialog extends BlackJackDialog {

	/**
	 * Player
	 */
	Player player;
	
	/**
	 * Dealer
	 */
	Dealer dealer;
	
	/**
	 * Player
	 * Dealer か Playerの区別
	 */
	private boolean isPlayer;
	
	/**
	 * 人間またはエージェント(人間 : 真、エージェント : 偽)
	 */
	private boolean human;
	
	/**
	 * 選択(ヒット : 真、スタンド : 偽)
	 */
	private boolean choice;
	
	/**
	 * Hitが選択されたかどうか
	 * 
	 * @return 真（hit）・偽（stand）
	 */
	public boolean isHitSelected() {
		return hitRadioButton.isSelected();
	}

	/**
	 * ChoiceDialogのコンンストラクタ
	 * 
	 * @param factory Widgetの部品を生成する工場
	 */
	public ChoiceDialog(WidgetFactory factory) {
		this.factory = factory;
	}

	/**
	 * Player情報を用いたダイアログの構築
	 */
	@Override
	public void displayDialog(Player player) {
		
		isPlayer = true;
		this.player = player;
		
		/* 土台 */
		globePanel = factory.createPanel();

		/* レイアウト */
		gridLayout = factory.createGridLayout();
		gridLayout.setRows(5);
		gridLayout.setColumns(1);
		globePanel.setLayout(gridLayout);
		
		/* プレイヤー情報 */
		playerInfoPanel = factory.createPanel();
		playerInfoPanel.setLayout(factory.createFlowLayout());
		
		/* 座席番号 */
		seatNumberLabel = factory.createLabel();
		seatNumberLabel.setText("座席番号：" + String.valueOf(player.getSeatNumber()));
		
		/* プレイヤー名 */
		nameLabel = factory.createLabel();
		nameLabel .setText("名前：" + player.getName());
		
		/* 戦略名 */
		strategyNameLabel = factory.createLabel();
		
		playerInfoPanel.add(seatNumberLabel);
		playerInfoPanel.add(nameLabel);
		playerInfoPanel.add(strategyNameLabel);
		globePanel.add(playerInfoPanel);
		
		/* チップ情報 */
		chipsValuePanel = factory.createPanel();
		chipsValuePanel.setLayout(factory.createFlowLayout());
		
		/* 購入チュップ */
		exchangeChipsLabel = factory.createLabel();
		exchangeChipsLabel.setText("購入チップ:"+ String.valueOf(player.getExchangeChips()));
		
		/* 所持チップ */
		chipsValueLabel = factory.createLabel();
		
		/* 掛けチップ */
		betChipsLabel = factory.createLabel();
		
		chipsValuePanel.add(exchangeChipsLabel);
		chipsValuePanel.add(chipsValueLabel);
		chipsValuePanel.add(betChipsLabel);		
		globePanel.add(chipsValuePanel);

		human = player.isHuman();

		/* OKボタン */
		okButtonPanel = factory.createPanel();
		okButtonPanel.setLayout(factory.createFlowLayout());
		okButton = factory.createButton();
		okButton.setText("OK");
		okButton.addActionListener(this);
		okButtonPanel.add(okButton);
		
		setupDialog();	
	}

	/**
	 * Dealer情報を用いたダイアログの構築
	 */
	@Override
	public void displayDialog(Dealer dealer) {
		
		isPlayer = false;
		this.dealer = dealer;
		
		/* 土台 */
		globePanel = factory.createPanel();

		/* レイアウト */
		gridLayout = factory.createGridLayout();
		gridLayout.setRows(4);
		gridLayout.setColumns(1);
		globePanel.setLayout(gridLayout);
		
		/* プレイヤー情報 */
		playerInfoPanel = factory.createPanel();
		playerInfoPanel.setLayout(factory.createFlowLayout());
		
		/* 座席番号 */
		seatNumberLabel = factory.createLabel();
		seatNumberLabel.setText("座席番号：" + String.valueOf(Table.DEALER_SEAT_NUMBER));
		
		/* プレイヤー名 */
		nameLabel = factory.createLabel();
		nameLabel .setText("名前：" + dealer.getName());
		
		/* 戦略名 */
		strategyNameLabel = factory.createLabel();
		strategyNameLabel.setText("戦略：" + dealer.getName());
		
		playerInfoPanel.add(seatNumberLabel);
		playerInfoPanel.add(nameLabel);
		playerInfoPanel.add(strategyNameLabel);
		globePanel.add(playerInfoPanel);

		human = false;
		
		/* OKボタン */
		okButtonPanel = factory.createPanel();
		okButtonPanel.setLayout(factory.createFlowLayout());
		okButton = factory.createButton();
		okButton.setText("OK");
		okButtonPanel.add(okButton);

		setupDialog();	
	}

	/**
	 * ダイアログの構築
	 */
	public void setupDialog() {

		/* タイマー */
		timer = factory.createTimer(DIALOG_DISPLAY_TIME, this);	
		
		/* メッセージ */	
		messagePanel = factory.createPanel();
		messagePanel.setLayout(factory.createFlowLayout());
		messageLabel = factory.createLabel();
		messageLabel.setText("Hitしますか? Standしますか? ");
		messagePanel.add(messageLabel);
		globePanel.add(messagePanel);

		/* ヒット・スタンド */
		choicePanel = factory.createPanel();
		choicePanel.setLayout(factory.createFlowLayout());
		
		/* Hit ボタン */
		hitRadioButton = factory.createRadioButton();
		hitRadioButton.setText("Hit");

		
		/* Stand ボタン */	
		standRadioButton = factory.createRadioButton();
		standRadioButton.setText("Stand");

		
		choicePanel.add(hitRadioButton);
		choicePanel.add(standRadioButton);
		globePanel.add(choicePanel);

		globePanel.add(okButtonPanel);

		/* ダイアログ */
		dialog = factory.createDialog();
		dialog.setTitle("ヒットまたはスタンド選択ダイアログ");
		dialog.setResizable(false);
		dialog.add(globePanel);
	}

	/**
	 * ダイアログを開く
	 */
	@Override
	public void redisplayDialog() {

		if (isPlayer){
			strategyNameLabel.setText("戦略：" + player.getStrategyName());
			chipsValueLabel.setText("所持チップ:"+ String.valueOf(player.getChipsValue()));
			betChipsLabel.setText("掛けチップ:"+ String.valueOf(player.getBetChips()));
			choice = player.getChoice();
		} else {
			choice = dealer.getChoice();
		}
		
		hitRadioButton.setSelected(choice);
		standRadioButton.setSelected(!choice);
				
		if (!human) {
			hitRadioButton.setEnabled(false);
			standRadioButton.setEnabled(false);
			okButton.setEnabled(false);
			timer.start();
		}
		
		dialog.pack();
		dialog.setVisible(true);
	}

	/**
	 * イベントを処理する
	 * 
	 */
	@Override
	public void mediateEvent(Object source) {

		if (source.equals(timer.getWidget())) {
			timer.stop();
			closeDialog();
		} else if (source.equals(okButton.getWidget())) {
			closeDialog();
		}
	}

	/**
	 * ダイアログを閉じる
	 */
	public void closeDialog() {
		dialog.setVisible(false);
	}
	
}