package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_G5_1916_최소비용구하기_정태현 {
	static int N, M;
	static int[][] map;
	static int[] dist;
	static int INF = 1000000001;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine()); //도시 수
		M = Integer.parseInt(br.readLine()); //버스 수
		map = new int[N][N];
		dist = new int[N];
		for(int i=0;i<N;i++){
			dist[i] = INF;
			for(int j=0;j<N;j++)
				map[i][j] = INF;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			
			if(map[r][c] > w) {
				map[r][c] = w;
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int from = Integer.parseInt(st.nextToken()) - 1;
		int to = Integer.parseInt(st.nextToken()) - 1;
		
		dijk(from);
		
		System.out.println(dist[to]);
		
	}

	private static void dijk(int start) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.offer(start);
		map[start][start] = 0;
		dist[start] = 0;
		int i, before;
		while(!pq.isEmpty()){
			before = pq.poll();
			for(i=0;i<N;i++){
				if(dist[i]>map[before][i]+dist[before]){
					dist[i] = map[before][i]+dist[before];
					pq.offer(i);
				}
			}
		}
	}

}
