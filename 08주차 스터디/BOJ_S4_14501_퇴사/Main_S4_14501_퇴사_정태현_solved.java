package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_S4_14501_퇴사_정태현_solved {
	static int N, ans;
	static int[][] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][2];
		ans = Integer.MIN_VALUE;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 0);
		System.out.println(ans);
	}

	private static void dfs(int cnt, int pay) {
		if(cnt == N) {
			ans = Math.max(ans, pay);
			return;
		}
		
		if(cnt+arr[cnt][0] <= N) { //다음껄 더해본 일수가 N을 넘지않는다
			dfs(cnt+arr[cnt][0], pay+arr[cnt][1]);			
		} else { //다음 껄 더해본 일수가 N을 넘는다.
			//dfs(cnt+1, pay); //왜 안되지
		}
		dfs(cnt+1, pay);
	}
}