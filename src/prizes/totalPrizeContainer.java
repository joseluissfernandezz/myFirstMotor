package prizes;

public class totalPrizeContainer {
	  private float totalPrize;

	public totalPrizeContainer() {
		super();
	}

	public totalPrizeContainer(int totalPrize) {
		super();
		this.totalPrize = totalPrize;
	}

	@Override
	public String toString() {
		return "totalPrizeContainer [totalPrize=" + totalPrize + "]";
	}

	public float getTotalPrize() {
		return totalPrize;
	}

	public void setTotalPrize(float totalPrize) {
		this.totalPrize = totalPrize;
	}
}
