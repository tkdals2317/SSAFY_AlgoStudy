package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Soulition_BOJ_21611_상어중학교 {
	static int[][] map;
	static int[][] cmap;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean visited[][];
	static boolean block[][];
	private static StringTokenizer st;
	private static BufferedReader br;
	private static int N, M;
	private static int by = 0;
	private static int bx = 0;
	Queue<Point> p;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		block = new boolean[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		block();
		int cnt = getCount();
		System.out.println("cnt : " + cnt);
		remove(by, bx);
		print();
		gravity();
		print();
		map = rotate();
		print();
		gravity();
		print();

	}

	private static void remove(int y, int x) {
		map[y][x] = -2;

		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			if (ny >= 0 && nx >= 0 && ny < N && nx < N && visited[ny][ny]) {
				remove(y, x);
			}
		}

	}

	private static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static int getCount() {
		int cnt = 0;
		int max = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (block[i][j] && !visited[i][j]) {
					cnt = dfs(i, j, 0, 0);
					if (max < cnt) {
						max = cnt;
						by = i;
						bx = j;
					}
				}
		return max;
	}

	private static int dfs(int y, int x, int cnt, int max) {
		if (max < cnt)
			max = cnt;

		visited[y][x] = true;
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			if (ny >= 0 && nx >= 0 && ny < N && nx < N && !visited[ny][ny] && block[ny][nx]) {
				dfs(ny, nx, cnt + 1, max);
			}
		}
		return max;
	}

	private static void block() {
		block = new boolean[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) {
					block[i][j] = true;
					for (int d = 0; d < 4; d++) {
						int y = i + dy[d];
						int x = j + dx[d];

						if (y >= 0 && x >= 0 && y < N && x < N && map[y][x] != -1)
							block[y][x] = true;
					}
				}
			}
	}

	private static void gravity() {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (map[i][j] == -2) {
					int y = i;
					while (y > 0) {
						if (map[y - 1][j] == -1)
							break;
						map[y][j] = map[y - 1][j];
						map[y - 1][j] = -2;
						y--;
					}
				}
	}

	private static int[][] rotate() {
		int[][] nMap = copyMap();
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				nMap[i][j] = map[N - 1 - j][i];
		return nMap;
	}

	private static int[][] copyMap() {
		int[][] nMap = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				nMap[i][j] = map[i][j];
		return nMap;
	}
}

class Point {
	int y, x;

	public Point(int y, int x) {
		super();
		this.y = y;
		this.x = x;
	}
}