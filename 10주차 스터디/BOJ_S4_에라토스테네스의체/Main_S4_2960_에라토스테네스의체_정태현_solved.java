package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S4_2960_에라토스테네스의체_정태현_solved {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		boolean[] num = new boolean[N+1];
		int cnt = 0;
		for (int i = 2; i <= N; i++) {
			num[i] = true;
		}
		
		for (int i = 2; i <= N; i++) {
			int mul = 1;
			while(i * mul <= N) {
				if(num[i*mul]) {
					num[i*mul] = false;
					cnt++;
					if(cnt == K) {
						System.out.println(i*mul);
						System.exit(0);
					}
				}
				mul++;
			}
		}
	}
}
