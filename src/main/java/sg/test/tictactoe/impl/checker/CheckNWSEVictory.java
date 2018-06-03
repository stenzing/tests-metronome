package sg.test.tictactoe.impl.checker;

import sg.test.tictactoe.api.Board;
import sg.test.tictactoe.api.FieldOwner;
import sg.test.tictactoe.api.Point;

public class CheckNWSEVictory extends BaseRowCheck {

	public CheckNWSEVictory(Board b) {
		super(b);
	}

	protected Point findStart(Point p, FieldOwner o) {
		Point start = p;
		while (b.getFieldOccupation(start.getNW()) == o) {
			start = start.getNW();
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
				return checkRow(p.getSE(), o, needed - 1);
			}
		}
	}

}
