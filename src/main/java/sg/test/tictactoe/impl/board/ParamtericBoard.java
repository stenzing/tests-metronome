package sg.test.tictactoe.impl.board;

import java.util.HashMap;
import java.util.Map;

import sg.test.tictactoe.api.Board;
import sg.test.tictactoe.api.BoardFieldInvalidException;
import sg.test.tictactoe.api.BoardSizeInvalidException;
import sg.test.tictactoe.api.FieldOwner;
import sg.test.tictactoe.api.Point;

public class ParamtericBoard implements Board {

	private final int boardSize;
	private final Map<Point, FieldOwner> boardData;

	public ParamtericBoard(int size) throws BoardSizeInvalidException {
		if (size<3 || size > 10) {
			throw new BoardSizeInvalidException(size);
		}
		boardSize = size;
		boardData = new HashMap<Point, FieldOwner>();
		for (int i = 1; i <= boardSize; i++) {
			for (int j = 1; j <= boardSize; j++) {
				boardData.put(new Point(i, j), FieldOwner.EMPTY);
			}
		}
	}
	

	public int getFieldSize() {
		return boardSize;
	}

	public FieldOwner getFieldOccupation(Point p) {
		return boardData.get(p);
	}

	public boolean claimField(Point p, FieldOwner owner) throws BoardFieldInvalidException {
		if (!boardData.containsKey(p)) {
			throw new BoardFieldInvalidException(boardSize, p);
		}
		
		if (boardData.get(p) == FieldOwner.EMPTY) {
			boardData.put(p, owner);
			return true;
		} else {
			return false;
		}
	}

}
