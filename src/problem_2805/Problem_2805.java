package problem_2805;

import java.util.Scanner;

public class Problem_2805 {
	
	private static long lowerBound(int[] tree, int M) {
		int from = 0;
		int to = tree.length - 1;
		
		long sum = 0;
		while(from < to) {
			int mid = (from + to) >>> 1;
			
			
		}
		
		return 0;
	}
	/**
	4 7
	20 15 10 17
	
	15
	 */

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
//		int N = sc.nextInt(); // 나무의 수 <= 1,000,000
//		int M = sc.nextInt(); // 가져가야할 최대 나무 길이 <= 2,000,000,000  
//		
//		int[] tree = new int[N]; // 나무들 높이 
//		for(int i=0; i<N; i++) {
//			tree[i] = sc.nextInt(); // <= 1,000,000,000
//		}
		
		int N = 4;
		int M = 10; //
		int[] tree = new int[] {
				20, 15, 10, 17
		};
		
		
		
	}
}
