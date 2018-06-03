package sg.test.tictactoe.api;

import java.io.OutputStream;

import sg.test.tictactoe.Game;

public interface Displayable {
	void onDisplay(Game game, OutputStream out);
}
