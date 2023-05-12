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
public class BasicIsHitA1Test {

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
	public void  Aを1と数えた時のchoiceテスト01() {
		
		// テスト条件: cardTotal >= 17
		// Playerの手札: 10, {6,...,10}, A: stand
		expected = false;
		for (int i = 5; i < 10; i++){
			table.putCard(new Card(RANK.TEN, SUIT.Spade), 1);
			table.putCard(new Card(ranks[i], SUIT.Diamond), 1);
			table.putCard(new Card(RANK.ACE, SUIT.Club), 1);
			player.isGettingHit();
			assertThat(player.getChoice(),is(expected));
			table.clearObject();
		}
	}

	@Test
	public void  Aを1と数えた時のchoiceテスト02() {
		
		// テスト条件: 13<=cardTotal <= 16 && dealerFaceUpCard >= 2 && dealerFaceUpCard <= 6
		// Playerの手札: A,4,6,{2..5}: stand
		
		for (int i = 6; i < 10; i++){
			
			// Dealerの手札: 2,..,6: stand
			expected = false;	
			for (int k = 1; k < 5; k++){
				table.putCard(new Card(RANK.FIVE, SUIT.Diamond), 1);
				table.putCard(new Card(ranks[i], SUIT.Heart), 1);
				table.putCard(new Card(RANK.ACE, SUIT.Club), 1);
				table.putCard(new Card(ranks[k], SUIT.Spade), 0);		
				player.isGettingHit();
				assertThat(player.getChoice(),is(expected));
				table.clearObject();
			}
			
			// Dealerの手札: 7,..,10: hit
			expected = true;		
			for (int k = 6; k < 10; k++){
				table.putCard(new Card(RANK.FIVE, SUIT.Diamond), 1);
				table.putCard(new Card(ranks[i], SUIT.Heart), 1);
				table.putCard(new Card(RANK.ACE, SUIT.Club), 1);
				table.putCard(new Card(ranks[k], SUIT.Spade), 0);		
				player.isGettingHit();
				assertThat(player.getChoice(),is(expected));
				table.clearObject();
			}
		}
	}

	@Test
	public void  Aを1と数えた時のchoiceテスト03() {
		
		// テスト条件: cardTota =12 && dealerFaceUpCard >= 4 && dealerFaceUpCard <= 6
		// Playerの手札: 5, 6, A
		// Dealerの手札: 2, 3: stand
		expected = true;	
		for (int k = 1; k < 3; k++){
			table.putCard(new Card(RANK.FIVE, SUIT.Diamond), 1);
			table.putCard(new Card(RANK.SIX, SUIT.Heart), 1);
			table.putCard(new Card(RANK.ACE, SUIT.Club), 1);
			table.putCard(new Card(ranks[k], SUIT.Spade), 0);		
			player.isGettingHit();
			assertThat(player.getChoice(),is(expected));
			table.clearObject();
		}
		
		// Dealerの手札: 4,...,6: hit
		expected = false;	
		for (int k = 3; k < 6; k++){
			table.putCard(new Card(RANK.FIVE, SUIT.Diamond), 1);
			table.putCard(new Card(RANK.SIX, SUIT.Heart), 1);
			table.putCard(new Card(RANK.ACE, SUIT.Club), 1);
			table.putCard(new Card(ranks[k], SUIT.Spade), 0);		
			player.isGettingHit();
			assertThat(player.getChoice(),is(expected));
			table.clearObject();
		}
		
		// Dealerの手札: 7,...,10: hit
		expected = true;
		for (int k = 6; k < 10; k++){
			table.putCard(new Card(RANK.FIVE, SUIT.Diamond), 1);
			table.putCard(new Card(RANK.SIX, SUIT.Heart), 1);
			table.putCard(new Card(RANK.ACE, SUIT.Club), 1);
			table.putCard(new Card(ranks[k], SUIT.Spade), 0);		
			player.isGettingHit();
			assertThat(player.getChoice(),is(expected));
			table.clearObject();
		}
	}
}
