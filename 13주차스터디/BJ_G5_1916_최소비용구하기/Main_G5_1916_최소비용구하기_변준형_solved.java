import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_G5_1916_최소비용구하기_변준형_solved {
	private static int N, M, ans;
	private static final int INF = Integer.MAX_VALUE;
	private static ArrayList<Node>[] adjList;
	private static int[] cost;
	private static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		adjList = new ArrayList[N + 1];
		cost = new int[N + 1];
		visited = new boolean[N + 1];
		ans = 0;

// 인접리스트 초기화
		for (int i = 0; i < N + 1; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			adjList[start].add(new Node(end, cost));
		}
		st = new StringTokenizer(br.readLine());
		int startPos = Integer.parseInt(st.nextToken());
		int endPos = Integer.parseInt(st.nextToken());

		ans = dijkstra(startPos, endPos);

		bw.write(ans + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static int dijkstra(int start, int end) {
// 여기서 선언하면 sample data가 여러개라도 매번 queue clear 할 필요 없음
		PriorityQueue<Node> pq = new PriorityQueue<>();

		for (int i = 1; i <= N; i++) {
			cost[i] = INF;
		}
		Arrays.fill(visited, false);
		cost[start] = 0;

		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node now = pq.poll();

// 간선 가중치가 변하는 문제라면 visited 대신 이걸 써야 함
// if (cost[now.dest] < now.cost) continue;

			if (visited[now.dest])
				continue;
			visited[now.dest] = true;

			for (Node next : adjList[now.dest]) {
// now.cost 자체가 이미 now.dest까지의 현재 기준 누적 최소 cost 이기 때문에
// 굳이 now.cost + cost[now.dest] 로 할필요 없음
				if (cost[next.dest] > next.cost + now.cost) {
					cost[next.dest] = next.cost + now.cost;
					pq.add(new Node(next.dest, cost[next.dest]));
				}
			}
		}
		return cost[end];
	}

	private static class Node implements Comparable<Node> {
		int dest, cost;

		public Node(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
}