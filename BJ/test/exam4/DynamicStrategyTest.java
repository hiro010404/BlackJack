package exam4;

import cardgame.Banner;
import cardgame.blackjack.ExtendedBlackJackPlayer;
import cardgame.blackjack.strategy.State;
import cardgame.blackjack.strategy.DynamicStrategy;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * DynamicStrategy の単体テスト
 */
public class DynamicStrategyTest {

	ExtendedBlackJackPlayer player;
	DynamicStrategy strategy;
	State expected;		// 状態遷移先の期待値

	@Before
	public void setUp() {
		player = new ExtendedBlackJackPlayer();
		player.setStrategy(strategy = new DynamicStrategy());
	}
	@Test
	public void DynamicBearのhandleState境界テスト() {
		strategy.setState(strategy.getDynamicBear());
		// 前回勝ちの場合
		expected = strategy.getDynamicBasic();
		
		player.setJudgement(Banner.WIN);
		strategy.decideBetChipsValue(player);
		assertThat(strategy.getState(), is(expected));

		strategy.setState(strategy.getDynamicBear());
		// 前回引き分けの場合
		expected = strategy.getDynamicBear();
		
		player.setJudgement(Banner.PUSH);
		strategy.decideBetChipsValue(player);
		assertThat(strategy.getState(), is(expected));
	}

	@Test
	public void DynamicBullのhandleState境界テスト() {
		strategy.setState(strategy.getDynamicBull());
		// 前回負けの場合
		expected = strategy.getDynamicBasic();
		
		player.setJudgement(Banner.LOSE);
		strategy.decideBetChipsValue(player);
		assertThat(strategy.getState(), is(expected));

		strategy.setState(strategy.getDynamicBull());
		// 前回引き分けの場合
		expected = strategy.getDynamicBull();
		
		player.setJudgement(Banner.PUSH);
		strategy.decideBetChipsValue(player);
		assertThat(strategy.getState(), is(expected));
	}

	@Test
	public void DynamicBasicのhandleState境界テスト() {
		strategy.setState(strategy.getDynamicBull());
		// 前回勝ちの場合
		expected = strategy.getDynamicBull();
		
		player.setJudgement(Banner.WIN);
		strategy.decideBetChipsValue(player);
		assertThat(strategy.getState(), is(expected));

		strategy.setState(strategy.getDynamicBasic());
		// 前回負けの場合
		expected = strategy.getDynamicBear();
		
		player.setJudgement(Banner.LOSE);
		strategy.decideBetChipsValue(player);
		assertThat(strategy.getState(), is(expected));

		strategy.setState(strategy.getDynamicBasic());
		// 前回引き分けの場合
		expected = strategy.getDynamicBasic();
		
		player.setJudgement(Banner.PUSH);
		strategy.decideBetChipsValue(player);
		assertThat(strategy.getState(),is(expected));
	}	
}