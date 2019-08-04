package problem_2661;

import java.util.Scanner;

// https://www.acmicpc.net/problem/2661
public class Promblem_2661 {
		
	 
	/** 나쁜수열이 존재하는지 검사 
	 * @param sb:  현재까지의 문자열 
	 * 
	 */
	private static boolean checkBadArray(String str) {
		int len = 1;
		while(len*2 <= str.length()) {
			int idx = str.length() - len;
			String lastStr = str.substring(idx);
			String preStr = str.substring(idx-len, idx);
			
			if(lastStr.equals(preStr)) {
				return true;
			}
			len++;
		}
		
		return false;		
	}
	
	
	// 
	private static String solve(String str, int n) {
		if(checkBadArray(str)) {
			return null;
		}
		
		if(str.length() == n) {
			return str;
		}
		
		String ret = null;
		// 현재 넣은 숫자가 1에서 실패하면 2를 넣는다 !
		for(int i=1; i<=3; i++) {
			ret = solve(str+i, n);
			
			if(ret != null && ret.length() == n) {
				return ret;
			}
		}
		
		
		return ret;
	}
/**
7

1213121

4
1213

5
12131




*/

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		int N = sc.nextInt();
		
//		checkBadArrayTest();		
		String ret = solve("", N);
		System.out.println(ret);
	
	}
	
	// test code
	private static void checkBadArrayTest() {
		String input1 ="1";
		assert checkBadArray(input1) == false;
		
		String input2 = "12323";
		assert checkBadArray(input2) == true;
		
		String input3 =  "23123";
		assert checkBadArray(input3) == false;
		
		String input4 =  "12131213";
		assert checkBadArray(input4) == true;
	}

}
