package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B2_13458_solved {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] a = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		long cnt = 0;
		
		for(int i=0; i<N; i++) {
			if(a[i]>=b) {
				a[i] = a[i] - b;
				cnt++;
			}else {
				a[i] = 0;
				cnt++;
			}
			if(a[i]<=0) continue;
			
			if(a[i]%c==0) {
				cnt += a[i]/c;
			}else {
				cnt += a[i]/c;
				cnt++;
			}
		}
		
		System.out.println(cnt);
		
	}

}






