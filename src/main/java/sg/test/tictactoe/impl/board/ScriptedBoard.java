package sg.test.tictactoe.impl.board;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import sg.test.tictactoe.api.BoardSizeInvalidException;
import sg.test.tictactoe.api.FieldOwner;
import sg.test.tictactoe.api.Point;

public class ScriptedBoard extends ParamtericBoard {

	public static class Builder {
		private String source;
		private int size;

		public Builder() {
		}

		public Builder setSource(String source) {
			this.source = source;
			return this;
		}

		public Builder setSize(int size) {
			this.size = size;
			return this;
		}

		public ScriptedBoard build() throws Exception {
			ScriptedBoard b = new ScriptedBoard(size);
			BufferedReader br = new BufferedReader(
					new InputStreamReader(Files.newInputStream(Paths.get(source), StandardOpenOption.READ)));
			String l = null;
			while ((l = br.readLine()) != null) {
				String[] parts = l.split(",",2);
				FieldOwner o = FieldOwner.valueOf(parts[0]);
				Point p = Point.valueOf(parts[1]);
				b.claimField(p, o);
			}
			return b;
		}
	}

	private ScriptedBoard(int size) throws BoardSizeInvalidException {
		super(size);
	}
}
