package problem_7562;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Problem_7562 {	
	
	private static int[] nys = new int[] {-2, -2, -1, 1, 2,  2,  1, -1};
	private static int[] nxs = new int[] {-1,  1,  2, 2, 1, -1, -2, -2};
	
	private static int solve(int n, int currY, int currX, int tarY, int tarX) {
		// BFS
		Queue<Night> queue = new LinkedList<Night>();
		int[][] visit = new int[n][n];
		
		// 현재 좌표 
		Night startNight = new Night(currY, currX, 0);
		queue.add(startNight);
		visit[startNight.getY()][startNight.getX()] = 1;
		
		while(!queue.isEmpty()) {
			Night night = queue.poll();
			
			// 목표 지점 도달 
			if(tarY == night.getY() && tarX == night.getX()) {
				return night.getMoveCount();
			}
			
			// next  add
			for(int i=0; i<8; i++) {
				int ny = nys[i] + night.getY();
				int nx = nxs[i] + night.getX();
				
				if(ny >= 0 && nx >= 0 && ny < n && nx < n) {
					Night nextNight = new Night(ny, nx, night.getMoveCount()+1);
					
					// 추가 가능 여부 체크  
					if(visit[nextNight.getY()][nextNight.getX()] == 0) {
						visit[nextNight.getY()][nextNight.getX()] = 1;
						// 추가
						queue.add(nextNight);
					}
				}
			}
			
		}
		
		return -1;
	}
	/**
3
8
0 0
7 0
100
0 0
30 50
10
1 1
1 1

5
28
0
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int testCase = sc.nextInt();
//		int testCase =1;
		for(int i=0; i<testCase; i++) {
			int l = sc.nextInt();// 한변의 길이
//			int l = 10;
			
			int y = sc.nextInt(), x = sc.nextInt();// 현재 위치
//			int y = 1, x = 1;// 현재 위치
			
			int targetY = sc.nextInt(), targetX = sc.nextInt();// 가려고 하는 위치
//			int targetY = 1, targetX = 1;
			
			
			int result = solve(l, y, x, targetY, targetX);
			
			System.out.println(result);
		}
		
//		moveTest();
	}

	
	private static void moveTest() {
		int n = 10;
		Night night = new Night(5, 5, 1);
		
		
		Night leftBottom = new Night(nys[0] + night.getY(), nxs[0] + night.getX(), 2);
		System.out.println(leftBottom);
		assert leftBottom.getY() == 6 && leftBottom.getX() == 3;
		
		Night leftTop = new Night(nys[1] + night.getY(), nxs[1] + night.getX(), 2);
		System.out.println(leftTop);
		assert leftTop.getY() == 4 && leftTop.getX() == 3;
		
		Night topLeft = new Night(nys[2] + night.getY(), nxs[2] + night.getX(), 2);
		System.out.println(topLeft);
		assert topLeft.getY() == 3 && topLeft.getX() == 4;
		
		Night topRight = new Night(nys[3] + night.getY(), nxs[3] + night.getX(), 2);
		System.out.println(topRight);
		assert topRight.getY() == 3 && topRight.getX() == 6;
		
		Night rightTop = new Night(nys[4] + night.getY(), nxs[4] + night.getX(), 2);
		System.out.println(rightTop);
		assert rightTop.getY() == 4 && rightTop.getX() == 7;
		
		Night rightBottom = new Night(nys[5] + night.getY(), nxs[5] + night.getX(), 2);
		System.out.println(rightBottom);
		assert rightBottom.getY() == 5 && rightBottom.getX() == 7;
		
		Night bottomRight = new Night(nys[6] + night.getY(), nxs[6] + night.getX(), 2);
		System.out.println(bottomRight);
		assert bottomRight.getY() == 7 && bottomRight.getX() == 6;
		
		Night bottomLeft = new Night(nys[7] + night.getY(), nxs[7] + night.getX(), 2);
		System.out.println(bottomLeft);
		assert bottomLeft.getY() == 7 && bottomLeft.getX() == 4;
	}
}
