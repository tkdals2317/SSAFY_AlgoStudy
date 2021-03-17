package baekjun;
import java.io.*;
import java.util.*;

public class Main_bj_1021_회전하는큐_구미_4_이준형2 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//		Deque<Integer> queue =new ArrayDeque<Integer>();

		ArrayList<Integer> list = new ArrayList<>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 입력
		for (int i = 0; i < N; i++) {
			list.add(i + 1);
		}

		st = new StringTokenizer(br.readLine());
		int cnt = 0;
		// 입력받은 수 만큼 반복
		for (int i = 0; i < M; i++) {

			int get_num = Integer.parseInt(st.nextToken());
			int n = list.indexOf(get_num);

			//앞으로가는게 더 빠를때
			if (n < list.size() - n) {
				while (true) {
					int tmp = list.remove(0);
					if (tmp == get_num)
						break;
					list.add(tmp);
					cnt++;
				}
			} else {	//뒤로가는거
				while (true) {
					cnt++;
					int tmp = list.remove(list.size() - 1);
					if (tmp == get_num)
						break;
					list.add(0,tmp);
				}
			}
		}

		System.out.println(cnt);

		br.close();
	}

}
