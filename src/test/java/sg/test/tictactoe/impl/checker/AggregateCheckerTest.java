package sg.test.tictactoe.impl.checker;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.Mockito;

import sg.test.tictactoe.api.Board;
import sg.test.tictactoe.api.LocalChecker;
import sg.test.tictactoe.api.Point;

@RunWith(BlockJUnit4ClassRunner.class)
public class AggregateCheckerTest {

	@Test
	public void checkInit() {
		LocalChecker c = Mockito.mock(LocalChecker.class);
		AggregateChecker target = new AggregateChecker(new LocalChecker[] {c});
		
		Assert.assertNotNull(target);
	}
	

	@Test
	public void checkInitWithNoChekcers() {
		AggregateChecker target = new AggregateChecker(new LocalChecker[] {});
		
		Assert.assertNotNull(target);
	}
	

	@Test
	public void checkBoardReference() {
		Board b = Mockito.mock(Board.class);
		LocalChecker c = Mockito.mock(LocalChecker.class);
		when(c.getBoard()).thenReturn(b);
		
		AggregateChecker target = new AggregateChecker(new LocalChecker[] {c});
		
		Assert.assertNotNull(target);
		
		Assert.assertEquals(b, target.getBoard());
		verify(c,times(1)).getBoard();
	}

	@Test
	public void checkBoardReferenceWhenNoCheckers() {
		AggregateChecker target = new AggregateChecker(new LocalChecker[] {});
		
		Assert.assertNotNull(target);
		
		Assert.assertNull(target.getBoard());
	}
	@Test(expected=NullPointerException.class)
	public void checkWrongInitParam() {
		LocalChecker c = Mockito.mock(LocalChecker.class);
		new AggregateChecker(new LocalChecker[] {null, c});
	}


	@Test
	public void checkProxyCalls() {
		LocalChecker c1 = Mockito.mock(LocalChecker.class);
		when(c1.checkCondition(any(Point.class))).thenReturn(false);
		LocalChecker c2 = Mockito.mock(LocalChecker.class);
		when(c2.checkCondition(any(Point.class))).thenReturn(true);
		
		Point p = new Point(0, 0);
		AggregateChecker target = new AggregateChecker(new LocalChecker[] {c1,c2});
		
		Assert.assertNotNull(target);
		
		Assert.assertTrue(target.checkCondition(p));
		
		verify(c1,times(1)).checkCondition(eq(p));
		verify(c1,times(1)).checkCondition(eq(p));
	}

	@Test
	public void checkAllProxyReturnFalse() {
		LocalChecker c1 = Mockito.mock(LocalChecker.class);
		when(c1.checkCondition(any(Point.class))).thenReturn(false);
		LocalChecker c2 = Mockito.mock(LocalChecker.class);
		when(c2.checkCondition(any(Point.class))).thenReturn(false);
		
		Point p = new Point(0, 0);
		AggregateChecker target = new AggregateChecker(new LocalChecker[] {c1,c2});
		
		Assert.assertNotNull(target);
		
		Assert.assertFalse(target.checkCondition(p));
		
		verify(c1,times(1)).checkCondition(eq(p));
		verify(c1,times(1)).checkCondition(eq(p));
	}

}
