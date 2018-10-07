import java.util.Scanner;
import java.util.Stack;

/*  히스토그램에서 가장 큰 막대를 출력
 *  
*/
public class Problem_1725 {
	
	// 스택을 이용한 풀이  
	private static int stackSolve(int[] arr) {
		// 히스토그램 인덱스 저장할 스택 
		Stack<Integer> stack = new Stack();
		
		int ret = -1;
		
		//전체 히스토그램에서 계산할 end 인덱스 
		for(int e=0; e<arr.length; e++) {
			// 너비를 계산할 조건
			while(!stack.isEmpty()) {
				// 현재 인덱스 까지의 최대 값 vs 현재 인덱스 값
				// 현재 값이 stack 의 최대값이면 너비를 계산하지 않음 
				if(arr[e] >= arr[stack.peek()]) {
					break;
				}
				// stack 에서 최대 값이 존재하는 인덱스 
				int s = stack.pop();
				int h = arr[s];
				int w;
				  
				if(stack.isEmpty()) {
					// 스택이 비었다면 [0 ~ e] 구간에서 arr[s] 값이 최소
					w = e; 
				}else {
					// 스택이 비어있지 않다면 
					// e - 1 : 최대값 인덱스 (종료 인덱스)
					// stack.peek() : 현재 값 다음으로 큰 값 인덱스 (시작 인덱스) 
					w = (e - 1 - stack.peek());
				}
				
				ret = Math.max(ret, w * h);
			}
			
			stack.push(e);
		}
		
		// 스택에 남아있는 값이 있는경우 
		while(!stack.isEmpty()) {
			// stack 에서 최대 값이 존재하는 인덱스 
			int s = stack.pop();
			int h = arr[s];
			int w;
			  
			if(stack.isEmpty()) {
				// 스택이 비었다면 [0 ~ n] 구간에서 arr[s] 값이 최소
				w = arr.length; 
			}else {
				// 스택이 비어있지 않다면 
				// e - 1 : 최대값 인덱스 (종료 인덱스)
				// stack.peek() = s - 1 : 현재 인덱스 - 1 (시작 인덱스) 
				w = (arr.length - 1 - stack.peek());
			}
			
			ret = Math.max(ret, w * h);
		}
		
		return ret;
	}
	
	
	/**
7
2
1
4
5
1
3
3

8
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 1 ~ 100,000
//		int N = sc.nextInt(); 
//		int []arr = new int[N];
//		for(int i=0; i<N; i++) {
//			arr[i] = sc.nextInt();
//		}
		
//		int N = 7;
//		int[] arr = new int[] {2, 1, 4, 5, 1, 3, 3};
		int[] arr = new int[] {1, 4, 3, 4, 1, 3, 3};
		
		System.out.println(stackSolve(arr));
	}
	
	
	// 세그먼트 트리 이용 
	private static int segmentTreeSolve(int[] arr) {
		
		return -1;
	}
	

	
}
