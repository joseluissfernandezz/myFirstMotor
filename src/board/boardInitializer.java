package board;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import readJson.Parameters;

public class boardInitializer {
	public int[][] boardInitializer(String playerInput, Parameters parameters) throws FileNotFoundException, IOException, ParseException{
		
		int[][] board = null;

	        switch (playerInput) {
	            case "show":
	                hardcodedBoard hardcodeBoard = new board.hardcodedBoard();
	                board = hardcodeBoard.createHardcodedBoard(parameters);
	                break;
	            default:
	                jsonBoard jsonboard = new board.jsonBoard();
	                board = jsonboard.createBoardFromJson(parameters);
	                break;
	        }

	        return board;
	}
}
