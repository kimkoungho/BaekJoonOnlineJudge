import java.util.LinkedList;
import java.util.Scanner;

public class Problem_2164 {
	
	static int solve(int N) {
		LinkedList<Integer> linkedList = new LinkedList();
		for(int i=1; i<=N; i++) {
			linkedList.add(i);
		}
		
		boolean isSw = true;
		int ret = linkedList.getFirst();
		linkedList.removeFirst();
		
		while(!linkedList.isEmpty()) {
			if(isSw) {
				linkedList.addLast(linkedList.getFirst());
			}else {
				ret = linkedList.getFirst();
			}
			
			linkedList.removeFirst();
			//System.out.println(linkedList);
			isSw = !isSw;
		}
		
		
		return ret;
	}
/**
6

4
*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		System.out.println(solve(N));
	}

}
