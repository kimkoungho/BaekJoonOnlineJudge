package Problem_10819;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Promblem_10819 {
	
	private static ArrayList<Integer> list = new ArrayList();
	private static int max = Integer.MIN_VALUE;
	
	private static int count = 0;
	
	private static int getSum(int[] arr) {
		int sum = 0;
		for(int i=0; i<list.size() - 1; i++) {
			sum += Math.abs(arr[list.get(i)] - arr[list.get(i+1)]);
		}
		return sum;
	}
	
	private static void dfs(int index, int[] arr, boolean[] visit) {
		count++;
		
		visit[index] = true;
		list.add(index);
		for(int i=0; i<arr.length; i++) {
			if(visit[i] == false) {
				dfs(i, arr, visit);
			}
		}
		
		// check
		int visitCount = 0;
		for(boolean v : visit) {
			if(v) {
				visitCount++;
			}
		}
		
		if(visitCount == arr.length) {
			max = Math.max(max, getSum(arr));
//			System.out.println(list);
//			System.out.println(max);
		}
		
		visit[index] = false;
		list.remove(list.size()-1);
	}
	
	private static void solve(int[] arr) {
		
		boolean[] visit = new boolean[arr.length];
		Arrays.fill(visit, false);
		
		for(int i=0; i<arr.length; i++) {
			dfs(i, arr, visit);
		}
		
		
	}
	
	/**
6
20 1 15 8 4 10
	 */

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
//		int n = 6;
//		int[] arr = new int[] {20, 1, 15, 8, 4, 10};
		int n = sc.nextInt();
		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = sc.nextInt();
		}
		
		
		
		solve(arr);
		
		System.out.println(max);
		
		System.out.println(count);
	}

}
