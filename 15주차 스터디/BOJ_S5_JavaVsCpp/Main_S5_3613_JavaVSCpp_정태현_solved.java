package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_S5_3613_JavaVSCpp_정태현_solved {
	private static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		sb = new StringBuilder();
		boolean flag = false;
		
		
		if(str.contains("_")) {
			if(str.contains("__") || str.charAt(str.length()-1)=='_' || str.charAt(0)=='_') {
				System.out.println("Error!");
				System.exit(0);
			}
			for (int i = 0; i < str.length(); i++) {
				if(Character.isUpperCase(str.charAt(i))) {
					System.out.println("Error!");
					System.exit(0);
				}
			}
			
			
			ctoj(str);
		} else {
			if(Character.isUpperCase(str.charAt(0))) {
				System.out.println("Error!");
				System.exit(0);	
			}
			for (int i = 0; i < str.length(); i++) {
				if(Character.isUpperCase(str.charAt(i))) {
					flag = true;
				}
			}
			if(!flag) {
				System.out.println(str);
				System.exit(0);				
			}
			
			
			jtoc(str);
		}
		
		System.out.println(sb);
	}

	private static void jtoc(String str) {
		for (int i = 0; i < str.length(); i++) {
			if(Character.isUpperCase(str.charAt(i))) {
				sb.append("_");
				sb.append(Character.toLowerCase(str.charAt(i)));
			} else {
				sb.append(str.charAt(i));				
			}
		}
		
	}

	private static void ctoj(String str) {
		boolean nexthypen = false;
		
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i)=='_') {
				nexthypen = true;
				continue;
			} else {
				if(nexthypen) {
					sb.append(Character.toUpperCase(str.charAt(i)));
					nexthypen = false;
				}
				else sb.append(str.charAt(i));
			}
		}
//		String[] s = str.split("_");
//		
//		for (int i = 1; i < s.length; i++) {
//			s[i] = Character.toUpperCase(s[i].charAt(0)) + s[i].substring(1);
//		}
//		
//		for (int i = 0; i < s.length; i++) {
//			sb.append(s[i]);
//		}
	}
}
