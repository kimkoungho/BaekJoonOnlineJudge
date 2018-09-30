import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 5 
// 1 2 (3)
// 1 1 (2)
public class Problem_1011 {
	
	// n(n+1) <= y - x 
	public static int search(long target, int k) {
		
		// 자연수의 합
		long sum = 0L;
		
		while(sum < target) {
			sum =  k * (k + 1);
			
			int ret = k * 2;
			if(sum == target) {// sum = target 대칭
				return k * 2;
			}else if(sum > target) { // 대칭이 아닌 경우  
				// 1 2 1 1 -> 5(target) 
				// 1 2 : sum = 6 - 2 
				sum -= k; 
				ret--; // 4 -> 3
			}
			
			//System.out.println("k : "+k +"( "+target + ", "+sum+")");
			
			// sum + (k+1, k, k-1, ... 1)
				
			// sum + (k+1) + (k+1, k, k-1, ... 1)
			if(target-sum <= k+1) {
				return ret+1;
			}
			
			k++;
		}
		
		return -1;
	}
	
/*
3
0 3
1 5
45 50

3
3
4

0 
 */
	public static void main(String[] args) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//2147483647
		//System.out.println(Integer.MAX_VALUE);
		
		try {
			int testCase = Integer.parseInt(br.readLine());
			
			for(int i=1; i<=testCase; i++) {
				String input = br.readLine();
				long x = Long.parseLong(input.split(" ")[0]);
				long y = Long.parseLong(input.split(" ")[1]);
				
//				System.out.println(solve(x, y, 0));
				
				System.out.println(search(y-x, 0));
			}
			
		}catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}

}
