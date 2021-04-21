import java.util.*;
import java.io.*;

public class Main_G4_4485_녹색옷을입은애가젤다지_이상민_solved {
	static int N;
	static int [][] map;
	static boolean [][] visited;
	static int [] di = {-1, 0, 1, 0};
	static int [] dj = { 0, 1, 0,-1};
	static class Point implements Comparable<Point>{
		int ypos;
		int xpos;
		int k;
		public Point(int ypos, int xpos, int k) {
			super();
			this.ypos = ypos;
			this.xpos = xpos;
			this.k = k;
		}
		@Override
		public String toString() {
			return "Point [ypos=" + ypos + ", xpos=" + xpos + ", k=" + k + "]";
		}
		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.k, o.k);
		} 
		
	}
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_4485.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = 1;
		do{
			N = Integer.parseInt(br.readLine());
			if(N==0) break;
			map = new int[N][N];
			visited = new boolean[N][N];
			StringTokenizer st = null;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine()," ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());	
				}
			}
			System.out.println("Problem "+tc+": "+bfs());
			tc++;
		}while(true);
		br.close();
	}
	static int bfs() {
		PriorityQueue<Point> queue = new PriorityQueue<>();
		queue.offer(new Point(0, 0, map[0][0]));
		visited[0][0] = true;
		//int sum = map[0][0];
		while(!queue.isEmpty()) {
			Point curr = queue.poll();
			int ci = curr.ypos;
			int cj = curr.xpos;
			int ck = curr.k;
			//sum += map[ci][cj];
			//System.out.println(map[ci][cj]+" "+ck);
			for (int d = 0; d < 4; d++) {
				int ni = ci + di[d];
				int nj = cj + dj[d];
				if(ni<0||ni>=N||nj<0||nj>=N||visited[ni][nj]) continue;
				if(ni==N-1&&nj==N-1) {
					//return sum+map[ni][nj];
					return ck+map[ni][nj];
				}
				visited[ni][nj]=true;
				queue.offer(new Point(ni,nj, ck+map[ni][nj]));
			}
			
		}
		return 0;
	}
	
}
