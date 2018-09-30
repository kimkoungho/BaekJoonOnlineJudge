import java.util.Scanner;

public class Problem_9465 {
	
	private static int max(int a, int b, int c) {
		int ret = Math.max(a, b);
		return Math.max(ret, c);
	}
	
	private static int solve(int[][] stickers, int n) {
		int[][] cache = new int[3][n];
		
		cache[0][0] = 0;
		cache[1][0] = stickers[0][0];
		cache[2][0] = stickers[1][0];
		for(int i=1; i<n; i++) {
			//cache[0][i] = i-1 행에서 max(선택 x, cache[1][i-1], cache[2][i-1]) + 0
			cache[0][i] = max(cache[0][i-1], cache[1][i-1], cache[2][i-1]);
			//cache[1][i] = i-1 행에서 max(선택 x, cache[2][i-1]) + stickers[0][i]
			cache[1][i] = Math.max(cache[0][i-1], cache[2][i-1]) + stickers[0][i];
			//cache[2][i] = i-1 행에서 max(선택 x, cache[1][i-1]) + stickers[1][i]
			cache[2][i] = Math.max(cache[0][i-1], cache[1][i-1]) + stickers[1][i];
			
			//System.out.println(i + " : " + cache[0][i] + ", " + cache[1][i] + ", " + cache[2][i]);
		} 
		
		return max(cache[0][n-1], cache[1][n-1], cache[2][n-1]);
	}
/**
2
5
50 10 100 20 40
30 50 70 10 60
7
10 30 10 50 100 20 40
20 40 30 50 60 20 80
*/
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for(int tc=1; tc<=T; tc++) {
			int n = sc.nextInt();
			
			int[][] stickers = new int[2][n];
			
			for(int i=0; i<2; i++) {
				for(int j=0; j<n; j++) {
					stickers[i][j] = sc.nextInt();
				}
			}
			
			System.out.println(solve(stickers, n));
		}
	}

}
