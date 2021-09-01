package PG;

import java.util.*;

public class Soulition_programmers_level3_블록이동하기 {

	static int dx[] = { 1, 0, -1, 0 };
	static int dy[] = { 0, -1, 0, 1 };

	public static void main(String[] args) {
		int[][] board = { { 0, 0, 0, 1, 1, }, { 0, 0, 0, 1, 0 }, { 0, 1, 0, 1, 1 }, { 1, 1, 0, 0, 1 },
				{ 0, 0, 0, 0, 0 } };
		System.out.println(bfs(board));
	}

	private static int bfs(int[][] board) {
		// TODO Auto-generated method stub
		int n = board.length;
		int y, x, oy, ox, dir, ans;
		Queue<Pos> q = new LinkedList<Pos>();
		q.add(new Pos(0, 0, 0, 0));
		boolean[][][] visited = new boolean[board.length][board.length][4];
		visited[0][0][0] = true;

		while (!q.isEmpty()) {
			Pos pos = q.poll();
			dir = pos.dir;
			ans = pos.cnt;
			y = pos.y;
			x = pos.x;
			oy = y + dy[dir];
			ox = x + dx[dir];

			if ((y == n - 1 && x == n - 1) || (oy == n - 1 && ox == n - 1))
				return ans;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				int nox = ox + dx[i];
				int noy = oy + dy[i];

				if (isValid(ny, nx, n, board) && isValid(noy, nox, n, board) && !visited[ny][nx][dir]) {
					visited[ny][nx][dir] = true;
					q.add(new Pos(ny, nx, ans+1, dir));
				}
			}

			for (int i = 1; i < 4; i = i + 2) {
				int ndir = (dir + i) % 4;

				// x, y 기준으로 회전
				if (isValid(y + dy[ndir], x + dx[ndir], n, board) && isValid(oy + dy[ndir], ox + dx[ndir], n, board)
						&& !visited[y][x][ndir]) {
					visited[y][x][ndir] = true;
					q.add(new Pos(y, x, ans+1, ndir)); // x, y 기준으로 회전
				}

				if (isValid(y + dy[ndir], x + dx[ndir], n, board) && isValid(oy + dy[ndir], ox + dx[ndir], n, board)
						&& !visited[oy][ox][ndir]) {
					visited[oy][ox][ndir] = true;
					q.add(new Pos(oy, ox, ans+1, ndir)); // ox, oy 기준으로 회전
				}
//				
//				if (isValid(oy + dy[ndir], ox + dx[ndir], n) && !visited[oy][ox][ndir]) {
//					visited[oy][ox][ndir] = true;
//					q.add(new Pos(oy, ox, ++ans, ndir)); // x, y 기준으로 회전
//				}
			}
		}
		return -1;
	}

	private static boolean isValid(int ny, int nx, int n, int[][] board) {
		if (ny >= n || nx >= n || ny < 0 || nx < 0)
			return false;
		if (board[ny][nx] != 0)
			return false;
		return true;
	}
}

class Pos {
	int x, y, cnt, dir;

	public Pos(int y, int x, int cnt, int dir) {
		super();
		this.x = x;
		this.y = y;
		this.cnt = cnt;
		this.dir = dir;
	}
}