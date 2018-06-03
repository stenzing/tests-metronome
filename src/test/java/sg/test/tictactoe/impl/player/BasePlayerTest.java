package sg.test.tictactoe.impl.player;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import sg.test.tictactoe.api.FieldOwner;
import sg.test.tictactoe.api.Point;

@RunWith(BlockJUnit4ClassRunner.class)
public class BasePlayerTest {

	class TestPlayer extends BasePlayer {

		public TestPlayer(FieldOwner oneself) {
			super(oneself, 'B');
		}

		public Point getNextMove() {
			return null;
		}

	}

	@Test
	public void testInit() {
		BasePlayer p = new TestPlayer(FieldOwner.P1);
		Assert.assertNotNull(p);
	}

	@Test
	public void testFieldOwner() {
		BasePlayer p = new TestPlayer(FieldOwner.P1);
		Assert.assertNotNull(p);
		Assert.assertEquals(FieldOwner.P1, p.getOwnerValue());
	}

	@Test
	public void testDisplayName() {
		BasePlayer p = new TestPlayer(FieldOwner.P1);
		Assert.assertNotNull(p);
		Assert.assertTrue(p.toString().contains(FieldOwner.P1.name()));
	}
}
