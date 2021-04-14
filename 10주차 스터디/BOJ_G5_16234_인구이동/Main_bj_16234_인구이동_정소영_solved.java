import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_bj_16234_인구이동_정소영_solved {
	static int N, L, R;
	static int[][] map;
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	static boolean[][] visited;
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visited = new boolean[N][N];

		move(0, 0, 0);

		System.out.println(result);
		br.close();

	}

	private static void move(int i, int j, int cnt) {

		boolean flag = true;
		while (flag) {
			flag = false;
			for (int k = 0; k < N; k++) {
				for (int k2 = 0; k2 < N; k2++) {
					int pv = map[k][k2];

					for (int l = 0; l < 4; l++) {
						int ni = k + di[l];
						int nj = k2 + dj[l];
						if (ni >= 0 && ni < N && nj >= 0 && nj < N) {
							int nv = map[ni][nj];
							if (!visited[ni][nj] && Math.abs(pv - nv) >= L && Math.abs(pv - nv) <= R) {
								bfs(ni, nj);
								flag = true;
							}
						}
					}

				}
			}
			visited = new boolean[N][N];
			if(flag) result++;
		}

	}
	
	private static void show() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void bfs(int i, int j) {
		visited[i][j] = true;
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(i, j));
		LinkedList<Pair> tmp = new LinkedList<>();
		tmp.add(new Pair(i, j));
		int sum = 0;

		while (!q.isEmpty()) {
			Pair temp = q.poll();
			int pi = temp.i;
			int pj = temp.j;
			int pv = map[pi][pj];
			sum += pv;
			for (int k = 0; k < 4; k++) {
				int ni = pi + di[k];
				int nj = pj + dj[k];
				if (ni >= 0 && ni < N && nj >= 0 && nj < N) {
					int nv = map[ni][nj];
					if (!visited[ni][nj] && Math.abs(pv - nv) >= L && Math.abs(pv - nv) <= R) {
						q.add(new Pair(ni, nj));
						tmp.add(new Pair(ni, nj));
						visited[ni][nj] = true;
					}
				}
			}
		}

		int country_cnt = tmp.size();
		sum /= country_cnt;
		for (int k = 0; k < country_cnt; k++) {
			Pair country = tmp.get(k);
			map[country.i][country.j] = sum;
		}

	}

	static class Pair {
		int i, j;

		public int getI() {
			return i;
		}

		public void setI(int i) {
			this.i = i;
		}

		public int getJ() {
			return j;
		}

		public void setJ(int j) {
			this.j = j;
		}

		public Pair(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

	}

}
