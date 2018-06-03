package sg.test.tictactoe.impl.checker;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import sg.test.tictactoe.api.BoardSizeInvalidException;
import sg.test.tictactoe.impl.board.ParamtericBoard;

@RunWith(BlockJUnit4ClassRunner.class)
public class BaseCheckTest {
	
	@Test
	public void testInit() throws BoardSizeInvalidException {
		BaseCheck target = new BaseCheck(new ParamtericBoard(3)) {
		};
		
		Assert.assertNotNull(target);
	}
	
	
	@Test
	public void testBoardReference() throws BoardSizeInvalidException {
		ParamtericBoard board = new ParamtericBoard(3);
		BaseCheck target = new BaseCheck(board) {
		};
		
		Assert.assertNotNull(target);
		Assert.assertEquals(board, target.getBoard());
	}	
	
}
