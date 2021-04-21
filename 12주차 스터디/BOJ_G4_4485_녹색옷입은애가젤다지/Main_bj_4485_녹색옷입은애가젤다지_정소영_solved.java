import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_bj_4485_녹색옷입은애가젤다지_정소영_solved {
	// 일반 백트래킹 => 시간 초과
	// => 다익스트라
	static int[][] map;
	//static boolean[][] visited;
	static int n;	// 2~125
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	static int result;
	final static int INFINITY = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		int t = 1;

		while (n != 0) {
			map = new int[n][n];
			//visited = new boolean[n][n];
			
			
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // 입력 완료

//			result = Integer.MAX_VALUE;
//			visited[0][0] = true;
//			dfs(0, 0, map[0][0]);
//			System.out.println("Problem " + t + ": " + result);
			System.out.println("Problem " + t + ": " + distancestra2());
			t++;
			n = Integer.parseInt(br.readLine());
		}

		br.close();
	}



//	private static void dfs(int i, int j, int sum) {
//
//		if (i == n - 1 && j == n - 1) {
//			result = Math.min(result, sum);
//		}
//
//		for (int d = 0; d < 4; d++) {
//
//			int ni = i + di[d];
//			int nj = j + dj[d];
//
//			if (ni >= 0 && ni < n && nj >= 0 && nj < n) {
//				if (!visited[ni][nj] && sum+map[ni][nj]<result) {
//					visited[ni][nj] = true;
//					dfs(ni, nj, sum + map[ni][nj]);
//					visited[ni][nj] = false;
//				}
//			}
//		}
//
//	}

	
	private static int distancestra() {	// 안됨 더 공부 필요
		int[][] distance = new int[n][n];
		boolean[][] visited = new boolean[n][n];
		
		for (int i = 0; i < n; i++) {
			Arrays.fill(distance[i], INFINITY);
		}
		distance[0][0] = map[0][0];
		
		int min = 0, current=0;
		for (int i = 0; i < n; i++) {
			min = INFINITY;
			for (int j = 0; j < n; j++) {
				if(!visited[i][j] && min>distance[i][j]) {
					min = distance[i][j];
					current = j;
				}
			}
			visited[i][current] = true;
			if(current==n-1 && i==n-1) break;
			
			for (int j = 0; j < n; j++) {
				if(!visited[i][j] && distance[i][j]>min+map[current][j]) {
					distance[i][j]=min+map[current][j];
				}
			}
			
		}
		return distance[n-1][n-1];
		
	}
	
	private static int distancestra2() {
		
		int[][] distance = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(distance[i], INFINITY);
		}
		
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		distance[0][0] = map[0][0]; // 초기 값
		pq.offer(new Pair (0, 0, map[0][0])); // 시작 좌표

		while (!pq.isEmpty()) {
			Pair tmp = pq.poll();

			int i = tmp.i;
			int j = tmp.j;
			
			for (int k = 0; k < 4; k++) {
				int ni = i + di[k];
				int nj = j + dj[k];

				if (ni >= 0 && ni < n && nj >= 0 && nj < n) {
					if (distance[ni][nj] > distance[i][j] + map[ni][nj]) { 
						distance[ni][nj] = distance[i][j] + map[ni][nj]; 
						pq.offer(new Pair (ni, nj, distance[ni][nj])); 
					}
				}
			}
		}
		return distance[n - 1][n - 1];
		
	}
	
	private static class Pair implements Comparable<Pair>{
		int i, j;
		int dist;
		
		public Pair(int i, int j, int dist) {
			super();
			this.i = i;
			this.j = j;
			this.dist = dist;
		}

		@Override
		public int compareTo(Pair o) {
			// TODO Auto-generated method stub
			return Integer.compare(dist, o.dist);
		}
		
		
	}
	
}
