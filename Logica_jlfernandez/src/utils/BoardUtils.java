package utils;

public class BoardUtils {
	public boolean inBoard(int row, int column, int[][] board) {
		return row >= 0 && column >= 0 && row < board.length && column < board[0].length;
	}
}
