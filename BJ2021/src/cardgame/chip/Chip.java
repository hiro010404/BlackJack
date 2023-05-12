package cardgame.chip;

import cardgame.Configuration;

/**
 * チップ
 * 
 * @author Yuuki Miyake
 * @author Takuma Torii
 * @author Jun Takata
 * 
 * @version 2.0, 2007/12/05
 */
public class Chip {

	/**
	 * 種類
	 */
	private int type;

	/**
	 * getter
	 * 
	 * @return 種類
	 */
	public int getType() {
		return type;
	}

	/**
	 * 額面
	 */
	private String name;

	/**
	 * getter
	 * 
	 * @return 額面
	 */
	public String getName() {
		return name;
	}

	/**
	 * 画像ファイル名
	 */
	private String imageFileName = Configuration.getConfiguration()
			.getStringProperty("UITable.imageDirectory") + "/";

	/**
	 * getter
	 * 
	 * @return 画像ファイル名
	 */
	public String getImageFileName() {
		return imageFileName;
	}

	Chip(int type, String name) {
		this.type = type;
		this.name = name;
		imageFileName += name + ".png";
	}
}
