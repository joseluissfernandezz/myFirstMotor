package utils;
import java.util.*;


public class FeaturesUtils {
	private static Random rn = new Random();
	public void wildCreation(int[][] board) {
		
		int rowValue = rn.nextInt(0,board.length);
		int columnValue = rn.nextInt(0,board[0].length);
		int randomValue = rn.nextInt(0,3);
		
		if(randomValue == 0) {
			
			board[rowValue][columnValue]=0;
			
		}
		
	}
}
