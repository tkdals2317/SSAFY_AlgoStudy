import java.util.Scanner;

public class Main_bj_20056_마법사상어와파이어볼_x_이해못함 {
	static int N, M, K;
	static int[][] map;
	static int[] di = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dj = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int[][] balls;
	static int[][] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		map = new int[N + 1][N + 1];
		balls = new int[M][5];
		

		for (int m = 0; m < M; m++) {
			for (int i = 0; i < 5; i++) {
				balls[m][i] = sc.nextInt();
			}
			map[balls[m][0]][balls[m][0]] = 1;
		}

		for (int k = 0; k < K; k++) {
			for (int m = 0; m < M; m++) {
				for (int s = 0; s < balls[m][4]; s++) {
					map[balls[m][0]][balls[m][0]] = 0;
					
					int ni = balls[m][0] + di[balls[m][3]];
					int nj = balls[m][1] + dj[balls[m][3]];
					if (ni >= N)
						ni -= N;
					else if (ni < 1)
						ni += N;
					if (nj >= N)
						nj -= N;
					else if (nj < 1)
						nj += N;

					balls[m][0] = ni;
					balls[m][1] = nj;
					
					if(map[ni][nj]!=0)
						map[ni][nj] = 1;
					else {
						map[ni][nj]++;
					}

				}
				
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if(map[i][j]>1) {	// 2개 이상의 파이어볼
							
						}
					}
				}
				
				
			} // 이동

		}
	}

}
