import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_bj_1157_단어공부_정소영_solved {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();

		System.out.println(word(str));

		br.close();
	}

	private static char word(String str) {

		int len = str.length();
		int[] alpha = new int[26];
		
		for (int i = 0; i < len; i++) {
			char c = str.charAt(i);
			if (c <= 90) {
				alpha[c - 65]++;
			} else {
				alpha[c - 97]++;
			}
		}

		int cnt = 0;
		int max = 0;
		int index = 0;

		for (int i = 0; i < 26; i++) {
			if (alpha[i] != 0) {
				if (alpha[i] > max) {
					max = alpha[i];
					index = i;
					cnt = 1;
				} else if (alpha[i] == max) {
					cnt++;
				}
				//System.out.print((char) (i+65)+ " : "+alpha[i]+" / ");
			}
		}
		if(cnt!=1) {
			return '?';
		}
		return (char) (index+65);
	}

}
