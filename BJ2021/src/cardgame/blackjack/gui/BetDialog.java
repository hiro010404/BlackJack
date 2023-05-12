package cardgame.blackjack.gui;

import cardgame.Player;
import cardgame.blackjack.gui.factory.WidgetFactory;

/**
 * ベットダイアログ
 * 
 * @author Hiroki Tanaka
 * @author Shiro Takata
 * 
 * @version 2.0, 2007/12/05
 * @version 3.0, 2015/12/02
 * @version 3.2, 2019/12/06
 */
public class BetDialog extends BlackJackDialog{

	/**
	 * Player
	 */
	Player player;
	
	/**
	 * 賭けチップ
	 */
	private int betChips;
	
	/**
	 * BetDialogのコンンストラクタ
	 * 
	 * @param factory Widgetの部品を生成する工場
	 */
	public BetDialog(WidgetFactory factory) {
		this.factory = factory;
	}

	/**
	 * Player情報を用いたダイアログの構築
	 */
	@Override
	public void displayDialog(Player player) {
		
		this.player = player;
		
		/* タイマー */
		timer = factory.createTimer(DIALOG_DISPLAY_TIME, this);

		/* プレイヤー情報 */
		playerInfoPanel = factory.createPanel();
		playerInfoPanel.setLayout(factory.createFlowLayout());
		
		/* 座席番号 */
		seatNumberLabel = factory.createLabel();
		seatNumberLabel.setText("座席番号：" + String.valueOf(player.getSeatNumber()));
		playerInfoPanel.add(seatNumberLabel);
		
		/* プレイヤー名 */
		nameLabel = factory.createLabel();
		nameLabel .setText("名前：" + player.getName());
		playerInfoPanel.add(nameLabel);
		
		/* 戦略名 */
		strategyNameLabel = factory.createLabel();
		strategyNameLabel.setText("戦略：" + player.getStrategyName());
		playerInfoPanel.add(strategyNameLabel);
		
		/* チップ情報 */
		chipsValuePanel = factory.createPanel();
		chipsValuePanel.setLayout(factory.createFlowLayout());
		
		/* 購入チップ */
		exchangeChipsLabel = factory.createLabel();
		chipsValuePanel.add(exchangeChipsLabel);
		
		/* 所持チップ */
		chipsValueLabel = factory.createLabel();
		chipsValuePanel.add(chipsValueLabel);
	
		/* ベットするかどうか */
		betMessagePanel = factory.createPanel();
		betMessagePanel.setLayout(factory.createFlowLayout());
		betMessageLabel = factory.createLabel();
		betMessageLabel.setText("ベットしますか ?");	
		betMessagePanel.add(betMessageLabel);

		/* ベットラジオパネル */
		betPanel = factory.createPanel();
		betPanel.setLayout(factory.createFlowLayout());
		
		/* ベットするラジオボタン */
		betRadioButton = factory.createRadioButton();
		betRadioButton.addActionListener(this);
		betRadioButton.setText("ベットする");
		betRadioButton.setSelected(true);
		betPanel.add(betRadioButton);
		
		/* ベットしないラジオボタン */
		noBetRadioButton = factory.createRadioButton();
		noBetRadioButton.addActionListener(this);
		noBetRadioButton.setText("ベットしない");
		betPanel.add(noBetRadioButton);
		
		/* ベットチップ数 */
		betChipsPanel = factory.createPanel();
		betChipsPanel.setLayout(factory.createFlowLayout());	
		betChipsTextField = factory.createTextField();
		betChipsTextField.setColumns(4);
//		betChipsTextField.setText(String.valueOf(player.getBetChips()));
		betChipsPanel.add(betChipsTextField);

		/* ボタン */
		okButtonPanel = factory.createPanel();
		okButtonPanel.setLayout(factory.createFlowLayout());
		okButton = factory.createButton();
		okButton.setText("OK");
		okButton.addActionListener(this);
		okButtonPanel.add(okButton);

		/* ステータス */
		statusPanel = factory.createPanel();
		statusLabel = factory.createLabel();
		statusPanel.add(statusLabel);

		/* 土台 */
		globePanel = factory.createPanel();

		/* レイアウト */
		gridLayout = factory.createGridLayout();
		gridLayout.setColumns(1);
		gridLayout.setRows(7);
		
		globePanel.setLayout(gridLayout);
		globePanel.add(playerInfoPanel);
		globePanel.add(chipsValuePanel);
		globePanel.add(betMessagePanel);
		globePanel.add(betPanel);
		globePanel.add(betChipsPanel);
		globePanel.add(okButtonPanel);
		globePanel.add(statusPanel);

		/* ダイアログ */
		dialog = factory.createDialog();
		dialog.setTitle("bet : ");
		dialog.setResizable(false);
		dialog.add(globePanel);
		
	}

	/**
	 * ダイアログを開く
	 */
	@Override
	public void redisplayDialog() {
		
		/* 戦略名、前回掛けチップ数と所持チップ数の表示 */
		strategyNameLabel.setText("戦略：" + player.getStrategyName());
		betChips = player.getBetChips(); 
		betChipsTextField.setText(String.valueOf(betChips));
		chipsValueLabel.setText("所持チップ:"+ String.valueOf(player.getChipsValue()));
		
		statusLabel.setText("");
		if (player.isHuman()) {
			betChipsTextField.setEnabled(true);
			betRadioButton.setSelected(true);
		} else {
			timer.start();
			betRadioButton.setEnabled(false);
			noBetRadioButton.setEnabled(false);
			betChipsTextField.setEnabled(false);
			betChipsTextField.setEditable(false);
			okButton.setEnabled(false);

			if (Integer.parseInt(betChipsTextField.getText()) <= 0) {
				betRadioButton.setSelected(false);
				noBetRadioButton.setSelected(true);
			}
		}

		/* ダイアログ */
		dialog.pack();
		dialog.setVisible(true);
	}

	/**
	 * イベントを仲介する
	 * 
	 * @param source イベントオブジェクト
	 */
	public void mediateEvent(Object source) {

		if (source.equals(timer.getWidget())) {
			closeDialog();
		} else if (source.equals(betRadioButton.getWidget())) {
			betChipsTextField.setEnabled(true);
		} else if (source.equals(noBetRadioButton.getWidget())) {
			betChips = 0;
			betChipsTextField.setText(String.valueOf(betChips));
			betChipsTextField.setEnabled(false);
		} else if (source.equals(okButton.getWidget())) {
			if (verify()) {
				closeDialog();
			}
		}
	}

	/**
	 * 入力された値を検証する
	 * 
	 * @return 検証結果（真-true, 偽-false）
	 */
	public boolean verify() {
		boolean isCorrect = true;

		if (!(betRadioButton.isSelected())) {
		} else {

			try {
				betChips = Integer.parseInt(betChipsTextField.getText());
				if (betChips <= 0 || player.getChipsValue() < betChips) {
					statusLabel.setText("所持金より少ない正の整数を入力してください!!");
					isCorrect = false;
				}	
			} catch (NumberFormatException error) {// 数字以外を入力した時
				statusLabel.setText("数字を入力してください !!");
				isCorrect = false;
			}
		}

		dialog.pack();
		return isCorrect;
	}

	/**
	 * ダイアログを閉じる
	 */
	public void closeDialog() {
		timer.stop();
		dialog.setVisible(false);
		player.setBetChips(betChips);
	}

}