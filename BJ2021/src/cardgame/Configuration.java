package cardgame;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 設定ファイルの読み込み
 * 
 * @author Torii Takuma
 * 
 * @version 2.0, 2007/12/05
 * @version 2.1, 2012/12/03 Takuma Torii Append `/` to access to JAR-internal
 *          files.
 */
public class Configuration {

	/**
	 * 単一の制御テーブル
	 */
	private static Configuration configuration = new Configuration();

	/**
	 * getter
	 * 
	 * @return configuration
	 */
	public static Configuration getConfiguration() {
		return configuration;
	}

	/**
	 * 設定ファイル名
	 */
	private static final String CONFIG_FILE = "bj.conf";

	/**
	 * プロパティ
	 */
	private Properties config = new Properties();

	private Configuration() {
		load();
	}

	/**
	 * 設定ファイル、あるいはそれと同じ場所に置かれているリソース
	 * ファイルをオープンして返す できなければnullが返る
	 */
	public InputStream openResource(String fileName) {
		try {	// Eclipseから実行する場合
			return new FileInputStream(fileName);
		} catch(IOException e){
		}

		{
			// javaコマンドからjarを実行する場合
			// Takuma Torii (2012/12/03)
			// Append `/` to access to JAR-internal files in this way.
			InputStream in = getClass().
				getResourceAsStream("/" + fileName);
			if(in != null) return in;
		}

		try {
			// javacでコンパイル後javaコマンドで実行する場合
			// (配布アーカイブではリソースファイルはsrcやbin
			// ディレクトリの真上にある)
			return new FileInputStream(
				System.getProperty("user.dir") + "/../" +
				fileName);
		} catch(IOException e){
		}

		return null;
	}
	/**
	 * 設定ファイルからデータを読み込む
	 */
	public void load() {
		try {
			config.load(openResource(CONFIG_FILE));
			return;
		} catch(IOException | NullPointerException error){
			error.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * 設定項目に対応する文字列を得る
	 * 
	 * @param key キー
	 * @return 文字列
	 */
	public String getStringProperty(String key) {
		return config.getProperty(key);
	}

	/**
	 * 設定項目に対応する文字列を得る(文字列がなければ初期値を返す)
	 * 
	 * @param key キー
	 * @param defaultValue 暗黙値
	 * @return 文字列
	 */
	public String getStringProperty(String key, String defaultValue) {
		return config.getProperty(key, defaultValue);
	}

	/**
	 * 設定項目に対応する数値を得る
	 * 
	 * @param key キー
	 * @return 整数
	 */
	public int getIntProperty(String key) {
		return Integer.parseInt(config.getProperty(key));
	}

	/**
	 * 設定項目に対応する数値を得る(数値がなければ暗黙値を返す)
	 * 
	 * @param key キー
	 * @param defaultValue 暗黙値
	 * @return 整数
	 */
	public int getIntProperty(String key, String defaultValue) {
		return Integer.parseInt(config.getProperty(key, defaultValue));
	}

	/**
	 * 設定項目に対応する二進値を得る
	 * 
	 * @param key キー
	 * @return 真偽
	 */
	public boolean getBooleanProperty(String key) {
		return config.getProperty(key).equals("true") ? true : false;
	}

	/**
	 * 設定項目に対応する二進値を得る(二進値がなければ暗黙値を返す)
	 * 
	 * @param key キー
	 * @param defaultValue 暗黙値
	 * @return 真偽
	 */
	public boolean getBooleanProperty(String key, String defaultValue) {
		return config.getProperty(key, defaultValue).equals("true") ? true
				: false;
	}

	/**
	 * Configurationの設定ファイル読み込みテスト
	 * 
	 * @param args 実行時パラメーター
	 */
	public static void main(String[] args) {
		Configuration conf = new Configuration();
		System.out.printf("Confiruration Test Deck.deckNumbers = %d", conf.getIntProperty("Deck.deckNumbers"));
	}
}
