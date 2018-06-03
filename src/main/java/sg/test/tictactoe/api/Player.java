package sg.test.tictactoe.api;

public interface Player {
	Point getNextMove();

	FieldOwner getOwnerValue();

	char getSymbol();
}
