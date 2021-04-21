import java.util.*;
import java.io.*;

class Solution_LV3_합승택시요금_이상민_solved {
	/*
	 * n : 지점의 개수, s : 출발지점, a : a의 도착지점, b : b의 도착지점, fares[][] : from, to, fare로
	 * 이뤄진 간선리스트
	 */
	static final int INF = 9999999;

	public int solution(int n, int s, int a, int b, int[][] fares) {
		int answer = 0;
		// 각 정점간의 모든 최소비용을 저장할 배열
		int[][] distance = new int[n + 1][n + 1];
		for (int i = 0; i <= n; i++) {
			// INF값으로 초기화
			Arrays.fill(distance[i], INF);
			// 자기자신은 0으로 초기화
			distance[i][i] = 0;
		}

		for (int i = 0; i < fares.length; i++) {
			int from = fares[i][0];
			int to = fares[i][1];
			int fare = fares[i][2];
			distance[from][to] = fare;
			distance[to][from] = fare;

		}
		for (int k = 1; k <= n; ++k) { // 경유지
			for (int i = 1; i <= n; ++i) { // 출발지
				if (i == k)
					continue; // 출발지와 경유지가 같다면 다음 출발지
				for (int j = 1; j <= n; ++j) { // 도착지
					if (i == j || k == j)
						continue; // 경유지와 목적지가 같거나 출발지가 곧 목적지라면 패스
					// 경유지와 출발지가 연결되있고 경유지와 목적지가 연결되있을때 직접가는것보다 경유지를 거쳤을 때 더 가깝다면 거리 갱신
					if (distance[i][k] != INF && distance[k][j] != INF
							&& distance[i][j] > distance[i][k] + distance[k][j]) {
						distance[i][j] = distance[i][k] + distance[k][j];
					}
				}
			}

		}
		/*
		 * for(int i=1; i <= n; i++){ for(int j = 1; j<=n;j++){
		 * System.out.print(distance[i][j]+" "); } System.out.println(); }
		 */
		// 처음에는 시작지에서 a b로 직접갈때로 초기화
		answer = distance[s][a] + distance[s][b];
		for (int i = 1; i <= n; i++) {
			// 모든 경유지에 각 경유지를 거치는 것이 더 거리가 짧다면 갱신
			answer = Math.min(answer, distance[s][i] + distance[i][a] + distance[i][b]);
		}
		return answer;
	}

}