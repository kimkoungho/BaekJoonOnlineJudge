import java.util.Scanner;

public class Problem_10868 {
	
	static class Node{
		private int start;
		private int end;
		private long value;
		
		public Node(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		public void setValue(long value) {
			this.value = value;
		}
		
		public int getStart() {
			return start;
		}
		public int getEnd() {
			return end;
		}
		public long getValue() {
			return value;
		}
		
		@Override
		public String toString() {
			return "[ start=" +start+", end="+end+", value="+value+"]"; 
		}
	}

	// 입력값 
	private static long[] arr;
	
	// 세그먼트 트리
	private static Node[] tree;

	// 세그먼트 트리 생성하기 
	private static void createTree() {
		// height : log2(n)
		int height = (int)Math.ceil(Math.log10(arr.length) / Math.log10(2));
		// 2^(height+1)
		int length = (1 << (height + 1));
		tree = new Node[length+1];
		
		setNode(1, 0, arr.length-1);
	}
	private static Node setNode(int index, int start, int end) {
		Node node = new Node(start, end);
		if(start == end) {
			node.setValue(arr[start]);
		}else {
			int m = (start + end) / 2;
			long leftValue = setNode(index * 2, start, m).getValue();
			long rightValue = setNode(index * 2 + 1, m+1, end).getValue();
			node.setValue(Math.min(leftValue, rightValue));
		}
		
		return tree[index] = node;
	}
	
	// 세그먼트 트리 검색하기 
	private static long search(int index, int start, int end) {
		Node node = tree[index];
		
		//System.out.println(node + " " + start + " " + end);
		// 현재 노드의 범위가 start, end 랑 겹치지 않는 경우
		if(node.getStart() > end || node.getEnd() < start) {
			return Long.MAX_VALUE;
		}
		
		// 현재 노드의 범위가 start, end 에 포함되는 경우 
		if(node.getStart() >= start && node.getEnd() <= end) {
			return node.getValue();
		}
		
		// 현재 노드의 범위가 start, end 랑 겹침 or start, end 가 노드에 범위에 포함 
		long leftValue = search(index * 2, start, end);
		long rightValue = search(index * 2 + 1, start, end);
		
		return Math.min(leftValue, rightValue);
	}
	/**
10 4
75
30
100
38
50
51
52
20
81
5
1 10
3 5
6 9
8 10
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 1 ~ 100,000
		int N = sc.nextInt();
		int M = sc.nextInt();

		// 1 ~ 1,000,000,000
		arr = new long[N];
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextLong();
		}
		
		createTree();
		
		for(int j=0; j<M; j++) {
			// 구간에서의 최소 값 찾기 
			int start = sc.nextInt();
			int end = sc.nextInt();
			
			System.out.println(search(1, start-1, end-1));
		}

	}
}




