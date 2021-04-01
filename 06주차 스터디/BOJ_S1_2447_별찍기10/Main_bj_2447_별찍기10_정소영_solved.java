import java.util.Scanner;

public class Main_bj_2447_별찍기10_정소영_solved {
	static char[][] star;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		star = new char[N][N];
		stars(0, 0, N);
		for (int i = 0; i < N; i++) {	// 0,1,2
			for (int j = 0; j < N; j++) {
				System.out.print(star[i][j]);
			}
			System.out.println();
		}
		sc.close();
	}

	private static void stars(int ii, int jj, int n) {
		if (n == 3) {
			for (int i = ii; i < ii + 3; i++) {	// 0,1,2
				for (int j = jj; j < jj + 3; j++) {
					if(i==ii+1 && j==jj+1)	continue;
					star[i][j] = '*';
				}
			}
			return;
		}
		for (int i = 0; i < 3; i++) {	// 0,1,2
			for (int j = 0; j < 3; j++) {
				if (!(i == 1 && j == 1)) {
					if(n>3) {
						stars(ii+i*n/3,jj+j*n/3,n/3);
					}
				} 
			}
		}
	}

}
