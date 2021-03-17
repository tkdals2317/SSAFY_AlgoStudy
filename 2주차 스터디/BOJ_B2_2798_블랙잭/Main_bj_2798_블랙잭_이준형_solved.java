package baekjun;
import java.io.*;
import java.util.*;

public class Main_bj_2798_블랙잭_구미_4_이준형 {

	static int M,N;
	static int max=0;
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		StringTokenizer st2 = new StringTokenizer(br.readLine());
		int[] bae = new int[N];
		for (int i = 0; i < N; i++) {
			bae[i] = Integer.parseInt(st2.nextToken());
		}
		
		check(bae,0,0,0);
		
		System.out.println(max);
		
	}

	static void check(int[] bae, int i,int cnt,int use_cnt) {
		if(use_cnt==3) {
			if(cnt>max&&cnt<=M) {
				max=cnt;
			}
			return;
		}
		if(i==N) {
			return;
		}
		
		check(bae, i+1, cnt+bae[i],use_cnt+1);
		check(bae,  i+1, cnt,use_cnt);
	}


}
