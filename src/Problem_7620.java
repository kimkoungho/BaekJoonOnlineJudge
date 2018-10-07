import java.util.List;
import java.util.Scanner;

// TODO:

public class Problem_7620 {
	
	// 최대 길이 17000 
	private static List<String> solve(String base, String target) {
		
		return null;
	}
	
	private static void show(int[][] arr) {
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	// 기본 변집거리 계산
	private static void getMinDistance(String base, String target) {
		int[][] cache = new int[target.length()+1][base.length()+1];
		
		// 행, 열 초기화 
		for(int col=0; col<=base.length(); col++) {
			cache[0][col] = col;
		}
		for(int row=0; row<=target.length(); row++) {
			cache[row][0] = row;
		}
		
		//show(cache);
		
		
		for(int i=1; i<=target.length(); i++) {
			char targetChar = target.charAt(i-1);
			
			for(int j=1; j<=base.length(); j++) {
				char baseChar = base.charAt(j-1);
				
				// (i-1, j-1), (i, j-1), (i-1, j) 중 최소값 찾기 
				int minValue = Math.min(cache[i-1][j-1], cache[i][j-1]);
				minValue = Math.min(minValue, cache[i-1][j]);
				
				if(baseChar == targetChar) {
					
				}else {
					minValue++;
				}
				
				cache[i][j] = minValue;
			}
		}
		
		show(cache);
	}
	/**
abcde
xabzdey

a x
c a
c b
m z
c d
c e
a y

aaaaeeeeeeeeeexeeeeeeeeeeaaaa
eeeeeeeeeebbbbeeeeeeeeee

12

	 */
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
//		String base = sc.nextLine();
//		String target = sc.nextLine();
		
		String base = "abcde";
		String target = "xabzdey";
		
		//getMinDistance(base, target);
		
		solve(base, target);
		
//		for(String cmd : solve(base, target)) {
//			System.out.println(cmd);
//		}
	}
}
