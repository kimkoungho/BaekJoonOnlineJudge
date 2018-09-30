import java.util.*;

public class Problem_9012 {
	/*
6
(())())
(((()())()
(()())((()))
((()()(()))(((())))()
()()()()(()()())()
(()((())()(
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int t = Integer.parseInt(sc.nextLine());

		for (int i = 0; i < t; i++) {
			String line = sc.nextLine();

			Stack<Character> st = new Stack<Character>();
			boolean flag = true;
			for (int j = 0; j < line.length(); j++) {
				char c = line.charAt(j);

				if (c == '(') {
					st.push(c);
				} else {
//					System.out.println(st.size());
					if (st.size() == 0) {
						flag = false;
						break;
					} else {
						st.pop();
					}
				}
			}
//			System.out.println(st.size());
			if (st.size() != 0)
				flag = false;

			if (flag)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}
}