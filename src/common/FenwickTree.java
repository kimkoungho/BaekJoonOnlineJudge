package common;

public class FenwickTree {
	/** 팬윅 트리를 저장할 1차원 배열 */
	private long[] tree;
	
	public FenwickTree(int size) {
		// 제약 인덱스 1부터 시작 
		tree = new long[size];
	}
	
	/** 팬윅트리 출력 */
	public void show() {
		for(int i=1; i<tree.length; i++) {
			System.out.print(tree[i] + "\t");
		}
		System.out.println();
	}
	
	/** 트리에서 index 포함한 구간의 값을 모두 업데이트   
	 * @param index : 원본 배열의 인덱스
	 * @param diff : 원래 값과의 차이 
	 * */
	public void update(int index, long diff) {
		while(index < tree.length) {
			tree[index] += diff;
			// index 에서 가장 오른쪽 1의 위치에 1을 더함  
			index += (index & -index);
		}
	}
	
	/** 1 ~ index 까지의 합을 구하는 메소드
	 * @param index : 합을 구할 마지막 인덱스 
	 * @return sum : 1 ~ index 까지의 합  
	 */
	private long getSum(int index) {
		long sum = 0;
		while(index > 0) {
			sum += tree[index];
			// index 의 2진수화 후에 가장 오른쪽 1의 위치를 찾아서
			// index 에서 가장 오른쪽 1의 위치를 지운다 
			index -= (index & -index);
		}
		return sum;
	}
	
	/** [start, end] 구간의 합을 구하는 메소드 
	 * @param start : 구간의 시작 인덱스 
	 * @param end : 구간의 종료 인덱스
	 * @return sum : [start, end] 구간의 합  
	*/
	public long getSum(int start, int end) {
		// 1 ~ end
		long totalSum = getSum(end);
		// 1 ~ (start-1)
		long subSum = getSum(start-1);
//		System.out.println(start + ", " +end);
//		show();
//		System.out.println(totalSum + " - " + subSum + " = " + (totalSum - subSum));
		
		return totalSum - subSum;
	}
}
