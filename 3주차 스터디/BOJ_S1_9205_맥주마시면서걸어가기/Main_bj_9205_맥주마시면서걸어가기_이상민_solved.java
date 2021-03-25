import java.util.*;
import java.io.*;

public class Main_bj_9205_맥주마시면서걸어가기_이상민_solved {
	static int N;
	static ArrayList<Point> adjList;
	static boolean[] visited;
	static class Point{
		int y;
		int x;
		public Point(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		@Override
		public String toString() {
			return "[" + y + ", " + x + "]";
		}
		
	}
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_9205.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			// 정점의 갯수
			N = Integer.parseInt(br.readLine());
			adjList = new ArrayList<Point>();
			visited = new boolean[N+2];
			for (int i = 0; i < N+2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int ypos = Integer.parseInt(st.nextToken());
				int xpos = Integer.parseInt(st.nextToken());		
				adjList.add(new Point(ypos,xpos));		
			}
			bfs();
			if(visited[N+1]) System.out.println("happy");
			else System.out.println("sad");
		}
		br.close();
	}
	static void bfs() {
		ArrayDeque<Point> queue = new ArrayDeque<Point>();
		queue.offer(adjList.get(0));
		visited[0] = true;
		
		while(!queue.isEmpty()){
			Point current = queue.poll();
			int cy = current.y;
			int cx = current.x;
			for (int i = 0; i < adjList.size(); i++) {
				if(getDistance(cx, cy, adjList.get(i).x, adjList.get(i).y)<=1000&&!visited[i]) {
					visited[i] = true;
					queue.offer(adjList.get(i));
				}
			}					
		}
	}
	static int getDistance(int x1, int y1, int x2, int y2) {
		int dx = Math.abs(x1-x2);
		int dy = Math.abs(y1-y2);
		int dist = dx+dy;
		return dist;
	}
}
