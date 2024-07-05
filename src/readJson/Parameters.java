package readJson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import enums.reelsEnum;
import enums.stagesEnum;

public class Parameters {

    int gameId;
    String gameName;
    String prizeLineType;
	stagesEnum stage;
    reelsEnum reels;
    ArrayList<ArrayList<Integer>> prizeLinePositions = new ArrayList<>();
    ArrayList<reelType> models;
    ArrayList<FreeSpins> freeSpinsAwardedsBySymbol = new ArrayList<>();
    ArrayList<Symbol> symbols = new ArrayList<>();
    gameParameters gameParameters;
	ArrayList<String> specialFeatures;
	
    public stagesEnum getStage() {
		return stage;
	}

	public void setStage(stagesEnum stage) {
		this.stage = stage;
	}

	public reelsEnum getReels() {
		return reels;
	}

	public void setReels(reelsEnum reels) {
		this.reels = reels;
	}

    public ArrayList<ArrayList<Integer>> getPrizeLinePositions() {
		return prizeLinePositions;
	}

	public void setPrizeLinePositions(ArrayList<ArrayList<Integer>> prizeLinePositions) {
		this.prizeLinePositions = prizeLinePositions;
	}

    public ArrayList<String> getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(ArrayList<String> specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

    public ArrayList<Symbol> getSymbols() {
        return symbols;
    }

    public void setSymbols(ArrayList<Symbol> symbols) {
        this.symbols = symbols;
    }

    public gameParameters getGameParameters() {
        return gameParameters;
    }

    public void setGameParameters(gameParameters gameParameters) {
        this.gameParameters = gameParameters;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getPrizeLineType() {
        return prizeLineType;
    }

    public void setPrizeLineType(String prizeLineType) {
        this.prizeLineType = prizeLineType;
    }

    public Parameters(int gameId, String gameName, String prizeLineType, ArrayList<reelType> models,
            ArrayList<FreeSpins> freeSpinsAwardedsBySymbol, ArrayList<Symbol> symbols,
            readJson.gameParameters gameParameters, int[][] boardPosition) {
        super();
        this.gameId = gameId;
        this.gameName = gameName;
        this.prizeLineType = prizeLineType;
        this.models = models;
        this.freeSpinsAwardedsBySymbol = freeSpinsAwardedsBySymbol;
        this.symbols = symbols;
        this.gameParameters = gameParameters;
    }

    public ArrayList<FreeSpins> getFreeSpinsAwardedsBySymbol() {
        return freeSpinsAwardedsBySymbol;
    }

    public void setFreeSpinsAwardedsBySymbol(ArrayList<FreeSpins> freeSpinsAwardedsBySymbol) {
        this.freeSpinsAwardedsBySymbol = freeSpinsAwardedsBySymbol;
    }

    public ArrayList<reelType> getModels() {
        return models;
    }

    public void setModels(ArrayList<reelType> models) {
        this.models = models;
    }

    @Override
	public String toString() {
		return "Parameters [gameId=" + gameId + ", gameName=" + gameName + ", prizeLineType=" + prizeLineType
				+ ", prizeLinePositions=" + prizeLinePositions + ", models=" + models + ", freeSpinsAwardedsBySymbol="
				+ freeSpinsAwardedsBySymbol + ", symbols=" + symbols + ", gameParameters=" + gameParameters
				+ ", specialFeatures=" + specialFeatures + "]";
	}

    @SuppressWarnings("unchecked")
	public Parameters(File file) {
        super();
        try {
        	 // Llamamos al archivo JSON y lo guardamos en un JSONObject.
            File jsonFile = file;
            Object jsonText = new JSONParser().parse(new FileReader(jsonFile));
            JSONObject json = (JSONObject) jsonText;

            this.stage = stagesEnum.MAIN;
            this.reels = reelsEnum.BASIC;
            
            // Obtenemos los valores simples del JSON.
            gameName = (String) json.get("gameName");
            prizeLineType = (String) json.get("prizeLineType");
            gameId = ((Long) json.get("gameId")).intValue();

            JSONArray prizeLinesPositionsJSON = (JSONArray) json.get("prizeLinesPositions");
            if(prizeLinesPositionsJSON!=null) {
            	for(int i=0; i<prizeLinesPositionsJSON.size();i++) {
                    JSONArray lineArray = (JSONArray) prizeLinesPositionsJSON.get(i);
                    ArrayList<Integer> line = new ArrayList<>();
                    for (Object pos : lineArray) {
                        line.add(((Long) pos).intValue());
                    }
                    this.prizeLinePositions.add(line);
            	}
            }
            
            // Mapeo de modelos
            models = new ArrayList<>();
            JSONArray modelsJson = (JSONArray) json.get("models");
            
            for (Object modelObj : modelsJson) {
            	
                JSONObject model = (JSONObject) modelObj;
                String id = (String) model.get("id");
                JSONArray reelsJson = (JSONArray) model.get("reels");

                ArrayList<ArrayList<Integer>> reelsList = new ArrayList<>();
                
                for (Object reelObj : reelsJson) {
                	
                    JSONArray individualReel = (JSONArray) reelObj;
                    ArrayList<Integer> literalReels = new ArrayList<>();
                    
                    for (Object num : individualReel) {
                        literalReels.add(((Long) num).intValue());
                    }
                    
                    reelsList.add(literalReels);
                    
                }
                
                models.add(new reelType(id, reelsList));
               
            }

            // Mapeo de freeSpinsAwardedBySymbol
            JSONArray freeSpinsArray = (JSONArray) json.get("freeSpinsAwardedsBySymbol");
            
            for (Object freeSpinObj : freeSpinsArray) {
            	
                JSONObject freeSpinObject = (JSONObject) freeSpinObj;
                FreeSpins freeSpin = new FreeSpins();
                freeSpin.stage = (String) freeSpinObject.get("stage");
                freeSpin.symbols = new ArrayList<>();

                JSONArray symbolsArray = (JSONArray) freeSpinObject.get("symbols");
                
                for (Object symbolObj : symbolsArray) {
                	
                    JSONObject amountObject = (JSONObject) symbolObj;
                    JSONObject amountMap = (JSONObject) amountObject.get("amount");

                    Amount amount = new Amount();
                    amount.idSymbol = ((Long) amountObject.get("idSymbol")).intValue();
                    amount.amount = new HashMap<>();
                    
                    for (Object key : amountMap.keySet()) {
                        amount.amount.put((String) key, ((Long) amountMap.get(key)).intValue());
                    }
                    
                    freeSpin.symbols.add(amount);
                    
                }
                
                freeSpinsAwardedsBySymbol.add(freeSpin);
            }

            // Mapeo de símbolos
            
            symbols = new ArrayList<>();
            
            JSONArray symbolsJson = (JSONArray) json.get("symbols");
            if (symbolsJson != null) {
            	for (Object symbolObj : symbolsJson) {
                	
                    JSONObject symbol = (JSONObject) symbolObj;
                    
                    symbols.add(new Symbol(
                    		
                        ((Long) symbol.get("id")).intValue(),
                        (String) symbol.get("name"),
                        ((Long) symbol.get("symbolPrize")).intValue(),
                        ((Long) symbol.get("minQuantity")).intValue(),
                        ((Long) symbol.get("maxQuantity")).intValue()
                        
                    ));
                }
            } 

            // Mapeo de gameParameters
            JSONObject gameParametersObj = (JSONObject) json.get("gameParameters");
            gameParameters = new gameParameters(
                ((Long) gameParametersObj.get("prizeCount")).intValue(),
                ((Long) gameParametersObj.get("maxCount")).intValue(),
                ((Long) gameParametersObj.get("numberRows")).intValue(),
                ((Long) gameParametersObj.get("numberColumns")).intValue(),
                ((Long) gameParametersObj.get("firstCell")).intValue(),
                ((Long) gameParametersObj.get("originalNumber")).intValue(),
                ((Long) gameParametersObj.get("bonusNumber")).intValue(),
                ((Long) gameParametersObj.get("bonusMin")).intValue()
       		);
            
            // Mapeo de specialFeatures
            specialFeatures = new ArrayList<>();
            JSONArray specialFeaturesJSON = (JSONArray) json.get("specialFeatures");
            
            for (Object feature : specialFeaturesJSON) {
                specialFeatures.add((String) feature);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
