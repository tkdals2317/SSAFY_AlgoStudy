package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Soulition_BOJ_20007_떡돌리기 {
	static int N, M, X, Y;
	static boolean[] visited;
	static ArrayList<home>[] arr; 
	static PriorityQueue<home> pq = new PriorityQueue<>(); 
	static int[] dist;
	static final int max = Integer.MAX_VALUE;
	static int answer = 1;

	public static class home implements Comparable<home> {
		int number, dist;

		public home(int number, int dist) {
			super();
			this.number = number;
			this.dist = dist;
		}

		@Override
		public int compareTo(home o) {
			// TODO Auto-generated method stub
			return this.dist - o.dist;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());

		arr = new ArrayList[N];
		for (int i = 0; i < N; i++) 
			arr[i] = new ArrayList<>();
		visited = new boolean[N];
		dist = new int[N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			arr[from].add(new home(to, dist));
			arr[to].add(new home(from, dist));
		}

		dijkstra();
		Arrays.sort(dist);
		visit();
		
		System.out.println(answer);
	}


	private static void visit() {
		// TODO Auto-generated method stub
		int totaldist =0;
		for (int i = 0; i < dist.length; i++) {
			if ( dist[i]*2 > X ) {
				answer = -1;
				break;
			}
			
			totaldist += dist[i]*2;
			
			if ( totaldist > X ) {
				answer++;
				totaldist = dist[i]*2;;
			}
		}
	}

	private static void dijkstra() {
		Arrays.fill(dist, max);
		dist[Y] = 0;

		pq.add(new home(Y, 0));

		while (!pq.isEmpty()) {
			home cur = pq.poll();
			int curPos = cur.number;
			int curdist = cur.dist;
			visited[curPos] = true;

			for (int i = 0; i < arr[curPos].size(); i++) {
				int target = arr[curPos].get(i).number;
				int target_dist = arr[curPos].get(i).dist;

				if (!visited[target] && dist[target] > target_dist + curdist) {
					dist[target] = target_dist + curdist;
					pq.add(new home(target, dist[target]));
				}
			}
		}
	}
}