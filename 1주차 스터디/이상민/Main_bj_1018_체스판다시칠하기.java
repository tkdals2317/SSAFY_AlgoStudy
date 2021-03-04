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
		for (int i = 0; i < N-8+1; i++) {
			for(int j = 0; j < M-8+1; j++) {
				for(int k = i; k<i+8;k++) {
					for (int q = j; q<j+8; q++) {
						if(board[k][q]!=tBorad1[k-i][q-j]) {

						};
					}
					System.out.println();
				}
				//min = min()
				System.out.println();
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
		System.out.println("입력 끝");
		sc.close();

	}

}
