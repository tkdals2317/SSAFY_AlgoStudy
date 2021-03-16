import java.util.*;
import java.io.*;
/**
 * 공평하게 선거구를 나누기 위해 두 선거구의 인구 차이를 최소하여, 최솟값을 출력
 * 입력 : 
 * 1번째 줄 구역의 개수 N
 * 2번째 줄 1번 구역부터 N번 구역까지 구역별 인구수가 순서대로 주어짐
 * 3번째 줄 각 N개의 구역의 인접한 구역의 정보가 주어짐 첫번째 정수 인접구역의 수, 그 뒤로는 인접한 구역의 번호
 * 
 * 조건
 * 1. 구역은 두개의 선거구로 나눠져야 함
 * 2. 각 구역은 두 선거구 중 하나에 반드시 포함되어야 함
 * 3. 선거구는 최소 하나의 구역을 가진다
 * 4. 구역 A가 구역 B가 인접하면 구역 B도 A와 인접(무향 그래프)
 * 5. 인접한 구역은 0개 이상이여야 하고 모두 같은 선거구에 포함되어야 함
 * 
 * 접근 방법 : PowerSet, 무향 그래프
 * 
 * @author tkdal
 */
public class Main_G5_17471_게리맨더링_이상민_X {
	static int N;
	static int[] population;
	static ArrayList<Integer>[] adj;
	static int min = Integer.MAX_VALUE;
 	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_bj_17471.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		population = new int[N+1]; 
		adj = new ArrayList[N+1];

		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
			adj[i] = new ArrayList<Integer>();
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int adjNum = Integer.parseInt(st.nextToken());
			for (int j = 0; j < adjNum; j++) {
				int from = i;
				int to = Integer.parseInt(st.nextToken());
				adj[from].add(to);
			}
		}
		int adjSize = 0;
		for (int i = 1; i <= N; i++) {
			//System.out.println(i+" 구역 인구수"+population[i]+", 인접 도시"+adj[i]);	
			adjSize += adj[i].size();
		}
/*		if(adjSize==0) {
			System.out.println(-1);
		}else {
			for (int i = 1; i <= N; i++) {
				bfs(i);				
			}
			System.out.println(min);
		}*/
		for (int i = 1; i <= N; i++) {
			bfs(i);				
		}
		System.out.println(min);
		br.close();
	}
 	static void bfs(int start) {
 		ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
 		boolean [] v = new boolean[N+1];
 		
 		queue.offer(start);
 		v[start] = true;
 		check(v);
 		while(!queue.isEmpty()) {
 			int current = queue.poll();
 			for (int i = 1; i < adj[current].size(); i++) {
				if(!v[adj[current].get(i)]) {
					queue.offer(adj[current].get(i));
					v[adj[current].get(i)] = true;
					check(v);
				}
			}
 		}
 	}
 	static void check(boolean[]v) {
 		int sum1 = 0;
 		int sum2 = 0;
 		for (int i = 1; i <= N; i++) {
			if(v[i]) sum1 += population[i];
			else sum2 += population[i];
		}
 		min = Math.min(min, Math.abs(sum1-sum2));
 	}
}
