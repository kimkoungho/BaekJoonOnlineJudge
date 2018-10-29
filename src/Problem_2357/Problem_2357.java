package Problem_2357;
import java.util.Scanner;

class SegTree{
	class Node{
		// 해당 노드의 범위 
		int start;
		int end;
		// 해당 범위의 최소
		long minValue;
		// 해당 범위 최대
		long maxValue;
		
		public Node(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		public String toString() {
			return "[start="+start+", end="+end+", minValue="+minValue+", maxValue="+maxValue+"]";
		}
	}
	
	private final int ROOT = 1;
	private Node[] tree;
	private int size;
	
	public SegTree(long[] input) {	
		//tree level
		int level = (int)Math.ceil(Math.log10(input.length) / Math.log10(2));
		// 2N - 1
		size = (1 << level + 1);
		tree =  new Node[size];
		
//		System.out.println(level);
//		System.out.println(size);
	
		setNode(1, input.length-1, ROOT, input);
	}
	
	// start, end  구간에 대하여 값 세팅
	private Node setNode(int start, int end, int nodeIndex, long[] input) {
		tree[nodeIndex] = new Node(start, end);
		
		if(start == end) { // leaf 노드 인경우
			tree[nodeIndex].minValue = input[start];
			tree[nodeIndex].maxValue = input[start];
		}else { // leaf 가 아닌 경우 
			int m = (start + end) / 2;
			
			// 좌측 노드 추출
			Node leftNode = setNode(start, m, nodeIndex * 2, input);
			// 우측 노드 추출
			Node rightNode = setNode(m+1, end, nodeIndex * 2 +1, input);
			// 최소값 세팅
			tree[nodeIndex].minValue = Math.min(leftNode.minValue, rightNode.minValue);
			// 최대값 세팅 
			tree[nodeIndex].maxValue = Math.max(leftNode.maxValue, rightNode.maxValue);
		}
		
		return tree[nodeIndex];
	}
	
	
	// 검색하기
	private long search(int start, int end, int nodeIndex, boolean isMin) {
		Node node = tree[nodeIndex];
		
		// 노드의 구간이 start, end 에 포함되지 않는 경우
		if(start > node.end || end < node.start) {
			return isMin ? Long.MAX_VALUE : Long.MIN_VALUE;
		}
		
		//노드의 구간이 start, end 에 포함되는 경우
		if(start<= node.start && node.end <= end) {
			return isMin ? node.minValue : node.maxValue;
		}
		
		// 노드의 구간이 start, end 와 겹치는 경우
		long left = search(start, end, nodeIndex * 2, isMin);
		long right = search(start, end, nodeIndex * 2+1, isMin);
		
		return isMin ? Math.min(left, right) : Math.max(left, right);
	}
	
	public long searchMin(int start, int end) {
		return search(start, end, ROOT, true);
	}
	
	public long searchMax(int start, int end) {
		return search(start, end, ROOT, false);
	}
	
	public void show() {
		for(int i=ROOT; i<tree.length; i++) {
			System.out.println(i +" : "+tree[i]);
		}
	}
}

public class Problem_2357 {
	
	
	private static void solve(long[] input, int[][] ranges) {
		SegTree tree = new SegTree(input);
		//tree.show();
		
		for(int i=0; i<ranges.length; i++) {
			long minValue = tree.searchMin(ranges[i][0], ranges[i][1]);
			long maxValue = tree.searchMax(ranges[i][0], ranges[i][1]);
			
			System.out.println(minValue + " " + maxValue);
		}
		
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
		// 1 ~ 100,000
		int N = sc.nextInt();
		int M = sc.nextInt();

		// 1 ~ 1,000,000,000
		long[] arr = new long[N + 1];
		for(int i=1; i<=N; i++) {
			arr[i] = sc.nextLong();
		}
		
		int[][] ranges = new int[M][2];
		for(int j=0; j<M; j++) {
			ranges[j][0] = sc.nextInt();
			ranges[j][1] = sc.nextInt();
		}
		
		
		solve(arr, ranges);
	}

}

