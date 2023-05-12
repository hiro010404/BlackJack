package cardgame.observer;

/**
 * カードデッキの観察者
 * 
 * @author Tsuyoshi Iwasaki
 * 
 * @version 2.0, 2007/12/05
 */
public interface CardDeckSubject {

	/**
	 * 観察者を登録する
	 * 
	 * @param observer
	 *            観察者
	 */
	public abstract void registerCardDeckObserver(CardDeckObserver observer);

	/**
	 * 観察者に通知する
	 */
	public abstract void notifyCardDeckObservers();
}
