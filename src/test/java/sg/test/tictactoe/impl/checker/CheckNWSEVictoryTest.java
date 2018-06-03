package sg.test.tictactoe.impl.checker;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.Mockito;
import sg.test.tictactoe.api.Board;
import sg.test.tictactoe.api.Point;
import sg.test.tictactoe.impl.board.ScriptedBoard;

@RunWith(BlockJUnit4ClassRunner.class)
public class CheckNWSEVictoryTest {

	@Test
	public void testInit() {
		Board b = Mockito.mock(Board.class);
		CheckNWSEVictory target = new CheckNWSEVictory(b);

		Assert.assertNotNull(target);
		Mockito.verify(b,Mockito.times(1)).getFieldSize();
	}


	@Test
	public void testVictory() throws Exception {
		Board b = new ScriptedBoard.Builder().setSize(5).setSource("src/test/resources/tstBoard02.dat").build();
		CheckNWSEVictory target = new CheckNWSEVictory(b);

		Assert.assertNotNull(target);
		
		Assert.assertTrue(target.checkCondition(new Point(2, 2)));
	}
}
