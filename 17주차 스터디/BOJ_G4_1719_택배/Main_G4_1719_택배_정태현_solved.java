package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_G4_1719_택배_정태현_solved {
	static int n, m;
	static int[][] adjMatrix, result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken()); //집하장 개수
		m = Integer.parseInt(st.nextToken()); //집하장 간 경로 개수
		
		adjMatrix = new int[n][n]; //인접행렬
		result = new int[n][n]; //결과출력
		
		int a, b, w;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			//1 ~ n => 0 ~ n-1
			a = Integer.parseInt(st.nextToken()) - 1;
			b = Integer.parseInt(st.nextToken()) - 1;
			w = Integer.parseInt(st.nextToken()); //가중치
			
			adjMatrix[a][b] = w;
			adjMatrix[b][a] = w;
		}
		
		//한 집하장을 시작점으로 모든 집하장의 경우를 다 봐야 하기 때문에 각 집하장을 시작점으로 다익스트라 n번
		for (int i = 0; i < n; i++) {
			dijkstra(i);
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(result[i][j] == 0) System.out.print("- ");
				else System.out.print(result[i][j]+" ");
			}
			System.out.println();
		}
	}

	private static void dijkstra(int start) {
		int[] dist = new int[n];
		boolean[] visited = new boolean[n];
		//최소값 업데이트 위해 무한으로 일단 초기화
		Arrays.fill(dist, Integer.MAX_VALUE);
		//출발지 노드 0
		dist[start] = 0;
		
		
		for (int i = 0; i < n; i++) {
			int min = Integer.MAX_VALUE;
			int cur = 0;
			
			//처리(visited)하지 않은 정점에서 start로부터 가장 가까운 정점 선택
			for (int j = 0; j < n; j++) {
				if(!visited[j] && min>dist[j]) {
					min = dist[j];
					cur = j;
				}
			}
			
			visited[cur] = true;
			//방문하지 않았고
			//본인이 아니고 or 갈수 있어야
			//출발점부터 current까지의 거리(min)와 current부터 j점까지의 거리 합이 출발점부터 j점까지의 거리보다 작다?
			for (int j = 0; j < n; j++) {
				if(!visited[j] && adjMatrix[cur][j] != 0 && dist[j] > min + adjMatrix[cur][j]) {
					dist[j] = min + adjMatrix[cur][j];
					//cur이 start => start 집하장부터 오는거고 중앙에 하나도 거치지 않는다 (start -> j)
					//따라서 해당 칸의 result는 현재 포인트인 j
					if(cur==start) result[start][j] = j + 1;
					//현재 최솟값 점 cur에서 오는 거라서 그냥 result의 cur번째 값을 저장해주면 됨
					else result[start][j] = result[start][cur];
				}
			}
		}
	}
}
