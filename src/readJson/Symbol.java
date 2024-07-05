package readJson;

public class Symbol {
	
	int id;
	String name;
	int symbolPrize;
	int minQuantity;
	int maxQuantity;
	
	public Symbol(int id, String name, int symbolPrize, int minQuantity, int maxQuantity) {
		super();
		this.id = id;
		this.name = name;
		this.symbolPrize = symbolPrize;
		this.minQuantity = minQuantity;
		this.maxQuantity = maxQuantity;
	}
	public int getMinQuantity() {
		return minQuantity;
	}
	public void setMinQuantity(int minQuantity) {
		this.minQuantity = minQuantity;
	}
	public int getMaxQuantity() {
		return maxQuantity;
	}
	public void setMaxQuantity(int maxQuantity) {
		this.maxQuantity = maxQuantity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSymbolPrize() {
		return symbolPrize;
	}
	public void setSymbolPrize(int symbolPrize) {
		this.symbolPrize = symbolPrize;
	}
	public Symbol() {

	}
	@Override
	public String toString() {
		return "Symbol [id=" + id + ", name=" + name + ", symbolPrize=" + symbolPrize + ", minQuantity=" + minQuantity
				+ ", maxQuantity=" + maxQuantity + "]";
	}
}
