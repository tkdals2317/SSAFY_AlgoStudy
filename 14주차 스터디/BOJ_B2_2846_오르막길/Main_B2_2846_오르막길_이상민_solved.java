import java.util.*;
import java.io.*;

public class Main_B2_2846_오르막길_이상민_solved {
	static int N;
	static int nArr[];
	static int LIS[];
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2846.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nArr = new int[N];
		LIS = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			nArr[i] = Integer.parseInt(st.nextToken());
		}
		int max = 0;
		for (int i = 1; i < N; i++) {
			if(nArr[i]>nArr[i-1]) {
				LIS[i] = LIS[i-1]+nArr[i]-nArr[i-1];
				max = Math.max(max, LIS[i]);
			}else {
				LIS[i] = 0;
			}
		}	
		System.out.println(max);
		br.close();
	}

}
