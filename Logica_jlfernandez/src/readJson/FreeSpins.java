package readJson;

import java.util.ArrayList;

public class FreeSpins {
	
	String stage;
	ArrayList<Amount> symbols;
	
	public FreeSpins() {
		super();
	}
	public FreeSpins(String stage, ArrayList<Amount> symbols) {
		super();
		this.stage = stage;
		this.symbols = symbols;
	}
	@Override
	public String toString() {
		return "FreeSpins [stage=" + stage + ", symbols=" + symbols + "]";
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public ArrayList<Amount> getSymbols() {
		return symbols;
	}
	public void setAmount(ArrayList<Amount> symbols) {
		this.symbols = symbols;
	}
}
