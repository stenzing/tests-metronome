package sg.test.tictactoe.api;

public interface Board {
	
	int getFieldSize();

	FieldOwner getFieldOccupation(Point p);

	boolean claimField(Point p, FieldOwner owner) throws BoardFieldInvalidException;
}
