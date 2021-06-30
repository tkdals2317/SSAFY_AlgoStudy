package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Soulition_programmers_level3_합승택시요금 {
	private static ArrayList<Node>[] list;

	public static void main(String[] args) {
		int n = 7, s = 4, a = 6, b = 2;
		int[][] fares = new int[][] { { 4, 1, 10 }, { 3, 5, 24 }, { 5, 6, 2 }, { 3, 1, 41 }, { 5, 1, 24 }, { 4, 6, 50 },
				{ 2, 4, 66 }, { 2, 3, 22 }, { 1, 6, 25 } };

		////////////////////////////////////////////////////////

		list = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++)
			list[i] = new ArrayList<>();

		for (int i = 0; i < fares.length; i++) {
			int start = fares[i][0]; // 출발지
			int end = fares[i][1]; // 목적지
			int cost = fares[i][2]; // 비용

			// 양방향 이동 포함해서 그래프 생성
			list[start].add(new Node(end, cost));
			list[end].add(new Node(start, cost));
		}

		int[] dS = new int[n + 1]; // 출발지에서 합승지점까지
		int[] dA = new int[n + 1]; // 합승지점에서 A까지
		int[] dB = new int[n + 1]; // 합승지점에서 B까지

		dS = dijk(s, dS); // 다익스트라 알고리즘을 각 정점까지 최단거리 계산
		dA = dijk(a, dA);
		dB = dijk(b, dB);

		int answer = Integer.MAX_VALUE;
		for (int i = 1; i <= n; i++) 
			answer = Math.min(answer, dS[i] + dA[i] + dB[i]); // 정답 추출

		System.out.println(answer);
	}

	static int[] dijk(int start, int[] dist) {
		Arrays.fill(dist, Integer.MAX_VALUE); // 거리 배열을 최댓값으로 초기화
		dist[start] = 0; // 시작 비용 0
		PriorityQueue<Node> q = new PriorityQueue<>(); // 큐에 넣을 횟수를 최소화 시키기 위해 pq 사용
		q.offer(new Node(start, 0));

		while (!q.isEmpty()) {
			Node node = q.poll();
			for (int i = 0; i < list[node.to].size(); i++) { // 현재 노드에서 갈 수 있는 모든 노드 만큼
				int to = list[node.to].get(i).to; // 다음 목적지
				int cost = list[node.to].get(i).cost; // 다음 목적지까지 비용

				if (dist[to] > node.cost + cost) { //더 작으면 갱신하고 큐에 넣음
					dist[to] = node.cost + cost;
					q.offer(new Node(to, dist[to]));
				}
			}
		}
		return dist;
	}
}

class Node implements Comparable<Node> {
	int to; // 목적지
	int cost; // 비용

	Node(int to, int cost) {
		this.to = to;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return cost - o.cost;
	}
}