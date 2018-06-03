package sg.test.tictactoe.impl.player;

import sg.test.tictactoe.api.FieldOwner;
import sg.test.tictactoe.api.Player;
import sg.test.tictactoe.api.Point;

public class CompPlayer extends BasePlayer implements Player {

	int fieldMax;

	public CompPlayer(FieldOwner oneself, char symbol, int fieldSize) {
		super(oneself, symbol);
		this.fieldMax = fieldSize;
	}

	public Point getNextMove() {
		int x = (int) (1 + Math.random() * fieldMax);
		int y = (int) (1 + Math.random() * fieldMax);
		return new Point(x, y);
	}

	@Override
	public String toString() {
		return "Computer " + super.toString();
	}
}
