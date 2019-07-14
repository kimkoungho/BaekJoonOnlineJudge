package problem_11650;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Problem_11650 {

/**
5
3 4
1 1
1 -1
2 2
3 3

1 -1
1 1
2 2
3 3
3 4
*/
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		int tc = sc.nextInt();
		
		ArrayList<Point> pointList = new ArrayList();
		
		for(int i=0; i<tc; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			pointList.add(new Point(x, y));
		}
		
		pointList.sort(new Comparator<Point>() {

			@Override
			public int compare(Point p1, Point p2) {
				if(p1.x == p2.x) {
					return p1.y - p2.y;
				}else {
					return p1.x - p2.x;
				}
			}
			
		});
		
		for(Point point : pointList) {
			System.out.println(point.x+" "+point.y);
		}
	}

}
