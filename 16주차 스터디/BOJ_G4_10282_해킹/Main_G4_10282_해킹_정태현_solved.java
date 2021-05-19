package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_G4_10282_해킹_정태현_solved {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			//각 정점으로의 최단 거리
			int[] dist = new int[n];
			//리스트를 인접행렬기능을 하게 사용
			List<int[]> list[] = new ArrayList[n];
			for (int i = 0; i < n; i++) { 
				list[i] = new ArrayList<int[]>(); 
			}
			Arrays.fill(dist, Integer.MAX_VALUE);
			
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				int s = Integer.parseInt(st.nextToken());
				//a는 b에 의존
				list[b].add(new int[] {a, s});
			}
			//출발점 본인
			dist[c] = 0;
			
			PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[1] - o2[1];
				}
			});
			
			pq.offer(new int[] {c, 0});
			
			while(!pq.isEmpty()) {
				int[] cur = pq.poll();
				//현재 번호, 가중치
				int cur_num = cur[0];
				int cur_wei = cur[1];
				//의존하는 컴퓨터 개수만큼
				for (int i = 0; i < list[cur_num].size(); i++) {
					//해당 컴퓨터의 정보를 list에서 get
					int[] next = list[cur_num].get(i);
					int next_num = cur[0];
					int next_wei = cur[1];
					//거쳐서 가는게 더 짧다
					if(dist[next_num] > cur_wei + next_wei) {
						dist[next_num] = cur_wei + next_wei;
						pq.offer(new int[] {next_num, dist[next_num]});
					}
				}
			}
			
			int ans1 = 0; //총 감염 컴퓨터 수
			int ans2 = 0; //마지막 컴퓨터 감염까지 걸리는 시간
			for (int i = 0; i < n; i++) {
				if(dist[i] != Integer.MAX_VALUE) ans1++;
				if(dist[i] != Integer.MAX_VALUE && ans2<dist[i]) ans2 = dist[i];
			}
		
		}
		
	}
}
