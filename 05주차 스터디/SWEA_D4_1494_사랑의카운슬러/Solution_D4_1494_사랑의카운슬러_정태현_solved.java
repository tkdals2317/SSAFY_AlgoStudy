package algorithm.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_1494_사랑의카운슬러_정태현_solved {
	static int[][] jr;
	static int N;
	static int[] numbers;
	static long min;
	static boolean[] isSelected;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			numbers = new int[N]; //지렁이 두마리 선택용
			jr = new int[N][2]; //지렁이 배열
			min = Long.MAX_VALUE; //최소값 출력용
			isSelected = new boolean[N]; //방문체크용
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				
				jr[i][0] = Integer.parseInt(st.nextToken());
				jr[i][1] = Integer.parseInt(st.nextToken());
			}
			
			perm(0, 0);
			
			System.out.println("#"+tc+" "+min);
		}
	}
	
	private static void perm(int cur, int cnt) {
		if(cnt == N/2) {
			int sctr = 0, sctc = 0;
			for (int i = 0; i < N; i++) {
				if(isSelected[i]) {
					sctr += jr[i][0];
					sctc += jr[i][1];
				}else {
					sctr -= jr[i][0];
					sctc -= jr[i][1];
				}
			}
			long ans = sctr*sctr + sctc*sctc;
			min = Math.min(min, ans);
		}
		
		for (int i = cur; i < N; i++) {
			if(isSelected[i]) continue;
			
			isSelected[i] = true;
			perm(i+1, cnt+1);
			isSelected[i] = false;
		}
	}
}