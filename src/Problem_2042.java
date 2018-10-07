import java.util.Scanner;

/** 세그먼트 트리를 이용한 구간합 구하기 */
public class Problem_2042 {

	private static final int CHANGE = 1;
	private static final int GET_SUM = 2;
	
	
	/**
5 2 2
1
2
3
4
5
1 3 6
2 2 5
1 5 2
2 3 5

17
12
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
		
		SegmentTree segmentTree = new SegmentTree(input);
		
		for(int j=0; j<M+K; j++) {
			
			int cmd = sc.nextInt();
			int b = sc.nextInt();
			long c = sc.nextLong();
			
			if(CHANGE == cmd) {
				segmentTree.update(b, c);
			}else if(GET_SUM == cmd){
				System.out.println(segmentTree.getSum(b, (int)c, segmentTree.ROOT_INDEX));
			}
		}
		
	}

}

class Node{
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
}

/** 세그먼트 트리 */
class SegmentTree{
	public final int ROOT_INDEX = 1;
	//원본 배열 
	private long[] input;
	//해당 트리의 값을 저장할 배열
	private Node[] tree;
	// 트리의 높이
	private int height;

	public SegmentTree(long[] input) {
		this.input = input;
		
		height = getHeight();
		// 2 ^ (height+1)
		int length = (1 << (height + 1));
		tree = new Node[length];
		
		// 계산 편의를 위해 1번부터 저장 
		setUp(0, input.length-1, ROOT_INDEX);
		
	}
	
	private int getHeight() {
		return (int) Math.ceil(Math.log10(input.length) / Math.log10(2));
	}
	
	/**  트리 세팅
	 * @param start: 노드의 시작 구간  
	 * @param end: 노드의 종료 구간 
	 * @param nodeIndex: 세그먼트 트리에서 현재 노드의 인덱스 
	 * @return nodeValue: 노드의 값 
	 */
	private long setUp(int start, int end, int nodeIndex) {
		// 해당 노드의 구간정보를 설정하여 생성 
		tree[nodeIndex] = new Node(start, end);
		
		if(start == end) {// leaf  노드 인 경우 값 복사  
			tree[nodeIndex].setValue(input[start]);
		}else {
			int m = (start + end) / 2;
			// 자노드 저장 
			long left = setUp(start, m, nodeIndex * 2);
			long right = setUp(m+1, end, nodeIndex * 2 + 1);
			tree[nodeIndex].setValue(left + right);
		}
		
		return tree[nodeIndex].getValue();
	}
	
	// 트리에서 부분합 찾기
	public long getSum(int start, int end, int nodeIndex) {
		Node node = tree[nodeIndex];
		
		// 찾으려는 범위가 현재 노드의 구간에 포함되지 않은 경우
		// start > 노드의 종료구간  or end < 노드의 시작구간  
		if(start > node.getEnd() || end < node.getStart()) {
			return 0;
		}
		
		// 찾으려는 범위가 노드의 범위를 포함하는 경우 
		// ex) start, end = 1, 5 / node.start, node.end = 3, 4
		if(start <= node.getStart() && end >= node.getEnd()) {
			return node.getValue();
		}
		
		//+ 찾으려는 범위가 노드의 범위에 포함되는 경우 
		// ex) start, end = 3, 4 / node.start, node.end = 0, 5
		//+ 두 범위가 겹쳐지는 경우
		// ex) start, end = 4, 7 // node.start, node.end = 5, 9
		// 왼쪽 자노드 탐색 + 오른쪽 자노드 탐색 
		long leftSum = getSum(start, end, nodeIndex * 2);
		long rightSum = getSum(start, end, nodeIndex * 2 + 1);
		
		return leftSum + rightSum;
	}
	
	// 업데이트 하기
	public void update(int index, long newValue) {
		long diff = newValue - input[index];
		input[index] = newValue;
	
		treeUpdate(index, ROOT_INDEX, diff);
	}
	
	private void treeUpdate(int index, int nodeIndex, long diff) {
		Node node = tree[nodeIndex];
		//  해당 노드의 범위에 찾으려는 인덱스가 포함되지 않는 경우 
		if(node.getStart() > index || node.getEnd() <index) {
			return;
		}
		
		node.setValue(node.getValue() + diff);
		// 업데이트 할 노드들이 더 있는 경우 
		if(node.getStart() != node.getEnd()) {
			// 왼쪽 노드 업데이트 
			treeUpdate(index, nodeIndex * 2, diff);
			// 오른쪽 노드 업데이트
			treeUpdate(index, nodeIndex * 2 + 1, diff);
		}
	}
	
	
}
