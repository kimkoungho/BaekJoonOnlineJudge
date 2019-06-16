package util;

import java.util.Arrays;

public class Permutation {
	
	private static int count = 0;
	// 1. element select permutation
	public static void selectPermutation(Object[] inputs) {
		// 방문 배열 
		boolean[] visited = new boolean[inputs.length];
		Arrays.fill(visited, false);
		// 출력 배열 
		Object[] outputs = new Object[inputs.length];
		selectPermutation(inputs, visited, outputs, 0);
	}
	
	private static void selectPermutation(Object[] inputs, boolean[] visited, Object[] outputs, int outputIndex) {
		// all element select 
		if(inputs.length == outputIndex) {
			System.out.println(Arrays.toString(outputs));
			return;
		}
		
		for(int i=0; i<inputs.length; i++) {
			// 이미 선택한 요소 제외 
			if(visited[i]) {
				continue;
			}
			
			// 현재 요소 선택 
			visited[i] = true;
			// 출력 index 에 값 설정 
			outputs[outputIndex] = inputs[i];
			// 재귀 호출 
			selectPermutation(inputs, visited, outputs, outputIndex+1);
			// 현재 요소 선택 취소 
			// 다른 위치에 값을 출력하기 위해 
			visited[i] = false;
		}
		
	}
	
	private static void swap(Object[] array, int baseIndex, int otherIndex) {
		Object base = array[baseIndex];
		array[baseIndex] = array[otherIndex];
		array[otherIndex] = base;
	}
	
	public static void swapPermutation(Object[] array, int n) {
		if(n==1) {
			System.out.println(Arrays.toString(array));
		}else {
			for(int i=0; i<n; i++) {
				swap(array, i, n-1); // 가장 마지막 원소 설정 
				swapPermutation(array, n-1); // n-1 개의 부분 문제 
				swap(array, i, n-1); // 복원 
			}
		}
	}
	
	public static void heapsPermutation(Object[] array, int n) {
		if(n == 1) {
			System.out.println(Arrays.toString(array));
		}else {
			for(int i=0; i<n-1; i++) {
				heapsPermutation(array, n-1);
				
				if(n % 2 == 0) {
					swap(array, i, n-1);
				}else {
					swap(array, 0, n-1);
				}
			}
			heapsPermutation(array, n-1);
		}
	}
	

	public static void main(String[] args) {
		Character[] inputs = new Character[] {'A', 'B', 'C', 'D'};
		
		selectPermutation(inputs);
//		swapPermutation(inputs, inputs.length);
//		System.out.println(count);
	}

}
