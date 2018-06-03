package sg.test.tictactoe.impl.checker;

import sg.test.tictactoe.api.Board;
import sg.test.tictactoe.api.LocalChecker;
import sg.test.tictactoe.api.FieldOwner;
import sg.test.tictactoe.api.Point;

public abstract class BaseRowCheck extends BaseCheck implements LocalChecker {
	protected final int needInARow;

	public BaseRowCheck(Board b) {
		super(b);
		needInARow = b.getFieldSize();
	}

	public boolean checkCondition(Point p) {
		final FieldOwner o = getBoard().getFieldOccupation(p);

		final Point start = findStart(p, o);
		return checkRow(start, o, needInARow);
	}

	protected abstract Point findStart(Point p, FieldOwner o);

	protected abstract boolean checkRow(Point p, FieldOwner o, int needed);
}
