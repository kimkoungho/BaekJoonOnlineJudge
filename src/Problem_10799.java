import java.util.Scanner;

public class Problem_10799 {
	
	static int solve(String line) {
		//쇠막대기 ( or )
		//레이저 () 
		
		int count = 0;
		int total = 0;
		for(int i=0; i<line.length(); i++) {
			
			if(line.charAt(i) == '(') {
				count++;
			}else {
				// 레이저이면 이전꺼 지우기, 막대마지막이면 막대기 종료지점 
				count--;
				
				//레이저인 경우 
				if(i>0 && line.charAt(i-1) == '(') {
					total += count;
				}else { // 막대기 마지막으로 시작점의 막대기 수 +
					total++;
				}
			}
		}
		
		
		return total;
	}
/**
()(((()())(())()))(())

17

(((()(()()))(())()))(()())

24
*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		String line = sc.nextLine();
		
		System.out.println(solve(line));
	}

}
