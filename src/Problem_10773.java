import java.util.Scanner;
import java.util.Stack;

public class Problem_10773 {

/**
4
3
0
4
0

0

10
1
3
5
4
0
0
7
0
0
6

7
*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		Stack<Integer> stack = new Stack();
		
		int k = sc.nextInt();
		for(int i=0; i<k; i++) {
			int num = sc.nextInt();
		
			if(!stack.isEmpty() && num == 0) {
				stack.pop();
			}else {
				stack.push(num);
			}
		}
		
		long sum = 0;
		for(int num : stack) {
			sum += num;
		}
		
		System.out.println(sum);
	}

}
