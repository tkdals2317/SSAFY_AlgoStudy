package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main_S2_1012_solved {
	static boolean isVisited[][];
	static int di[] = {0, -1, 0, 1};	// »óÇÏÁÂ¿ì
	static int dj[] = {-1, 0, 1, 0};	// »óÇÏÁÂ¿ì
	static int M,N,K;
	static int a[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc=0; tc<T; tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int cnt = 0;
			a = new int[N][M];
			isVisited = new boolean[N][M];
			
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				a[y][x] = 1;
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(a[i][j] == 1 && isVisited[i][j] == false) {
						dfs(i, j);
						cnt++;
					}
				}
			}
			
			System.out.println(cnt);
		}
	}
	
	public static void dfs(int n1, int n2) {
		isVisited[n1][n2] = true;
		for(int i=0; i<di.length; i++) {
			int ni = n2 + di[i];
			int nj = n1 + dj[i];
			if(ni>=0 && nj>=0 && ni<M && nj<N) {
				if(a[nj][ni] == 1 && !isVisited[nj][ni]) {
					dfs(nj, ni);
				}
			}
		}
		
		
	}
}