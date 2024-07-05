package readJson;

import java.util.ArrayList;

public class reelType {

	String id;
	ArrayList<ArrayList<Integer>> reels;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ArrayList<ArrayList<Integer>> getReels() {
		return reels;
	}
	public void setReels(ArrayList<ArrayList<Integer>> reels) {
		this.reels = reels;
	}
	public reelType(String id, ArrayList<ArrayList<Integer>> reels) {
		super();
		this.id = id;
		this.reels = reels;
	}
	public reelType() {
		super();
	}
	@Override
	public String toString() {
		return "reelType [id=" + id + ", reels=" + reels + "]";
	}
}
