import java.io.FileInputStream;
import java.util.Scanner;

public class Main_bj_1018_체스판다시칠하기 {
	static char[][] tBorad1 = {
			{'B','W','B','W','B','W','B','W'},
			{'W','B','W','B','W','B','W','B'},
			{'B','W','B','W','B','W','B','W'},
			{'W','B','W','B','W','B','W','B'},
			{'B','W','B','W','B','W','B','W'},
			{'W','B','W','B','W','B','W','B'},
			{'B','W','B','W','B','W','B','W'},
			{'W','B','W','B','W','B','W','B'}			
	};
	static char[][] tBorad2 = {
			{'W','B','W','B','W','B','W','B'},
			{'B','W','B','W','B','W','B','W'},
			{'W','B','W','B','W','B','W','B'},
			{'B','W','B','W','B','W','B','W'},
			{'W','B','W','B','W','B','W','B'},
			{'B','W','B','W','B','W','B','W'},
			{'W','B','W','B','W','B','W','B'},
			{'B','W','B','W','B','W','B','W'}
	};
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/Main_1018.txt"));
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		char [][] board = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = sc.next();
			for (int j = 0; j < M; j++) {
				board[i][j] = str.charAt(j);
			}
		}
		int min1 = Integer.MAX_VALUE;
		int min2 = Integer.MAX_VALUE;
		for (int i = 0; i < N-8+1; i++) {
			for(int j = 0; j < M-8+1; j++){
				int cnt = 0;
				for(int k = i; k<i+8;k++) {
					for (int q = j; q<j+8; q++) {
						if(board[k][q]!=tBorad1[k-i][q-j]) {
							cnt++;
						};
					}
				}
				min1 = Math.min(min1, cnt);
				cnt=0;
				for(int k = i; k<i+8;k++) {
					for (int q = j; q<j+8; q++) {
						if(board[k][q]!=tBorad2[k-i][q-j]) {
							cnt++;
						};
					}
				}
				min2 = Math.min(min2, cnt);
			}
		}
		int sol = Math.min(min1, min2);
		System.out.println(sol);
		sc.close();

	}

}
