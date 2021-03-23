import java.util.*;
import java.io.*;

public class Main_S4_14501_퇴사_이상민_solved {
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14501.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int [] dp = new int[N+1];
		int [] day = new int [N];
		int [] price = new int [N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			day[i] = Integer.parseInt(st.nextToken());
			price[i] = Integer.parseInt(st.nextToken());
		}
		dfs(N, 0, day, price, 0);
		System.out.println(max);
/*		System.out.println(Arrays.toString(day));
		System.out.println(Arrays.toString(price));
		for (int i = 0; i < N; i++) {
			if(i+day[i]<=N) {
				dp[i+day[i]] = Math.max(dp[i+day[i]],dp[i]+price[i]);
			}
			dp[i+1] = Math.max(dp[i+1], dp[i]); 
			System.out.println(Arrays.toString(dp));
		}
		System.out.println(dp[N]);
		br.close();*/
	}

	private static void dfs(int n, int cnt, int[] day, int[] price, int tPrice) {
		if(cnt>=n) {
			max = Math.max(max, tPrice);
			return;
		}
		//해당일(cnt)에 소요시간을 더했을때 마지막날을 넘지 않는다면
		if(cnt+day[cnt]<=n) {
			//해당 일의 상담 소요기간과 이익을 더해서 재귀
			dfs(n, cnt+day[cnt], day, price, tPrice+price[cnt]);
		}
		//일하는 날을 더했을 때 마지막 날을 넘는다면
		else {
			//소요기간만 더해주고 재귀
			dfs(n, cnt+day[cnt], day, price, tPrice);
		}
		//해당일을 선택하지 않은 경우
		dfs(n, cnt+1, day, price, tPrice);
	}

}
