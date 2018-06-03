package sg.test.tictactoe.impl.board;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import sg.test.tictactoe.api.FieldOwner;
import sg.test.tictactoe.api.Point;
import sg.test.tictactoe.impl.board.ScriptedBoard.Builder;

@RunWith(BlockJUnit4ClassRunner.class)
public class ScriptedBoardTest {

	@Test
	public void testInit() throws Exception {
		ScriptedBoard target = new Builder().setSize(3).setSource("src/test/resources/tstBoard01.dat").build();
		Assert.assertNotNull(target);
	}

	@Test
	public void testFieldSize() throws Exception {
		ScriptedBoard target = new Builder().setSize(4).setSource("src/test/resources/tstBoard01.dat").build();
		Assert.assertEquals(target.getFieldSize(), 4);
	}

	@Test
	public void testBoardIsNotEmpty() throws Exception {
		ScriptedBoard target = new Builder().setSize(4).setSource("src/test/resources/tstBoard01.dat").build();
		for (int i = 1; i <= 3; i++) {
			for (int j = 1; j <= 3; j++) {
				if (target.getFieldOccupation(new Point(i, j)) != FieldOwner.EMPTY) {
					return;
				}
			}
		}
		Assert.assertFalse("None of the fields was occupied",true);
	}

}
