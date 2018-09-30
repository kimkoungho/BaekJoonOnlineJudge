import java.util.Scanner;

public class Problem_1309 {
	
	private static final int DIV = 9901;

	
	private static long solve(int n) {
		long[] sums = new long[n+1];

		// 점화식 : sums[n] = sums[n-1] * 2 + sums[n-2]
		// TODO: 난 못찾음 ...
		
		sums[0] = 1;
		sums[1] = 3;
		for(int i=2; i<=n; i++) {
			sums[i] = (sums[i-1] * 2 + sums[i- 2])%DIV;
		}
		
		return sums[n];
	}
/**
4

41
*/
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		System.out.println(solve(N));
	}

}
