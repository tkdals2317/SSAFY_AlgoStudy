package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B2_2798_solved {
	static int N, M;
	static int MAX; //카드의 최대합
	static int[] a;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		a = new int[N];
		MAX = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0,0,0);
		System.out.println(MAX);
	}
	
	public static void dfs(int cnt, int csum, int cn) {
		if(csum > M) return; //카드의 합이 M을 넘어가버리면 return
		if(cn == 3) { //카드 3장 뽑으면 그 합을 구해서 MAX와 비교 후 최대값 지정한 뒤 return;
			MAX = Math.max(MAX, csum);
			return;
		}
		
		if(cnt == N) return;
		dfs(cnt+1, csum+a[cnt], cn+1);
		dfs(cnt+1, csum, cn);
	}
}
