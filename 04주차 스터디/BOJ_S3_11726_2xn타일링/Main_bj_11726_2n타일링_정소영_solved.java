import java.io.*;
import java.util.*;
// https://www.acmicpc.net/problem/11726
// 분할정복 ? 재귀 ? => 시간초과
// dp로 해결

public class Main_bj_11726_2n타일링_정소영_solved {

	static long[] square;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		if(n==1)	System.out.println(1);
		else if(n==2)	System.out.println(2);
		
		else {
			square = new long[n];
			square[0] = 1;
			square[1] = 2;

			for (int i = 2; i < n; i++) {
				square[i] = square[i-1] + square[i-2];
				square[i] %= 10007;
			}
			System.out.println(square[n-1]);
		}
		
	}
	
}
