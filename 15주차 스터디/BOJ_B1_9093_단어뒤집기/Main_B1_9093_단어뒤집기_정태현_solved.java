package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B1_9093_단어뒤집기_정태현_solved {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine()); //테케 수
		StringBuilder sb = new StringBuilder();
		
		String s = null;
		for (int tc = 0; tc < T; tc++) {
			String[] str = br.readLine().split(" ");
			
			for (int i = 0; i < str.length; i++) {
				for (int j = str[i].length() - 1; j >= 0; j--) {
					sb.append(str[i].charAt(j));
//					System.out.print(str[i].charAt(j));
				}
				sb.append(" ");
//				System.out.print(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
