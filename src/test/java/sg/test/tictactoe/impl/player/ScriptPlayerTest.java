package sg.test.tictactoe.impl.player;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import sg.test.tictactoe.api.FieldOwner;
import sg.test.tictactoe.api.Point;
import sg.test.tictactoe.api.PointParseException;

@RunWith(BlockJUnit4ClassRunner.class)
public class ScriptPlayerTest {

	@Test
	public void testInit() throws Exception {
		ScriptPlayer p = new ScriptPlayer.Builder().setOwner(FieldOwner.P1)
				.setSource("src/test/resources/scriptPlayer01.steps").build();
		Assert.assertNotNull(p);
	}

	@Test
	public void testNextMove() throws Exception {
		ScriptPlayer p = new ScriptPlayer.Builder().setOwner(FieldOwner.P1)
				.setSource("src/test/resources/scriptPlayer01.steps").build();
		Assert.assertNotNull(p);

		for (Point expected : new Point[] { new Point(1, 2), new Point(3, 2), new Point(2, 2) }) {
			Point nextMove = p.getNextMove();
			Assert.assertNotNull(nextMove);
			Assert.assertEquals(expected, nextMove);
		}

	}

	@Test(expected=PointParseException.class)
	public void testFailOnWrongInputsFirst() throws Exception {
		ScriptPlayer p = new ScriptPlayer.Builder().setOwner(FieldOwner.P1)
				.setSource("src/test/resources/scriptPlayer02.steps").build();
		Assert.assertNull(p);
	}

}
