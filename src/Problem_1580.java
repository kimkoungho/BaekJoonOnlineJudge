import java.util.Scanner;

public class Problem_1580 {

	
	public static long solve(long a, long b) {
		long ret = Math.min(a, b);
		long mod = -1;
		while(mod != 0) {
			if(a < b) {
				mod = b % a;
				b = mod;
			}else {
				mod = a % b;
				a = mod;
			}
			//System.out.println(a + ", "+b+" = "+mod);
			if(mod == 0) {
				break;
			}
			ret  = mod;
		}
		
		
		return ret;
	}
/**
3 4
1

3 6
111

500000000000000000 500000000000000002
11

4 500000000000000000
*/
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 2^63 
//		long a = sc.nextLong();
//		long b = sc.nextLong();
		
		long a = 500000000000000000L;
		long b = 10;

		long ret = solve(a, b);
		StringBuffer sb = new StringBuffer();
		for(int i=1; i<=ret; i++) {
			sb.append("1");
		}
		
		System.out.println(sb.toString());
	}

}
