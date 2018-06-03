package sg.test.tictactoe.impl.checker;

import sg.test.tictactoe.api.Board;
import sg.test.tictactoe.api.FieldOwner;
import sg.test.tictactoe.api.Point;

public class CheckSWNEVictory extends BaseRowCheck {

	public CheckSWNEVictory(Board b) {
		super(b);
	}

	protected Point findStart(Point p, FieldOwner o) {
		Point start = p;
		while (b.getFieldOccupation(start.getSW()) == o) {
			start = start.getSW();
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
				return checkRow(p.getNE(), o, needed - 1);
			}
		}
	}

}
