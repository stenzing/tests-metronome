package sg.test.tictactoe.api;

public class BoardFieldInvalidException extends Exception {

	private Point p;
	private int boardSize;

	public BoardFieldInvalidException(int boardSize, Point p) {
		super(String.format("Position $s is not valid on a board of size %s", p, boardSize));
		this.p = p;
		this.boardSize = boardSize;
	}

	private static final long serialVersionUID = 1L;
}
