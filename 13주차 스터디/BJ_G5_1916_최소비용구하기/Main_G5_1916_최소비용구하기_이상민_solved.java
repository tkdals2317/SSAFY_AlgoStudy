import java.util.*;
import java.io.*;

public class Main_G5_1916_최소비용구하기_이상민_solved {
	static class Node implements Comparable<Node>{
		int vertex;
		int totalDistance;
		
		public Node(int vertex, int totalDistance) {
			this.vertex = vertex;
			this.totalDistance = totalDistance;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.totalDistance, o.totalDistance);
		}	
	}
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_1916.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 도시(정점)의 갯수
		int M = Integer.parseInt(br.readLine()); // 버스(간선)의 갯수
		
		final int INF = Integer.MAX_VALUE;
		
		int [][] adjList = new int [N][N];
		int [] distance = new int[N];
		boolean [] visited = new boolean[N];
		
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				adjList[i][j] = INF;
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			int weight = Integer.parseInt(st.nextToken());
			adjList[from][to] = Math.min(adjList[from][to], weight);
			//adjList[to][from] = weight;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(adjList[i][j] == INF) adjList[i][j] = -1;
			}
		}
		

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken())-1;
		int end = Integer.parseInt(st.nextToken())-1;
		Arrays.fill(distance, INF);
		distance[start] = 0;
		//System.out.println(Arrays.toString(distance));
		
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		
		queue.offer(new Node(start, distance[start]));
	
		while(!queue.isEmpty()) {
			Node curr = queue.poll();
			
			if(visited[curr.vertex]) continue;

			visited[curr.vertex] = true;
			if(curr.vertex==end) break;

			for (int c = 0; c < N; c++) {
				if(!visited[c]&&adjList[curr.vertex][c]>=0&&distance[c]>curr.totalDistance+adjList[curr.vertex][c]) {
					distance[c]=curr.totalDistance+adjList[curr.vertex][c];
					queue.offer(new Node(c, distance[c]));
				}	
			}
			//System.out.println(Arrays.toString(distance));
		}
		System.out.println(distance[end]);
		
		br.close();
	}
}
