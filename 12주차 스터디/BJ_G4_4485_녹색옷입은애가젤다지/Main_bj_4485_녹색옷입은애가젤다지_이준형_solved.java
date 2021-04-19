import java.io.*;
import java.util.*;

public class Main_bj_4485_녹색옷입은애가젤다지_구미_4_이준형 {

	static class pos {
		int i;
		int j;

		public pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	static int N;
	static int[][] map;
	static int[][] map_new;
	static boolean[][] visit;
	static int[] di = { 0, 1, 0, -1 };
	static int[] dj = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int go = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

//			printmap();
			
			findway();
//			printnewmap();

			System.out.println("Problem" + " " + go + ":" + " " + map_new[N - 1][N - 1]);
			go++;
		}

		br.close();
	}

	// 최소 비용 길 찾기
	private static void findway() {
		ArrayDeque<pos> que = new ArrayDeque<>();
		que.offer(new pos(0, 0));
		map_new = new int[N][N];
		map_new[0][0] = map[0][0];
		visit = new boolean[N][N];
		visit[0][0] = true;

		while (!que.isEmpty()) {
			int size = que.size();
			for (int s = 0; s < size; s++) {
				pos tmp = que.poll();
				for (int k = 0; k < 4; k++) {
					int ci = tmp.i + di[k];
					int cj = tmp.j + dj[k];
					if (ci >= 0 && ci < N && cj >= 0 && cj < N) {
						if (visit[ci][cj] == false) {
							map_new[ci][cj] = map_new[tmp.i][tmp.j] + map[ci][cj];
							visit[ci][cj] = true;
							que.offer(new pos(ci, cj));
						} else if (map_new[ci][cj] > map_new[tmp.i][tmp.j] + map[ci][cj]) {
							map_new[ci][cj] = map_new[tmp.i][tmp.j] + map[ci][cj];
							que.offer(new pos(ci, cj));
						}
					}
				}
			}
		}

	}

	// 맵출력
	private static void printmap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("----------");
	}

	private static void printnewmap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map_new[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("----------");
	}

}
