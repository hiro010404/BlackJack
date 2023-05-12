package cardgame.blackjack.gui;

import kwing.BorderLayout;
import kwing.RadioButton;
import cardgame.Configuration;
import cardgame.Player;
import cardgame.blackjack.gui.factory.WidgetFactory;
import cardgame.blackjack.strategy.Strategy;

/**
 * ゲーム参加時のプレイヤー情報を入力するダイアログ
 * 
 * @author Tsuyoshi Iwasaki
 * @author Shiro Takata
 * 
 * @version 2.1, 2013/12/12
 * @version 3.0, 2015/12/02
 * @version 3.1, 2018/12/06
 */
public class JoinDialog extends BlackJackDialog {

	/**
	 * Player の名前（デフォルト）
	 */
	public static final String NAME = Configuration.getConfiguration().getStringProperty("ChoiceDialog.playerName");	
	/**
	 * 人間の場合に使用する戦略名
	 */
	public final String HUMAN_STRATEGY = "HumanStrategy";

	/**
	 * 購入チップス数（デフォルト）
	 */
	public static final int CHIPS_VALUE= Configuration
			.getConfiguration().getIntProperty("ChoiceDialog.chipsValue");

	/**
	 * Player
	 */
	Player player;
	
	/**
	 * 人間（デフォルト）またはエージェント
	 */
	protected boolean human = true;

	/**
	 * getter
	 * 
	 * @return human
	 */
	public boolean isHuman() {
		return human;
	}

	/**
	 * JoinDialogのコンストラクタ
	 * 
	 * @param factory Widgetの部品を生成する工場
	 */
	public JoinDialog(WidgetFactory factory) {
		this.factory = factory;
	}

	/**
	 * Playerのダイアログのセットアップ
	 */
	@Override
	public void displayDialog(Player player) {
		
		this.player = player;

		/* Layout の生成 */
		borderLayout = factory.createBorderLayout();
		flowLayout = factory.createFlowLayout();
		gridLayout = factory.createGridLayout();

		/* 座席番号 */
		seatNumberPanel = factory.createPanel();
		seatNumberPanel.setLayout(flowLayout);		
		seatNumberLabel = factory.createLabel();
		seatNumberLabel.setText("座席番号 : " + player.getSeatNumber());
		seatNumberPanel.add(seatNumberLabel);

		/* プレイヤー名入力 パネル */
		playerInfoPanel = factory.createPanel();
		playerInfoPanel.setLayout(flowLayout);
		
		nameLabel = factory.createLabel();
		nameLabel.setText("名前");
		playerInfoPanel.add(nameLabel);
		
		nameTextField = factory.createTextField();
		nameTextField.setColumns(8);
		nameTextField.setText(NAME);
		playerInfoPanel.add(nameTextField);

		/* 購入チップ数入力　パネル */
		chipsValuePanel = factory.createPanel();
		chipsValuePanel.setLayout(flowLayout);
		
		chipsValueLabel = factory.createLabel();
		chipsValueLabel.setText("購入チップ数");
		chipsValuePanel.add(chipsValueLabel);
		
		chipsValueTextField = factory.createTextField();
		chipsValueTextField.setColumns(4);
		chipsValueTextField.setText(String.valueOf(CHIPS_VALUE));
		chipsValuePanel.add(chipsValueTextField);

		/* プレーヤーモード（人間 or エージェント) */
		modePanel = factory.createPanel();
		modePanel.setLayout(flowLayout);		
		/* 人間ボタン */
		humanRadioButton = factory.createRadioButton();
		humanRadioButton.setText("人間");
		humanRadioButton.addActionListener(this);
		/* モード（人間かエージェント）デフォルトは人間 */
		humanRadioButton.setSelected(isHuman());
		modePanel.add(humanRadioButton);
		
		/* エージェントボタン */
		agentRadioButton = factory.createRadioButton();
		agentRadioButton.setText("エージェント");
		agentRadioButton.addActionListener(this);
		agentRadioButton.setSelected(!isHuman());
		modePanel.add(agentRadioButton);

		/* 戦略ラジオボタンをパネルに追加 */
		strategyPanel = factory.createPanel();

		for (Strategy.STRATEGY strategy : Strategy.STRATEGY.getStrategies()) {
			radio = factory.createRadioButton();
			radio.setText(strategy.name());
			strategyRadioButtons.add(radio);
			/* セットされた初期選択戦略を有効にする */
			if (radio.getText().equals(Strategy.STRATEGY.BasicStrategy.name())) {
				radio.setSelected(true);
			}
			strategyPanel.add(radio);
		}
		
		/* セットした戦略数のレイアウトにする　*/
		gridLayout.setRows(strategyRadioButtons.size());
		gridLayout.setColumns(1);
		strategyPanel.setLayout(gridLayout);
		
		/* Radioボタンの非表示 */
		setStrategyRadioButtonsEnable(!isHuman());

		/* ボタン */
		okButtonPanel = factory.createPanel();
		okButtonPanel.setLayout(flowLayout);
		
		okButton = factory.createButton();
		okButton.setText("OK");
		okButton.addActionListener(this);
		okButtonPanel.add(okButton);

		/* 入力 */
		inputsPanel = factory.createPanel();
		inputsPanel.setLayout(flowLayout);
		inputsPanel.add(seatNumberPanel);
		inputsPanel.add(playerInfoPanel);
		inputsPanel.add(chipsValuePanel);
		inputsPanel.add(modePanel);
		inputsPanel.add(strategyPanel);
		inputsPanel.add(okButtonPanel);

		/* ステータス */
		statusPanel = factory.createPanel();
		statusLabel = factory.createLabel();
		statusLabel.setText("参加するプレイヤーの情報を入力してください。");
		statusPanel.add(statusLabel);

		/* 土台 */
		globePanel = factory.createPanel();
		globePanel.setLayout(borderLayout);
		globePanel.add(inputsPanel, BorderLayout.NORTH);
		globePanel.add(statusPanel, BorderLayout.SOUTH);
		
		dialog = factory.createDialog();
		dialog.setTitle("Entry");
		dialog.setResizable(false);
		dialog.add(globePanel);
		dialog.pack();
		dialog.setVisible(true);
	}

