package readJson;

public class gameParameters {
	
	int prizeCount;
	int maxCount;
	int numberRows;
	int numberColumns;
	int firstCell;
	int originalNumber;
	int bonusNumber;
	int bonusMin;
	
	public int getPrizeCount() {
		return prizeCount;
	}
	public void setPrizeCount(int prizeCount) {
		this.prizeCount = prizeCount;
	}
	public int getMaxCount() {
		return maxCount;
	}
	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}
	public int getNumberRows() {
		return numberRows;
	}
	public void setNumberRows(int numberRows) {
		this.numberRows = numberRows;
	}
	public int getNumberColumns() {
		return numberColumns;
	}
	public void setNumberColumns(int numberColumns) {
		this.numberColumns = numberColumns;
	}
	public int getFirstCell() {
		return firstCell;
	}
	public void setFirstCell(int firstCell) {
		this.firstCell = firstCell;
	}
	public int getOriginalNumber() {
		return originalNumber;
	}
	public void setOriginalNumber(int originalNumber) {
		this.originalNumber = originalNumber;
	}
	public gameParameters(int prizeCount, int maxCount, int numberRows, int numberColumns, int firstCell,
			int originalNumber, int bonusNumber, int bonusMin) {
		super();
		this.prizeCount = prizeCount;
		this.maxCount = maxCount;
		this.numberRows = numberRows;
		this.numberColumns = numberColumns;
		this.firstCell = firstCell;
		this.originalNumber = originalNumber;
		this.bonusNumber = bonusNumber;
		this.bonusMin = bonusMin;
	}
	public int getBonusNumber() {
		return bonusNumber;
	}
	public int getBonusMin() {
		return bonusMin;
	}
	public void setBonusMin(int bonusMin) {
		this.bonusMin = bonusMin;
	}
	public void setBonusNumber(int bonusNumber) {
		this.bonusNumber = bonusNumber;
	}
	public gameParameters() {
		super();

	}
	@Override
	public String toString() {
		return "gameParameters [prizeCount=" + prizeCount + ", maxCount=" + maxCount + ", numberRows=" + numberRows
				+ ", numberColumns=" + numberColumns + ", firstCell=" + firstCell + ", originalNumber=" + originalNumber
				+ ", bonusNumber=" + bonusNumber + ", bonusMin=" + bonusMin + "]";
	}
}
