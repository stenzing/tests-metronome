package sg.test.tictactoe.impl.checker;

import sg.test.tictactoe.api.Board;
import sg.test.tictactoe.api.FieldOwner;
import sg.test.tictactoe.api.GlobalChecker;
import sg.test.tictactoe.api.Point;

public class CheckDraw extends BaseCheck implements GlobalChecker {

	public CheckDraw(Board b) {
		super(b);
	}

	public boolean checkCondition() {
		int size = b.getFieldSize();
		for (int i = 1; i <= size; i++) {
			for (int j = 1; j <= size; j++) {
				if (b.getFieldOccupation(new Point(i, j)) == FieldOwner.EMPTY) {
					return false;
				}
			}
		}
		return true;
	}

}
