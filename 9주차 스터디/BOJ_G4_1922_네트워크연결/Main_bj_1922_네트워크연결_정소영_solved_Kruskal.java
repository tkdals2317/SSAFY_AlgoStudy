import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


// Kruskal or Prim 전형적인 문제

public class Main_bj_1922_네트워크연결_정소영_solved_Kruskal {
	static int N, M;
	static int parents[];
	static Edge[] edgeList;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		parents = new int[N+1];
		edgeList = new Edge[M];
		
		for (int i = 0; i < M; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int weight = sc.nextInt();
			edgeList[i] = new Edge(from, to, weight);
		}
		
		Arrays.sort(edgeList);
		
		make();
		int result = 0;
		int count = 1;
		
		for(Edge edge:edgeList) {
			if(union(edge.from, edge.to)) {
				result+= edge.weight;
				if(++count == N) {
					break;
				}
			}
		}
		
		System.out.println(result);
		sc.close();
	}
	
	
	static void make() {
		for(int i=0;i<N+1; i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int a) {
		if(parents[a]==a) {
			return a;
		}
		return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) {
			return false;
		}
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	
	
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
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}
		
		
	}

}
