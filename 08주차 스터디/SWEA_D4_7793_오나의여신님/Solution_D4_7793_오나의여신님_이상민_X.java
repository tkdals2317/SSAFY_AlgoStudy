import java.util.*;
import java.io.*;

public class Solution_D4_7793_오나의여신님_이상민_X {
	static int N, M;
	static char [][]map;
	static boolean [][]v;
	static boolean [][]v2;
	static Point suyean;
	static int pi, pj;
	static int [] di = {-1, 0, 1, 0};
	static int [] dj = { 0, 1, 0,-1};
	
	static class Point {
		int i;
		int j;
		public Point(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		@Override
		public String toString() {
			return "[" + i + ", " + j + "]";
		}
		
	}
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_d4_7793.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

			
		for (int tc = 1; tc <= T; tc++) {
			//구현 시작
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			v = new boolean[N][M];
			v2 = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = str.charAt(j);
					if(map[i][j]=='S') {
						suyean = new Point(i,j);
					}
				}
			}
			int sol = bfs(suyean);
			if(sol<0) System.out.println("#"+tc+" GAME OVER");
			else System.out.println("#"+tc+" "+sol);
			
		}
		
		br.close();
	} //end of main
	static int bfs(Point start) {
		ArrayDeque<Point> squeue = new ArrayDeque<Point>();
		ArrayDeque<Point> dqueue = new ArrayDeque<Point>();
		int move = 0;
		squeue.offer(start);
		v[start.i][start.j] = true;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]=='*') {
					dqueue.offer(new Point(i,j));
					v[i][j] = true;
					v2[i][j] = true;
					
				}
				if(map[i][j]=='X') {
					v[i][j] = true;
					v2[i][j] = true;
				}
			}
		}
		
		while(!squeue.isEmpty()) {
			Point current = squeue.poll();
			int ci = current.i;
			int cj = current.j;
			
			move++;
			for (int d = 0; d < 4; d++) {
				int ni = ci + di[d];
				int nj = cj + dj[d];
				if(ni>=0&&ni<N&&nj>=0&&nj<M&&!v[ni][nj]&&!v2[ni][nj]) {
					if(map[ni][nj]=='D') return move;
					squeue.offer(new Point(ni,nj));
					map[ci][cj] = '.';
					map[ni][nj] = 'S';
					//v[ci][cj] = false;
					v[ni][nj] = true;
				}
			}
			for (int i = 0; i < dqueue.size(); i++) {
				Point devil = dqueue.poll();
				int cdi = devil.i;
				int cdj = devil.j;
				for (int d = 0; d < 4; d++) {
					int ndi = cdi + di[d];
					int ndj = cdj + dj[d];
					if(ndi>=0&&ndi<N&&ndj>=0&&ndj<M&&map[ndi][ndj]=='.'&&!v2[ndi][ndj]) {						
						dqueue.offer(new Point(ndi,ndj));
						map[ndi][ndj] = '*';
						v2[ndi][ndj] = true;
					}
				}
			}
			
		}
		return -1;
		
	}

}
