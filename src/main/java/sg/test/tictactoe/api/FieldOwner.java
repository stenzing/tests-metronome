package sg.test.tictactoe.api;

import java.util.Arrays;
import java.util.List;

public enum FieldOwner {
	P1, P2, P3, P4, P5, EMPTY;

	public static List<FieldOwner> getValidPlayers() {
		return Arrays.asList(P1, P2, P3, P4, P5);
	}
}
