package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_G4_20007_떡돌리기_정태현_solved {
	static int N, M, X, Y;
	static int[][] adjMatrix;
	static int[] dist;
	public static void main(String[] args) throws IOException {
		// Input tools -------------------
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		// Input tools -------------------
		
		
		// Variable ----------------------
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); //정점 개수
		M = Integer.parseInt(st.nextToken()); //간선 개수
		X = Integer.parseInt(st.nextToken()); //
		Y = Integer.parseInt(st.nextToken());
		adjMatrix = new int[N][N]; //인접행렬
		dist = new int[N]; //출발점부터 각 정점까지 최단 거리 저장 배열
		int a, b, w; //인접행렬 입력용
		// Variable ----------------------
		
		
		// Input -------------------------
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			adjMatrix[a][b] = w;
			adjMatrix[b][a] = w;
		}
		// Input -------------------------
		
		
		// Problem Solving ---------------
		dijkstra(Y);
		System.out.println(dayCnt());
		// Problem Solving ---------------
	}
	private static int dayCnt() {
		boolean[] visited = new boolean[N];
		visited[Y] = true; //우리 집
		
		int day = 0; //일 수
		int homecnt = 0; //방문한 집
		int cur = 0; //현재 왕복에 필요한 일 수
		
		//단 한개의 집을 들리는 경우가 X를 넘어버리면 모든 집을 돌기는 불가능, -1
		for (int i = 0; i < dist.length; i++) {
			if(dist[i] * 2 > X) return day = -1;
		}
		
		//방문한 집이 N-1(우리집 제외)개면 끝
		while(homecnt!=N-1) {
			int min = Integer.MAX_VALUE; //최소값
			int idx = 0; //최소값 인덱스
			
			//최소값 먼저 찾고
			for (int i = 0; i < dist.length; i++) {
				if(!visited[i] && dist[i] < min) {
					min = dist[i];
					idx = i;
				}
			}
			
			//왕복 거리
			cur += dist[idx] * 2;
			
			if(cur > X) { //왕복 거리가 X이상이면 하루 끝, day+1, cur 초기화
				day++;
				cur = 0;
			} else { //왕복 거리가 X이하면 그냥 visited 처리만, 방문한 집 개수 homecnt+1
				visited[idx] = true;
				homecnt++;
			}
		}
		
		return day+1;
	}
	
	private static void dijkstra(int start) {
		//방문처리
		boolean[] visited = new boolean[N];
		//계속 최소값으로 업데이트가 되니까 기존 상태값은 MAX_VALUE로 준다
		Arrays.fill(dist, Integer.MAX_VALUE);
		//출발지 노드는 0, 자기 자신과의 거리는 0
		dist[start] = 0;
		
		for (int i = 0; i < N; i++) {
			//1번에서 최소값을 찾기 위한 변수
			int min = Integer.MAX_VALUE;
			//현재 정점을 관리하기 위한 변수
			int current = 0;
			//1. 처리하지 않은 정점 중 출발지로부터 가장 가까운 정점 선택
			for (int j = 0; j < N; j++) {
				//방문하지 않은 상태인 것 중 최소를 찾아나간다
				if(!visited[j] && min>dist[j]) {
					min = dist[j];
					current = j;
				}
			}
			visited[current] = true;
			
			//2. 선택 정점을 경유지로 해서 아직 비선택된 다른 정점으로의 최소비용 따진다
			for (int j = 0; j < N; j++) {
				//방문하지 않았고, 본인이 아니어야 or 갈 수 있어야
				//출발점부터 기존 정점으로 가는 거리보다 거쳐서 가는게 더 짧다면
				//출발점부터 current까지의 거리(min)와 current부터 j점까지의 거리 합이 출발점부터 j점까지의 거리보다 작다? 그럼 거쳐가는 게 이득
				if(!visited[j] && adjMatrix[current][j] != 0 && dist[j] > min + adjMatrix[current][j]) {
					dist[j] = min + adjMatrix[current][j];
				}
			}
		}
		
	}
}
