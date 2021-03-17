import java.io.*;
import java.util.*;


/*
 * 1. 연결리스트 생성
 * 2. 2가지 구역으로 구분
 * 3. 각 구역이 연결되어 있는지 확인
 * 4. 각 구역의 인구 수의 합의 차이를 구함
 * 5. 구한 차이값 중 최소값을 출력
 */

public class Main_bj_17471_게리맨더링_정소영_solved {
	
	static Node[] adjList;
	static int N;
	static boolean[] isSelected;
	static boolean[] visited;
	static int[] people; 
	static int p1, idxp1, cntp1=0;
	static int p2, idxp2, cntp2=0;
	static int res=Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		people = new int[N+1];
		adjList = new Node[N+1];
		isSelected = new boolean[N+1];
		
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i < N+1; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken());
			for (int j = 0; j < num; j++) {
				int nxt = Integer.parseInt(st.nextToken());
				adjList[i] = new Node(nxt, adjList[i]);
				adjList[nxt] = new Node(i, adjList[nxt]);
			}
		}	
		// 1. 연결리스트 생성
		
		
		// 2. 2가지 구역으로 구분
		generateSubset(1);
		if(res==Integer.MAX_VALUE) System.out.println(-1);
		// 만약 res가 int의 max 값 그대로라면 한번도 구역이 제대로 나뉜 적이 없다는 뜻으로 -1 출력
		else System.out.println(res);
		br.close();
		
	}
	
	private static void generateSubset(int cnt) {
		// 2가지 구역으로 구분하기 위한 부분 집합 함수
		if(cnt==N) {
			p1=0;
			p2=0;
			
			for (int i = 1; i < N+1; i++) {
				if(isSelected[i])	{	// isSelected가 true라면 구역1
					p1++;				// 구역1에 해당하는 갯수를 p1에 저장
					idxp1 = i;			// 마지막으로 구역1에 해당하는 index를 idxp1에 저장
				}
				else {					// isSelected가 false라면 구역2
					p2++;				// 구역2에 해당하는 갯수를 p2에 저장
					idxp2 = i;			// 마지막으로 구역2에 해당하는 index를 idxp2에 저장
				}
			}
			if(p1 != 0 && p1 != N) {	
				// 만약 구역1에 해당하는 갯수인 p1이 0개이거나 N개라면 한 구역에 모두 쏠려있다는 경우이므로 이 경우는 제외
				
				// dfs로 구역1 탐색
				visited = new boolean[N+1];
				cntp1 = 0;		// visted가 true인 부분을 체크하면 탐색된 부분의 길이를 알 수 있음	
				isRight(idxp1);		
				
				
				// dfs로 구역2 탐색
				visited = new boolean[N+1];
				cntp2 = 0;		// visted가 true인 부분을 체크하면 탐색된 부분의 길이를 알 수 있음	
				isRight2(idxp2);
				
				
				// 구역1의 갯수 p1과 dfs로 탐색하였을 때 방문한 곳의 갯수가 같으면 한 구역으로 연결되어 있다는 뜻.
				if(cntp1==p1 && cntp2==p2) {
					getResult();	// 두 구역의 인구수 차이 구하는 함수
				}
			}
			return;
		}
		
		isSelected[cnt] = true;
		generateSubset(cnt+1);
		
		isSelected[cnt] = false;
		generateSubset(cnt+1);
	}
	
	
	private static void getResult() {	// 두 구역의 인구수 차이 구하는 함수
		int tmp=0;
		for (int i = 1; i < N+1; i++) {
			if(isSelected[i])	tmp += people[i];	// 구역 1이라면 더하기
			else tmp -= people[i];		// 구역 2라면 빼기
		}
		
		res = Math.min(res, Math.abs(tmp));	// 두구역의 차이의 절댓값과 res를 비교해 min 값 저장
	}

	
	private static void isRight(int current) {	// 구역1의 dfs 코드
		visited[current] = true;
		cntp1++;
		for (Node temp = adjList[current];temp!=null;temp=temp.next) {
			if(!visited[temp.vertex] && isSelected[temp.vertex]) {
				isRight(temp.vertex);
			}
		}
	}
	
	
	private static void isRight2(int current) {	// 구역2의 dfs 코드
		visited[current] = true;
		cntp2++;
		for (Node temp = adjList[current];temp!=null;temp=temp.next) {
			if(!visited[temp.vertex] && !isSelected[temp.vertex]) {
				isRight2(temp.vertex);
			}
		}
	}
	

	static class Node{	// 연결리스트를 위한 Node 생성
		int vertex;
		Node next;
		public Node(int vertex, Node next) {
			super();
			this.vertex = vertex;
			this.next = next;
		}
		public Node(int vertex) {
			super();
			this.vertex = vertex;
		}
		
	}

}
