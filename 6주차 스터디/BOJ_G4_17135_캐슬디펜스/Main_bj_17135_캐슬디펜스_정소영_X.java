import java.util.Arrays;
import java.util.Scanner;

public class Main_bj_17135_캐슬디펜스_정소영_X {
	static int N, M, D;
	static int res = 0;
	static int[][] map;
	static int[] numbers = new int[3];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		D = sc.nextInt();

		map = new int[N + 1][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		// 입력받기

		combination(0, 0);

		sc.close();
	}

	private static void combination(int cnt, int start) {
		if (cnt == 3) {
			for (int i = 0; i < M; i++) {
				map[N][i] = 0;
			}
			for (int i = 0; i < 3; i++) {
				map[N][numbers[i]] = 2;
			}
			// 궁수 자리 선점 완료

			shoot();
			// 화살쏘기
			return;
		}

		for (int i = start; i < N; i++) { // i:시도하는 수
			numbers[cnt] = i;
			combination(cnt + 1, i + 1);
		}
	}

	private static void shoot() {
		label1:for (int k = 0; k < M; k++) {
			if(map[N][k]==2) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j]==1) {
						if((i-M)*(i-M)+(j-k)*(j-k)<=D) {
							map[i][j]=0;
							continue label1;
						}
					}
				}
			}
		}
		}
	}

}
