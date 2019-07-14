package problem_2580;

import java.io.IOException;
import java.util.Scanner;


public class Problem_2580 {
		
	
	/**
0 3 5 4 6 9 2 7 8
7 8 2 1 0 5 6 0 9
0 6 0 2 7 8 1 3 5
3 2 1 0 4 6 8 9 7
8 0 4 9 1 3 5 0 6
5 9 6 8 2 0 4 1 3
9 1 7 6 5 2 0 8 0
6 0 3 7 0 1 9 5 2
2 5 8 3 9 4 7 6 0

4 0 5 0 0 0 8 0 0
3 9 0 0 0 0 5 0 0
1 0 0 0 0 0 0 0 2
0 0 0 0 0 4 0 0 0
6 8 0 0 0 0 0 0 1
2 0 0 0 9 1 0 0 0
0 0 0 4 5 0 0 9 0
0 0 2 0 0 0 3 0 0
9 1 0 0 8 0 0 0 4

---
1 3 5 4 6 9 2 7 8
7 8 2 1 3 5 6 4 9
4 6 9 2 7 8 1 3 5
3 2 1 5 4 6 8 9 7
8 7 4 9 1 3 5 2 6
5 9 6 8 2 7 4 1 3
9 1 7 6 5 2 3 8 4
6 4 3 7 8 1 9 5 2
2 5 8 3 9 4 7 6 1

4 2 5 9 1 7 8 6 3 
3 9 8 2 4 6 5 1 7 
1 6 7 5 3 8 9 4 2 
7 5 1 8 2 4 6 3 9 
6 8 9 3 7 5 4 2 1 
2 3 4 6 9 1 7 8 5 
8 7 3 4 5 2 1 9 6 
5 4 2 1 6 9 3 7 8 
9 1 6 7 8 3 2 5 4
	 */
	
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		Sudoku sudoku = new Sudoku();
		
		String[][] values = sudoku.getValues();
		
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				int value = sc.nextInt();
				
				if(value >= 1 && value <= 9) {
					sudoku.setValue(i, j, Integer.toString(value), values);
				}
			}
		}
		
		values = sudoku.search(values);
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				System.out.print(values[i][j]+" ");
			}
			System.out.println();
		}
	}
}
