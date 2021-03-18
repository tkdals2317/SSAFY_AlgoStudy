import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/13458
// => 단순 계산 문제, 그러나 자료형에 유의
public class Main_bj_13458_시험감독_정소영_solved {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] a = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), " ");
		int B = Integer.parseInt(st.nextToken());
		double C = Integer.parseInt(st.nextToken());
		
		long sum = 0;
		// 만약 N*a 가 int 범위를 훨씬 넘을 수 있음
		for (int i = 0; i < N; i++) {
			if(a[i]<=B) {
				sum += 1;
			} else {
				sum += Math.ceil((a[i]-B)/C)+1;
			}
		}
		System.out.println(sum);
	}

}
