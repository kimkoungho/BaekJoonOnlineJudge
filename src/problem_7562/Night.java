package problem_7562;

public class Night {
	// 위치 정보 
	private int y;
	private int x;
	// 이동 횟수 
	private int moveCount;
	
	public Night(int y, int x, int moveCount) {
		this.y = y;
		this.x = x;
		this.moveCount = moveCount;
	}
	
	public int getY() {
		return this.y;
	}
	public int getX() {
		return this.x;
	}
	public int getMoveCount() {
		return this.moveCount;
	}
	
	
	@Override
	public String toString() {
		return "[y="+y+", x="+x+", moveCount="+moveCount+"]";
	}
}
