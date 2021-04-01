package swea;
import java.io.*;

public class Solution_d4_7792_반장선출_구미_4_이준형 {

	static int[] visit;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			// 초기화
			int max = 0;
			String answer = null;
			for (int i = 0; i < N; i++) {
				String str = br.readLine();

				int cnt = check(str);
				if (cnt == max) {
					// 사전순서로 처리
					max = cnt;
					answer = check_sequence(answer, str);
				} else if (cnt > max) {
					max = cnt;
					answer = str;
				}

			}
			sb.append("#" + tc + " " + answer + "\n");
		}
		System.out.println(sb);
		br.close();
	}

	// 문자열 순서 비교
	static String check_sequence(String answer, String str) {
		int i = 0;
		while (true) {
			// 탈출조건
			if (answer.length() <= i || str.length() <= i) {
				break;
			}
			if (answer.charAt(i) - '0' < str.charAt(i) - '0')
				return answer;
			else if (answer.charAt(i) - '0' > str.charAt(i) - '0')
				return str;
			i++;
		}
		return answer;
	}

	// 서로 다른 문자의 개수
	static int check(String str) {
		visit = new int[50];
		int cnt = 0;
		for (int i = 0; i < str.length(); i++) {
			char tmp = str.charAt(i);
			if (tmp != ' ' && visit[tmp - '0'] == 0) {
				visit[tmp - '0'] = 1;
				cnt++;
			}
		}
		return cnt;
	}

}
