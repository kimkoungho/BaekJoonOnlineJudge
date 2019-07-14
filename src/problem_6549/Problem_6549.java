package problem_6549;
import java.util.Scanner;

public class Problem_6549 {
	

/**
7 2 1 4 5 1 3 3
4 1000 1000 1000 1000
0

8
4000

7 3 3 2 1 4 5 1
7 3 3 2 1 4 2 1
9 2 4 3 4 2 1 2 5 2 

8
7
10


*/
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n; //100,000 
		while((n=sc.nextInt())!=0) {
			
			long[] hist = new long[n];
			for(int i=0; i<n; i++) {
				// 1,000,000,000
				hist[i] = sc.nextLong();
			}
			
			
		}

	}

}
