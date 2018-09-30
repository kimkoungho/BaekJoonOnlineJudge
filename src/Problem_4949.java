import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Problem_4949 {
	
	static List<Character> opener = Arrays.asList('(', '[');
	static List<Character> closer = Arrays.asList(')', ']');
	
	static boolean solve(String line) {
		
		Stack<Character> charStack = new Stack();
		
		for(char c : line.toCharArray()) {
			if(opener.contains(c)) {
				charStack.push(c);
			}else if(closer.contains(c)) {
				if(charStack.isEmpty() || closer.indexOf(c) != opener.indexOf(charStack.peek())) {
					return false;
				}else {
					charStack.pop();
				}
			}
		}
		
		
		return charStack.isEmpty();
	}
/**
So when I die (the [first] I will see in (heaven) is a score list).
[ first in ] ( first out ).
Half Moon tonight (At least it is better than no Moon at all].
A rope may form )( a trail in a maze.
Help( I[m being held prisoner in a fortune cookie factory)].
([ (([( [ ] ) ( ) (( ))] )) ]).
 .
.

yes
yes
no
no
no
yes
yes
*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext()) {
			String line = sc.nextLine();
			
			if(".".equals(line)) {
				break;
			}
			
			if(solve(line)) {
				System.out.println("yes");
			}else {
				System.out.println("no");
			}
		}
	}

}
