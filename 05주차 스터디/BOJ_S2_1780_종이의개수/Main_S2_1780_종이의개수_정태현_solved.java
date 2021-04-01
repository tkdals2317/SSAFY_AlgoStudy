package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_S2_1780_종이의개수_정태현_solved {
	static int[][] a;
	static int N;
	static int one = 0, zero = 0, minus = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		a = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		divide(0,0,N);
		
		System.out.println(minus);
		System.out.println(zero);
		System.out.println(one);
	}
	
	public static void divide(int si, int sj, int dN) {
		int n1 = 0, n0 = 0, nm1 = 0;
		
		for(int i=si; i<si+dN; i++) {
			for(int j=sj; j<sj+dN; j++) {
				if(a[i][j] == 1) n1++;
				else if(a[i][j] == 0) n0++;
				else nm1++;
			}
		}
		
		if(n1 == dN*dN) { //모두 1
			one++;
			return;
		}else if(n0 == dN*dN) { //모두 0
			zero++;
			return;
		}else if(nm1 == dN*dN) {
			minus++;
			return;
		}else {
			divide(si, sj, dN/3);
			divide(si+dN/3, sj, dN/3);
			divide(si+2*dN/3, sj, dN/3);
			divide(si, sj+dN/3, dN/3);
			divide(si, sj+2*dN/3, dN/3);
			divide(si+dN/3, sj+dN/3, dN/3);
			divide(si+dN/3, sj+2*dN/3, dN/3);
			divide(si+2*dN/3, sj+dN/3, dN/3);
			divide(si+2*dN/3, sj+2*dN/3, dN/3);
		}
		
		return;
	}
}
