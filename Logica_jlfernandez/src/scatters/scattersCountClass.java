package scatters;

import enums.stagesEnum;
import readJson.Amount;
import readJson.FreeSpins;
import readJson.Parameters;

import java.util.*;

public class scattersCountClass {
	public HashMap<Integer, Integer> scattersCount(Parameters Parameters, int[][] board) {

			HashMap<Integer,Integer> scattersHash = new HashMap<Integer,Integer>();
			
			for(FreeSpins stage : Parameters.getFreeSpinsAwardedsBySymbol()) {
				for(Amount Symbol : stage.getSymbols()) {
					scattersHash.put(Symbol.getIdSymbol(), 0);
				}
			}

			for(int rows=0; rows<board.length; rows++) {
				for(int columns=0; columns<board[rows].length; columns++) {
					if(scattersHash.containsKey(board[rows][columns])) {
						scattersHash.put(board[rows][columns], scattersHash.get(board[rows][columns])+1);
					}
				}
			}	
		
			return scattersHash;

	}
}
