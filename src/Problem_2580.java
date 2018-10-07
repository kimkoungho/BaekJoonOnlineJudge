import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem_2580 {
	
	// 스도쿠 판 
	private static int[][] board = new int[9][9];
	
	private static void solve(int r, int c) {
		
	}
	/**
0 3 5 4 6 9 2 7 8
7 8 2 1 0 5 6 0 9
0 6 0 2 7 8 1 3 5
3 2 1 0 4 6 8 9 7
8 0 4 9 1 3 5 0 6
5 9 6 8 2 0 4 1 3
9 1 7 6 5 2 0 8 0
6 0 3 7 0 1 9 5 2
2 5 8 3 9 4 7 6 0

1 3 5 4 6 9 2 7 8
7 8 2 1 3 5 6 4 9
4 6 9 2 7 8 1 3 5
3 2 1 5 4 6 8 9 7
8 7 4 9 1 3 5 2 6
5 9 6 8 2 7 4 1 3
9 1 7 6 5 2 3 8 4
6 4 3 7 8 1 9 5 2
2 5 8 3 9 4 7 6 1
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		List<Point> targetList = new ArrayList<Point>();
		
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				board[i][j] = sc.nextInt();
				if(board[i][j] == 0) {
					targetList.add(new Point(i, j));
				}
			}
		}

//		System.out.println(targetList);
		
		for(Point point : targetList) {
			// 해당 좌표에 넣을 수 있는 모든 값 
			
		
			boolean[] row = new boolean[9];
			boolean[] col = new boolean[9];
			for(int i=0; i<9; i++) {
				if(board[point.x][i] >0) {
					row[board[point.x][i]-1] = true;
				}
				if(board[i][point.y] > 0) {
					col[board[i][point.y]-1] = true;
				}
			}
		
			boolean[] area = new boolean[9];
			int r = (point.x / 3) * 3;
			int c = (point.y / 3) *  3;
			for(int i=r; i<r+3; i++) {
				for(int j=c; j<c+3; j++) {
					if(board[i][j] > 0) {
						area[board[i][j]-1] = true;
					}
				}
			}
			
			// 후보자들
			List<Integer> candidates = new ArrayList();
			for(int i=0; i<9; i++) {
				if(!row[i] && !col[i] && !area[i]) {
					candidates.add(i+1);
				}
			}
			System.out.println(point);
			System.out.println(candidates);
			
		}
		
		
	}
	
	
}
