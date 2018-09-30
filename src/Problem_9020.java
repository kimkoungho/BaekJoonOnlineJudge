import java.awt.Point;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Problem_9020 {

	static Set<Integer> primeSet = new HashSet();
	
	//특정 수가 소수인지 판별 
	static boolean isPrime(int num) {
		
//		if(primeSet.contains(num)) {
//			return true;
//		}
		
		for(int unit = 2; unit<=Math.sqrt(num)+1; unit++) {
			if(num%unit == 0) {
				if(num/unit == 1) {
					return true;
				}else {
					return false;
				}
			}
		}
		
//		primeSet.add(num);
		return true;
	}
	
	static Point solve(int n) { // n : 짝
		int pivot = (int)(n/2.0);
		
		Point ret = null;
		
		while(pivot >= 2) {
			int other = n - pivot;
			
			//System.out.println(pivot + " : " + isPrime(pivot) + " , "+other + ": "+isPrime(other));
			if(isPrime(pivot) && isPrime(other)) {
				return new Point(pivot, other);
			}
			pivot--;
		}
		
		return ret;
	}
/**
3
8
10
16

3 5
5 5
5 11

5
2
4
6
12
14

1 1
2 2
3 3
5 7
7 7

4
20
22
40
42

7 13
11 11
17 23
19 23
*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int tc = sc.nextInt();
		for(int i=0; i<tc; i++) {
			int n = sc.nextInt();
			
//			int n = (int)(4 + Math.round(Math.random() * 5000) * 2);
//			System.out.println(n);
			
//			System.out.println(isPrime(n));
			
			Point ret = solve(n);
			
			System.out.println(ret.x+" "+ret.y);
		}
	}

}
