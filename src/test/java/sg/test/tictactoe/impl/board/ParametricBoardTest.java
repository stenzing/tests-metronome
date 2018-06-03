package sg.test.tictactoe.impl.board;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import sg.test.tictactoe.api.BoardFieldInvalidException;
import sg.test.tictactoe.api.BoardSizeInvalidException;
import sg.test.tictactoe.api.FieldOwner;
import sg.test.tictactoe.api.Point;

@RunWith(BlockJUnit4ClassRunner.class)
public class ParametricBoardTest {

	@Test
	public void testInit() throws BoardSizeInvalidException {
		new ParamtericBoard(3);
	}

	@Test
	public void testFieldSize() throws BoardSizeInvalidException {
		ParamtericBoard target = new ParamtericBoard(4);
		Assert.assertEquals(target.getFieldSize(), 4);
	}

	@Test(expected = BoardSizeInvalidException.class)
	public void testSizeTooSmall() throws BoardSizeInvalidException {
		new ParamtericBoard(0);
	}

	@Test(expected = BoardSizeInvalidException.class)
	public void testSizeTooLarge() throws BoardSizeInvalidException {
		new ParamtericBoard(11);
	}

	@Test
	public void testBoardIsEmpty() throws BoardSizeInvalidException {
		ParamtericBoard target = new ParamtericBoard(3);
		for (int i = 1; i <= 3; i++) {
			for (int j = 1; j <= 3; j++) {
				Assert.assertTrue(target.getFieldOccupation(new Point(i, j)) == FieldOwner.EMPTY);
			}
		}
	}

	@Test
	public void testValidClaim() throws BoardSizeInvalidException, BoardFieldInvalidException {
		ParamtericBoard target = new ParamtericBoard(3);
		Point p = new Point(1, 2);
		Assert.assertTrue(target.claimField(p, FieldOwner.P1));
		Assert.assertTrue(target.getFieldOccupation(p) == FieldOwner.P1);
	}

	@Test(expected = BoardFieldInvalidException.class)
	public void testInvalidFieldClaim() throws BoardSizeInvalidException, BoardFieldInvalidException {
		ParamtericBoard target = new ParamtericBoard(3);
		Point p = new Point(4, 2);
		Assert.assertFalse(target.claimField(p, FieldOwner.P1));
	}

	@Test
	public void testFieldAlreadyTaken() throws BoardSizeInvalidException, BoardFieldInvalidException {
		ParamtericBoard target = new ParamtericBoard(3);
		Point p = new Point(1, 2);
		Assert.assertTrue(target.claimField(p, FieldOwner.P1));
		Assert.assertTrue(target.getFieldOccupation(p) == FieldOwner.P1);
		Assert.assertFalse(target.claimField(p, FieldOwner.P1));
	}

}
