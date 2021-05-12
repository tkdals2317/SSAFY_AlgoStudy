import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_G5_12865_평범한배낭_변준형_solved {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String T;

		T = br.readLine();
		st = new StringTokenizer(T);

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int weight[] = new int[N + 1];
		int value[] = new int[K + 1];
		int dp[][] = new int[N + 1][K + 1];
		for (int i = 1; i < N + 1; i++) {
			T = br.readLine();
			st = new StringTokenizer(T);
			weight[i] = Integer.parseInt(st.nextToken());
			value[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				if (j - weight[i] >= 0) {
					dp[i][j] = Math.max(dp[i - 1][j], value[i] + dp[i - 1][j - weight[i]]);

				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		System.out.println(dp[N][K]);
	}
}