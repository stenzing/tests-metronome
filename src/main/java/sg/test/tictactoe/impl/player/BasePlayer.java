package sg.test.tictactoe.impl.player;

import sg.test.tictactoe.api.FieldOwner;
import sg.test.tictactoe.api.Player;

/**
 * Base player that has knowledge of itself.
 * 
 * @author gstenzinger
 *
 */
public abstract class BasePlayer implements Player {

	private final FieldOwner oneself;
	private final char symbol;

	public BasePlayer(FieldOwner oneself, char symbol) {
		this.oneself = oneself;
		this.symbol = symbol;
	}

	public final FieldOwner getOwnerValue() {
		return oneself;
	}

	public final char getSymbol() {
		return symbol;
	}

	@Override
	public String toString() {
		return "Player(" + oneself + ": " + symbol + ")";
	}

}