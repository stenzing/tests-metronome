package sg.test.tictactoe.impl.checker;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import sg.test.tictactoe.api.Board;
import sg.test.tictactoe.api.FieldOwner;
import sg.test.tictactoe.api.Point;

@RunWith(BlockJUnit4ClassRunner.class)
public class CheckDrawTest {

	@Test
	public void testInit() {
		Board b = Mockito.mock(Board.class);
		CheckDraw target = new CheckDraw(b);

		Assert.assertNotNull(target);
	}

	@Test
	public void testOccupation() {
		Board b = Mockito.mock(Board.class);
		Mockito.when(b.getFieldSize()).thenReturn(1);
		Mockito.when(b.getFieldOccupation(Mockito.any(Point.class))).thenReturn(FieldOwner.P1);
		CheckDraw target = new CheckDraw(b);

		Assert.assertNotNull(target);

		Assert.assertTrue(target.checkCondition());
	}

	@Test
	public void testFreeSpot() {
		Board b = Mockito.mock(Board.class);
		Mockito.when(b.getFieldSize()).thenReturn(3);
		Mockito.when(b.getFieldOccupation(Mockito.any(Point.class))).thenAnswer(new Answer<FieldOwner>() {

			public FieldOwner answer(InvocationOnMock arg0) throws Throwable {
				Point p = arg0.getArgument(0);
				return p.equals(new Point(2, 3)) ? FieldOwner.P1 : FieldOwner.EMPTY;
			}
		});
		CheckDraw target = new CheckDraw(b);

		Assert.assertNotNull(target);

		Assert.assertFalse(target.checkCondition());
	}
}
