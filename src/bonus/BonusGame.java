package bonus;

import readJson.Parameters;

public class BonusGame {

	public int zeroCheck(int[][] board, Parameters Parameters) {
		int zeroCount=0;
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++) {
				if(board[i][j]==Parameters.getGameParameters().getBonusNumber()) {
					zeroCount++;
				}
			}
		}
		return zeroCount;	
	}


}
