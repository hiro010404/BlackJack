package cardgame;

import cardgame.blackjack.gui.UITable;

/**
 * カード
 * 
 * @author Yuuki Miyake
 * @author Takuma Torii
 * 
 * @version 2.0, 2007/09/27
 */
public class Card {

	/**
	 * カードの向きが表（true）
	 */
	public static final boolean FACE_UP = true;

	/**
	 * 裏の画像ファイル名
	 */
	public static final String FACE_DOWN_IMAGE_FILE = UITable.IMAGE_DIR
			+ "/FACE_DOWN.png";

	/**
	 * 名前
	 */
	private String name;

	/**
	 * getter
	 * 
	 * @return 名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * ランク
	 */
	private RANK rank;

	/**
	 * getter
	 * 
	 * @return ランク
	 */
	public RANK getRank() {
		return rank;
	}

	/**
	 * 絵柄
	 */
	private SUIT suit;

	/**
	 * getter
	 * 
	 * @return 絵柄
	 */
	public SUIT getSuit() {
		return suit;
	}

	/**
	 * カードの向き(表：true、裏：false）
	 */
	protected boolean faceUp = FACE_UP;

	/**
	 * getter
	 * 
	 * @return 表（true）または裏（false）
	 */
	public boolean isFaceUp() {
		return faceUp;
	}

	/**
	 * setter
	 * 
	 * @param faceUp 表（true）または裏（false）
	 */
	public void setFaceUp(boolean faceUp) {
		this.faceUp = faceUp;
	}

	/**
	 * 画像ファイル名
	 */
	private String imageFileName;

	/**
	 * getter
	 * 
	 * @return 画像ファイル名
	 */
	public String getImageFileName() {
		return imageFileName;
	}

	/**
	 * ランクの集合
	 */
	public enum RANK {
		ACE("A"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN(
				"7"), EIGHT("8"), NINE("9"), TEN("10"), JACK("J"), QUEEN("Q"), KING(
				"K");

		private String rank;

		/**
		 * getter
		 * 
		 * @return ランク
		 */
		public String getRank() {
			return rank;
		}

		/**
		 * アルファベットと番号を対応付ける
		 * 
		 * @param rank ランク
		 */
		RANK(String rank) {
			this.rank = rank;
		}

	}

	/**
	 * getter
	 * 
	 * @return ランクの集合
	 */
	public static RANK[] getRanks() {
		return RANK.values();
	}

	/**
	 * カードの番号を返す
	 * 
	 * @return カードの番号
	 */
	public int getCardNumber() {
		return rank.ordinal() + 1;
	}

	/**
	 * 絵柄
	 */
	public enum SUIT {
		Club, Diamond, Heart, Spade
	}

	/**
	 * getter
	 * 
	 * @return CardSuitの配列
	 */
	public static SUIT[] getSuits() {
		return SUIT.values();
	}

	/**
	 * カードのコンストラクタ
	 * 
	 * @param rank ランク.
	 * @param suit 絵柄.
	 */
	public Card(RANK rank, SUIT suit) {
		name = suit.name() + rank.getRank();
		this.rank = rank;
		this.suit = suit;
		imageFileName = "Card" + name + ".png";
	}

}
