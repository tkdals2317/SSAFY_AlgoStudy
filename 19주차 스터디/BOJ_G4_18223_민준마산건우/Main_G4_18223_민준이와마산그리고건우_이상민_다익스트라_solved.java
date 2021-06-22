import java.util.*;
import java.io.*;
public class Main_G4_18223_민준이와마산그리고건우_이상민_다익스트라_solved {
	static int V, E, P;
	static int [][] matrix;
	static final int INF = Integer.MAX_VALUE;
	static class Node implements Comparable<Node>{
		int vertex;
		int totalDistance;
		public Node(int vertex, int totalDistance) {
			this.vertex = vertex;
			this.totalDistance = totalDistance;
		}
		
		public int compareTo(Node o) {
			return Integer.compare(this.totalDistance , o.totalDistance);
		}
		
	}
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_18223.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		matrix = new int[V+1][V+1];

		
		for(int i = 0; i < E; i ++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());			
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			matrix[from][to] = weight;
			matrix[to][from] = weight;
		}

 		//System.out.println(dijkstra(1, V));		
 		//System.out.println(dijkstra(1, P));		
 		//System.out.println(dijkstra(P, V));		
		
		//출발지에서 목적지로 가는 시간이 경유지를 거치는 경우과 같으면 경로에 포함된다 판단
 		if(dijkstra(1, V)==dijkstra(1, P)+dijkstra(P, V)) {
 			System.out.println("SAVE HIM");
 		}else {
 			System.out.println("GOOD BYE");
 		}
 		
		br.close();
	}
	static int dijkstra(int start, int end) {
		boolean [] visited = new boolean[V+1];
		
		int []distance = new int[V+1];
		
		Arrays.fill(distance, INF);
		
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		
		distance[start] = 0;
		
		queue.offer(new Node(start, distance[start]));
		
		while(!queue.isEmpty()) {
			Node curr = queue.poll();
			
			if(visited[curr.vertex]) continue;
			
			visited[curr.vertex] = true;

			if(curr.vertex==end) break;
			
			for(int c = 1; c <= V; c++) {
				if(!visited[c]&&matrix[curr.vertex][c]!=0&&distance[c]>curr.totalDistance+matrix[curr.vertex][c]) {
					distance[c]=curr.totalDistance+matrix[curr.vertex][c];
					queue.offer(new Node(c, distance[c]));
				}
			}	
		}
		//System.out.println(Arrays.toString(distance));
		
		return distance[end];
	}
}