	/**
	 * イベントを仲介する
	 * 
	 * @param source オブジェクト
	 */
	@Override
	public void mediateEvent(Object source) {

		if (source.equals(humanRadioButton.getWidget())) {
			setStrategyRadioButtonsEnable(false);
		} else if (source.equals(agentRadioButton.getWidget())) {
			setStrategyRadioButtonsEnable(true);
		} else if (source.equals(okButton.getWidget())) {
			if (verify()) {
				closeDialog();
			}
		}
	}

	/**
	 * ラジオボタンの表示・非表示を行う
	 * 
	 * @param enable 有効性
	 */
	private void setStrategyRadioButtonsEnable(boolean enable) {
		for (RadioButton strategy : strategyRadioButtons) {
			strategy.setEnabled(enable);
		}
	}

	/**
	 * 入力値を検証する
	 * 
	 * @return 入力値が正しいかどうか
	 */
	public boolean verify() {
		boolean isCorrect = verifyPlayerName() && verifyChips();
		dialog.pack();

		return isCorrect;
	}

	/**
	 * プレイヤー名の入力値を検証する
	 * 
	 * @return プレイヤー名が正しいか
	 */
	private boolean verifyPlayerName() {
		boolean isCorrect = true;

		if (nameTextField.getText().trim().equals("")) {
			statusLabel.setText("名前を入力してください!!");
			isCorrect = false;
		}

		return isCorrect;
	}

	/**
	 * チップの入力値を検証する
	 * 
	 * @return チップが基準を満たしているか
	 */
	private boolean verifyChips() {
		boolean isCorrect = true;

		try {
			int value = Integer.parseInt(chipsValueTextField.getText());
			if (value > 4096 || value <= 0) {
				statusLabel.setText("所持金は$1以上、$4096以下の数字を入力してください!!");
				isCorrect = false;
			}
		} catch (NumberFormatException e) {// 数字以外を入力した時
			statusLabel.setText("所持金は数字を入力してください!!");
			isCorrect = false;
		}

		return isCorrect;
	}

	/**
	 * ダイアログを閉じる
	 */
	public void closeDialog() {

		/* 各種入力をセットする */
		player.setName(nameTextField.getText());
		player.setExchangeChips(Integer.parseInt(chipsValueTextField.getText()));
		player.setHuman(humanRadioButton.isSelected());

		/* 選択された戦略名をセット */
		if (humanRadioButton.isSelected()) {
			player.setStrategyName (HUMAN_STRATEGY);
		} else {
			for (RadioButton strategy : strategyRadioButtons) {
				if (strategy.isSelected()) {
					player.setStrategyName(strategy.getText());
				}
			}
		}
		/* Dialogを不可視にする */
		dialog.setVisible(false);
	}
	
}