package cardgame.blackjack.gui;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import kwing.Frame;
import kwing.Graphics;
import cardgame.Banner;
import cardgame.Card;
import cardgame.Configuration;
import cardgame.Hand;
import cardgame.Table;
import cardgame.blackjack.BlackJack;
import cardgame.blackjack.gui.factory.AWWidgetFactory;
import cardgame.blackjack.gui.factory.SWWidgetFactory;
import cardgame.blackjack.gui.factory.WidgetFactory;
import cardgame.chip.ChipsPack;

/**
 * テーブル
 * 
 * @author Yuuki Miyake
 * @author Takuma Torii
 * 
 * @version 2.0, 2007/12/05
 * @version 2.1, 2012/12/03 Takuma Torii Use ImageIO instead of
 *          java.awt.Toolkit.getImage().
 * @version 2.1, 2012/12/03 Takuma Torii Append `/` to access to JAR-internal
 *          files.
 */
public class UITable extends Table {

	/**
	 * ブラックジャックの表示タイトル
	 */
	private final String TITLE = Configuration.getConfiguration()
			.getStringProperty("UITable.Title");

	/**
	 * 遅延時間
	 */
	private static final int DELAY_TIME = Configuration.getConfiguration()
			.getIntProperty("UITable.delayTime");

	/**
	 * 画像ファイルの絶対パス
	 */
	public static final String IMAGE_DIR = Configuration.getConfiguration()
			.getStringProperty("UITable.imageDirectory");

	/**
	 * カードを重ねて描画する最大枚数
	 */
	private static final int MAX_CARD_LINEUP = Configuration.getConfiguration()
			.getIntProperty("UITable.cardLineUp");

	public static final int TABLE_PADDING_X = 10; // 左、右

	public static final int TABLE_PADDING_Y = 5; // 上、下

	public static final int FIELD_PADDING_X = 5; // 左、右

	public static final int FIELD_PADDING_Y = 0; // 上、下

	/**
	 * チップの山の高さ
	 */
	private static final int CHIPS_PACK_HEGHT = 3 * 14 + 35;

	/**
	 * 手札の高さ
	 */
	private static final int HAND_HEGHT = 20 * 3 + 20 * 2 + 103;

	/**
	 * バナーの高さ
	 */
	private static final int BANNER_HEIGHT = 64;

	/**
	 * ディーラーの座席の幅
	 */
	public static final int DEALER_FIELD_WIDTH = 175;

	/**
	 * ディーラーの座席の高さ
	 */
	public static final int DEALER_FIELD_HEIGHT = HAND_HEGHT;

	/**
	 * プレイヤー1人分の座席の幅
	 */
	public static final int PLAYER_FIELD_WIDTH = DEALER_FIELD_WIDTH;

	/**
	 * プレイヤー1人分の座席の高さ
	 */
	public static final int PLAYER_FIELD_HEIGHT = 315;

	/**
	 * プレイヤー同士の間隔
	 */
	public static final int PLAYER_INTERVAL_X = 180;

	/**
	 * プレイヤー名を表示する高さ
	 */
	public static final int NAME_HEIGHT = 0;

	/**
	 * テーブルの幅
	 */
	public static final int TABLE_WIDTH = TABLE_PADDING_X + FIELD_PADDING_X
			+ PLAYER_INTERVAL_X * (SEAT_NUMBERS - 1) + PLAYER_FIELD_WIDTH
			+ FIELD_PADDING_X + TABLE_PADDING_X;

	/**
	 * テーブルの高さ
	 */
	public static final int TABLE_HEIGHT = TABLE_PADDING_Y + FIELD_PADDING_Y
			+ BANNER_HEIGHT + DEALER_FIELD_HEIGHT + CHIPS_PACK_HEGHT
			+ PLAYER_FIELD_HEIGHT + NAME_HEIGHT + FIELD_PADDING_Y
			+ TABLE_PADDING_Y;

	/**
	 * 描画をずらす間隔
	 */
	private static final int BANNER_SPACING = 0;

	private static final int DEALER_SPACING_X = TABLE_WIDTH / 2
			- DEALER_FIELD_WIDTH / 2;

	private static final int DEALER_SPACING_Y = 100;

