import java.util.Scanner;

public class Problem_2357 {
	
	class Range{
		int start;
		int end;
		
		public Range(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		public int getStart() {
			return start;
		}
		public int getEnd() {
			return end;
		}
	}
	
	// fenwick tree 를 이용한 
	private static void solveFenwickTree(long[] input) {
		
	}
	
	/**
10 4
75 30 100 38 50 51 52 20 81 5
1 10
3 5
6 9
8 10

5 100
38 100
20 81
5 81
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
//		// 1 ~ 100,000
//		int N = sc.nextInt();
//		int M = sc.nextInt();
//
//		// 1 ~ 1,000,000,000
//		long[] arr = new long[N];
//		for(int i=0; i<N; i++) {
//			arr[i] = sc.nextLong();
//		}
//		
//		for(int j=0; j<M; j++) {
//			
//		}
		
		
		//
		int N = 10, M = 4;
		long[] arr = new long[] {
				75, 30, 100, 38, 50, 51, 52, 20, 81, 5
		};
		
		
		
	}

}
interface TreeFunction{
	
	void call(long[] tree, int index, long value);
}

class Fenwick{
	
	private long[] tree;
	
	private void update(int index, long value) {
		while(index < tree.length) {
			//
			tree[index] = Math.min(tree[index], value);
			tree[index] = Math.max(tree[index], value);
			tree[index] += value;
			//
			
			index += (index & -index);
		}
	}
	
	public void init(long[] input) {
		tree = new long[input.length];
		// update
	}
	
	private void search(int index) {
		long value = 0;
		while(index > 0) {
			
		}
	}
	
	public void search(int start, int end) {
		
	}
	
}
