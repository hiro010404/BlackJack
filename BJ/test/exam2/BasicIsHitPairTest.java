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
public class BasicIsHitPairTest {

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

	@Test
	public void ペアカード時のchoiceテスト01() {
		// テスト条件： cardNumber >= 9
		// Playerの手札: 9, 10: stand
		expected = false;
		for (int i = 8; i < 10; i++){
			table.putCard(new Card(ranks[i], SUIT.Club), 1);	// テストPlayerの座席番号を1とする
			table.putCard(new Card(ranks[i], SUIT.Diamond), 1);
			player.isGettingHit();	// Basic Strategy戦略を用いてHit（true）かStand（false）とする
			assertThat(player.getChoice(),is(expected));
			table.clearObject(); // Tableのカードを片付ける
		}
	}

	@Test
	public void ペアカード時のchoiceテスト02() {
		// テスト条件：cardNumber >= 7 && dealerFaceUpCard >= 2 && dealerFaceUpCard <= 6
		// Playerの手札: 7,8
		
		for (int i = 6; i < 8; i++){
			// Dealerの手札: 2,...,6: stand
			expected = false;
			for (int k = 1; k < 6; k++){
				table.putCard(new Card(ranks[i], SUIT.Club), 1);
				table.putCard(new Card(ranks[i], SUIT.Diamond), 1);
				table.putCard(new Card(ranks[k], SUIT.Spade), 0);	//ディーラーの座席番号は0
				player.isGettingHit();	
				assertThat(player.getChoice(),is(expected));
				table.clearObject();
			}
			// Dealerの手札: 7,...,10: hit
			expected = true;
			for (int k = 6; k < 10; k++){
				table.putCard(new Card(ranks[i], SUIT.Club), 1);
				table.putCard(new Card(ranks[i], SUIT.Diamond), 1);
				table.putCard(new Card(ranks[k], SUIT.Spade), 0);
				player.isGettingHit();	
				assertThat(player.getChoice(),is(expected));
				table.clearObject();
			}
		}
	}

	@Test
	public void ペアカード時のchoiceテスト03() {
		// テスト条件: cardNumber >= 6 && dealerFaceUpCard >= 4 && dealerFaceUpCard <= 6
		// Playerの手札: 6; Dealerの手札:3; hit
		expected = true;
		table.putCard(new Card(RANK.SIX, SUIT.Club), 1);
		table.putCard(new Card(RANK.SIX, SUIT.Diamond), 1);
		table.putCard(new Card(RANK.THREE, SUIT.Spade), 0);
		player.isGettingHit();
		assertThat(player.getChoice(),is(expected));
		table.clearObject();
		// Playerの手札: 6; Dealerの手札: 4,...,6: stand
		expected = false;
		for (int k = 3; k < 6; k++){
			table.putCard(new Card(RANK.SIX, SUIT.Club), 1);
			table.putCard(new Card(RANK.SIX, SUIT.Diamond), 1);
			table.putCard(new Card(ranks[k], SUIT.Spade), 0);
			player.isGettingHit();
			assertThat(player.getChoice(),is(expected));
			table.clearObject();
		}
		// Playerの手札: 6; Dealerの手札: 7,..,10: hit
		expected = true;
		for (int k = 6; k < 10; k++){
			table.putCard(new Card(RANK.SIX, SUIT.Club), 1);
			table.putCard(new Card(RANK.SIX, SUIT.Diamond), 1);
			table.putCard(new Card(ranks[k], SUIT.Spade), 0);
			player.isGettingHit();
			assertThat(player.getChoice(),is(expected));
			table.clearObject();
		}
	}

	@Test
	public void ペアカード時のchoiceテスト04() {
		// Playerの手札: 2,..,5: hit
		expected = true;
		for (int i = 1; i < 5; i++){
			table.putCard(new Card(ranks[i], SUIT.Club), 1);
			table.putCard(new Card(ranks[i], SUIT.Diamond), 1);
			player.isGettingHit();
			assertThat(player.getChoice(),is(expected));
			table.clearObject();
		}
	}

}
