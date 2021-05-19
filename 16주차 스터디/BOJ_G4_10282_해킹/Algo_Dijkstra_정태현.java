package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * Dijkstra 알고리즘
 * 시작 점에서 거리가 최소인 정점을 선택하면서 최단 경로를 구하는 알고리즘
 * 탐욕 기법을 사용
 */
public class Algo_Dijkstra {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		//정점 개수 입력 0,1,2...V-1
		int V = Integer.parseInt(br.readLine());
		//출발점 0
		int start = 0;
		//도착점 V-1
		int end = V-1;
		//인접배열
		int[][] adjMatrix = new int[V][V];
		//2차원 배열 형태 인접배열 생성
		for (int i = 0; i < V; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < V; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//최소비용 저장 배열: 출발점부터 n번 노드까지 최단거리 저장
		int[] distance = new int[V];
		//방문처리
		boolean[] visited = new boolean[V];
		//계속 최소값으로 업데이트가 되니까 기존 상태값은 MAX_VALUE로 준다
		Arrays.fill(distance, Integer.MAX_VALUE);
		//출발지 노드는 0, 자기 자신과의 거리는 0
		distance[start] = 0;
		
		for (int i = 0; i < V; i++) {
			//1번에서 최소값을 찾기 위한 변수
			int min = Integer.MAX_VALUE;
			//현재 정점을 관리하기 위한 변수
			int current = 0;
			//1. 처리하지 않은 정점 중 출발지로부터 가장 가까운 정점 선택
			for (int j = 0; j < V; j++) {
				//방문하지 않은 상태인 것 중 최소를 찾아나간다
				if(!visited[j] && min>distance[j]) {
					min = distance[j];
					current = j;
				}
			}
			visited[current] = true;
			
			//만약 최종 도착지만 보고 싶다면
			if(current == end) break;
			
			//2. 선택 정점을 경유지로 해서 아직 비선택된 다른 정점으로의 최소비용 따진다
			for (int j = 0; j < V; j++) {
				//방문하지 않았고, 본인이 아니어야 or 갈 수 있어야
				//출발점부터 기존 정점으로 가는 거리보다 거쳐서 가는게 더 짧다면
				//출발점부터 current까지의 거리(min)와 current부터 j점까지의 거리 합이 출발점부터 j점까지의 거리보다 작다? 그럼 거쳐가는 게 이득
				if(!visited[j] && adjMatrix[current][j] != 0 && distance[j] > min + adjMatrix[current][j]) {
					distance[j] = min + adjMatrix[current][j];
				}
			}
		}
		
		System.out.println(distance[end]);
	}
}
