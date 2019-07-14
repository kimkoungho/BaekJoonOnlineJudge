package problem_9251;

import java.util.Scanner;

/** 
 * LCS reference
 * https://ko.wikipedia.org/wiki/%EC%B5%9C%EC%9E%A5_%EA%B3%B5%ED%86%B5_%EB%B6%80%EB%B6%84_%EC%88%98%EC%97%B4
 */
public class Problem_9251 {


/**
ACAYKP
CAPCAK

4

XMJYAUZ
MZJAWXU
*/
	// TODO: 최적화 코드 
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String str1 = sc.nextLine();
		String str2 = sc.nextLine();
		
//		String str1 = "XMJYAUZ";
//		String str2 = "MZJAWXU";
//		
		int lcs = 0;
		int[][] cache = new int[str1.length() + 1][str2.length()+1];
		for(int i=0; i<str1.length(); i++) {
			for(int j=0; j<str2.length(); j++) {
				// 같으면 왼쪽 대각선 값 + 1
				if(str1.charAt(i) == str2.charAt(j)) {
					cache[i+1][j+1] = cache[i][j] + 1;
				}else { // 다르면 위 vs 왼쪽 
					cache[i+1][j+1] = Math.max(cache[i][j+1], cache[i+1][j]);
				}
				
				lcs = Math.max(lcs, cache[i+1][j+1]);
			}
		}
		
//		for(int i=0; i<cache.length; i++) {
//			for(int j=0; j<cache.length; j++) {
//				System.out.print(cache[i][j] + "\t");
//			}
//			System.out.println();
//		}
		
		System.out.println(lcs);
	}

}
