package sg.test.tictactoe.impl.player;

import java.io.ByteArrayInputStream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import sg.test.tictactoe.api.FieldOwner;
import sg.test.tictactoe.api.Point;

@RunWith(BlockJUnit4ClassRunner.class)
public class LineInPlayerTest {

	@Test
	public void testInit() {
		ByteArrayInputStream bin = new ByteArrayInputStream(new byte[100]);
		LineInPlayer p = new LineInPlayer(FieldOwner.P1, 'H', bin);
		Assert.assertNotNull(p);
	}

	@Test
	public void testNextMove() {
		ByteArrayInputStream bin = new ByteArrayInputStream("(2,1)\n".getBytes());
		LineInPlayer p = new LineInPlayer(FieldOwner.P1, 'H', bin);
		Assert.assertNotNull(p);

		Point nextMove = p.getNextMove();
		Assert.assertNotNull(nextMove);

		Assert.assertEquals(new Point(2, 1), nextMove);
	}


	@Test
	public void testWrongInputsFirst() {
		ByteArrayInputStream bin = new ByteArrayInputStream("d\n(a,3)\n(2,1)\n".getBytes());
		LineInPlayer p = new LineInPlayer(FieldOwner.P1, 'H', bin);
		Assert.assertNotNull(p);

		Point nextMove = p.getNextMove();
		Assert.assertNotNull(nextMove);

		Assert.assertEquals(new Point(2, 1), nextMove);
	}

}