	private static final int REWARD_SPACING = DEALER_SPACING_Y
			+ DEALER_FIELD_HEIGHT - CHIPS_PACK_HEGHT + FIELD_PADDING_Y * 4;

	private static final int HAND_SPACING = (int) (DEALER_SPACING_Y
			+ DEALER_FIELD_HEIGHT + CHIPS_PACK_HEGHT * 1.5);

	private static final int BET_SPACING = HAND_SPACING + CHIPS_PACK_HEGHT + 7;

	private static final int NAME_SPACING = BET_SPACING + CHIPS_PACK_HEGHT + 52;

	/**
	 * 名前を描画する際の原点座標
	 */
	protected List<Point> nameOriginPoint = new ArrayList<Point>();

	/**
	 * 手札を描画する際の原点座標
	 */
	protected List<Point> handOriginPoint = new ArrayList<Point>();

	/**
	 * 賭けチップを描画する際の原点座標
	 */
	protected List<Point> betChipsOriginPoint = new ArrayList<Point>();

	/**
	 * 報酬チップを描画する際の原点座標
	 */
	protected List<Point> rewardChipsOriginPoint = new ArrayList<Point>();

	/**
	 * バナーを描画する際の原点座標
	 */
	protected List<Point> bannerOriginPoint = new ArrayList<Point>();

	/**
	 * テーブルを表示する
	 */
	protected Frame frame;

	/**
	 * テーブルを描画する
	 */
	protected Graphics graphics;

	/**
	 * Widgetの部品を生成する工場
	 */
	private WidgetFactory factory;

	/**
	 * UITableのコンストラクタ
	 */
	public UITable() {
	}

	@Override
	public ChipsPack createBetChipsPack(int seatNumber) {
		return new UIChipsPack(graphics, betChipsOriginPoint.get(seatNumber));
	}

	@Override
	protected Hand createHand(int seatNumber) {
		return new UIHand(graphics, handOriginPoint.get(seatNumber));
	}

	@Override
	protected Banner createBanner(int seatNumber) {
		return new UIBanner(graphics, bannerOriginPoint.get(seatNumber));
	}

	@Override
	public ChipsPack createRewardChipsPack(int seatNumber) {
		return new UIChipsPack(graphics, rewardChipsOriginPoint.get(seatNumber));
	}

	
	/**
	 * テーブルを構築する
	 */
	@Override
	public void setupTable() {
		
		String GUI = BlackJack.getGUI();
		if (GUI.equals(BlackJack.AWT)) {
			factory = AWWidgetFactory.getInstance();
		} else if (GUI.equals(BlackJack.SWING)) {
			factory = SWWidgetFactory.getInstance();
		}
		
		frame = factory.createFrame();
		graphics = factory.createGraphics(TABLE_WIDTH, TABLE_HEIGHT);

		frame.setTitle(TITLE);
		frame.add(graphics.getCanvas());

		drawTable();

		frame.setResizable(false);
		
		setUpOriginPoint();
		super.setupTable();
		
	}

	/**
	 * テーブルを描画する
	 */
	@Override
	public void drawTable() {
		delay(1);

		graphics.setColor("green");
		graphics.fillRect(0, 0, TABLE_WIDTH, TABLE_HEIGHT);

		drawArc();

		drawCardLine(SEAT_NUMBERS);

		drawCardDeck();
	}

	/**
	 * ゲームの進行を止める
	 * 
	 * @param delayTime 遅延時間
	 */
	public static void delay(int delayTime) {
		try {
			Thread.sleep(DELAY_TIME * delayTime);
		} catch (Exception e) {
			System.err.println(e);
			System.exit(1);
		}

	}

	/**
	 * ディーラーのフィールド（弧）を描画する
	 * 
	 */
	protected void drawArc() {

		graphics.setColor("white");
		graphics.setStroke(6.0f);
		graphics.drawArc(0, -(TABLE_HEIGHT / 2), TABLE_WIDTH - 10,
				(TABLE_HEIGHT - 60), 180, 180);
		graphics.setStroke(2.0f);

	}

	/**
	 * カード置場の白線を描画する
	 * 
	 * @param seatNumber 座席番号
	 */
	protected void drawCardLine(int seatNumber) {
		drawField(DEALER_SPACING_X, DEALER_SPACING_Y);

		for (int i = 1; i <= seatNumber; i++) {
			int x = getFieldPoint(i).x;
			int y = getFieldPoint(i).y + HAND_SPACING;

			drawField(x, y);
		}

	}

