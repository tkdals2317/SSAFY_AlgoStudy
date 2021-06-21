package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_G4_18223_민준마산건우_정태현_solved {
	static int V, E, P;
	static int[][] adjMatrix;
	static int[] route;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		//정점은 0 ~ V-1
		V = Integer.parseInt(st.nextToken()); //정점 개수
		E = Integer.parseInt(st.nextToken()); //간선 개수
		P = Integer.parseInt(st.nextToken()) - 1; //건우 정점 위치
		adjMatrix = new int[V][V];
		int a, b, w;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken()) - 1;
			b = Integer.parseInt(st.nextToken()) - 1;
			w = Integer.parseInt(st.nextToken());
			adjMatrix[a][b] = w;
			adjMatrix[b][a] = w;
		}
		
		// 더 오래걸림 ;;;
//		int dist1 = dijkstra(0, V-1);
//		int idx = V-1;
//		while(idx != 0) {
//			idx = route[idx];
//			if(idx == P) {
//				System.out.println("SAVE HIM");
//				System.exit(0);
//			}
//		}
//		int dist2 = dijkstra(0, P);
//		int dist3 = dijkstra(P, V-1);
		
		//========== 이부분만 있어도 통과 ==========
		if(dijkstra(0, V-1) >= dijkstra(0, P) + dijkstra(P, V-1)) {
			System.out.println("SAVE HIM");
		} else {
			System.out.println("GOOD BYE");
		}
		//========== 이부분만 있어도 통과 ==========
	}
	private static int dijkstra(int start, int end) {
		int[] dist = new int[V];
		route = new int[V];
		boolean[] visited = new boolean[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		
		for (int i = 0; i < V; i++) {
			int min = Integer.MAX_VALUE;
			int cur = 0;
			for (int j = 0; j < V; j++) {
				if(!visited[j] && min > dist[j]) {
					min = dist[j];
					cur = j;
				}
			}
			visited[cur] = true;
			
			//end에 도착했으면 그냥 break
			if(cur == end) break;
			
			for (int j = 0; j < V; j++) {
				if(!visited[j] && adjMatrix[cur][j] != 0 && dist[j] > min + adjMatrix[cur][j]) {
					dist[j] = min + adjMatrix[cur][j];
					route[j] = cur;
				}
			}
		}
		
		return dist[end];
	}
	
}
