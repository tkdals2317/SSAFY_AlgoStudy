package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_G4_1922_네트워크연결_정태현_solved {
	static int[] parents;
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
//			return this.weight - o.weight;
		}
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	static int findSet(int a) {
		if(parents[a] == a) return a;
		return findSet(parents[a]);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
		
        Edge[] edgelist = new Edge[M];
        parents = new int[N+1];
        
        for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edgelist[i] = new Edge(a, b, c);
		}
        
        Arrays.sort(edgelist);
        
        
        for (int i = 0; i < N + 1; i++) { //make
            parents[i] = i;
        }
        
        int result = 0;
        int cnt = 0;
        
        for(Edge edge: edgelist) {
        	if(union(edge.from, edge.to)) {
        		result += edge.weight;
        		if(++cnt == N) break;
        	}
        }
        
        System.out.println(result);
	}
}