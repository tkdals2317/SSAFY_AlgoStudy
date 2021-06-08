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
			//빙산  수가 0개면 그냥 그대로 녹아서 없어지는 거라서 0을 출력
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
					//dfs로 빙하 개수 카운트
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
		//process 이전 빙산이었는데 녹아서 0이 된것은 바다로 치지 않기 때문에 visited 사용
		boolean[][] visited = new boolean[N][M];
		
		//현재 년도의 빙산수만큼 돌기 위함
		int size = q.size();
		for (int i = 0; i < size; i++) {
			Pos cur = q.poll();
			//해당 칸은 원래 빙산이었음을 나타내기 위해
			visited[cur.r][cur.c] = true;
			
			for (int j = 0; j < dr.length; j++) {
				int nr = cur.r + dr[j];
				int nc = cur.c + dc[j];
				
				if(nr >= 0 && nc >= 0 && nr < N && nc < M) {
					if(!visited[nr][nc] && map[nr][nc]==0) {
						map[cur.r][cur.c]--;
					}
				}
				
				//녹는 과정에서 0이하가 되어버리면 더 녹일 필요 없이 break
				if(map[cur.r][cur.c] <= 0) {
					map[cur.r][cur.c] = 0;
					break;
				}
			}
			
			//다음 년도 process를 위해 0이상 빙산인 것들을 q에 넣음
			if(map[cur.r][cur.c] > 0) {
				q.offer(new Pos(cur.r, cur.c));
			}
		}
	}
}
