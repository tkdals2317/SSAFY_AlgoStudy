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
			int c = Integer.parseInt(st.nextToken()) - 1;
			//리스트를 인접행렬기능을 하게 사용 => 인접리스트
			List<int[]> list[] = new ArrayList[n];
			for (int i = 0; i < n; i++) { 
				list[i] = new ArrayList<int[]>(); 
			}
			
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				int s = Integer.parseInt(st.nextToken());
				//a는 b에 의존
				list[b].add(new int[] {a, s});
			}
			
			PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[1] - o2[1];
				}
			});
			
			//각 정점으로의 최단 거리
			int[] dist = new int[n];
			//출발점 본인
			Arrays.fill(dist, Integer.MAX_VALUE);
			dist[c] = 0;
			
			pq.offer(new int[] {c, 0});
			
			while(!pq.isEmpty()) {
				int[] cur = pq.poll();
				//현재 번호, 가중치
				int now_num = cur[0];
				int now_val = cur[1];
				//의존하는 컴퓨터 개수만큼
				for (int i = 0; i < list[now_num].size(); i++) {
					//해당 컴퓨터의 정보를 list에서 get
					int[] next = list[now_num].get(i);
					int next_num = next[0];
					int next_val = next[1];
					//시간이 더 짧은 것을 계속 업데이트
					if(dist[next_num] > now_val + next_val) {
						dist[next_num] = now_val + next_val;
						pq.offer(new int[] {next_num, dist[next_num]});
					}
				}
			}
			
			int ans1 = 0; //총 감염 컴퓨터 수
			int ans2 = 0; //마지막 컴퓨터 감염까지 걸리는 시간
			for (int i = 0; i < n; i++) {
				if(dist[i] != Integer.MAX_VALUE) ans1++;
				//이 중 최대값을 저장
				if(dist[i] != Integer.MAX_VALUE && ans2<dist[i]) ans2 = dist[i];
			}
			
			System.out.println(ans1+ " "+ans2);
		
		}
		
	}
}
