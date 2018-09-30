import java.util.Scanner;

public class Problem_2903 {
	
	private static int solve(int n) {
		// 사각형의 갯수 4^(n)
		double rectCount = Math.pow(4, n);
		
		// 1개의 행의 사각형의 갯수 sqrt(4^(n))
		double rowRectCount = Math.sqrt(rectCount);
		
		// 1개의 행의 사각형의 위쪽 점의 갯수 sqrt(4^(n)) + 1
		double rowHighPointCount = rowRectCount + 1;
		
		// 점의 총갯수 ( sqrt(4^(n)) + 1 ) ^ 2
		return (int)Math.pow(rowHighPointCount, 2);
	}
/**
2

9

3

25
 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		System.out.println(solve(N));
	}

}
