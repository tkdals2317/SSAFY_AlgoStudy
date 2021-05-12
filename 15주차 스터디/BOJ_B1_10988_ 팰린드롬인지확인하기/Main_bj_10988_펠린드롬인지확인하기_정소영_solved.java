import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_bj_10988_펠린드롬인지확인하기_정소영_solved {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		
		System.out.println(palindrome(str));
		
		
		br.close();
		
		
	}

	private static int palindrome(String str) {
		int len = str.length();
		
		for (int i = 0; i < len/2; i++) {
			if(str.charAt(i)!=str.charAt(len-1-i)) {
				return 0;
			}
		}
		
		
		return 1;
	}

}
