import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;
import java.util.Scanner;

// 전형적인 dfs
public class Main_bj_2667_단지번호붙이기_정소영_solved {
	static int n;
	static char[][] map;
	static boolean[][] visited;
	static int[] result = new int[25 * 25];
	static int[] di = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dj = { 0, 0, -1, 1 };
	static int res = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		map = new char[n][n];
		visited = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			char[] str = sc.next().toCharArray();
			for (int j = 0; j < n; j++) {
				map[i][j] = str[j];
			}
		}
		// 입력 끝

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j] && map[i][j] == '1') {
					dfs(i, j, 0);
					res++;
					// 총 단지 수 카운트
				}
			}
		}
		System.out.println(res);
		Arrays.sort(result);	// 답 정렬
		for (int i = 0; i < 25 * 25; i++) {
			if (result[i] != 0) {
				System.out.println(result[i]);
			}
		}
		sc.close();
	}

	private static void dfs(int i, int j, int cnt) {

		visited[i][j] = true;
		result[res]++;
		for (int k = 0; k < 4; k++) {
			int ni = i + di[k];
			int nj = j + dj[k];
			if (ni >= 0 && ni < n && nj >= 0 && nj < n) {
				if (map[ni][nj] == '1' && !visited[ni][nj]) {
					dfs(ni, nj, cnt++);
				}
			}
		}
	}
}
/*
7
0110100
0110101
1110101
0000111
0100000
0111110
0111000

 * 3 7 8 9
 * 
 */
