import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_G4_2573_빙산_변준형_solved {
	static int n, m, ax, ay;
	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };
	static int map[][];
	static int tmp[][];
	static boolean visited[][];

	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		tmp = new int[n][m];

		for (int i = 0; i < n; i++) {
			if (i == 0 || i == n - 1) {
				br.readLine();
				continue;
			}
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				if (j == 0 || j == m - 1) {
					st.nextToken();
					continue;
				}
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int time = 0;
		while (true) {
			// 빙산 개수
			int parts = getParts();
			if (parts >= 2) {
				System.out.println(time);
				break;
			} else if (parts == 0) {
				System.out.println(0);
				break;
			}

			time++;
			// 녹이기
			for (int i = 1; i < n - 1; i++) {
				for (int j = 1; j < m - 1; j++) {
					if (map[i][j] > 0) {
						tmp[i][j] = melt(i, j);
					}
				}
			}
			// 녹인거 적용시키기
			for (int i = 1; i < n - 1; i++) {
				for (int j = 1; j < m - 1; j++) {
					if (map[i][j] > 0) {
						map[i][j] -= tmp[i][j];
						if (map[i][j] < 0) {
							map[i][j] = 0;
						}
					}
				}
			}
		}
	}

	// 빙산 덩어리 몇개인지 개수 반환
	public static int getParts() {
		visited = new boolean[n][m];
		int cnt = 0;
		for (int i = 1; i < n - 1; i++) {
			for (int j = 1; j < m - 1; j++) {
				if (map[i][j] > 0 && !visited[i][j]) {
					dfs(i, j);
					cnt++;
				}
			}
		}
		return cnt;
	}

	// 빙산 덩어리가 몇개인지 확인을 위한 탐색
	public static void dfs(int x, int y) {
		visited[x][y] = true;
		for (int i = 0; i < 4; i++) {
			ax = x + dx[i];
			ay = y + dy[i];
			if (!visited[ax][ay] && map[ax][ay] > 0) {
				dfs(ax, ay);
			}
		}
	}

	// 빙산 녹이기
	public static int melt(int x, int y) {
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			ax = x + dx[i];
			ay = y + dy[i];
			if (map[ax][ay] == 0) {
				cnt++;
			}
		}
		return cnt;
	}

	// 상태확인용
	public static void show() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}