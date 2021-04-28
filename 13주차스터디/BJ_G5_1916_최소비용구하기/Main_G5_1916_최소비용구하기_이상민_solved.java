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
		//입력값의 최소값만 받기 위한 초기화
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
			//같은 출발지에 같은 도착지인 버스가 여러 대 일 수 있으므로 최소값으로 갱신
			adjList[from][to] = Math.min(adjList[from][to], weight);
			//유향그래프이므로 양쪽에다 해줄 필요 X
			//adjList[to][from] = weight;
		}
		//갱신되지 않은 INF값들은 연결되지 않았으므로 다시 -1로 갱신
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				//-1로 해주는 이유? 버스의 요금이 0원부터이기 때문
				if(adjList[i][j] == INF) adjList[i][j] = -1;
			}
		}
		

		st = new StringTokenizer(br.readLine());
		//시작점, 끝점 입력
		int start = Integer.parseInt(st.nextToken())-1;
		int end = Integer.parseInt(st.nextToken())-1;
		//출발지로부터 거리 INF로 초기화
		Arrays.fill(distance, INF);
		//출발지로부터 출발지는 거리가 0
		distance[start] = 0;
		//System.out.println(Arrays.toString(distance));
		
		//최소비용인 것부터 꺼내기 위한 PQ
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		
		//출발지 큐에 넣기
		queue.offer(new Node(start, distance[start]));
	
		while(!queue.isEmpty()) {
			Node curr = queue.poll();
			//방문한 도시라면 continue
			if(visited[curr.vertex]) continue;
			//방문처리
			visited[curr.vertex] = true;
			//현재 뽑아낸 도시가 도착지라면 loop 탈출
			if(curr.vertex==end) break;

			for (int c = 0; c < N; c++) {
				// 1. 방문하지 않은 정점인가 
				// 2. 현재 도시와 c도시가 연결이 되었는가? 
				// 3. 위 조건을 만족하고 출발지에서 현재도시를 거쳐 c도시로 가는 비용이 c도시로 가는 비용보다 작다면 
				if(!visited[c]&&adjList[curr.vertex][c]>=0&&distance[c]>curr.totalDistance+adjList[curr.vertex][c]) {
					//출발지에서 c도시로 가는 비용을 갱신 시켜준다
					distance[c]=curr.totalDistance+adjList[curr.vertex][c];
					//큐에 삽입
					queue.offer(new Node(c, distance[c]));
				}	
			}
			//System.out.println(Arrays.toString(distance));
		}
		//도착지까지의 거리를 출력
		System.out.println(distance[end]);
		
		br.close();
	}
}
