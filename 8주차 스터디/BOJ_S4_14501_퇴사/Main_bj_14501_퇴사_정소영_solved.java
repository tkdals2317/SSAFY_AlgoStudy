import java.util.Scanner;

public class Main_bj_14501_퇴사_정소영_solved {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int consult[][] = new int[N + 1][2];
		int dp[] = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			consult[i][0] = sc.nextInt();
			consult[i][1] = sc.nextInt();
			dp[i] = consult[i][1];
		}
		
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j < i; j++) {
				if (i - j >= consult[j][0]) {
					dp[i] = Math.max(consult[i][1] + dp[j], dp[i]);
				}
			}
		}

		int max = 0;

		for (int i = 1; i <= N; i++) {
			if (i + consult[i][0] <= N + 1) {
				if (max < dp[i]) {
					max = dp[i];
				}
			}
		}

		System.out.println(max);

	}

}
