package sg.test.tictactoe.impl;

import java.io.IOException;
import java.io.OutputStream;

import sg.test.tictactoe.Game;
import sg.test.tictactoe.api.Board;
import sg.test.tictactoe.api.Displayable;
import sg.test.tictactoe.api.FieldOwner;
import sg.test.tictactoe.api.Player;
import sg.test.tictactoe.api.Point;

public class ConsoleView implements Displayable {

	public void onDisplay(Game game, OutputStream out) {
		Board b = game.getBoard();

		int size = b.getFieldSize();
		try {
			for (int i = 0; i <= size; i++) {
				for (int j = 0; j <= size; j++) {
					if (i == 0 || j == 0) {
						diplayHeader(i, j, out);
					} else {
						displayFiled(game, i, j, out);
					}
				}
				out.write('\n');
			}
			out.write('\n');
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	private void displayFiled(Game game, int i, int j, OutputStream out) throws IOException {
		Board b = game.getBoard();

		FieldOwner o = b.getFieldOccupation(new Point(i, j));
		Player player = game.getPlayerByFieldOwner(o);
		if (player != null) {
			char s = player.getSymbol();
			out.write(s);
		} else {
			out.write(' ');
		}
	}

	private void diplayHeader(int i, int j, OutputStream out) throws IOException {
		if (i == 0 && j == 0) {
			out.write(' ');
		} else if (i == 0) {
			out.write(48 + j);
		} else {
			out.write(48 + i);
		}
	}

}
