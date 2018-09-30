import java.util.Scanner;

public class Problem_11727 {
	private static final int DIV = 10007;
	
	private static int solve(int n) {
		/**규칙 
		 * 1. [0][0] 에 2 * 1 사각형을 넣은 경우
		 * -> solve(n-1)
		 * 2. [0][0] 에 1 * 2 사각형을 넣은 경우
		 * -> solve(n-2)
		 * 3. [0][0] 에 2 * 2 사각형을 넣은 경우
		 * -> solve(n-2)
		 * */
		
		int minusOne, minusTwo, ret;
		minusTwo = 1; // n = 1
		minusOne = 3; // n =2
		ret = 0;
		
		if(n== 1) return minusTwo;
		else if(n == 2) return minusOne;
		
		for(int i=3; i<=n; i++) {
			// a[n] = a[n-1] + a[n-2] * 2
			ret = minusOne + minusTwo * 2;
			ret %= DIV;
			minusTwo = minusOne;
			minusOne = ret;
		}
		
		return ret;
	}
/**
2
-> 3
8
-> 171
12
-> 2731
*/
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		
		System.out.println(solve(n));
	}

}
