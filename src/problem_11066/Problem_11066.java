package problem_11066;
import java.util.Arrays;
import java.util.Scanner;

public class Problem_11066 {
	private static int[] files;
	// s ~ e 까지 파일의 누적합 
	private static int[][] totalSumed;
	// s ~ e 까지 해당 파일을 만드는데 필요한 비용 
	private static int[][] sumed;
	
	private static void printArr(int[][] arr) {
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr.length; j++) {
				System.out.print(arr[i][j]+"\t");
			}
			System.out.println();
		}
	}

	//s ~ e 까지 file 들을 합치는 최소 값	
	private static int solve(int s, int e) {
		if(s==e) {
			// 해당 파일의 값
			sumed[s][e] = files[s]; 
			// 해당 파일을 만드는데 쓴 비용 
			return totalSumed[s][e] = 0;
		}
		
		if(totalSumed[s][e] != -1) {
			return totalSumed[s][e];
		}
		
		//
		if(sumed[s][e] == -1) {
			sumed[s][e] = 0;
			for(int i=s; i<=e; i++) {
				sumed[s][e] += files[i];
			}
		}
		

		
		// 이전 파일들을 만드는 값중 최소값 
		int minTotalSum = Integer.MAX_VALUE;
		for(int i=s; i<e; i++) {
			// 2개의 파일을 합치는 값 
			// s - i, i+1 - e
			int totalSum = solve(s, i) + solve(i+1, e);
			
			minTotalSum = Math.min(minTotalSum, totalSum);
		}
		totalSumed[s][e] = minTotalSum + sumed[s][e];
//		System.out.println(s +" , "+ e);
//		System.out.println("sum");
//		printArr(sumed);
//		System.out.println("total");
//		printArr(totalSumed);
//		System.out.println();
		
		// 현재 파일을 만드는 값 + 이전 파일을 만들때 썻던 총 비용  
		return totalSumed[s][e];
	}
/**
2
4
40 30 30 50
15
1 21 3 4 5 35 5 4 3 5 98 21 14 17 32


300
864

2
3
40 30 30
8
21 3 4 2 30 4 5 7

160

*/
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			int k = sc.nextInt();
			
			files = new int[k];
			sumed = new int[k][k];
			totalSumed = new int[k][k];
			
			
			for(int i=0; i<k; i++) {
				files[i] = sc.nextInt();
				
				Arrays.fill(sumed[i], -1);
				Arrays.fill(totalSumed[i], -1);
			}

			System.out.println(solve(0, files.length-1));
		}
		
//		// 단일 테스트
//		files = new int[] {40, 30, 30, 50};
//		
//		sumed = new int[4][4];
//		totalSumed = new int[4][4];
//		
//		System.out.println(solve(0, 3));
	}
	
}
