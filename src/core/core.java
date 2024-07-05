package core;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import board.boardInitializer;
import board.displayBoard;
import board.hardcodedBoard;
import board.jsonBoard;
import bonus.BonusGame;
import enums.prizeLineTypeEnum;
import enums.reelsEnum;
import enums.specialFeaturesEnum;
import enums.stagesEnum;
import prizes.prizeLineCount;
import prizes.prizesClass;
import prizes.totalPrizeContainer;
import readJson.Parameters;
import scatters.scattersControl;
import scatters.scattersCountClass;

public class core {
	
    public final static Scanner sc = new Scanner(System.in);
    public final static Random rn = new Random();
	
    public int freeSpins = 0;
    public int totalSpins = 0;
	
	@SuppressWarnings("unchecked")
	public core(Parameters Parameters) throws IOException, ParseException, java.text.ParseException, JSONException {

        boolean game = true;
        int[][] board = null;
        int zeroCount = 0;
        
        while (game) {
        	
            System.out.println("Press Enter to play, show to hardcode or type 'exit' to quit");
            String playerInput = sc.nextLine();
            
            if (playerInput.equals("exit")) {
                game = false;
            } else {
                boardInitializer boardInitializer = new boardInitializer();
                board = boardInitializer.boardInitializer(playerInput, Parameters);
            }

            if (board != null && game) {
            	
                JSONArray prizesArray = new JSONArray();
                JSONObject prizesObject = new JSONObject();
                
                prizesObject.put("stage", Parameters.getStage());
                prizesObject.put("reels", Parameters.getReels());
                
            	scattersCountClass scattersCount = new scattersCountClass();
            	HashMap<Integer,Integer> scatters = scattersCount.scattersCount(Parameters, board);
            	
            	int lastFreeSpins = freeSpins;
                int originalNumber = Parameters.getGameParameters().getOriginalNumber();

            	scattersControl scattersControl = new scattersControl();
            	freeSpins = scattersControl.checkFreeSpins(scatters, freeSpins, Parameters);
            	
            	if(freeSpins >= lastFreeSpins && Parameters.getStage() == stagesEnum.FREE_SPINS && prizesObject.get("stage") == Parameters.getStage()) {
                	totalSpins += (freeSpins - lastFreeSpins)+1;
            	} else if(freeSpins >= lastFreeSpins) {
            		totalSpins += freeSpins - lastFreeSpins;
            	}

                displayBoard displayBoard = new displayBoard();
                displayBoard.displayBoard(board);
              
                prizesObject.put("prizes", prizesArray);
                int totalPrize=0;
                
            	BonusGame BonusGame = new BonusGame();
            	zeroCount = BonusGame.zeroCheck(board, Parameters);
            	
                prizesClass prizesInstance = new prizesClass(); 
                
                if(freeSpins <= lastFreeSpins && prizeLineTypeEnum.valueOf(Parameters.getPrizeLineType()) != prizeLineTypeEnum.LEFT_TO_RIGHT) {
	               
                	for (int row = 0; row < board.length; row++) {
	                    for (int column = 0; column < board[row].length; column++) {
	                    	
	                        prizesInstance.setMultiplier(originalNumber);
	                        
	                        Set<String> visitedCells = new HashSet<>();
	                        	
	                        	JSONObject prize = prizesInstance.prizes(board, row, column, originalNumber, visitedCells, Parameters);
	                       
		                        if (prize != null) {
		                            prizesArray.add(prize);
		                        }
		                        
	                    }
	                }
	                
                } else if(zeroCount != Parameters.getGameParameters().getBonusMin() &&
                		freeSpins <= lastFreeSpins &&
                		prizeLineTypeEnum.valueOf(Parameters.getPrizeLineType()) == prizeLineTypeEnum.LEFT_TO_RIGHT){
                	
                	totalPrizeContainer totalPrizeContainer = new totalPrizeContainer(0);

                	prizeLineCount prizeLineCount = new prizeLineCount();
                	prizesObject.put("prizes", prizeLineCount.countLine(board, totalPrizeContainer, Parameters));
                	totalPrize += totalPrizeContainer.getTotalPrize();
                	
                } else {
                	System.out.println("FREE SPINS PRIZE");
                }
                
                for(Object obj : prizesArray) {
                	
                	JSONObject obj2 = (JSONObject) obj; 
                	totalPrize += ((int)obj2.get("prize"));
                	
                }    
                
                if(Parameters.getSpecialFeatures().contains((specialFeaturesEnum.MINIGAMES).toString())){
                	
                	if(zeroCount == Parameters.getGameParameters().getBonusMin()) {
                		System.out.println(stagesEnum.BONUS_GAME);
                		Parameters.setStage(stagesEnum.BONUS_GAME);
                	}
                	
                }
                
                if(Parameters.getStage() == stagesEnum.MAIN) {
                	totalSpins=0;
                }

                prizesObject.put("totalPrize", (float) totalPrize / 100);
                prizesObject.put("totalAwardedsFreeSpins", totalSpins);
                prizesObject.put("remainingFreeSpins", freeSpins);
                
                System.out.println(prizesObject);
                System.out.println();
            }

        }
        System.out.println("____________________________");

        System.out.println();
    }
}
