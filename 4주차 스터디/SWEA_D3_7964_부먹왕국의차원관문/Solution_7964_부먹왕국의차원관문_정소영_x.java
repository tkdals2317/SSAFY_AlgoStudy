import java.io.*;
import java.util.*;

// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWuSgKpqmooDFASy&categoryId=AWuSgKpqmooDFASy&categoryType=CODE&problemTitle=7964&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1&&&&&&&&&&&&&&&&&&
public class Solution_7964_부먹왕국의차원관문_정소영_x {
	static int N,D;
	static int[] castle;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			castle = new int[N];
			for (int i = 0; i < N; i++) {
				castle[i] = Integer.parseInt(st.nextToken());
			}
			int cnt=0;
			int sum=0;
			for (int i = 0; i < N; i++) {
				if(castle[i]==0) {
					cnt++;
					if(cnt==D) {
						sum++;
						cnt=0;
					}
				}else {
					cnt=0;
				}
			}
			System.out.println("#"+t+" "+sum);
		}
	}

}

/*
3
6 2
1 0 0 0 0 1
10 2
0 0 1 0 1 0 0 0 0 1
10 1
0 0 0 0 0 0 0 0 0 0

*/