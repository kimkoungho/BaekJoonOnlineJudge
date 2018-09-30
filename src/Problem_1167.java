import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

import java.util.Scanner;


//TODO: bfs 구현 

public class Problem_1167 {

	private static final int maxEdge = 10001;
	
	private static boolean[] visited;
	
	private static int[] dfs(int start, List<Map<Integer, Integer>> tree) {
		
		int[] retNode = new int[] {start, 0};
		
		visited[start] = true;
		Map<Integer, Integer> rowMap = tree.get(start);
		
		for(Integer nv : rowMap.keySet()) {
			if(!visited[nv]) {//방문하지 않은 노드 
				int[] nextNode = dfs(nv, tree);
				
				if(retNode[1] < nextNode[1] + rowMap.get(nv)) {
					retNode[0] = nextNode[0];
					retNode[1] = nextNode[1] + rowMap.get(nv);
				}
				
				//System.out.println(start + " - " + nv + " = "+ (nextNode[1] + rowMap.get(nv)));
			}
		}
		
		return retNode;
	}
	
	private static int solve(List<Map<Integer, Integer>> tree) {
		
		//가장 거리가 먼 임의의 노드 찾기
		Arrays.fill(visited, false);
		int[] startNode = dfs(1, tree);
		
		//System.out.println(startNode[0]);
		//해당 노드에서 가장 먼 노드와 거리
		Arrays.fill(visited, false);
		int[] retNode = dfs(startNode[0], tree);
		
		return retNode[1];
	}
	
	/*
5
1 3 2 -1
2 4 4 -1
3 1 2 4 3 -1
4 2 4 3 3 5 6 -1
5 4 6 -1

11

7
1 2 3 3 4 4 5 -1
2 1 3 5 9 -1
3 1 4 6 3 -1
4 1 5 -1
5 2 9 -1
6 3 3 7 10 -1
7 6 10 -1

17

7
1 2 1 3 1 -1
2 1 1 -1
3 1 1 4 3 5 2 -1
4 3 3 -1
5 3 2 6 3 7 10 -1
6 5 3 -1
7 5 10 -1
15
	 */
	
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		// 2 < v < 100,000
		int vSize = sc.nextInt(); 
		
		List<Map<Integer, Integer>> tree = new ArrayList(Collections.nCopies(vSize+1, new HashMap()));
		visited = new boolean[vSize+1];
		
		for(int i=0; i<vSize; i++) {
			Map<Integer, Integer> rowMap = new HashMap();
			
			int v = -1, nv = -1;
			
			int input = -1;
			while((input=sc.nextInt())!= -1) {
				if(v == -1) {
					v = input;
				}else if(nv == -1) {
					nv = input;
				}else {
					rowMap.put(nv, input);
					nv = -1;
				}
			}
			tree.set(v, rowMap);
		}
		
		System.out.println(solve(tree));
		
	}

}
