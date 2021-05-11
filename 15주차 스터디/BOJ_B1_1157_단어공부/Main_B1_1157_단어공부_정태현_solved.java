package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B1_1157_단어공부_정태현_solved {
	private static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine().toUpperCase();
		
		int cnt[] = new int[26];
		int max = Integer.MIN_VALUE;
		int alp = 0;
		char ans = '?';
		for (int i = 0; i < str.length(); i++) {
			alp = str.charAt(i) - 65;
			
			cnt[alp]++;
			
			if(max < cnt[alp]) {
				max = cnt[alp];
				ans = str.charAt(i);
			} else if (max == cnt[alp]) ans = '?';
			
		}
		System.out.println(ans);
	}

}
