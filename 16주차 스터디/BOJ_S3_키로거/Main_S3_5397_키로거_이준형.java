import java.io.*;
import java.util.*;

public class Main_bj_5397_키로거_구미_4_이준형3 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
//			ArrayList<Character> list = new ArrayList<>();
			String str = br.readLine();

			char[] bae = new char[str.length()];
			Stack<Character> front = new Stack<>();
			Stack<Character> back = new Stack<>();

			for (int i = 0; i < str.length(); i++) { // 입력 길이만큼
				char tmp = str.charAt(i);
				if (tmp == '<') {
					if (front.size() != 0) {
						back.push(front.pop());
					}
				} else if (tmp == '>') {
					if (back.size()!=0)
						front.push(back.pop());
				} else if (tmp == '-') { // 문자 제거
					if (front.size()!=0) {
						front.pop();
					}
				} else {
					front.push(tmp);
				}
			}
			while(!front.isEmpty())
				back.push(front.pop());
			while(!back.isEmpty())
				sb.append(back.pop());
			
			sb.append("\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.print(sb.toString());
		br.close();
	}

}
