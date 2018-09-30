
import java.util.Scanner;
import java.util.TreeMap;

public class Problem_2312 {
	
	static TreeMap<Integer, Integer> solve(int N) {
		int pivot = 2;
		
		TreeMap<Integer, Integer> map = new TreeMap();
		while(N>1) {
			if(N%pivot == 0) {
				if(map.containsKey(pivot)) {
					map.put(pivot, map.get(pivot)+1);
				}else {
					map.put(pivot, 1);
				}
				
				N /= pivot;
			}else {
				pivot++;
			}
			
		}
		
		return map;
	}
/**
2
6
24
 
2 1
3 1
2 3

*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		
		int tc = sc.nextInt();
		
		for(int i=0; i<tc; i++) {
			int N = sc.nextInt();
			
			TreeMap<Integer, Integer> map = solve(N);
			for(Integer key : map.keySet()) {
				System.out.println(key + " " +map.get(key));
			}
		}
	}

}
