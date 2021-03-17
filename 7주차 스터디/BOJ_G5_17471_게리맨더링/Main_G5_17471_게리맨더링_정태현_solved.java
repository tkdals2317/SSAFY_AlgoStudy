package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G5_17471_게리맨더링_정태현_solved {
	static int N, ans;
	static int[] pop, area; //인구
	static int[][] graph; //연결선
	static boolean visited[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); //선거구역수
		ans = Integer.MAX_VALUE;
		pop = new int[N+1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			pop[i] = Integer.parseInt(st.nextToken());
		}
		
		graph = new int[N+1][N+1];
		area = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int Ccnt = Integer.parseInt(st.nextToken()); //연결된 갯수
			for (int j = 1; j <= Ccnt; j++) {
				int node = Integer.parseInt(st.nextToken());
				graph[i][node] = 1;
				graph[node][i] = 1; //인접행렬 
			}
		}
		
		dfs(1);
		
		if(ans == Integer.MAX_VALUE) { //ans 값이 변하지 않았다면 구역 못나눈것
			System.out.println(-1);
		} else {
			System.out.println(ans);
		}
	}
	private static void dfs(int cnt) {
		if(cnt == N+1) {
			int a1 = 0, a2 = 0;
			for (int i = 1; i <= N; i++) { //구역체크후 1이면 a1에 인구값+, 2면 a2에 인구값+
				if(area[i] == 1) a1 += pop[i];
				else a2 += pop[i];
			}
			twoSector(a1, a2);
			
			return; //구역검증 끝 or 구역이 2개가 아니라면 dfs로 다시 구역 설정
		}
		
		area[cnt] = 1; //구역 1 설정
		dfs(cnt+1);
		area[cnt] = 2; //구역 2 설정
		dfs(cnt+1);
	}
	
	private static void twoSector(int a1, int a2) {
		visited = new boolean[N+1];
		
		int AreaNCnt = 0;
		for (int i = 1; i <= N; i++) {
			if(!visited[i]) {
				SectorCheck(i, area[i]);
				AreaNCnt++; //한구역 끝나면 ++
			}
		}
		if(AreaNCnt == 2) { //구역이 2개인가
			ans = Math.min(ans, Math.abs(a1 - a2));
		}
		
	}
	private static void SectorCheck(int num, int num2) {
		visited[num] = true;
		
		for (int i = 1; i <= N; i++) {
			if(graph[num][i] == 1 && !visited[i]) //연결되어 있는가, 방문하지 않았나
				if(area[i] == num2) SectorCheck(i, num2); //같은 구역인가
		}
	}
	
}
