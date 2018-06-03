package sg.test.tictactoe.api;

public class BoardSizeInvalidException extends Exception {
	private static final long serialVersionUID = 1L;

	private final int size;

	public BoardSizeInvalidException(int size) {
		super(String.format("Board size limits are [3,10], invalid value of %d given.", size));
		this.size = size;
	}
}
