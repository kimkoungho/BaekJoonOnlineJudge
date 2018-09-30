import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Problem_ {

	/*
	 * 8 12
	 * 5 3 4 2 1 8 6 7
	 * encoded?
	 */
	// 1 2 3 4 5 6 7 8
	
	// 5 3 4 2 1 8 6 7
	// 1 4 2 3 5 7 8 6
	// 5 2 3 4 1 6 7 8
	// 1 3 4 2 5 8 6 7
	// 5 4 2 3 1 7 8 6
	// 1 2 3 4 5 6 7 8
	
	
	
	public static String solve(int m, int []pattern, String line) {
		
		
//		for(int i=0; i<m; i++) {
//			StringBuffer sb = new StringBuffer(line.length());
//			for(int j=0; j<line.length(); j++) {
//				sb.append(line.charAt(pattern[j]));
//			}
//			System.out.println(sb.toString());
//			line = sb.toString();
//		}
		
		return "";
	}
	
	
	public static void main(String[] args) {
		
		// sub array , sub pattern -> find cycle ..
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			String line = null;
			while((line=br.readLine()) != null) {
				int n = Integer.parseInt(line.split(" ")[0]);
				int m = Integer.parseInt(line.split(" ")[1]);
				
				if(n==0 && m==0) break;
				
				line = br.readLine();
				String []items = line.split(" ");
				int []pattern = new int[n];
				for(int i=0; i<n; i++) 
					pattern[i] = (Integer.parseInt(items[i]) - 1);
				
				line = br.readLine();
				System.out.println(solve(m, pattern, line));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
