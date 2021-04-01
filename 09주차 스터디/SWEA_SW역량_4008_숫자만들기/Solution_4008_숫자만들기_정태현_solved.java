package algorithm.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4008_숫자만들기_정태현_solved {
	static int N, max, min;
	static int[] num, opr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); //숫자 개수
			num = new int[N]; //숫자 넣을 배열
			opr = new int[4];
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < opr.length; i++) {
				opr[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			
			bf(1, 0, num[0]);
			int res = max - min;
			System.out.println("#"+tc+" "+res);
		}
	}
	
	private static void bf(int cnt, int idx, int ans) {
		if(cnt == N) { //연산자 다 뽑음
			max = Math.max(max, ans);
			min = Math.min(min, ans);
		}
		
		for (int i = 0; i < opr.length; i++) {
			if(opr[i] > 0) {
				if(i==0) { // +
					opr[i]--; //1개처리, -1
					bf(cnt+1, idx+1, ans + num[idx+1]);
					//처음 지정한 값 ans에 그 다음 숫자와 + 연산자 처리
					opr[i]++;
				}else if(i==1) { // -
					opr[i]--; //1개처리, -1
					bf(cnt+1, idx+1, ans - num[idx+1]);
					//처음 지정한 값 ans에 그 다음 숫자와 - 연산자 처리
					opr[i]++;
				}else if(i==2) { // *
					opr[i]--; //1개처리, -1
					bf(cnt+1, idx+1, ans * num[idx+1]);
					//처음 지정한 값 ans에 그 다음 숫자와 * 연산자 처리
					opr[i]++;
				}else {
					opr[i]--; //1개처리, -1
					bf(cnt+1, idx+1, ans / num[idx+1]);
					//처음 지정한 값 ans에 그 다음 숫자와 / 연산자 처리
					opr[i]++;
				}
			}
		}
	}
}