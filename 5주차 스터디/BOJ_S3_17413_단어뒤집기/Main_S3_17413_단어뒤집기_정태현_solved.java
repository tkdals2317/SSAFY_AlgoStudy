package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_S3_17413_단어뒤집기_solved {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> s = new Stack<>();
		
		String str = br.readLine();
		boolean tag = false;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if(c == '<') {
				while(!s.isEmpty()) System.out.print(s.pop());
				tag = true;
				System.out.print("<");
				
			}
			else if(c == '>') {
				tag = false;
				System.out.print(">");
			}
			else if(tag) {
				System.out.print(c);
			}
			else if(!tag) {
				if(c == ' ') {
					while(!s.isEmpty()) System.out.print(s.pop());
					System.out.print(c);
				}else {
					s.push(c);
				}
			}
		}
		
		while(!s.isEmpty()) {
			System.out.print(s.pop());
		}
	}

}






