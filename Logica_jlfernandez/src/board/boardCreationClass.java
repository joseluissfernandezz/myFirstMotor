package board;
import org.json.simple.parser.ParseException;

import enums.reelsEnum;
import enums.stagesEnum;
import main.main;
import readJson.FreeSpins;
import readJson.Parameters;
import readJson.reelType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
public class boardCreationClass {
	
	private static Random rn = new Random();

    public void boardCreation(int[][] board, Parameters Parameters) throws FileNotFoundException, IOException, ParseException {
    	
		int reelSize=0;
        int randCellReel=0;
        
    	for(int i=0; i<board[0].length; i++) {
    		
			if(Parameters.getStage() == stagesEnum.MAIN) {

    			Parameters.setReels(reelsEnum.BASIC);    			
    			for(reelType reel : Parameters.getModels()) {
    				
    				if(reel.getId().equals(reelsEnum.BASIC.toString())) {
    					
    					reelSize = reel.getReels().get(i).size();
    					randCellReel = rn.nextInt(0, reelSize);
    	        		for(int j=0; j<board.length; j++) {   	        			
    	        			board[j][i]= reel.getReels().get(i).get((randCellReel+j)%reelSize);
    	        		} 
    				}
    			}
    		} else if(Parameters.getStage() == stagesEnum.FREE_SPINS) {

    			Parameters.setReels(reelsEnum.BONUS);
    			for(reelType reel : Parameters.getModels()) {
    				if(reel.getId().equals(reelsEnum.BONUS.toString())) {
    					reelSize = reel.getReels().get(i).size();
    					randCellReel = rn.nextInt(0, reelSize);
    	        		for(int j=0; j<board.length; j++) {   	        			
    	        			board[j][i]= reel.getReels().get(i).get((randCellReel+j)%reelSize);
    	        		} 
    				}
    			}
    		} else if(Parameters.getStage() == stagesEnum.BONUS_GAME){
    			
    			Parameters.setReels(reelsEnum.MINIGAME);
    			for(reelType reel : Parameters.getModels()) {
    				if(reel.getId().equals(reelsEnum.BASIC.toString())) {
    					reelSize = reel.getReels().get(i).size();
    					randCellReel = rn.nextInt(0, reelSize);
    	        		for(int j=0; j<board.length; j++) {   	        			
    	        			board[j][i]= reel.getReels().get(i).get((randCellReel+j)%reelSize);
    	        		} 
    				}
    			}        		
    		}

    	}
    }
}