package sg.test.tictactoe.impl.checker;

import sg.test.tictactoe.api.Board;
import sg.test.tictactoe.api.LocalChecker;
import sg.test.tictactoe.api.Point;

public final class AggregateChecker implements LocalChecker {

	private final LocalChecker[] checkers;

	public AggregateChecker(LocalChecker checks[]) {
		for (LocalChecker check : checks) {
			if (check == null) {
				throw new NullPointerException("Checker in array list parameter cannot be NULL");
			}
		}
		checkers = checks;
	}

	public boolean checkCondition(final Point p) {

		for (LocalChecker aCheck : checkers) {
			if (aCheck.checkCondition(p)) {
				return true;
			}
		}

		return false;
	}

	public Board getBoard() {
		if (checkers.length == 0) {
			return null;
		} else {
			return checkers[0].getBoard();
		}
	}

}
