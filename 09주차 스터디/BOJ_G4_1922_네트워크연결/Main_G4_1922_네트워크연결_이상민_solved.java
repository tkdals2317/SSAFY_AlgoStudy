import java.util.*;
import java.io.*;

public class Main_G4_1922_네트워크연결_이상민_solved {
	static int V, E;
	static int [] parents; 
	static Edge[] edgeList; //간선 정보를 저장할 리스트
	static class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
	}
	static void make() {
		//정점 개수만큼
		for (int i = 0; i <= V; i++) {
			parents[i] = i;
		}
	}
	//부모 찾기
	static int findSet(int a) {
		if(parents[a]==a) return a;
		return parents[a] = findSet(parents[a]);
	}
	//두 정점 연결하기
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;	
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_1922.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine()); //컴퓨터(정점)의 수
		E = Integer.parseInt(br.readLine()); //연결할수 있는 선(간선)의 수
		parents = new int[V+1];
		edgeList = new Edge[E];
		
		for (int i = 0; i < E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);
		}
		//가중치 기준으로 오름차순 정렬
		Arrays.sort(edgeList);
		
		
		make();
		//가중치의 합
		int result = 0;
		//선택된 간선의 수
		int count = 0;
		//간선 정보를 하나 뽑아서
		for (Edge edge : edgeList) {
			//union 시켰을 때 부모가 다른 경우 합치고 가중치를 결과값에 더해준다.
			//만약 같은 부모일 경우(즉, 싸이클이 발생하는 경우) union의 반환값 false로 인해 자동으로 넘어감
			if(union(edge.from, edge.to)) {
				result += edge.weight;
				//만약 선택된 간선의 수가 (정점의 개수-1)과 같아지면 더 이상 연결하지 않아도 되므로 break 
				if(++count==V-1) break;
			}
		}
		System.out.println(result);
		
		br.close();
	}//end of main
	
}//end of class
