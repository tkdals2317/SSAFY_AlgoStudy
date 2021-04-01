import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_bj_16236_아기상어_정소영_x {

	static int N;
	static int[][] map;
	static int[][] visited;
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	static int baby = 2;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		visited = new int[N][N];

		int si = 0, sj = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 9) {
					si = i;
					sj = j;
				}
			}
		}

		bfs(si, sj);

	}

	private static void bfs(int i, int j) {
		Queue<Pair> q = new LinkedList<Pair>();
		q.offer(new Pair(i, j, 0));
		visited[i][j] = 1;
		int time=0, size=2, eat=0;
		while (!q.isEmpty()) {

			Pair next = q.poll();
			int pi = next.ii;
			int pj = next.jj;
			for (int k = 0; k < 4; k++) {
				int ni = pi + di[k];
				int nj = pj + dj[k];
				
				if (ni >= 0 && ni < N && nj >= 0 && nj < N) {

					visited[ni][nj] = visited[next.ii][next.jj] + 1;
					q.offer(new Pair(ni, nj));
					if (map[ni][nj] > 0 && map[ni][nj] < baby) {
						
					}

				}

			}

		}

	}

	static class Pair {
		int ii;
		int jj;
		int time;

		public Pair(int ii, int jj, int time) {
			super();
			this.ii = ii;
			this.jj = jj;
			this.time = time;
		}

		public Pair(int ii, int jj) {
			super();
			this.ii = ii;
			this.jj = jj;
		}

	}

}
