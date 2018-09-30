import java.util.Scanner;

public class Problem_11726 {

	private static final int DIV = 10007;
	
	private static int solve(int n) {
		/* 규칙  
		 * 1. [0][0] 에 2 * 1 직사각형을 채우는 경우
		 * -> solve(n-1) : 2 * (n-1) 개 를 채우는 경우 
		 * 2. [0][0] 에 1 * 2 직사각형을 채우는 경우
		 * -> solve(n-2) : 2 * (n-2) 개 를 채우는 경우 
		 * */
		if(n == 1) {
			return 1;
		}else if(n==2) {
			return 2;
		}
		
		int ret = 0;
		int minusOne = 2, minusTwo = 1;
		for(int i=3; i<=n; i++) {
			// a[n] = a[n-1] + a[n-2]
			ret = minusOne + minusTwo;
			ret %= DIV;
			minusTwo = minusOne;
			minusOne = ret;
		}
		
		return ret;
	}
/**
2 
->2

9
->55
*/
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		System.out.println(solve(n));
	}

}
