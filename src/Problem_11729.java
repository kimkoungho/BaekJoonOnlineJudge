import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




public class Problem_11729 {
	
	static List<Point> hist = new ArrayList();
	
	static int hanoi(int n, int from, int by, int to) {
		int ret = 1;
		if(n == 1) {
			hist.add(new Point(from, to));
		}else {
			ret += hanoi(n-1, from, to, by); // from -> by 로 n-1 개 이동
			hist.add(new Point(from, to));
			ret += hanoi(n-1, by, from, to); // by -> to 로 n-1 개 이동 
		}
		
		return ret;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 하노이
				
		//원판 수 
		int N = sc.nextInt();
		
		int ret = hanoi(N, 1, 2, 3);
		System.out.println(ret);
		for(Point path : hist) {
			System.out.println(path.x + " " + path.y);
		}
		
	}

}
