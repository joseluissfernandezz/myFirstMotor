package prizes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import org.json.simple.parser.ParseException;

import enums.prizeLineTypeEnum;
import main.main;
import readJson.Parameters;
import readJson.Symbol;
import utils.*;

public class prizesCounterClass {
    private final int VISITED_CELL = -1;
    private prizesClass prizesInstance;


    public int prizeCounter(int[][] board, int row, int column, int originalNumber, Set<String> visitedCells, Parameters Parameters) throws FileNotFoundException, IOException, ParseException {
           	        	    		
    		int maxCount = 0;
        	
            for (Symbol symbol : Parameters.getSymbols()) {
                if (symbol.getId() == originalNumber) maxCount = symbol.getMaxQuantity();
            }
            
            BoardUtils BoardUtils = new BoardUtils();
            
            if (!BoardUtils.inBoard(row, column, board) || visitedCells.contains(row + "," + column)) {
                return visitedCells.size();
            }
            
            if (board[row][column] == 0 || board[row][column] == originalNumber) {
                visitedCells.add(row + "," + column);
                
                if (board[row][column] != 0) {
                    board[row][column] = VISITED_CELL;
                }
                
                if (prizesInstance.getMultiplier() < maxCount) {
                    prizesInstance.incrementMultiplier();
                }
                
                prizeCounter(board, row + 1, column, originalNumber, visitedCells, Parameters);
                prizeCounter(board, row - 1, column, originalNumber, visitedCells, Parameters);
                prizeCounter(board, row, column + 1, originalNumber, visitedCells, Parameters);
                prizeCounter(board, row, column - 1, originalNumber, visitedCells, Parameters);
                
            }
            return visitedCells.size();
    }
    
    public prizesCounterClass(prizesClass instance) {
        this.prizesInstance = instance;
    }

}
