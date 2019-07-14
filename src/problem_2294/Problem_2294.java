package problem_2294;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

public class Problem_2294 {
	
	static HashMap<Integer, Integer> cache;
	static TreeSet<Integer> coinSet;
	
	

	static int solve(int k) {
		
//		System.out.println(k);
		System.out.println(cache);
		
		if(cache.containsKey(k)) {
			return cache.get(k);
		}
		
		int ret = -1;
		
		for(Integer coin : coinSet.descendingSet()) {
			if(coin > k) {
				continue;
			}else if(coin == k) {// 1개 동전 = 목표
				cache.put(k, 1);
//				System.out.println(coin + " = " +k);
				return cache.get(coin);
			}else {
				int prev = solve(k-coin);
				if(prev != -1) {
					ret = ret == -1 ? prev+1 : Math.min(ret, prev+1); 
				}
			}
		}
		
		//System.out.println(k + " " + ret);
		cache.put(k, ret);
		return cache.get(k);
	}
	
/**
3 15
1
5
12

3

3 22
7
5
10

3

5 23
9
3
10
8
2

3
*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int N, k;
		N = sc.nextInt();
		k = sc.nextInt();
		
		cache = new HashMap();
		coinSet = new TreeSet();
		
		for(int i=0; i<N; i++) {
			coinSet.add(sc.nextInt());
		}
		
		System.out.println(solve(k));
		
	}

}
