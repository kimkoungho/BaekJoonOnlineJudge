import java.util.Arrays;
import java.util.Scanner;

public class Problem_11052 {
	
	private static int N;
	private static int[] setPrice;

	private static int[][] cache;
	
	
	private static int solve(int count, int target) {
		//메모제이션 
		if(cache[count][target] != -1) {
			return cache[count][target];
		}
		
		int ret = setPrice[count];
		 
		for(int i=1; i<=target; i++) {
			
			if(count+i > target) {
				break;
			}
			
			
			//최대값 
			ret = Math.max(ret, setPrice[count] + solve(i, target-count));
		}
		
//		System.out.println(count+" "+target+"-------");
//		System.out.println(ret);
		
		cache[count][target] = ret;
		return ret;
	}
	
	//개선 
	static int solveCount() {
		// 부분합을 저장할 배열 
		// maxPrice[i] : 개수 i 가 가질수 있는 최대 값 
		int[] maxPrice = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			maxPrice[i] = setPrice[i];
			
			for(int j=1; j<=i/2; j++) {
				//System.out.println(maxPrice[i] + " vs " + maxPrice[j] + maxPrice[i-j]); 
				if(maxPrice[i] < maxPrice[j]+maxPrice[i-j]) {
					maxPrice[i] = maxPrice[j]+maxPrice[i-j];
				}
			}
		}
		
		return maxPrice[N];
	}
	
/**
4
1 5 6 7

10

5
10 9 8 7 6

50

10
1 1 2 3 5 8 13 21 34 55

55

10
5 10 11 12 13 30 35 40 45 47

50

4
5 2 8 10

20

4
3 5 15 16

18
*/
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		//붕어빵의 개수 
		N = sc.nextInt();
		//붕어빵 세트의 가격
		setPrice = new int[N+1];
		cache = new int[N+1][N+1];
		
		for(int i=0; i<N+1; i++) {
			Arrays.fill(cache[i], -1);
		}
		
		setPrice[0] = 0;
		for(int i=1; i<=N; i++) {
			setPrice[i] = sc.nextInt();
		}
		
		//System.out.println(solve(0, N));
		
		System.out.println(solveCount());
	}
	
//	개선 시작 ....
// https://www.acmicpc.net/source/8986240
	

}
