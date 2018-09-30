import java.util.Scanner;

public class Problem_9461 {

	private static long solve(int n) {
		//파도반 수열 
		// A[k] = A[k-5] + A[k-1]
		
		// 5개 
		long[] cache = new long[] {1,1,1,2,2};
		
		if(n <= 5) {
			return cache[n-1];
		}
		
		int idx = 0;
		// 반복 횟수 
		for(int i=6; i<=n; i++) { 
			 if(idx == cache.length) {
				 idx = 0;
			 }
			 
			// 값
			cache[idx] += idx == 0 ? cache[4] : cache[idx-1];
			//System.out.println(i+" : " + cache[idx]);
			
			idx++;
		}
		
		
		
		return cache[idx-1];
	}
/**
2
6
12

3
16
*/
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int i=1; i<=T; i++) {
			int N = sc.nextInt();
			System.out.println(solve(N));
		}
	}

}
