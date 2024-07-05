package readJson;

import java.util.HashMap;

public class Amount {

	int idSymbol;
	HashMap<String, Integer> amount;
	
	public int getIdSymbol() {
		return idSymbol;
	}
	public void setIdSymbol(int idSymbol) {
		this.idSymbol = idSymbol;
	}
	public HashMap<String, Integer> getAmount() {
		return amount;
	}
	public void setAmount(HashMap<String, Integer> amount) {
		this.amount = amount;
	}
	public Amount() {
		super();
	}
	public Amount(int idSymbol, HashMap<String, Integer> amount) {
		super();
		this.idSymbol = idSymbol;
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Amount [idSymbol=" + idSymbol + ", amount=" + amount + "]";
	}
}
