package prizes;

import java.util.*;

import org.json.*;

import readJson.Parameters;
import readJson.Symbol;

public class prizeLineCount {

	@SuppressWarnings("null")
	public JSONArray countLine(int[][] board, totalPrizeContainer totalPrizeContainer, Parameters Parameters) throws JSONException {
		// TODO Auto-generated method stub

		JSONArray symbolsArray = new JSONArray();
		
		for(int i=0; i<Parameters.getPrizeLinePositions().size();i++) {

			int originalNumber = 0;
			int prizeLineCount=0;
			ArrayList<Integer> positionsList = new ArrayList<>();
			
			for(int j=0; j<Parameters.getPrizeLinePositions().get(i).size(); j++) {
				
				int actualPosition = Parameters.getPrizeLinePositions().get(i).get(j);
				int x = actualPosition / Parameters.getGameParameters().getNumberColumns();
				int y = actualPosition % Parameters.getGameParameters().getNumberColumns();
				
				if(j==0) {
					originalNumber = board[x][y];
					prizeLineCount++;
				} else if(board[x][y]==originalNumber) {
					prizeLineCount++;
				} else {
					break;
				}

				positionsList.add(actualPosition);
				
			}
			

			int spinPrize = 0;
			
			for(Symbol symbol : Parameters.getSymbols()) {
				if(symbol.getId() == originalNumber) {
					spinPrize=symbol.getSymbolPrize()*prizeLineCount;
					if(prizeLineCount>=symbol.getMinQuantity()) {
						
						totalPrizeContainer.setTotalPrize(totalPrizeContainer.getTotalPrize()+spinPrize);

						JSONObject symbols = new JSONObject();
						
						symbols.put("quantity", prizeLineCount);
						symbols.put("positions", positionsList);
						symbols.put("prize", spinPrize);
						symbols.put("symbols", originalNumber);
						symbolsArray.put(symbols);
						
						String formattedNumber = String.format("%.2f", (float) totalPrizeContainer.getTotalPrize() / 100);
								
						System.out.println("Symbol " + originalNumber + " x " + prizeLineCount + " : " + spinPrize + " cents");
						System.out.println("Total prize: " + formattedNumber);
						
					}
				}
			}		
		}
		
		return symbolsArray;
		
	}

}
