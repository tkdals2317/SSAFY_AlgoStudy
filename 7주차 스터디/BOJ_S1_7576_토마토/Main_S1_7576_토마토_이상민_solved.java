import java.util.*;
import java.io.*;
public class Main_S1_7576_토마토_이상민_solved {
	static int M, N;
	static int [][] map;
	static boolean [][] v;
	static int[] di = {-1,0,1,0};
	static int[] dj = { 0,1,0,-1};
	
	static class Point{
		int pi;
		int pj;
		int time;
		
		public Point(int pi, int pj, int time) {
			super();
			this.pi = pi;
			this.pj = pj;
			this.time = time;
		}

		@Override
		public String toString() {
			return "[" + pi + ", " + pj + ", " + time + "]";
		}


			
	}
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_7576.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 6
		M = Integer.parseInt(st.nextToken()); // 4
		map = new int[M][N];
		v = new boolean[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		bfs();
	
		
		br.close();			
	}
	static void bfs() {
		ArrayDeque<Point> queue = new ArrayDeque<Point>();
		int time = 0;
		boolean flag = false;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]==1) {
					queue.offer(new Point(i,j, time));
					v[i][j] = true;
				}
			}
		}
		while(!queue.isEmpty()) {
			Point current = queue.poll();
			time = current.time;
			for (int d = 0; d < di.length; d++) {
				int ni = current.pi + di[d];
				int nj = current.pj + dj[d];
				if(ni>=0 && ni<M && nj>=0 && nj < N && map[ni][nj]==0 && !v[ni][nj]) {
					queue.offer(new Point(ni,nj, current.time+1));
					v[ni][nj] = true;
					map[ni][nj] = 1;
				}			
			}					
		}
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]==0) {
					flag=true;
				}
			}
		}
		if(flag) {
			System.out.println(-1);
		}else {
			System.out.println(time);			
		}
		
	}
}
