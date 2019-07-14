package problem_2042;
import java.util.ArrayList;
import java.util.Scanner;


public class Problem_2042 {

	private static final int CHANGE = 1;
	private static final int GET_SUM = 2;
	
	static class Command{
		int cmd;
		int b;
		long c;
		
		public Command(int cmd, int b, long c) {
			this.cmd = cmd;
			this.b = b;
			this.c = c;
		}
	}
	
	/** 세그먼트 트리를 이용한 구간합 구하기 */
	private static void solveSegmentTree(long[] input, ArrayList<Command> commandList) {
		SegmentTree segmentTree = new SegmentTree(input);
		
		for(Command command : commandList) {
			if(CHANGE == command.cmd) {
				int index = command.b;
				long newValue = command.c;
				
				segmentTree.update(index, newValue);
			}else if(GET_SUM == command.cmd){
				int start = command.b;
				int end = (int)command.c;
				
				System.out.println(segmentTree.getSum(start, end, segmentTree.ROOT_INDEX));
			}
		}
	}
	
	/** 펜윅 트리를 이용한 구간합 구하기 */
	private static void solveFenwickTree(long[] input, ArrayList<Command> commandList) {
		FenwickTree fenwickTree = new FenwickTree(input.length);
		 for(int i=1; i<input.length; i++) {
			 fenwickTree.update(i, input[i]);
		 }
		 
		 for(Command command : commandList) {
			 
				if(CHANGE == command.cmd) {
					int index = command.b;
					long newValue = command.c;
					long diff = newValue - input[index];
					input[index] = newValue;
					
					fenwickTree.update(index, diff);
				}else if(GET_SUM == command.cmd){
					int start = command.b;
					int end = (int)command.c;
					
					System.out.println(fenwickTree.getSum(start, end));
				}
			}
	}
	
	/**
5 2 2
1 2 3 4 5
1 3 6
2 2 5
1 5 2
2 3 5

17
12

5 1 1
1 2 3 4 5
1 3 6
2 2 5

17

8 1 1
1 2 3 4 5 6 7 8
1 5 10
2 3 8

38
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 배열의 길이 : 1 ~ 1,000,000
		int N = sc.nextInt();
		// 값을 변경할 횟수 : 1 ~ 10,000
		int M = sc.nextInt();
		// 구간 합을 구할 횟수 : 1 ~ 10,000
		int K = sc.nextInt();
		
		long[] input = new long[N+1];
		for(int i=1; i<=N; i++) {
			input[i] = sc.nextLong();
		}
		
		ArrayList<Command> commandList = new ArrayList();
		for(int j=0; j<M+K; j++) {
			int cmd = sc.nextInt();
			int b = sc.nextInt();
			long c = sc.nextLong();
			
			commandList.add(new Command(cmd, b, c));
		}
		
		//solveSegmentTree(input, commandList);
		solveFenwickTree(input, commandList);
	}

}