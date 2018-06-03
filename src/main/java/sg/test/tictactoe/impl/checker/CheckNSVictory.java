package sg.test.tictactoe.impl.checker;

import sg.test.tictactoe.api.Board;
import sg.test.tictactoe.api.FieldOwner;
import sg.test.tictactoe.api.Point;

public class CheckNSVictory extends BaseRowCheck {

	public CheckNSVictory(Board b) {
		super(b);
	}

	protected Point findStart(Point p, FieldOwner o) {
		Point start = p;
		while (b.getFieldOccupation(start.getN()) == o) {
			start = start.getN();
		}
		return start;
	}

	protected boolean checkRow(Point p, FieldOwner o, int needed) {
		if (needed == 1) {
			return (b.getFieldOccupation(p) == o);
		} else {
			if (b.getFieldOccupation(p) != o) {
				return false;
			} else {
				return checkRow(p.getS(), o, needed - 1);
			}
		}
	}

}
