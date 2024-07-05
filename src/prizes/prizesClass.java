package prizes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import enums.prizeLineTypeEnum;
import main.main;
import readJson.Parameters; 
import readJson.Symbol;

public class prizesClass {

    private final int VISITED_CELL = -1;

    private int symbolId;
    private int multiplier;
    private float spinPrize;
    private int totalPrize;

    @SuppressWarnings({ "unchecked" })
    public JSONObject prizes(int[][] board, int row, int column, int originalNumber, Set<String> visitedCells, Parameters Parameters) throws FileNotFoundException, IOException, ParseException, java.text.ParseException {
        prizeLineTypeEnum prizeLineEnum = prizeLineTypeEnum.valueOf(Parameters.getPrizeLineType());
        if (board[row][column] != VISITED_CELL && prizeLineEnum == prizeLineTypeEnum.ADJACENT) {

// 			Guardar como numero original el numero actual.
            originalNumber = board[row][column];

// 			Creamos una instancia de prizesCounterClass y contar los premios
            prizesCounterClass countPrize = new prizesCounterClass(this);
            int numeroMax = countPrize.prizeCounter(board, row, column, originalNumber, visitedCells, Parameters);
            int prizeCount = -1;

            symbolId = originalNumber;

// 			Buscamos el símbolo en los reels para obtener la cantidad mínima requerida para un premio.
            for (Symbol symbol : Parameters.getSymbols()) {
                if (symbol.getId() == symbolId) prizeCount = symbol.getMinQuantity();
            }

            if (multiplier >= prizeCount && originalNumber != 0) {

// 				Crear un nuevo objeto JSON donde almacenaremos el mensaje que se muestra en pantalla
                JSONObject symbols = new JSONObject();
                ArrayList<Symbol> symbolsArray = Parameters.getSymbols();

                symbols.put("symbols", originalNumber);
                symbols.put("quantity", numeroMax);

// 				Buscar el símbolo y calcular el premio
                
                for (Symbol obj : symbolsArray) {
                    if (obj.getId() == symbolId) {

                        spinPrize = obj.getSymbolPrize();
                        totalPrize += spinPrize * multiplier;

                        System.out.println("Symbol " + symbolId + " x " + multiplier + " : " + (int) spinPrize * multiplier  + " cents");
                        String formattedNumber = String.format("%.2f", (float) totalPrize / 100);
                        System.out.println("Total prize: " + formattedNumber + "€");
                        symbols.put("prize",(int) spinPrize* multiplier);
                		ArrayList<Integer> arrayList= new ArrayList<>();
                		

                        for (String visitedCellsObj : visitedCells) {
                        	
                            String[] visitedSplit = visitedCellsObj.split(",");
                            int visitedRow = Integer.parseInt(visitedSplit[1]);
                            int visitedColumn = Integer.parseInt(visitedSplit[0]);
                            int numberColumns = Parameters.getGameParameters().getNumberColumns();
                            int position = visitedRow + numberColumns * visitedColumn;

                            arrayList.add(position);
                            
                        }
                        
                        symbols.put("positions", arrayList);
                        
                    }
                }
                
                return symbols;
            }
        } 
            
		return null;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void incrementMultiplier() {
        multiplier++;
    }
    public int getSymbolId() {
		return symbolId;
	}

	public void setSymbolId(int symbolId) {
		this.symbolId = symbolId;
	}

	public float getSpinPrize() {
		return spinPrize;
	}

	public void setSpinPrize(float spinPrize) {
		this.spinPrize = spinPrize;
	}

	public int getTotalPrize() {
		return totalPrize;
	}

	public void setTotalPrize(int totalPrize) {
		this.totalPrize = totalPrize;
	}

	public int getVISITED_CELL() {
		return VISITED_CELL;
	}

	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}
}
