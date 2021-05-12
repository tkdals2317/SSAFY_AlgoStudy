import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_B1_10988_팰린드롬인지확인하기_변준형_Solved {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] ch = br.readLine().toCharArray();
		int last = ch.length / 2;
		for (int i = 0; i < last; i++) {
			// 팰린드롬이 아닌 경우
			if (ch[i] != ch[ch.length - i - 1]) {
				System.out.println(0);
				return;
			}
		}
		// 팰린드롬인 경우
		System.out.println(1);
	}
}