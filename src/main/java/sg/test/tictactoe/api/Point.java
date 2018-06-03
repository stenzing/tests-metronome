package sg.test.tictactoe.api;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Point implements Serializable {
	private static final long serialVersionUID = 1L;
	private final int x;
	private final int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public static Point valueOf(String s) throws PointParseException {
		Pattern p = Pattern.compile("^\\(([0-9]*),[ ]?([0-9]*)\\)$");
		Matcher m = p.matcher(s);
		if (!m.find()) {
			throw new PointParseException("Could not parse point value"); 
		}
		int x = Integer.valueOf(m.group(1));
		int y = Integer.valueOf(m.group(2));
		return new Point(x, y);
		
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	@Override
	public int hashCode() {
		return 10 * y + x;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point o = (Point) obj;
			return o.getX() == x && o.getY() == y;
		}
		return super.equals(obj);
	}

	public Point getN() {
		return new Point(x, y-1);
	}

	public Point getS() {
		return new Point(x, y+1);
	}

	public Point getE() {
		return new Point(x+1, y);
	}

	public Point getW() {
		return new Point(x-1, y);
	}

	public Point getNE() {
		return getN().getE();
	}

	public Point getNW() {
		return getN().getW();
	}

	public Point getSE() {
		return getS().getE();
	}

	public Point getSW() {
		return getS().getW();
	}
}
