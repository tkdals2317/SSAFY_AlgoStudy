

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main_bj_2846_오르막길_구미_4_정소영 {
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] loads = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			loads[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(climb(N, loads));
		
		
		br.close();
		
	}

	private static int climb(int n, int[] loads) {
		int result = 0;
		int start = loads[0];
		int end = 0;
		
		for (int i = 1; i < n; i++) {
			if(loads[i] > loads[i-1]) {
				end = loads[i];
				result = Math.max(end-start, result);
			} else {
				start = loads[i];
			}
		}
		
		
		
		return result;
	}

}
