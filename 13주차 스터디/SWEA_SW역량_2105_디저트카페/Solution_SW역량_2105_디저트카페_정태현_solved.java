package algorithm.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SW역량_2105_디저트카페_정태현_solved {
	static int[] dr = {1, 1, -1, -1}; //좌하, 우하, 우상, 좌상
	static int[] dc = {1, -1, -1, 1};
	static int N, ans;
	static int[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			ans = -1;
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < N - 2; i++) {
				for (int j = 1; j < N - 1; j++) {
					dfs(i, j, i, j, 0, 1, new boolean[101]);
				}
			}
			
			System.out.println("#"+tc+" "+ans);
			
		}
	}
	private static void dfs(int r, int c, int ansr, int ansc, int dir, int cnt, boolean[] dessert) {
		//첫 칸
		dessert[map[r][c]] = true;
		
		if(dir>3) return;
		
		r = r + dr[dir];
		c = c + dc[dir];
		
		if(r==ansr && c==ansc) {
			ans = Math.max(ans, cnt);
			return;
		}
		
		if(r<ansr) return;
		if(r<0 || c<0 || r>=N || c>=N) return;
		if(dessert[map[r][c]]) return;
		
		
		dessert[map[r][c]] = true;
		dfs(r, c, ansr, ansc, dir, cnt+1, dessert);
		dfs(r, c, ansr, ansc, dir+1, cnt+1, dessert);
		dessert[map[r][c]] = false;
		
		
	}
}
