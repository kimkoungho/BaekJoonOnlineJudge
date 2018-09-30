import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//matrix n승 구하기
public class Problem_10830 {
	/*
2 5 
1 2 
3 4

69 558
337 406
--

3 3
1 2 3
4 5 6
7 8 9

468 576 684
62 305 548
656 34 412
--

5 10
1 0 0 0 1
1 0 0 0 1
1 0 0 0 1
1 0 0 0 1
1 0 0 0 1

512 0 0 0 512
512 0 0 0 512
512 0 0 0 512
512 0 0 0 512
512 0 0 0 512

5 20
1 0 0 0 1
1 0 0 0 1
1 0 0 0 1
1 0 0 0 1
1 0 0 0 1
	 
	 1*1+2*3 = 7, 1*2+2*4=10
	 3*1+4*3 = 15
	 */
	
	public final static int MOD = 1000; 
	
	public static int[][] matrixMultiply(int a[][], int b[][]) {
		if (a == null || b == null || a[0].length != b.length) {
			return null;
		}

		int ret[][] = new int[a.length][b[0].length];

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b.length; j++) {
				ret[i][j] = 0;
				for (int k = 0; k < b.length; k++) {
					ret[i][j] += a[i][k] * b[k][j];
				}
				ret[i][j] %= MOD;
			}
		}

		return ret;
	}
	
	public static int count = 0;
	public static int[][] matrixPow(int a[][], long n){
		count ++;
//		System.out.println(count);
		if(n==1)
			return a;
		
		/* A^n 승 행
		 * n:짝수 - A^(n/2) * A^(n/2)
		 * n:홀수 - A^(n/2) * A^(n/2) * A
		 * 분할 정복으로 구할 수 있음 
		 * 1<= n <= 1천억
		 * log(2, 1천억) = 
		 */
		
		//n/2 배열 
		int [][]ret = matrixPow(a, n/2);
		ret = matrixMultiply(ret, ret); //n/2 * 2 배열 
		
		if(n%2!=0) {	
			//홀수인 경우 원본 배열 곱하기
			 ret = matrixMultiply(ret, a);
		}
		
		
		return ret;
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long b = sc.nextLong();

		int[][] arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				//1000 인 경우 계산 필요없음
				arr[i][j] = (sc.nextInt() % MOD);
			}
		}
		
		long time = System.currentTimeMillis();
		int[][] ret = matrixPow(arr, b);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(ret[i][j] + " ");
			}
			System.out.println();
		}
		
		long after = System.currentTimeMillis();
		System.out.println(after-time);
	}

}
