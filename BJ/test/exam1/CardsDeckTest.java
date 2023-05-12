package exam1;

import static org.junit.Assert.*;

//import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

import cardgame.Configuration;
import cardgame.Deck;

/**
 * カード枚数テスト
 */
public class CardsDeckTest {

	int cardSetsNumber = Configuration.getConfiguration().getIntProperty(
			"Deck.deckNumbers");

	/**
	 * カード枚数のチェック
	 */
	@Test
	public void testCardsNumber() {
		Deck cardsDeck = new Deck();
		cardsDeck.setupDeck();
		assertEquals(cardSetsNumber * 52, cardsDeck.getCardsNumber());
	}

}
