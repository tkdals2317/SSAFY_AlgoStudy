package PG;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_LV2_거리두기확인하기_배태호_solved {
	static char[][] map;
	static boolean[][] visited;
	static int dy[] = { 0, 1, 0, -1 };
	static int dx[] = { 1, 0, -1, 0 };
	static int answer[] = new int[5];

	public static void main(String[] args) {
		String[][] places = { { "POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP" },
				{ "POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP" }, { "PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX" },
				{ "OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO" }, { "PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP" } };

		//////////////////////////////////////////////

		
		int[] answer = new int[places.length];
		for (int i = 0; i < places.length; i++) 
			answer[i]=1;
		
		for (int t = 0; t < places.length; t++) {
			map = new char[5][5];
			visited= new boolean[5][5];
			for (int i = 0; i < 5; i++)
				for (int j = 0; j < 5; j++)
					map[i][j] = places[t][i].charAt(j);

			for (int i = 0; i < 5; i++)
				for (int j = 0; j < 5; j++)
					if (map[i][j] == 'P')
						if (!bfs(i, j)) {
							answer[t] = 0;
							i=5;
							break;
						}
		}
		
		for (int i = 0; i < places.length; i++) {
			System.out.print(answer[i]+" ");
		}
	}

	private static boolean bfs(int i, int j) {
		// TODO Auto-generated method stub
		Point p = new Point(i, j, 0);
		Queue<Point> q = new LinkedList<>();
		visited[i][j] = true;
		q.add(p);

		while (!q.isEmpty()) {
			Point np = q.poll();
			int y = np.y;
			int x = np.x;
			int cnt = np.cnt;

			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];

				if (ny >= 0 && nx >= 0 && ny < 5 && nx < 5) 
					if (!visited[ny][nx]  && map[ny][nx] != 'X') {
						if(map[ny][nx] == 'P'&& cnt<2)  return false;
						visited[ny][nx] = true;
						if(cnt<2)
						q.add(new Point(ny, nx, cnt+1));
					}
			}
		}
		return true;
	}
}

class Point {
	int y, x, cnt;

	public Point(int y, int x, int cnt) {
		super();
		this.y = y;
		this.x = x;
		this.cnt = cnt;
	}
}