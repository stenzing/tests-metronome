package sg.test.tictactoe.impl.checker;

import sg.test.tictactoe.api.Board;
import sg.test.tictactoe.api.Checker;

public abstract class BaseCheck implements Checker{

	protected Board b;

	public BaseCheck(Board b) {
		this.b = b;
	}

	public Board getBoard() {
		return b;
	}
}