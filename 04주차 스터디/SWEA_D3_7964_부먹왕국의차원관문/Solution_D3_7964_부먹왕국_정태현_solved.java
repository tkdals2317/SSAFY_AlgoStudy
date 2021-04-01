package algorithm.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D3_7964_solved {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			int[] a = new int[N];

			int cnt = 0;
			int idx = 0;
			int cont = D - 1;
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++) {
				a[i] = Integer.parseInt(st.nextToken());
				if(a[i] == 1)
					idx = i;
			}
			
			for(int i = idx; i >= 0; i--) {
				if(a[i] == 1) {
					cont = D - 1;
				}else if(cont == 0) {
					a[i] = 1;
					cnt++;
					cont = D -1;
				}else {
					cont--;
				}
			}
			
			for(int i = idx; i < N; i++) {
				if(a[i] == 1) {
					cont = D - 1;
				}else if(cont == 0) {
					a[i] = 1;
					cnt++;
					cont = D - 1;
				}else {
					cont--;
				}
			}
			System.out.println("#" + tc + " " + cnt);
		}
	}
}