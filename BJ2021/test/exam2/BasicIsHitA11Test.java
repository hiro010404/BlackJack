package exam2;

import cardgame.CardGame;
import cardgame.Table;
import cardgame.Card;
import cardgame.Card.RANK;
import cardgame.Card.SUIT;
import cardgame.blackjack.gui.UITable;
import cardgame.blackjack.BlackJackPlayer;
import cardgame.blackjack.ExtendedBlackJackPlayer;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Basic Strategy の単体テスト（網羅テスト）
 */
public class BasicIsHitA11Test {

	static Table table;
	static BlackJackPlayer player;
	RANK[] ranks = Card.getRanks();		// カードランクの集合 ACE("A"),...,KING("K")
	boolean expected;	// Hit(true) or Stand(false) 期待値

	@BeforeClass					// TableとPlayerの準備として1回だけ実行する
	public static void setUp() {
		table = new UITable();
		CardGame.setTable(table);
		table.setupTable(); 	// Tableの準備
		player = new ExtendedBlackJackPlayer();
		player.setupPlayer(1);	// 座席番号1のPlayerの準備
	}
	/**
	 * A(エース)を11と数えるとき
	 */
	@Test
	public void  Aを11と数えた時のchoiceテスト01() {
		// テスト条件: cardTotal >= 19
		// Playerの手札: A, {8,...,10}; stand
		expected = false;
		
		for (int i = 7; i < 10; i++){
			table.putCard(new Card(RANK.ACE, SUIT.Club), 1);
			table.putCard(new Card(ranks[i], SUIT.Diamond), 1);		
			player.isGettingHit();
			assertThat(player.getChoice(),is(expected));
			table.clearObject();
		}
	}

	@Test
	public void  Aを11と数えた時のchoiceテスト02() {
		// テスト条件: cardTotal = 18 && dealerFaceUpCard >= 2 && dealerFaceUpCard <= 8
		// Playerの手札: A, 7; Dealerの手札: 2,..,8: stand
		expected = false;
		
		for (int i = 1; i < 8; i++){
			table.putCard(new Card(RANK.ACE, SUIT.Club), 1);
			table.putCard(new Card(RANK.SEVEN, SUIT.Diamond), 1);
			table.putCard(new Card(ranks[i], SUIT.Spade), 0);	
			player.isGettingHit();
			assertThat(player.getChoice(),is(expected));
			table.clearObject();
		}
		// Playerの手札: A, 7; Dealerの手札: 9, 10: hit
		expected = true;
		
		for (int i = 8; i < 10; i++){
			table.putCard(new Card(RANK.ACE, SUIT.Club), 1);
			table.putCard(new Card(RANK.SEVEN, SUIT.Diamond), 1);
			table.putCard(new Card(ranks[i], SUIT.Spade), 0);	
			player.isGettingHit();
			assertThat(player.getChoice(),is(expected));
			table.clearObject();
		}
	}

	@Test
	public void  Aを11と数えた時のchoiceテスト03() {
		// Playerの手札: A, {2,...,6} （cardTotal <=17）
		expected = true;
		
		for (int i = 1; i < 6; i++){
			table.putCard(new Card(RANK.ACE, SUIT.Club), 1);
			table.putCard(new Card(ranks[i], SUIT.Diamond), 1);
			player.isGettingHit();
			assertThat(player.getChoice(),is(expected));
			table.clearObject();
		}
	}
}
