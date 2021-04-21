package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G4_4485_녹옷입젤_정태현_solved {
	static int[] dr = {-1, 0, 1, 0}; //상우하좌
	static int[] dc = {0, 1, 0, -1};
	static int N, ans;
	static int[][] cave, rupee;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int pnum = 1;
		
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N == 0) break; //종료
			
			cave = new int[N][N]; //동굴
			rupee = new int[N][N]; //각 칸에 가기 위한 최소 루피값 저장?
			ans = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					cave[i][j] = Integer.parseInt(st.nextToken());
					rupee[i][j] = Integer.MAX_VALUE;
				}
			}
			
			bfs();
			
			System.out.println("Problem "+pnum+": "+ans);
			pnum++;
		}
	}
	
	private static void bfs() {
		Queue<Pos> q = new LinkedList<Pos>();
		
		ans = Integer.MAX_VALUE;
		q.offer(new Pos(0, 0)); //첫 점부터 시작
		rupee[0][0] = cave[0][0]; //첫 칸에 뺏기는 돈 미리 저장
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			if(cur.r==N-1 && cur.c==N-1) {
				if(rupee[cur.r][cur.c]<ans) ans = rupee[cur.r][cur.c];
				continue;
			}
			
			for (int i = 0; i < dr.length; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				
				if(nr<0 || nc<0 || nr>=N || nc>=N) continue; //범위 바깥
					//지금칸 + 다음칸 <= 다음루피칸 => 다음 루피칸 큐에 넣고 새로값지정
				if(rupee[cur.r][cur.c] + cave[nr][nc] < rupee[nr][nc]) { //더 작은 값이다
					rupee[nr][nc] = rupee[cur.r][cur.c] + cave[nr][nc];
					q.offer(new Pos(nr, nc));
				}
			}
		}
		
	}

	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
