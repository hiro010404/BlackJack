package cardgame.observer;

/**
 * 手札の観察者
 * 
 * @author Tsuyoshi Iwasaki
 * 
 * @version 2.0, 2007/12/05
 */
public interface HandSubject {

	/**
	 * 観察者を登録する
	 * 
	 * @param observer
	 *            観察者
	 */
	public abstract void registerHandObserver(HandObserver observer);

	/**
	 * 観察者に通知する
	 * 
	 * @param seatNumber
	 * 		座席番号
	 */
	public abstract void notifyHandObservers(int seatNumber);
}