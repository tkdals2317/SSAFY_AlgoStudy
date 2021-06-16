import java.util.*;
import java.io.*;

public class Main_G4_20007_떡돌리기_이상민_solved {
	// N : 집의 개수, M : 양방향 도로의 개수, X : 최대 갈수 있는 거리, Y : 출발지
	static int N, M, X, Y;

	static class Node implements Comparable<Node> {
		int vertex; // 점
		int totalDistance; // 점까지의 거리

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
		System.setIn(new FileInputStream("res/input_bj_20007.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // N : 집의 개수(정점의 개수)
		M = Integer.parseInt(st.nextToken()); // M : 양방향 도로의 개수(간선의 개수)
		X = Integer.parseInt(st.nextToken()); // X : 최대 갈수 있는 거리
		Y = Integer.parseInt(st.nextToken()); // Y : 출발지

		final int INF = Integer.MAX_VALUE;
		// 인접 행렬
		int[][] matrix = new int[N][N];
		// 거리를 갱신할 배열
		int[] distance = new int[N];
		// 방문처리 배열
		boolean[] visited = new boolean[N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			// 왕복거리임으로 *2
			matrix[from][to] = weight*2;
			matrix[to][from] = weight*2;
		}
		
		// 처음엔 무한대로 초기화
		Arrays.fill(distance, INF);      
		// 시작점은 0으로 초기화
		distance[Y] = 0;

		PriorityQueue<Node> queue = new PriorityQueue<Node>();

		queue.offer(new Node(Y, distance[Y]));

		while (!queue.isEmpty()) {
			Node current = queue.poll();
			// 이미 방문한 정점이라면 continue
			if (visited[current.vertex])
				continue;
			// 방문처리
			visited[current.vertex] = true;

			for (int c = 0; c < N; c++) {
				//경유지(c)를 들리는 방법이 이전에 구해놓은 최소거리보다 짧다면
				if(!visited[c]&&matrix[current.vertex][c]!=0&&
					distance[c]>current.totalDistance+matrix[current.vertex][c]) {
					//최소 거리 갱신
					distance[c]=current.totalDistance+matrix[current.vertex][c];
					queue.offer(new Node(c, distance[c]));
				}
			}
		}
		//오름차순으로 정렬
		Arrays.sort(distance);
		//System.out.println(Arrays.toString(distance));
		
		System.out.println(visit(distance));
				
		br.close();
	}
	static int visit(int [] distance) {
		if(distance[N-1]>X) return -1;
		int cnt = 0;
		int sum = 0;
		int index = 0;
		while(index<N) {
			//더할수 있을때까지 더하자
			while(index<N&&sum+distance[index]<=X) {
				sum +=distance[index++];
			}
			//만약 최대거리까지 갔다면 날자 하루 증가, sum은  0으로 초기화
			cnt++;
			sum = 0;
		}
		return cnt;
	}
}
