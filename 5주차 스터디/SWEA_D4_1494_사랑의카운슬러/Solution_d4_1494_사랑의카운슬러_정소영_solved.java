import java.util.Scanner;

// https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=4&contestProbId=AV2b_WPaAEIBBASw&categoryId=AV2b_WPaAEIBBASw&categoryType=CODE&problemTitle=&orderBy=INQUERY_COUNT&selectCodeLang=ALL&select-1=4&pageSize=10&pageIndex=3

public class Solution_d4_1494_사랑의카운슬러_정소영_solved {
	static int N;
	static int[][] cordi;
	static int[] check;
	static long res=Long.MAX_VALUE;
	static long tx=0, ty=0;
	static long ax=0, ay=0;	// 전체 값을 다 저장
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			res=Long.MAX_VALUE;
			cordi = new int[N][2];
			check = new int[N/2];
			ax=0; 
			ay=0;
			for (int n = 0; n < N; n++) {
				cordi[n][0] = sc.nextInt();
				cordi[n][1] = sc.nextInt();
				ax -= cordi[n][0];
				ay -= cordi[n][1];
			}
			tx=ax;
			ty=ay;
			combi(0,0);
			
			System.out.println("#"+t+" "+res);
		}
		sc.close();
	}
	private static void combi(int cnt, int start) {
		if(cnt==N/2) {
			for (int i = 0; i < N/2; i++) {
				tx+=cordi[check[i]][0]*2;
				ty+=cordi[check[i]][1]*2;
			}
			if(res>tx*tx+ty*ty) {
				res=tx*tx+ty*ty;
			}
			tx=ax;
			ty=ay;
			return;
		}
		for (int i = start; i < N; i++) {
			check[cnt] = i;
			combi(cnt+1,i+1);
		}
	}

}
/*
2
4
6 0
3 3
-7 2
-4 -1
2
-100000 100000
100000 -100000

[0, 1]
[0, 2]
[0, 3]
[1, 2]
[1, 3]
[2, 3]


*/