package scatters;

import java.util.*;
import readJson.*;
import enums.specialFeaturesEnum;
import enums.stagesEnum;
import main.main;
import readJson.Amount;
import readJson.Parameters;

public class scattersControl {

	public int checkFreeSpins(HashMap<Integer, Integer> scatters, int free_spins, Parameters Parameters) {
		
		  FreeSpins freeSpinsByStage = null;
		
		  for(FreeSpins stage : Parameters.getFreeSpinsAwardedsBySymbol()) {
			  if(stagesEnum.valueOf(stage.getStage()) == Parameters.getStage()) {
				  freeSpinsByStage = stage; 
			  }
		  }
		  
		  for (int number : scatters.keySet()) {
	            if (Parameters.getStage() == stagesEnum.MAIN) {
	                
	                for (Amount sym : freeSpinsByStage.getSymbols()) {        
	                    
	                    ArrayList<Integer> maxValueList = new ArrayList<>();
	                    
	                    for (String key : sym.getAmount().keySet()) {
	                        maxValueList.add(Integer.parseInt(key));
	                    }
	                    
	                    int maxValue = Collections.max(maxValueList);
	                    int scatterCount = scatters.get(number);
	                    
	                    if (scatterCount > maxValue) {
	                        scatterCount = maxValue; 
	                    }
	                    
	                    String numberToCompare = "" + scatterCount;
	                
	                    if (sym.getAmount().containsKey(numberToCompare) && sym.getIdSymbol() == number) {
	                        free_spins += sym.getAmount().get(numberToCompare);    
	                    }
	                }
	                
	            } else if(Parameters.getStage()  == stagesEnum.FREE_SPINS) {
	                
	            	for (Amount sym : freeSpinsByStage.getSymbols()) {        
	                    
	                    ArrayList<Integer> maxValue = new ArrayList<>();
	                    
	                    for (String key : sym.getAmount().keySet()) {
	                        maxValue.add(Integer.parseInt(key));
	                    }
	                    
	                    Collections.max(maxValue);
	                    
	                    int scatterCount = scatters.get(number);
	                    int maxScatter = maxValue.get(0);
	                    
	                    if (scatterCount > maxScatter) {
	                        scatterCount = maxScatter; 
	                    }
	                    
	                    String numberToCompare = "" + scatterCount;
	                
	                    if (sym.getAmount().containsKey(numberToCompare) && sym.getIdSymbol() == number) {
	                        free_spins += sym.getAmount().get(numberToCompare);    
	                    }
	                }
	                
	            } else {
	            	
	            }
	        }
	        
	        if (Parameters.getStage() == stagesEnum.FREE_SPINS || Parameters.getStage() == stagesEnum.BONUS_GAME) {
	            free_spins--;
	        }
	        
	        if (free_spins > 0) {
	        	Parameters.setStage(stagesEnum.FREE_SPINS);
	        } else {
	        	Parameters.setStage(stagesEnum.MAIN);
	        }
	        return free_spins;
	    }
}
