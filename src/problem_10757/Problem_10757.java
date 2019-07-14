package problem_10757;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Problem_10757 {
	
	private static String bigDecimalSum(String a, String b) {
		// b 가 무조건 길이가 길도록 
		if(a.length() > b.length()) {
			return bigDecimalSum(b, a);
		}
		
		StringBuffer sum = new StringBuffer();
		int unit = 0;
		
		int i = a.length()-1;
		int j = b.length()-1;
		while(j>=0) {
			//System.out.println(i+", "+j);
			int aNum = 0, bNum = 0;
			
			if(i>=0) {
				aNum = (int)(a.charAt(i) - 48);
			}
			if(j>=0) {
				bNum = (int)(b.charAt(j) - 48);
			}
			
			int num = aNum + bNum + unit;
			sum.append(num%10);
			unit = num / 10;
			
			j--;
			i--;
		}
		if(unit != 0) {
			sum.append(unit);
		}
		
		
		//System.out.println(sum);
		
		return (sum.reverse()).toString();
	}
	
	private static String solve(String a, String b) {
		BigDecimal num1 = new BigDecimal(a);
		BigDecimal num2 = new BigDecimal(b);
		
		return num1.add(num2).toString();
	}
/**
9223372036854775807 9223372036854775808
-> 18446744073709551615

557291773 774762349
-> 1332054122

557291773 
774762349
*/
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		String line = reader.readLine();
		
		String[] inputs = line.split(" ");
		
		//System.out.println(solve(inputs[0], inputs[1]));
		System.out.println(bigDecimalSum(inputs[0], inputs[1]));
		
		
//		ArrayList<String> tcList = getTestCaseList(3);
//		
//		for(String testcase : tcList) {
//			String[] inputs = testcase.split(" ");
//			
//			System.out.println("start");
//			System.out.println(inputs[0]);
//			System.out.println(inputs[1]);
//			System.out.println("result");
//			System.out.println(solve(inputs[0], inputs[1]));
//			System.out.println(bigDecimalSum(inputs[0], inputs[1]));
//			System.out.println("------------------------------------");
//		}
	} 
	
	private static String getTestCase() {
		//int len = (int)Math.round(Math.random()*9999 + 1);
		int len = (int)Math.round(Math.random()*10 + 1);
		
		StringBuffer sb = new StringBuffer();
		// 최초의 숫자는 0이 아니게
		int firstVal = 0;
		while(firstVal == 0) {
			firstVal = (int)Math.round(Math.random() * 9);
		}
		sb.append(firstVal);
		
		for(int i=1; i<len; i++) {
			sb.append((int)Math.round(Math.random() * 9));
		}
		
		return sb.toString();
	}
	
	private static ArrayList<String> getTestCaseList(int size){
		ArrayList<String> tcList = new ArrayList();
		
		
		for(int i=0; i<size; i++) {
			String a = getTestCase();
			String b = getTestCase();
			
			tcList.add(a+" "+b);
		}
		
		return tcList;
	}
}
