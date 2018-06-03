package sg.test.tictactoe.impl.player;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sg.test.tictactoe.api.FieldOwner;
import sg.test.tictactoe.api.Player;
import sg.test.tictactoe.api.Point;

/**
 * Player implementation using a movement list;
 * 
 * @author gstenzinger
 *
 */
public class ScriptPlayer extends BasePlayer implements Player {

	public static class Builder {
		private String movementFile;
		private FieldOwner oneself;
		private char symbol;

		public Builder setSource(String source) {
			this.movementFile = source;
			return this;
		}

		public Builder setOwner(FieldOwner owner) {
			this.oneself = owner;
			return this;
		}

		public Builder setSymbol(char symbol) {
			this.symbol = symbol;
			return this;
		}

		public ScriptPlayer build() throws Exception {

			BufferedReader br = new BufferedReader(
					new InputStreamReader(Files.newInputStream(Paths.get(movementFile), StandardOpenOption.READ)));
			String l = null;
			List<Point> movementList = new ArrayList<Point>();
			while ((l = br.readLine()) != null) {
				l = l.trim();
				if ("".equals(l) || l.startsWith("#")) {
					continue;
				}
				Point p = Point.valueOf(l);
				movementList.add(p);
			}
			return new ScriptPlayer(oneself, symbol, movementList);
		}
	}

	Iterator<Point> movements;

	private ScriptPlayer(FieldOwner oneself, char symbol, List<Point> movementList) {
		super(oneself, symbol);
		movements = movementList.iterator();
	}

	public Point getNextMove() {
		return movements.next();
	}

	@Override
	public String toString() {
		return "Scripted " + super.toString();
	}
}
