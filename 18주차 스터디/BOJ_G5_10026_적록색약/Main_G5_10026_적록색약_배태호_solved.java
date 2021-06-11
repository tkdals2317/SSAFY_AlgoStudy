package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Soulition_BOJ_10026_적록색약 {

	static int N, ans;
	static int dx[] = { 1, 0, -1, 0 };
	static int dy[] = { 0, -1, 0, 1 };
	private static boolean[][] visited;
	static char map[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		visited = new boolean[N][N];
		map = new char[N][N];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++)
				map[i][j] = s.charAt(j);
		}

		sol();
		System.out.println(ans);
	}

	private static void sol() {
		int cnt = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (!visited[i][j]) {
					visited[i][j] = true;
					dfs(i, j, map[i][j]);
					cnt++;
				}
		System.out.print(cnt + " ");

		visited = new boolean[N][N];
		changeMap();
		cnt = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (!visited[i][j]) {
					visited[i][j] = true;
					dfs(i, j, map[i][j]);
					cnt++;
				}
		System.out.print(cnt);
	}

	private static void changeMap() {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (map[i][j] == 'R')
					map[i][j] = 'G';
	}

	private static void dfs(int y, int x, char character) {

		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			if (ny < 0 || nx < 0 || ny >= N || nx >= N)
				continue;
			if (!visited[ny][nx] && character == map[ny][nx]) {
				visited[ny][nx] = true;
				dfs(ny, nx, character);
			}
		}
	}
}
