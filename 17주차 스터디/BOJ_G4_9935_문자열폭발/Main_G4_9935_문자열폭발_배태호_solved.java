package Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Soulition_BOJ_9935_문자열폭발 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		String input = br.readLine();
		String boom = br.readLine();

		Stack<Character> st = new Stack<>();

		for (int i = 0; i < input.length(); i++) {
			st.push(input.charAt(i));

			if (st.size() >= boom.length()) {
				boolean flag = true;
				
				for (int j = 0; j < boom.length(); j++) 
					if (st.get(st.size() - boom.length() + j) != boom.charAt(j)) {
						flag = false;
						break;                                            
					}
				
				if (flag)
					for (int j = 0; j < boom.length(); j++)
						st.pop();
			}
		}

		for (char ch : st)
			ans.append(ch);

		System.out.println((st.size() == 0) ? "FRULA" : ans);
	}
}