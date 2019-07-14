package problem_1351;
import java.util.HashMap;
import java.util.Scanner;

public class Problem_1351 {

	static HashMap<Long, Long> cache = new HashMap();
	
	static long solve(long N, long P, long Q) {
		if(N == 0) {// A[0] = 1
			return 1;
		}
		
		if(cache.containsKey(N)) {
			return cache.get(N);
		}
		
		long ret =solve(Math.floorDiv(N, P), P, Q) + solve(Math.floorDiv(N, Q), P, Q);
		cache.put(N, ret);
		
		return ret;
	}
	
/**
7 2 3

7
*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		long N, P, Q;
		N = sc.nextLong(); // 0 ~ 10^12
		P = sc.nextLong(); // 2 ~ 10^9
		Q = sc.nextLong(); // 2 ~ 10^9
		
		System.out.println(solve(N, P, Q));
	}

}
