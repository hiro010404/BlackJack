package cardgame.blackjack.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import kwing.BorderLayout;
import kwing.Button;
import kwing.Dialog;
import kwing.FlowLayout;
import kwing.GridLayout;
import kwing.Label;
import kwing.Panel;
import kwing.RadioButton;
import kwing.TextField;
import kwing.Timer;
import cardgame.Configuration;
import cardgame.Dealer;
import cardgame.Player;
import cardgame.blackjack.gui.factory.WidgetFactory;

/**
 * ベットダイアログ
 * 
 * @author Shiro Takata
 * 
 * @version 3.0, 2015/12/02
 * @version 3.1, 2018/12/06
 */
public abstract class BlackJackDialog implements ActionListener, ItemListener {
		
	/**
	 * 表示時間（エージェントのみ）
	 */
	public static final int DIALOG_DISPLAY_TIME = Configuration
			.getConfiguration().getIntProperty("BetDialog.dialogDisplayTime");

	/**
	 * Widgetの部品を生成する工場
	 */
	public WidgetFactory factory; 
	
	/**
	 * ダイアログ
	 */
	protected Dialog dialog;

	/**
	 * タイマー
	 */
	protected Timer timer;

	/**
	 * BorderLayout
	 */
	protected BorderLayout borderLayout;

	/**
	 * FlowLayout
	 */
	protected FlowLayout flowLayout;

	/**
	 * GridLayout
	 */
	protected GridLayout gridLayout;

	/**
	 * 土台パネル
	 */
	protected Panel globePanel;

	/**
	 * 座席番号パネル
	 */
	protected Panel seatNumberPanel;

	/**
	 * 座席番号ラベル
	 */
	protected Label seatNumberLabel;

	/**
	 * プレイヤー情報パネル
	 */
	protected Panel playerInfoPanel;

	/**
	 * 名前ラベル
	 */
	protected Label nameLabel;
	
	/**
	 * 名前入力のテキストフィールド
	 */
	protected TextField nameTextField;

	/**
	 * 所持チップパネル
	 */
	protected Panel chipsValuePanel;
	
	/**
	 * 所持チップラベル
	 */
	protected Label chipsValueLabel;

	/**
	 * 掛けチップラベル
	 */
	protected Label betChipsLabel;
	
	/**
	 * チップス数入力フィールド
	 */
	protected TextField chipsValueTextField;
	
	/**
	 * OKボタンパネル
	 */
	protected Panel okButtonPanel;

	/**
	 * OKボタン
	 */
	protected Button okButton;

	/**
	 * モードパネル
	 */
	protected Panel modePanel;

	/**
	 * メッセージパネル
	 */
	protected Panel messagePanel;

	/**
	 * ヒットパネル
	 */
	protected Panel choicePanel;

	/**
	 * ラジオボタン(人間)
	 */
	protected RadioButton humanRadioButton;

	/**
	 * ラジオボタン(エージェント)
	 */
	protected RadioButton agentRadioButton;

	/**
	 * 戦略パネル
	 */
	protected Panel strategyPanel;
	
	/**
	 * 戦略名ラベル
	 */
	protected Label strategyNameLabel; 

	/**
	 * 入力全体パネル
	 */
	protected Panel inputsPanel;

	/**
	 * ステータスパネル
	 */
	protected Panel statusPanel;
	/**
	 * ステータスラベル
	 */
	protected Label statusLabel;

	/**
	 * 戦略のラジオボタンリスト
	 */
	protected List<RadioButton> strategyRadioButtons = new ArrayList<RadioButton>();

	/**
	 * 戦略それぞれのラジオボタン
	 */
	protected RadioButton radio;
	
	/**
	 * hit ラジオボタン
	 */
	protected RadioButton hitRadioButton;
	
	/**
	 * ラジオボタン(スタンド)
	 */
	protected RadioButton standRadioButton;
	
	/**
	 * メッセージラベル
	 */
	protected Label messageLabel;
	
	/**
	 * ベットパネル
	 */
	protected Panel betPanel;

	/**
	 * 賭けチップパネル
	 */
	protected Panel betChipsPanel;
	
	/**
	 * 賭けチップテキストフィールド
	 */
	protected TextField betChipsTextField;
	
	/**
	 * ベットメッセージパネル
	 */
	protected Panel betMessagePanel;
	
	/**
	 * メッセージラベル
	 */
	protected Label betMessageLabel;
	
	/**
	 * プレイヤーの購入チップ数ラベル
	 */
	protected Label exchangeChipsLabel;
	
	/**
	 * ラジオボタン(ベットする)
	 */
	protected RadioButton betRadioButton;

	/**
	 * ラジオボタン(ベットしない)
	 */
	protected RadioButton noBetRadioButton;

	/**
	 * BetDialogのコンンストラクタ
	 */
	public BlackJackDialog() {
	}
	
	/**
	 * Player情報を用いたダイアログの構築
	 * 
	 * @param player プレーヤー
	 */
	public void displayDialog(Player player){
	}
	
	/**
	 * ディーラー情報を用いたダイアログの構築
	 * 
	 * @param dealer ディーラー
	 */
	public void displayDialog(Dealer dealer){
	}

	/**
	 * ダイアログの再表示
	 */
	public void redisplayDialog(){
	}

	/**
	 * イベントを処理する
	 */
	@Override
	public void actionPerformed(ActionEvent event){
		mediateEvent(event.getSource());
	}

	@Override
	public void itemStateChanged(ItemEvent event){
		mediateEvent(event.getSource());
	}

	/**
	 * イベントを仲介する
	 * 
	 * @param source イベントオブジェクト
	 */
	public void mediateEvent(Object source){
	}

	/**
	 * ダイアログを閉じる
	 */
	public abstract void closeDialog(); 
}