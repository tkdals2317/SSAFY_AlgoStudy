import java.io.*;
import java.util.*;

public class Main_bj_7576_토마토_구미_4_이준형2 {

	static class pos {	//위치 체크용
		int i;
		int j;

		public pos(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}

	static int[] di = { 1, -1, 0, 0 }; // 방향벡터
	static int[] dj = { 0, 0, 1, -1 };
	static int[][] map;
	static int M, N, ans;
	static ArrayDeque<pos> dequeue;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken()); // j
		N = Integer.parseInt(st.nextToken()); // i

		dequeue=new ArrayDeque<>();
		map = new int[N][M]; // 배열 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				map[i][j] = tmp;
				if (tmp == 1) { // 익은거 있으면 큐에 넣기
					dequeue.offer(new pos(i, j));
				}
			}
		}

		ans = -1;	//몇일 지났는지
		search(); // bfs 호출

		int flag = 0;
		for (int i = 0; i < N; i++) { // 배열에서 안익은거 있는지 체크
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0)
					flag = 1;
			}
		}
		if (flag == 1)
			System.out.println(-1); // 안익은거 존재
		else
			System.out.println(ans); // 아니면 몇일 걸렸는지 출력

		br.close();
	}

	// 토마토변화 dfs 구현
	private static void search() {
		while (!dequeue.isEmpty()) {
			int size = dequeue.size();
			for (int s = 0; s < size; s++) {
				pos p = dequeue.poll();
				int i = p.i;
				int j = p.j;
				for (int k = 0; k < 4; k++) { // 4방향 체크
					int ci = i + di[k];
					int cj = j + dj[k];
					if (ci >= 0 && ci < N && cj >= 0 && cj < M && map[ci][cj] != -1 && map[ci][cj] == 0) { // 범위조건,바뀌는조건
						map[ci][cj] = 1; // 익은걸로 변화
						dequeue.offer(new pos(ci,cj));
					}
				}
			}
			ans++;	//횟수증가
		}

	}

	// 배열출력
	private static void printmap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("==============");
	}
}
