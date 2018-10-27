package common;

public class SegmentTree {

	class Node{
		/** 노드의 구간 시작 정보 */
		private int start;
		/** 노드의 구간 종료 정보 */
		private int end;
		/** 노드에 저장할 부분합 */
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
	
	/** 트리에서 부분합 찾기
	 * @param start: 찾으려는 시작 구간   
	 * @param end: 찾으려는 종료 구간  
	 * @param nodeIndex: 세그먼트 트리에서 현재 노드의 인덱스 
	 * @return nodeValue: 노드의 값 
	 */
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
	
	/** 외부에서 호출할 업데이트 메소드 
	 * @param index : 값을 변경할 입력 배열의 인덱스
	 * @param newValue : 변경할 새로운 값 
	 */
	public void update(int index, long newValue) {
		long diff = newValue - input[index];
		input[index] = newValue;
	
		treeUpdate(index, ROOT_INDEX, diff);
	}
	
	/** 트리를 순회하면서 업데이트 수행 
	 *  @param index : 값을 변경할 입력 배열의 인덱스
	 *  @param nodeIndex : 현재 노드의 인덱스 
	 *  @param diff : 원래 값과 새로운 값의 차이 (newValue - input[index]) 
	 */
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
