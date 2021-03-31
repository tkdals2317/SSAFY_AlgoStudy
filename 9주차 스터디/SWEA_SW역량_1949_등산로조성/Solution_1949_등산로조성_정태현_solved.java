package algorithm.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1949_등산로조성_정태현_solved {
	static int[] dr = {-1, 0, 1, 0}; //상우하좌
	static int[] dc = {0, 1, 0, -1};
	static int N, K;
	static int maxH, maxL; //최대 높이
	static int[][] arr;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			maxH = Integer.MIN_VALUE;
			maxL = Integer.MIN_VALUE;
			arr = new int[N][N]; //맵
			visited = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					maxH = Math.max(maxH, arr[i][j]);
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(arr[i][j] == maxH) {
						visited[i][j] = true; //방문
						dfs(i, j, maxH, 1, 0);
						visited[i][j] = false;
					}
				}
			}
			System.out.println("#"+tc+" "+maxL);
		}
	}
	private static void dfs(int r, int c, int ht, int len, int cnt) {
		for (int i = 0; i < dr.length; i++) {
			maxL = Math.max(maxL, len);
			
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(nr >= 0 && nc >= 0 && nr < N && nc < N && !visited[nr][nc]) {
				if(ht <= arr[nr][nc]) {
					if(ht> arr[nr][nc] - K && cnt == 0) {
						visited[nr][nc] = true;
						dfs(nr, nc, ht - 1, len + 1, cnt+1);
						visited[nr][nc] = false;						
					}
				} else {
					visited[nr][nc] = true;
					dfs(nr, nc, arr[nr][nc], len + 1, cnt);
					visited[nr][nc] = false;
				}
			}
		}
		
	}
}