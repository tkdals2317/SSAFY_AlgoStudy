import java.io.*;
import java.util.*;

public class Main_bj_3190_뱀_구미_4_이준형 {

	static class pos { // 위치 정보
		int i;
		int j;

		public pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	static class time { // 시간정보
		int t;
		char d;

		public time(int t, char d) {
			this.t = t;
			this.d = d;
		}
	}

	static int[] di = { -1, 0, 1, 0 }; // 방향벡터 상우하좌
	static int[] dj = { 0, 1, 0, -1 };

	static int look; // 뱀이 보는 방향
	static int headi; // 범 머리 위치
	static int headj;
	static int tail; // 꼬리 정보
	static int head; // 머리정보

	static int N; // 보드 크기
	static int[][] map; // 맵

	static ArrayDeque<time> timeque = new ArrayDeque<>(); // 시간 큐

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		map = new int[N + 1][N + 1]; // 1행 1열부터 시작

		// 사과 넣어주기
		int K = Integer.parseInt(br.readLine());
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			map[i][j] = -1;
		}

		// 시간 방향정보 넣어주기
		int L = Integer.parseInt(br.readLine());
		for (int k = 0; k < L; k++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			char d = st.nextToken().charAt(0);
			timeque.offer(new time(t, d));
		}

		// 뱀 이동
		map[1][1] = 1; // 초기화
		headi = 1;
		headj = 1;
		look = 1;
		tail = 1;

		int ans = process();

		System.out.println(ans);

		br.close();
	}

	// 뱀 이동
	private static int process() {

		int timecount = 0;

		while (true) {
			timecount++; // 시간증가

			int ci = headi + di[look];
			int cj = headj + dj[look];
			if (ci >= 1 && ci <= N && cj >= 1 && cj <= N && map[ci][cj] < 1) { // 이동가능
				if (map[ci][cj] == 0) { // 사과 안먹었으면 꼬리제거
					remove_tail();
					tail++;
				}
				map[ci][cj] = timecount + 1; // 머리의 위치 이동
				headi = ci;
				headj = cj;

			} else { // 이동 불가능 하므로 종료
				return timecount;
			}

			searchtime(timecount); // 시간에 따른 방향변화

		}

	}

	// 맵출력
	private static void printmap() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(map[i][j] + "   ");
			}
			System.out.println();
		}
		System.out.println("----------");
	}

	// 맵 돌면서 tail발견하면 제거
	private static void remove_tail() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(map[i][j]==tail)
					map[i][j]=0;
			}
		}
	}

	// 시간에 따른 방향 변경
	private static void searchtime(int timecount) {
		int size = timeque.size();
		for (int s = 0; s < size; s++) { // 큐 사이즈 만큼 돌면서
			time tmp = timeque.poll();
			if (tmp.t == timecount) { // 시간이 일치할경우
				char direc = tmp.d;
				if (direc == 'L') { // 방향 정보 변경
					look = (look + 3) % 4;
				} else if (direc == 'D') {
					look = (look + 1) % 4;
				}
				return;
			} else { // 아니면 다시 넣어주기
				timeque.offer(tmp);
			}
		}

	}

}
