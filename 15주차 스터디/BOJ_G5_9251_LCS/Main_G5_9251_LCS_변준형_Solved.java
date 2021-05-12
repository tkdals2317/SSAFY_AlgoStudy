import java.util.Scanner;

public class Main_G5_9251_LCS_변준형_Solved {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s1 = sc.nextLine();
		String s2 = sc.nextLine();
		
		int dp [][] = new int[s2.length()+1][s1.length()+1];
		
		for (int i = 1; i <= s2.length(); i++) {
			for (int j = 1; j <= s1.length(); j++) {
				if(s1.charAt(j-1) == s2.charAt(i-1))			//	문자가 일치할 경우 (좌측 위 대각선 값 + 1)
					dp[i][j] = dp[i-1][j-1] + 1;
				else
					dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);	//	문자가 불일치할 경우 (왼쪽 or 위쪽 중 큰 값)
			}
		}
		System.out.println(dp[s2.length()][s1.length()]);
	}
}

//참고 사이트  http://lcs-demo.sourceforge.net/