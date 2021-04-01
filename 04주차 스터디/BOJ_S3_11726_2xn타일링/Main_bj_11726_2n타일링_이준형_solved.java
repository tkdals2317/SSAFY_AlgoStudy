package baekjun;
import java.io.*;
import java.util.*;

public class Main_bj_11726_2n타일링_구미_4_이준형 {

	static int all_cnt;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[][] bae = new int[2][N];

		all_cnt = 0; // 가능한가짓수
		search(0, 0, 0, N, bae);
		System.out.println(all_cnt % 10007);

		br.close();
	}

	// DFS 좌표축ij, 사각형수count, 배열 bae
	private static void search(int i, int j, int count, int N, int[][] bae) {
		if (i == 1 && j == N) {
//			printbae(N, bae);
			count += checkbae(N, bae); // 위아래 모양 가능한개수 체크
			if (count == N)
				all_cnt++;
			return;
		}
		if (j == N) { // j가 끝에 도달하면
			j = 0;
			i++;
		}
		if (j - 1 >= 0 && bae[i][j - 1] == 0) {
			bae[i][j - 1] = 1;
			bae[i][j] = 1;
			search(i, j + 1, count + 1, N, bae);
			bae[i][j - 1] = 0;
			bae[i][j] = 0;
		}
		search(i, j + 1, count, N, bae);
	}

	// 위아래 모양 가능한지
	private static int checkbae(int N, int[][] bae) {
		int count = 0;
		for (int j = 0; j < N; j++) {
			if (bae[0][j] == 0 && bae[1][j] == 0)
				count++;
		}
		return count;
	}

	// 배열출력
	private static void printbae(int N, int[][] bae) {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(bae[i][j]);
			}
			System.out.println();
		}
		System.out.println("===============");
	}

}
