package problem_10803;
import java.io.*;
import java.util.Arrays;

public class Problem_10803 {
	/*
6 5
5

7 9
6

7 3
5

12 5

	 */
	
	static int[][] cache;
	
	public static int getRectCount(int w, int h) { // 직사각형에서 정사각형의 갯수를 return 
		if(w<h) // h가 무조건 작도록 
			return getRectCount(h, w);
		
		//기저 사례 : 계산한 적 있음
		if(cache[h][w] != -1)
			return cache[h][w];
	
		//h = 현재 만들 수 있는 가장 큰 정사각형의 한변의 길이  
		if(w%h == 0) 
			return cache[h][w] = w/h; 
		
		
		int cnt = Integer.MAX_VALUE;
		//int cnt = getRectCount(w-h, h) + 1;
		
//		if(w*3 >= h*h) {
			cnt = Math.min(cnt, getRectCount(w-h, h) + 1);
//		}else {
			//w 자르기 
			for(int i=1; i<=w/2; i++) {
				int subCnt = getRectCount(w-i, h) + getRectCount(i, h);
				cnt = Math.min(cnt, subCnt);
			}
			//h 자르기 
			for(int j=1; j<=h/2; j++) {
				int subCnt = getRectCount(w, h-j) + getRectCount(w, j);
				cnt = Math.min(cnt, subCnt);
			}
//		}
			
		return cache[h][w] = cnt;
	}
	
	public static void show() {
		for(int i=1; i<cache.length; i++) {
			for(int j=1; j<cache[0].length; j++)
				System.out.print(cache[i][j]+"\t");
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			String[] items = (br.readLine()).split(" ");
			
			// 1 ~ 10000
			int n = Integer.parseInt(items[0]);
			// 1 ~ 100
			int m = Integer.parseInt(items[1]);
			
			// dp[301][101] -> 
			//n >= 3*m // 3*n >= m*m
			// m*m (현재 가장 큰 정사각형) vs 3*n
			
			// 5 6 -> 5*5 <= 3*6 - x
			// 7 3 -> 3*3 <= 3*7 - o
			
			if(n>m) { 
				cache = new int[m+1][n+1];
				for(int i=0; i<=m; i++) 
					Arrays.fill(cache[i], -1);
			}else { 
				cache = new int[n+1][m+1];
				for(int i=0; i<=n; i++)
					Arrays.fill(cache[i], -1);
			}
			
			System.out.println(getRectCount(n, m));
//			show();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
