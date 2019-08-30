
public class NimAIPlayer extends NimPlayer implements Testable {
	
	public NimAIPlayer() {
		super();
	}
	
	public void setAiPlayer() {
		
	}
	
	
	
	public int removeStone(int i, int maxStoneremove , int stoneLeft) {
		Integer move = 0;
		move = (stoneLeft-1) % (maxStoneremove+1);
		return move;
	}
	
	
}
