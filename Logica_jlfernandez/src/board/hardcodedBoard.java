package board;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import org.json.simple.parser.ParseException;
import main.main;
import readJson.Parameters;

public class hardcodedBoard {
	
    public final static Scanner sc = new Scanner(System.in);
    
    public int[][] createHardcodedBoard(Parameters Parameters) throws FileNotFoundException, IOException, ParseException {
 
		
        int rows = Parameters.getGameParameters().getNumberRows();
        int columns = Parameters.getGameParameters().getNumberColumns();
    	int[][] board = new int[rows][columns];
    	
        for(int row=0; row<board.length; row++) {
        	
        	System.out.println("Line "+(row+1)+": ");
        	
        	String boardLine= sc.nextLine();
        	String[] separatedLine = boardLine.split(" ");
        	
        	for(int column=0; column<separatedLine.length ; column++) {  
        		
        		board[row][column] =Integer.parseInt(separatedLine[column]);	
        		
        	}
        	
        } 
        return board;
    }
}
