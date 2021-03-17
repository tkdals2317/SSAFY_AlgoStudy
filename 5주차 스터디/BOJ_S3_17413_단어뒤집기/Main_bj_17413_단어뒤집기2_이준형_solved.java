package baekjun;
import java.io.*;
import java.util.*;

public class Main_bj_17413_단어뒤집기2_구미_4_이준형 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = br.readLine();
		int flag = 0;	//현재 괄호 안인지 아닌지 판단
		Stack<Character> stack = new Stack<>();	//문자 저장할 스택
		char c;
		for (int i = 0; i < str.length(); i++) { // 문자열의 길이 만큼 반복
			c = str.charAt(i); // 한문자씩 처리
			if (flag == 1) { // 괄호 안에 있는 경우 문자를 그대로 넣어줌
				sb.append(c);
				if (c == '>')	// 닫는 괄호를 만난 경우 상태 변경
					flag = 0; 
			} else if (flag == 0) { // 괄호 안이 아닌 경우
				if (c == ' ') {		//공백 문자 만나면 있는거 다 넣어줌
					while (!stack.isEmpty()) {
						sb.append(stack.pop());
					}
					sb.append(c);
				} else if (c == '<') {
					flag = 1;	//상태변경
					while (!stack.isEmpty()) {
						sb.append(stack.pop());
					}
					sb.append(c);
				} else {	//아니면 스택에 저장
					stack.push(c);
				}
			}
		}
		while (!stack.isEmpty()) { // 스택에 남은거 써주기
			sb.append(stack.pop());
		}
		System.out.println(sb);
		br.close();
	}

}
