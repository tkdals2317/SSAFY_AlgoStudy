import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_G5_5430_AC_변준형_solved {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			String s = br.readLine();
			int N = Integer.parseInt(br.readLine());
			String tmp = br.readLine().replace("[", "").replace("]", "");	//	 괄호 삭제
			StringTokenizer st = new StringTokenizer(tmp, ",");
			Deque<Integer> ary = new ArrayDeque<>();
			boolean flag = false;											// false : 원래방향 , true : 반대방향
			boolean mode = false;
			for (int i = 0; i < N; i++) {
				ary.add(Integer.parseInt(st.nextToken()));
			}
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == 'R') {
					mode = !mode;
				} else {
					if (ary.isEmpty()) {
						flag = true;
						break;
					} else {
						if (mode) {
							ary.removeLast();
						} else {
							ary.removeFirst();
						}
					}
				}
			}
			if (!flag) {
				sb.append("[");
				if (mode) {
					while (ary.size() > 1) {
						sb.append(ary.pollLast() + ",");
					}
				} else {
					while (ary.size() > 1) {
						sb.append(ary.pollFirst() + ",");
					}
				}
				if (ary.size() != 0)
					sb.append(ary.getFirst());
				sb.append("]\n");
			} else {
				sb.append("error\n");
			}
		}
		System.out.println(sb);
	}

}