package problem_2829;
import java.util.Scanner;

public class Problem_2829 {
	/*
2 
1 -2
4 5

3
1 2 3
4 5 6
7 8 9

3
1 4 6
2 4 6
3 4 6
	 */
	public static int beautifulMatrix(int arr[][]) {
		
		//대각선의 부분합을 저장
		//왼쪽 상단 -> 우측 하단으로 가는 대각선의 합
		int leftToRight[][] = new int[arr.length][arr.length];
		//오른쪽 상단 -> 왼쪽 하단으로 가는 대각선의 합
		int rightToLeft[][] = new int[arr.length][arr.length];
		
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr.length; j++) {
				if(i>0 && j>0) {
					leftToRight[i][j] += arr[i][j] + leftToRight[i-1][j-1];
				}else {
					leftToRight[i][j] = arr[i][j];
				}
				
				if(i>0 && j+1<arr.length) {
					rightToLeft[i][j] += arr[i][j] + rightToLeft[i-1][j+1];
				}else {
					rightToLeft[i][j] = arr[i][j];
				}
			}
		}
		
//		for(int i=0; i<arr.length; i++) {
//			for(int j=0; j<arr.length; j++) {
//				System.out.print(rightToLeft[i][j]+"\t");
//			}
//			System.out.println();
//		}
		
		//행렬의 값들은 -1000< A[i][j] < 1000
		int ret = Integer.MIN_VALUE;
		
		//N 크기의 행렬에서 만들수 있는 각 부분행렬 구하기
		// N x N , N-1 x N-1 , ... 3 x 3, 2 x 2
		for(int m=arr.length-1; m>=1; m--) {
			//System.out.println((m+1)+ "--------\n");
			for(int i=m; i<arr.length; i++) {
				for(int j=m; j<arr.length; j++) {
					//m 차원 부분 행렬
					//[0][0] ~ [m-1][m-1], [1][1] ~ [m][m], ... [N-1-M][N-1-M] ~ [N-1][N-1]
					
					//leftToRight[i][j] = arr[0][0] + arr[1][1] + ... + arr[i][j]
					int leftSum = leftToRight[i][j];
					if(i-m>0 && j-m>0) {
						//[1][1] ~ [m][m] 인 경우
						//leftToRight[m][m] - [0][0]
						leftSum -= leftToRight[i-m-1][j-m-1];
					}
					//rightToLeft[i][j-m] = arr[0][m] + arr[1][m-1] + .... + arr[i][0]
					int rightSum = rightToLeft[i][j-m];
					if(i-m>0 && j+1<arr.length) {
						//[1][m] ~ [m][1] 인 경우 
						//rightToLeft[m][1] - [1][m]
						rightSum -= rightToLeft[i-m-1][j+1];
					}
					
					//System.out.println(leftSum + " - "+rightSum);
					ret = Math.max(ret, leftSum-rightSum);
				}
			}
		}
		
		return ret;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int arr[][] = new int[N][N];
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr.length; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		System.out.println(beautifulMatrix(arr));
		
	}

}
