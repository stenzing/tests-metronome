package sg.test.tictactoe.impl.checker;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.Mockito;

import sg.test.tictactoe.api.Board;
import sg.test.tictactoe.api.BoardSizeInvalidException;
import sg.test.tictactoe.api.FieldOwner;
import sg.test.tictactoe.api.Point;
import sg.test.tictactoe.impl.board.ParamtericBoard;

@RunWith(BlockJUnit4ClassRunner.class)
public class BaseRowCheckTest {
	
	private class TestRowCheck extends BaseRowCheck{

		public TestRowCheck(Board b) {
			super(b);
		}

		@Override
		protected Point findStart(Point p, FieldOwner o) {
			return p;
		}

		@Override
		protected boolean checkRow(Point p, FieldOwner o, int needed) {
			return p.getX()==1;
		}}
	
	@Test
	public void testInit() throws BoardSizeInvalidException {
		BaseRowCheck target = new TestRowCheck(new ParamtericBoard(3));
		
		Assert.assertNotNull(target);
	}
	
	@Test
	public void testCalls() throws BoardSizeInvalidException {
		BaseRowCheck target = Mockito.spy(new TestRowCheck(new ParamtericBoard(3)));
		
		Assert.assertNotNull(target);
		Point p = new Point(1, 1);
		target.checkCondition(p);
		
		Mockito.verify(target,Mockito.times(1)).findStart(Mockito.eq(p), Mockito.any(FieldOwner.class));
		Mockito.verify(target,Mockito.times(1)).checkCondition(Mockito.eq(p));
	}
	
	@Test
	public void testCheckSuccess() throws BoardSizeInvalidException {
		BaseRowCheck target = new TestRowCheck(new ParamtericBoard(3));
		
		Assert.assertNotNull(target);
		Assert.assertTrue(target.checkCondition(new Point(1, 1)));
	} 
	
	@Test
	public void testCheckFailure() throws BoardSizeInvalidException {
		BaseRowCheck target = new TestRowCheck(new ParamtericBoard(3));
		
		Assert.assertNotNull(target);
		Assert.assertFalse(target.checkCondition(new Point(2, 1)));
	} 
}
