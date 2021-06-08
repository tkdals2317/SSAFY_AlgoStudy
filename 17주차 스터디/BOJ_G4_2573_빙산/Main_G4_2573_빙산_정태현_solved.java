package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G4_2573_빙산_정태현_solved {
	//위치 배열용
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
	
	static int[] dr = {-1, 0, 1, 0}; //상우하좌
	static int[] dc = {0, 1, 0, -1};
	static int N, M;
	static int[][] map;
	static Queue<Pos> q;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// ---------- Variable ----------
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		q = new LinkedList<Pos>();
		int yearcnt = 0;
		// ---------- Variable ----------
		
		
		// ----------Input map ----------
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");			
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]!=0) {
					q.offer(new Pos(i, j));
				}
			}
		}
		// ----------Input map ----------
		
		
		// -------Problem Solving -------
		while(iceCnt() < 2) {
			//시간초과 원인
			if(iceCnt() == 0) {
				yearcnt = 0;
				break;
			}
			bfs();
			yearcnt++;
		}
		
		System.out.println(yearcnt);
		// -------Problem Solving -------
	}

	private static int iceCnt() {
		boolean[][] visited = new boolean[N][M];
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!visited[i][j] && map[i][j] != 0) {
					dfs(i, j , visited);
					cnt++;
				} 
			}
		}
		
		return cnt;
	}

	private static void dfs(int r, int c, boolean[][] visited) {
		visited[r][c] = true;
		
		for (int i = 0; i < dr.length; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(nr >= 0 && nc >= 0 && nr < N && nc < M) {
				if(map[nr][nc] != 0 && !visited[nr][nc]) {
					dfs(nr, nc, visited);
				}
			}
		}
	}

	private static void bfs() {
		boolean[][] visited = new boolean[N][M];
		int size = q.size();
		for (int i = 0; i < size; i++) {
			Pos cur = q.poll();
			visited[cur.r][cur.c] = true;
			
			for (int j = 0; j < dr.length; j++) {
				int nr = cur.r + dr[j];
				int nc = cur.c + dc[j];
				
				if(nr >= 0 && nc >= 0 && nr < N && nc < M) {
					if(!visited[nr][nc] && map[nr][nc]==0) {
						map[cur.r][cur.c]--;
					}
				}
				
				if(map[cur.r][cur.c] <= 0) {
					map[cur.r][cur.c] = 0;
					break;
				}
			}
			
			if(map[cur.r][cur.c] > 0) {
				q.offer(new Pos(cur.r, cur.c));
			}
		}
	}
}
