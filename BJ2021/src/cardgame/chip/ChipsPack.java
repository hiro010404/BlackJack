package cardgame.chip;

import java.util.ArrayList;
import java.util.List;

/**
 * チップの山
 * 
 * @author Yuuki Miyake
 * @author Jun Takata
 * 
 * @version 2.0, 2007/12/05
 */
public class ChipsPack {

	/**
	 * $100チップ
	 */
	private int chip100Type = (new Chip100()).getType();

	/**
	 * $25チップ
	 */
	private int chip25Type = (new Chip25()).getType();

	/**
	 * $5チップ
	 */
	private int chip5Type = (new Chip5()).getType();

	/**
	 * $1チップ
	 */
	private int chip1Type = (new Chip1()).getType();

	/**
	 * $100チップの山
	 */
	protected List<Chip> chips100 = new ArrayList<Chip>();

	/**
	 * $25チップの山
	 */
	protected List<Chip> chips25 = new ArrayList<Chip>();

	/**
	 * $5チップの山
	 */
	protected List<Chip> chips5 = new ArrayList<Chip>();

	/**
	 * $1チップの山
	 */
	protected List<Chip> chips1 = new ArrayList<Chip>();

	/**
	 * 所持チップ
	 */
	protected int chipsValue = 0;

	/**
	 * getter
	 * 
	 * @return 所持チップ
	 */
	public int getChipsValue() {
		return chipsValue;
	}
	
	/**
	 * setter
	 * 
	 * @param chipsValue
	 * 		所持チップ
	 */
	public void setChipsValue(int chipsValue) {
		 clearChipsPack();
		addChipsPack(chipsValue);
	}

	public ChipsPack() {
		chipsValue = 0;
	}

	/**
	 * チップを加える
	 * 
	 * @param addChipsValue
	 *            追加するチップ
	 */
	public void addChipsPack(int addChipsValue) {

		this.chipsValue += addChipsValue;

		for (int i = 0; i < addChipsValue / chip100Type; i++) {
			chips100.add(new Chip100());
		}
		addChipsValue = addChipsValue % chip100Type;

		for (int i = 0; i < addChipsValue / chip25Type; i++) {
			chips25.add(new Chip25());
		}
		addChipsValue = addChipsValue % chip25Type;

		for (int i = 0; i < addChipsValue / chip5Type; i++) {
			chips5.add(new Chip5());
		}
		addChipsValue = addChipsValue % chip5Type;

		for (int i = 0; i < addChipsValue / chip1Type; i++) {
			chips1.add(new Chip1());
		}
	}

	/**
	 * 所持チップから、賭けチップを減算する
	 * 
	 * @param betChips
	 *            賭けチップ
	 * @return 減算された所持チップ
	 */
	public int subtractChipsValue(int betChips) {
		int result = this.chipsValue - betChips;
		clearChipsPack();
		addChipsPack(result);

		return result;
	}

	/**
	 * 所持チップを取り去る
	 */
	public void clearChipsPack() {
		chips100.clear();
		chips25.clear();
		chips5.clear();
		chips1.clear();

		chipsValue = 0;
	}

	/**
	 * @return $100チップの枚数
	 */
	protected int getChip100Count() {
		return chips100.size();
	}

	/**
	 * @return $25チップの枚数
	 */
	protected int getChip25Count() {
		return chips25.size();
	}

	/**
	 * @return $5チップの枚数
	 */
	protected int getChip5Count() {
		return chips5.size();
	}

	/**
	 * @return $1チップの枚数
	 */
	protected int getChip1Count() {
		return chips1.size();
	}
}
