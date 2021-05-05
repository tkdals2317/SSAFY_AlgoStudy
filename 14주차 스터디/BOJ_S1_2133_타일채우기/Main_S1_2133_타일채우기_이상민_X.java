import java.util.*;
import java.io.*;
public class Main_S1_2133_타일채우기_이상민_X {
	static int N;
	static int [] dp;
	//기본적으로 dp[i] = dp[i-2]*3 이라는 점화식을 세운다(2*n일때의 점화식)
	//하지만 세로가 3이므로 i가 짝수번째일때마다 새로운 규칙의 방법을 추가해준다
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[31];
		dp[0] = 1;
		dp[1] = 0;
		dp[2] = 3;
		if(N>=4) {
            for(int i=4;i<31;i+=2) {
                dp[i] = dp[i-2]*3;
                for(int j=4;j<=i;j+=2) {
                    dp[i] += dp[i-j]*2;
                }
            }    
        }
        System.out.println(dp[N]);
		
		br.close();
	}

}
