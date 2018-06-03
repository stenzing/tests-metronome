package sg.test.tictactoe.api;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class PointTest {

	@Test
	public void testInit() {
		Point target = new Point(0, 0);
		Assert.assertNotNull(target);
	}

	@Test
	public void testPointToN() {
		Point target = new Point(0, 0).getN();
		Assert.assertNotNull(target);
		Assert.assertEquals(0, target.getX());
		Assert.assertEquals(-1, target.getY());
	}

	@Test
	public void testPointToS() {
		Point target = new Point(0, 0).getS();
		Assert.assertNotNull(target);
		Assert.assertEquals(0, target.getX());
		Assert.assertEquals(1, target.getY());
	}

	@Test
	public void testPointToE() {
		Point target = new Point(0, 0).getE();
		Assert.assertNotNull(target);
		Assert.assertEquals(1, target.getX());
		Assert.assertEquals(0, target.getY());
	}

	@Test
	public void testPointToW() {
		Point target = new Point(0, 0).getW();
		Assert.assertNotNull(target);
		Assert.assertEquals(-1, target.getX());
		Assert.assertEquals(0, target.getY());
	}

	@Test
	public void testPointToNW() {
		Point target = new Point(0, 0).getNW();
		Assert.assertNotNull(target);
		Assert.assertEquals(-1, target.getX());
		Assert.assertEquals(-1, target.getY());
	}


	@Test
	public void testPointToNE() {
		Point target = new Point(0, 0).getNE();
		Assert.assertNotNull(target);
		Assert.assertEquals(1, target.getX());
		Assert.assertEquals(-1, target.getY());
	}

	@Test
	public void testPointToSW() {
		Point target = new Point(0, 0).getSW();
		Assert.assertNotNull(target);
		Assert.assertEquals(-1, target.getX());
		Assert.assertEquals(1, target.getY());
	}

	@Test
	public void testPointToSE() {
		Point target = new Point(0, 0).getSE();
		Assert.assertNotNull(target);
		Assert.assertEquals(1, target.getX());
		Assert.assertEquals(1, target.getY());
	}
	
	@Test
	public void testEqualityConstraints() {
		Point p1 = new Point(1, 2);
		Point p2 = new Point(1, 2);
		
		Assert.assertTrue(p1.equals(p2));
		Assert.assertFalse(p1.equals(new Object()));
		Assert.assertEquals(p1.hashCode(), p2.hashCode());
		Assert.assertFalse(p1.equals(p2.getE()));
	}

}
