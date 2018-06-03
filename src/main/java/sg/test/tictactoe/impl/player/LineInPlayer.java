package sg.test.tictactoe.impl.player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import sg.test.tictactoe.api.FieldOwner;
import sg.test.tictactoe.api.Player;
import sg.test.tictactoe.api.Point;
import sg.test.tictactoe.api.PointParseException;

public class LineInPlayer extends BasePlayer implements Player {

	BufferedReader in;

	public LineInPlayer(FieldOwner oneself, char symbol, InputStream in) {
		super(oneself, symbol);
		this.in = new BufferedReader(new InputStreamReader(in));
	}

	public Point getNextMove() {
		while (true) {
			System.out.print("Next step in format '(x, y)': ");
			try {
				String l = in.readLine();
				return Point.valueOf(l);
			} catch (PointParseException e) {
				System.err.println(e.getMessage());
			} catch (IOException e) {
				throw new RuntimeException("Reading of Movement source exception", e);
			}
		}
	}

	@Override
	public String toString() {
		return "Human " + super.toString();
	}
}
