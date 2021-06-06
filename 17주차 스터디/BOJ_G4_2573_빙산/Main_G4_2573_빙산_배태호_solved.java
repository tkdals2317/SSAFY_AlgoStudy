package Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Soulition_BOJ_2573_빙산 {
	static int N, M, ans;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static int[][] map;
	static int[][] copy;
	static boolean visited[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		copy = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				copy[i][j] = map[i][j];
			}
		}

		sol();
		System.out.println(ans);
	}

	private static void sol() {
		while (true) {
			visited = new boolean[N][M];

			for (int i = 0; i < N; i++)
				for (int j = 0; j < M; j++)
					if (map[i][j] > 0)
						melt(i, j);

			copymap();

			ans++;
			if (check())
				return;
		}
	}

	private static void copymap() {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				map[i][j] = copy[i][j];
	}

	private static void melt(int y, int x) {
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if (ny < N && nx < M && ny >= 0 && nx >= 0)
				if (map[ny][nx] <= 0)
					cnt++;
		}
		copy[y][x] -= cnt;
	}

	private static boolean check() {
		boolean flag = false;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0 && !visited[i][j] && flag) return true;
				if (map[i][j] > 0 && !visited[i][j]) {
					dfs(i, j);
					flag = true;
				}
			}
		if(!flag) {
			ans=0;
			return true;
		}
		return false;
	}

	private static void dfs(int y, int x) {
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			if (ny < N && nx < M && ny >= 0 && nx >= 0) 
				if (!visited[ny][nx] && map[ny][nx] > 0) {
					visited[ny][nx] = true;
					dfs(ny, nx);
				}
		}
	}
}