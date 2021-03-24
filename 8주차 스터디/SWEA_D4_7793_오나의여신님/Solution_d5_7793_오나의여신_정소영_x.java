import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_d5_7793_오나의여신_정소영_x {
	static int N, M, res;
	static int[] di = { -1, 0, 1, 0 }; // 북동남왼
	static int[] dj = { 0, 1, 0, -1 }; // 위오아왼
	static char[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int T = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			visited = new boolean[N][M];
			int si = 0, sj = 0;
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = str.charAt(j);
					if (map[i][j] == 'S') {
						si = i;
						sj = j;
					} else if (map[i][j] == 'X') {
						visited[i][j] = true;
					} else if (map[i][j] == '*') {
						visited[i][j] = true;
					} else if (map[i][j] == 'D') {
						visited[i][j] = true;
					}
				}
			}

			res = Integer.MAX_VALUE;
			dfs(si, sj, 0);

			// 68 D 여신 / 42 * 악마 / 83 S 수연 / 88 X 돌 / 46 . 평범
			show();
			System.out.println();
			System.out.println(res);
		}

	}

	private static void dfs(int i, int j, int cnt) {
		if (map[i][j] == 'D') {
			res = Math.min(res, cnt);
			return;
		}

		for (int k = 0; k < 4; k++) {
			int ni = i + di[k];
			int nj = i + dj[k];
			if (ni >= 0 && ni < N && nj >= 0 && nj < M) {
				if (visited[ni][nj] == false && map[ni][nj] == '.') {
					visited[ni][nj] = true;
					for (int y = 0; y < N; y++) {
						for (int x = 0; x < M; x++) {
							if(visited[y][x] && map[y][x]=='*') {
								spread(y, x);
							}
						}
					}
					dfs(ni, nj, cnt + 1);
					visited[ni][nj] = false;
				} 
			}
		}

	}

	private static void spread(int i, int j) {
		for (int k = 0; k < 4; k++) {
			int ni = i + di[k];
			int nj = i + dj[k];
			if (ni >= 0 && ni < N && nj >= 0 && nj < M && map[ni][nj] == '.') {
				map[ni][nj] = '*';
				visited[i][j] = true;
			}
		}
	}

	private static void show() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}
/*

2
5 3
D*S
.X.
.X.
.X.
...
5 3
D*S
...
.X.
.X.
...

*/