	/**
	 * 長方形の外枠を座標毎に描画する
	 * 
	 * @param x 描画開始x座標
	 * @param y 描画開始y座標
	 */
	protected void drawField(int x, int y) {
		graphics.setColor("white");
		graphics.drawRoundRect(x, y, UIHand.CARD_WIDTH, UIHand.CARD_HEIGHT);
		graphics.repaint();
	}

	/**
	 * 指定した座席の原点座標を返す
	 * 
	 * @param seatNumber
	 *            座席番号
	 * @return フィールドの原点
	 */
	protected Point getFieldPoint(int seatNumber) {
		int x = TABLE_PADDING_X + FIELD_PADDING_X + PLAYER_INTERVAL_X
				* (seatNumber - 1);
		int y = TABLE_PADDING_Y + FIELD_PADDING_Y;

		return new Point(x, y);
	}

	/**
	 * カードデッキを描画する
	 */
	public void drawCardDeck() {
		int x = DEALER_SPACING_X + DEALER_FIELD_WIDTH;
		int y = DEALER_SPACING_Y;

		for (int i = 0; i < MAX_CARD_LINEUP; i++) {
			graphics.draw(readCardDeck(), x + i, y - i);
		}
		graphics.repaint();
	}

	/**
	 * デッキを読み込む
	 * 
	 * @return デッキの画像
	 */
	protected Image readCardDeck() {
		String file = Card.FACE_DOWN_IMAGE_FILE;
		try {
			return ImageIO.read(Configuration.getConfiguration().
				openResource(file));
		} catch(IOException | IllegalArgumentException error){
			return null;
		}
		// Takuma Torii (2012/12/03)
		// The method getImage() is asynchronous, that is, it does not ensure to
		// load the image file on site.
		// This way may be unstable in the modern Java technology.
		// (see also java.awt.image.ImageObserver.)
		// The alternative, ImageIO.read() works fine in this context except the
		// image file does not exist.
		// For the reason, here, I will return null instead.
		/*
		 * return java.awt.Toolkit.getDefaultToolkit().getImage(
		 * getClass().getResource("/" + file));
		 */
	}

	/**
	 * 原点座標を追加、保持する
	 */
	private void setUpOriginPoint() {

		for (int seatNumber = DEALER_SEAT_NUMBER; seatNumber <= SEAT_NUMBERS; seatNumber++) {
			int relativeX = getFieldPoint(seatNumber).x;
			int relativeY = getFieldPoint(seatNumber).y;

			bannerOriginPoint.add(new Point(relativeX, relativeY
					+ BANNER_SPACING));
			rewardChipsOriginPoint.add(new Point(relativeX, relativeY
					+ REWARD_SPACING));

			if (seatNumber == DEALER_SEAT_NUMBER) {
				handOriginPoint.add(new Point(DEALER_SPACING_X,
						DEALER_SPACING_Y));
			} else {
				handOriginPoint.add(new Point(relativeX, relativeY
						+ HAND_SPACING));
			}

			betChipsOriginPoint.add(new Point(relativeX, relativeY
					+ BET_SPACING));
			nameOriginPoint.add(new Point(relativeX, relativeY + NAME_SPACING));
		}
	}

	/**
	 * プレイヤー名を座席に表示する
	 * 
	 * @param name
	 *            描画対象の名前の文字列
	 * @param seatNumber
	 *            描画する座席の番号
	 */
	@Override
	public void showPlayerName(String name, int seatNumber) {
		Point np = getNamePoint(seatNumber);

		graphics.drawText(name, np.x, np.y);
		graphics.repaint();
	}

	/**
	 * プレイヤー名を書くための相対座標を返す
	 * 
	 * @param seatNumber 座席番号
	 * @return 相対座標
	 */
	protected Point getNamePoint(int seatNumber) {
		return nameOriginPoint.get(seatNumber);
	}

	/**
	 * Swing版UITableの単体テスト テーブルを描画する
	 * 
	 * @param args 実行時パラメーター
	 */
	public static void main(String args[]) {
		Table table = new UITable();
		table.setupTable();
	}

}
