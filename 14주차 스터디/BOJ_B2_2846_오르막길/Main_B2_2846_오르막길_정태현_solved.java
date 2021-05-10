package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B2_2846_오르막길_정태현 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		int[] road = new int[N+1];
		ArrayList<Integer> lis = new ArrayList<>();
		st = new StringTokenizer(br.readLine()," ");
		//길정보 입력
		for (int i = 0; i < N; i++) {
			road[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			
		}
		
		int idx = 0;
		int max = Integer.MIN_VALUE;
		//LIS : https://chanhuiseok.github.io/posts/algo-49/
		while(idx < N) {
			//비어있을 경우 해당 인덱스의 값을 넣어서 시작
			if(lis.size()==0) lis.add(road[idx]);
			//그 다음 칸이 더 작으면
			if(road[idx+1] <= road[idx]) {
				//끝에서 처음 뺀값
				max = Math.max(max, lis.get(lis.size()-1)-lis.get(0));
				lis.clear();
				idx++;
			} else {
				lis.add(road[++idx]);
			}
		}
		
		System.out.println(max);
	}
}
