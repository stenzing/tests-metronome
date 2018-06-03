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
public class CheckNSVictoryTest {

	@Test
	public void testInit() {
		Board b = Mockito.mock(Board.class);
		CheckNSVictory target = new CheckNSVictory(b);

		Assert.assertNotNull(target);
		Mockito.verify(b,Mockito.times(1)).getFieldSize();
	}


	@Test
	public void testVictory() {
		Board b = Mockito.mock(Board.class);
		Mockito.when(b.getFieldSize()).thenReturn(3);
		Mockito.when(b.getFieldOccupation(Mockito.any(Point.class))).thenAnswer(new Answer<FieldOwner>() {

			public FieldOwner answer(InvocationOnMock arg0) throws Throwable {
				Point p = arg0.getArgument(0);
				return inBorders(p)?FieldOwner.P1:null;
			}
			
			private boolean inBorders(Point p) {
				return (p.getY()>0 && p.getY()<4);
			}
		});
		CheckNSVictory target = new CheckNSVictory(b);

		Assert.assertNotNull(target);
		
		Assert.assertTrue(target.checkCondition(new Point(2, 2)));
		Mockito.verify(b,Mockito.times(1)).getFieldSize();
		Mockito.verify(b,Mockito.times(6)).getFieldOccupation(Mockito.any(Point.class));
	}

	@Test
	public void testNoVictory() {
		Board b = Mockito.mock(Board.class);
		Mockito.when(b.getFieldSize()).thenReturn(3);
		Mockito.when(b.getFieldOccupation(Mockito.any(Point.class))).thenAnswer(new Answer<FieldOwner>() {
			private int goodCallNumber=5;
			
			public FieldOwner answer(InvocationOnMock arg0) throws Throwable {
				Point p = arg0.getArgument(0);
				if (goodCallNumber-->0) {					
					return inBorders(p)?FieldOwner.P1:null;
				} else {
					return inBorders(p)?FieldOwner.P2:null;
				}
			}

			private boolean inBorders(Point p) {
				return (p.getY()>0 && p.getY()<4);
			}
		});
		CheckNSVictory target = new CheckNSVictory(b);

		Assert.assertNotNull(target);
		
		Assert.assertFalse(target.checkCondition(new Point(2, 2)));
		Mockito.verify(b,Mockito.times(1)).getFieldSize();
		Mockito.verify(b,Mockito.times(6)).getFieldOccupation(Mockito.any(Point.class));
	}

}
