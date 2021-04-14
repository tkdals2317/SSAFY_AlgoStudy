import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G5_3055_탈출_변준형_solved {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int T;
	static int R, C;
	static char[][] map;
	static Queue<Point> points;
	static int A;

	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));

		tokens = new StringTokenizer(input.readLine(), " ");
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());

		points = new LinkedList<>();
		map = new char[R][];
		Point sPoint = null;
		for (int r = 0; r < R; r++) {
			map[r] = input.readLine().toCharArray();
			for (int c = 0; c < C; c++) {
				if (map[r][c] == '*') {
					points.offer(new Point(r, c, true));
				} else if (map[r][c] == 'S') {
					sPoint = new Point(r, c, false);
				}
			}
		} // 지도 읽기
			// 고슴도치는 모든 물이 다 들어간 다음에 동작
		points.offer(sPoint);
		// System.out.println(points);
		A = Integer.MAX_VALUE;
		bfs();
		// 여전히 A가 MAX_VALUE이면 비버집을 못간것.
		output.append(A == Integer.MAX_VALUE ? "KAKTUS" : A).append("\n");

		System.out.println(output);
	}

	static void bfs() {
		int turn = 1;
		while (!points.isEmpty()) {
			// 초마다 현재 queue 사용하기...
			int size = points.size();
			while (size-- > 0) {
				Point head = points.poll();

				// 자식 탐색 한다.
				for (int d = 0; d < deltas.length; d++) {
					int nr = head.r + deltas[d][0];
					int nc = head.c + deltas[d][1];

					if (isIn(nr, nc)) {
						// 지금 녀석이 물이라면.. 다음으로 이동은 .과 S
						if (head.isWater) {
							if (map[nr][nc] == '.' || map[nr][nc] == 'S') {
								map[nr][nc] = '*'; // 방문처리
								points.offer(new Point(nr, nc, true));
							}
						}
						// 지금 녀석이 고슴도치라면.. . 또는 D (비버집 - 만나면 종료)
						else {
							if (map[nr][nc] == 'D') {
								A = turn;
								return;
							} else if (map[nr][nc] == '.') {
								map[nr][nc] = 'S';
								points.offer(new Point(nr, nc, false));
							}
						}
					}
				}
			}
			turn++;
		}
	}

	static boolean isIn(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}

	static class Point {
		int r, c;
		boolean isWater;

		public Point(int r, int c, boolean isWater) {
			super();
			this.r = r;
			this.c = c;
			this.isWater = isWater;
		}

	}

}
