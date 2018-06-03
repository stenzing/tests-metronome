package sg.test.tictactoe.impl.checker;

import sg.test.tictactoe.api.Board;
import sg.test.tictactoe.api.FieldOwner;
import sg.test.tictactoe.api.Point;

public class CheckWEVictory extends BaseRowCheck {

	public CheckWEVictory(Board b) {
		super(b);
	}

	protected Point findStart(Point p, FieldOwner o) {
		Point start = p;
		while (b.getFieldOccupation(start.getW()) == o) {
			start = start.getW();
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
				return checkRow(p.getE(), o, needed - 1);
			}
		}
	}

}
