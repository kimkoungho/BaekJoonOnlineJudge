package problem_1074;
import java.io.IOException;
import java.util.Scanner;


public class Problem_1074 {
	
	private static int solve(int r, int c, int N) {
		
		//System.out.println(r + ", " + c + " .. " + N);
		//기저 사례 : 
		if(N == 1) {
			if(r == 0) {
				return c;
			}else {
				return r+c+1;
			}
		}
		
		int ret = 0;
		
		// 각 사분면의 기준 길이 
		int n = (int)Math.pow(2, N-1);
		
		if(r<n && c <n) {//1 사분면 
			return solve(r, c, N-1); //이동 거리 
		}else {
			// 한 사분면의 마지막 
			int prevLen = solve(n-1, n-1, N) + 1; 
			
			if(r<n) {
				ret = prevLen + solve(r, c-n, N-1);
			}else if(c<n) {
				ret = prevLen * 2 + solve(r-n, c, N-1);
			}else {
				ret = prevLen * 3 + solve(r-n, c-n, N-1);
			}
			
			//System.out.println(prevLen + ", " +ret);
		}
		
		return ret;
	}

/**
2 3 1

11

3 7 7

63

1 0 0
1 0 1
1 1 0
1 1 1
2 0 0
2 0 2
2 2 2
2 3 3
3 6 6
5 31 31
11 2047 2047
12 4095 4095
13 8191 8191
14 16383 16383
15 32767 32766
*/
	

	public static void main(String[] args) throws IOException {
		//Z
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext()) {
			// r, c 가 몇 번째 사각형에 있는지 ...
			int N, r, c;
			N = sc.nextInt();
			r = sc.nextInt();
			c = sc.nextInt();
			
			System.out.println(solve(r, c, N));
		}
		
	}
	
	

}
