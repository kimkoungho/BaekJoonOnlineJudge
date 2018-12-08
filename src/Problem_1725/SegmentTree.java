package Problem_1725;

// 히스토그램에서 구간의 최소값 을 저장  
class SegmentTree {

	class Node{
		// 노드의 시작인덱스 
		int start;
		// 노드의 종료인덱스 
		int end;
		// start ~ end 에서 최소 값이 있는 인덱스 
		int minIndex;
		
		public Node(int start, int end) {
			this.start = start;
			this.end = end;
		}
		public void setMinIndex(int index) {
			this.minIndex = index;
		}
		public int getStart() {
			return this.start;
		}
		public int getEnd() {
			return this.end;
		}
		public int getMinIndex() {
			return this.minIndex;
		}
		
		@Override
		public String toString() {
			return "[" + start + ", " + end+"] :: " + minIndex;
		}
	}
	
	// 입력 배열
	private int[] input;
	
	// 트리의 루트 인덱스
	private static int ROOT_INDEX = 1;
	// 입력 값을 2진 트리 
	private Node[] tree;
	
	public SegmentTree(int[] input) {
		this.input = input;
		
		//높이 구하기 : h = log2(length)
		int h = (int) Math.ceil(Math.log10(input.length) / Math.log10(2));
		// 트리의 총 노드 수 : 2 ^ (height+1)
		int length = (1 << (h + 1));
		
		tree = new Node[length];
		setUpNode(0, input.length-1, ROOT_INDEX);
	}
	
	/** 입력 값으로 트리 초기화
	 *  left ~ right 구간의 최소 값 인덱스를 tree[nodeIndex] 에 저장 
	 *  @param left : 입력배열의 시작 인덱스 
	 *  @param right: 입력배열의 종료 인덱스
	 *  @param nodeIndex: 노드인덱스 
	 *  @return minIndex : left ~ right 구간의 최소값이 존재하는 인덱스  
	 * */
	private int setUpNode(int left, int right, int nodeIndex) {
		// 노드 생성 
		tree[nodeIndex] = new Node(left, right);
		
		if(left == right) { // leaf 노드 인경우 (left = right)
			tree[nodeIndex].setMinIndex(left);
		}else {
			int m = (left + right) / 2;
			// 왼쪽 탐색 : 왼쪽 자노드  
			int leftIndex = setUpNode(left, m, nodeIndex * 2);
			// 오른쪽 탐색 : 오른쪽 자노드
			int rightIndex = setUpNode(m+1, right, nodeIndex * 2 + 1);
			
			// 왼 vs 오른 값 비교후 저장  
			int minIndex = input[leftIndex] < input[rightIndex] ? leftIndex : rightIndex;
			tree[nodeIndex].setMinIndex(minIndex);
		}
		
		return tree[nodeIndex].getMinIndex();
	}
	
	/** 최소값 인덱스 탐색 */
	public int getMinIndex(int start, int end, int nodeIndex) {
		Node node = tree[nodeIndex];
		
		// 노드의 구간 왼쪽 or 오른쪽에 [start, end] 가 존재
		if(end < node.getStart() || node.getEnd() < start) {
			return -1;
		}
	
		// start, end 가 node 구간을 포함하는 경우 : start, node.start, node.end, end
		if(start <= node.getStart() && node.getEnd() <= end) {
			// 어차피 호출한쪽에서 최소 값 비교 수행 
			return node.getMinIndex();
		}
		
		// 노드 구간 내부에 존재 : node.start, start, end, node.end
		// 노드 구간과 겹침 : node.start , start, node.end, end
		int leftIndex = getMinIndex(start, end, nodeIndex * 2);
		int rightIndex = getMinIndex(start, end, nodeIndex * 2 +1);
		
		
		if(leftIndex == -1) {
			return rightIndex;
		}else if(rightIndex == -1) {
			return leftIndex;
		}else {
			return input[leftIndex] < input[rightIndex] ? leftIndex : rightIndex;
		}
	}
	
	/** 구간의 최대 히스토그림 너비 */
	public int getMaxArea(int left, int right) {
		int minIndex = getMinIndex(left, right, ROOT_INDEX);
		
		// left ~ right 최소 기준 너비 
		int area = (right - left + 1) * input[minIndex];
		
		if(minIndex > left) {
			// 최소 값 인덱스 기준 좌측 탐색
			int leftArea = getMaxArea(left, minIndex-1);
			area = Math.max(area, leftArea);
		}
		
		if(minIndex < right) {
			// 최소 값 인덱스 기준 우측 탐색 
			int rightArea = getMaxArea(minIndex+1, right);			
			area = Math.max(area, rightArea);
		}		
		
		return area;
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Node node : tree) {
			sb.append(node+"\n");
		}
		return sb.toString();
	}
	
	
//	// 세그 먼트 트리 테스트 
//	public static void main(String[] args) {
//		int[] arr = new int[] {2, 1, 4, 5, 1, 3, 3};
//		SegmentTree tree = new SegmentTree(arr);
//		System.out.println(tree.toString());
//		
//		// 전체에서 제일 적은 index 탐색 : 1 || 4
//		int rootMinIndex = tree.getMinIndex(0, arr.length-1, ROOT_INDEX);
//		System.out.println(rootMinIndex);
//		
//		// 0 ~ 3 구간 : 1
//		System.out.println(tree.getMinIndex(0, 3, ROOT_INDEX));
//	}
}

