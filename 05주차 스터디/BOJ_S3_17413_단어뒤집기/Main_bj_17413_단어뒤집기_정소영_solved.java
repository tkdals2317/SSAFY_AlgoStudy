import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// https://www.acmicpc.net/problem/17413
// 문자열 정렬하기
public class Main_bj_17413_단어뒤집기_정소영_solved {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] s = br.readLine().toCharArray();
		Stack<Character> st = new Stack<>();
		//ArrayDeque
		int len = s.length;
		boolean flag = false;
		for (int i = 0; i < len; i++) {
			if (s[i] == '<') {
				while(!st.isEmpty()) {
					System.out.print(st.pop());
				}
				System.out.print(s[i]);
				flag = true;
			}else if(s[i] == '>') {
				System.out.print(s[i]);
				flag = false;
			}else if(flag) {
				System.out.print(s[i]);
			}else {
				if(s[i]!=' ') {
					st.push(s[i]);
				}else if(s[i]==' '){
					while(!st.isEmpty()) {
						System.out.print(st.pop());
					}
					System.out.print(' ');
				}
			}
			

		}
		while(!st.isEmpty()) {
			System.out.print(st.pop());
		}
		br.close();
	}

}
