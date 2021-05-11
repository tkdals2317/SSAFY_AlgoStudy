package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B1_10988_팰린드롬_정태현_solved {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		
		boolean isPal = true;
		int len = str.length() - 1;
		
		for (int i = 0; i <= len/2; i++) {
			if(str.charAt(i)==str.charAt(len-i)) {
				continue;
			} else {
				isPal = false;
				break;
			}
		}
		
		if(isPal) System.out.println(1);
		else System.out.println(0);
	}
}
