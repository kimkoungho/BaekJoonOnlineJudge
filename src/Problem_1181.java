import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Problem_1181 {
	//단어 정렬 
	
	/**
13
but
i
wont
hesitate
no
more
no
more
it
cannot
wait
im
yours
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(reader.readLine());
	
		Set<String> wordSet = new HashSet();
		for(int i=0; i<N; i++) {
			wordSet.add(reader.readLine());
		}
		
		List<String> wordList = new ArrayList(wordSet);
		
		Collections.sort(wordList, new Comparator<String>() {
			@Override
			public int compare(String base, String target) {
				
				if(base.length() == target.length()) {
					return base.compareTo(target);
				}
				 
				return base.length() - target.length();
			}
		});
		
		for(String word : wordList) {
			System.out.println(word);
		}
		
	}

}
