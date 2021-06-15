package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_G5_10026_적록색약_정태현_solved {
	static int[] dr = {-1, 0, 1, 0}; //상우하좌
	static int[] dc = {0, 1, 0, -1};
	static int N;
	static char[][] map;
	public static void main(String[] args) throws IOException {
		// Input tools -------------------
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		// Input tools -------------------
		
		
		// Variable ----------------------
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		// Variable ----------------------		
		
		
		// Input -------------------------
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		// Input -------------------------
		
		
		// Problem Solving ---------------
		int norm = normCnt();
		//그냥 맵 수정하고 다시 dfs
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 'R') {
					map[i][j] = 'G';
				}
			}
		}
		int rgw = normCnt();
		
		System.out.println(norm+" "+rgw);
		// Problem Solving ---------------
		
	}

	private static int normCnt() {
		boolean[][] visited = new boolean[N][N];
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					dfs(i, j, visited, map[i][j]);
					cnt++;
				}
			}
		}
		
		return cnt;
	}

	private static void dfs(int r, int c, boolean[][] visited, char color) {
		visited[r][c] = true;
		
		for (int i = 0; i < dr.length; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(nr >= 0 && nc >= 0 && nr < N && nc < N) {
				if(map[nr][nc]==color && !visited[nr][nc]) {
					dfs(nr, nc, visited, map[nr][nc]);
				}					
			}
		}
	}
	
	
}
