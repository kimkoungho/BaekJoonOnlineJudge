package problem_2805;

import java.util.Arrays;
import java.util.Scanner;

public class Problem_2805 {
	
	private static int lowerBound(int[] tree, int M) {
		// 정렬 되었다는 가정 
		Arrays.sort(tree);
		
		int from = 0; // 안 잘라도 되는 경우   
		int to = tree[tree.length - 1]; // 최대 
		
		int result = 0;
		while(from <= to) {
			int mid = (from + to) >>> 1;
			
			long sum = 0;
			for(int i=0; i<tree.length; i++) {
				if(tree[i] > mid) {
					sum += (tree[i] - mid);
				}
			}
			  
			// 조건을 만족하는 값중 가장 큰 값 (mid)
			if(M <= sum) { 
				// mid + 1 : from+1 = to 연속된 수 일 경우 무한 루프 생성 방지 
				from = mid +1;
				// 현재 상태에서 조건을 만족하는 값을 result 저장 
				result = Math.max(result, mid);
			}else {  
				to = mid -1;
			} 
		}
		
		return result;
	}
	/**
	4 7
	20 15 10 17
	
	15
	
	
	10 30
	3 7 10 15 17 20 26 30 31 32
	
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 나무의 수 <= 1,000,000
		int M = sc.nextInt(); // 가져가야할 최대 나무 길이 <= 2,000,000,000  
		
		int[] tree = new int[N]; // 나무들 높이 
		for(int i=0; i<N; i++) {
			tree[i] = sc.nextInt(); // <= 1,000,000,000
		}
		
		int h = lowerBound(tree, M);
		System.out.println(h);
		
		
	}
}
