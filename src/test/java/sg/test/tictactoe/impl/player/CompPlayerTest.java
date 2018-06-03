package sg.test.tictactoe.impl.player;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import sg.test.tictactoe.api.FieldOwner;

@RunWith(BlockJUnit4ClassRunner.class)
public class CompPlayerTest {

	@Test
	public void testInit() {
		CompPlayer p = new CompPlayer(FieldOwner.P1, 'C', 3);
		Assert.assertNotNull(p);
	}

	@Test
	public void testFieldSizeKnowlege() {
		CompPlayer p = new CompPlayer(FieldOwner.P1, 'C', 3);
		Assert.assertNotNull(p);
		Assert.assertEquals(3, p.fieldMax);
	}

	@Test
	public void testNextMove() {
		CompPlayer p = new CompPlayer(FieldOwner.P1, 'C', 3);
		Assert.assertNotNull(p);

		Assert.assertNotNull(p.getNextMove());
	}

}
