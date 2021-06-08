package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_G4_9935_문자열폭발_정태현_solved {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		//원본 문자열
		String str = br.readLine();
		//폭발문자열
		String bomb = br.readLine();
		StringBuilder sb = new StringBuilder();
		
//		char[] strarr = new char[str.length()];
		Stack<Character> ans = new Stack<Character>();
		char[] bombarr = new char[bomb.length()];
		for (int i = 0; i < bomb.length(); i++) {
			bombarr[i] = bomb.charAt(i);
		}
		boolean check = false;
		for (int c = str.length() - 1; c >= 0; c--) {
			ans.push(str.charAt(c));
			
			if(ans.peek() == bombarr[0] && ans.size() >= bombarr.length) {
				for (int i = 0; i < bombarr.length; i++) {
					if(ans.elementAt(ans.size()-1-i) == bombarr[i]) {
						check = true;
					} else {
						check = false;
						break;
					}
				}
			}
			
			if(check) {
				for (int i = 0; i < bombarr.length; i++) {
					ans.pop();
				}
				check = false;
			}
		}
		
		if(ans.size()==0) System.out.println("FRULA");
		else {
			int size = ans.size();
			for (int i = 0; i < size; i++) {
				sb.append(ans.pop());
			}
		}
		System.out.println(sb);
	}
}
