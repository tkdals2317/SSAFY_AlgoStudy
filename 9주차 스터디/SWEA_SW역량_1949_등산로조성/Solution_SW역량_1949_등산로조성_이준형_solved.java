import java.io.*;
import java.util.*;

public class Solution_SW역량_1949_등산로조성_구미_4_이준형 {

	static class pos { // 위치 저장 클래스
		int i;
		int j;

		public pos(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}

	static int[] di = { 0, 0, 1, -1 }; // 4방향벡터
	static int[] dj = { 1, -1, 0, 0 };
	static int[][] map; // 맵
	static int[][] visit; // 방문체크
	static int max_len; // 최대길인
	static int N, K; // 배열크기,깍을수 있는 최대깊이
	static Stack<pos> stack; // 가장 높은 위치 저장 스택

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) { // 테케만큼 반복
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 값 저장
			K = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			for (int i = 0; i < N; i++) { // 맵 배열에 입력
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			search_high(); // 높은 위치 찾아서 스택에 저장

			max_len = Integer.MIN_VALUE; // 초기화
			while (!stack.isEmpty()) { // 스택에 저장된 위치에서 다 출발
				pos tmp = stack.pop();
				visit = new int[N][N]; // 방문체크배열 초기화
				visit[tmp.i][tmp.j] = 1; // 시작위치 체크
				search_way(tmp.i, tmp.j, 1, 1); // 위치i,j,길이,깍기 횟수
			}

			System.out.println("#" + tc + " " + max_len); // 최고 길이 출력
		}

		br.close();

	}

	// 길찾기 dfs 값을 변경시키면서 가는것은 dfs로 처리
	private static void search_way(int i, int j, int idx, int can) {
		int flag = 0; // 더이상 갈수 없는지 체크
		for (int k = 0; k < 4; k++) {
			int ci = i + di[k];
			int cj = j + dj[k];
			if (ci >= 0 && ci < N && cj >= 0 && cj < N && visit[ci][cj] == 0) { // 범위조건,방문조건
				if (map[ci][cj] < map[i][j]) { // 자신보다 낮은 곳으로 이동
					visit[i][j] = 1; // 방문체크
					search_way(ci, cj, idx + 1, can);
					visit[i][j] = 0;
					flag = 1;
				}
				else if (can == 1 && map[ci][cj] - K < map[i][j]) {	// 깍아서 이동 가능한지 체크
					int tmp = map[ci][cj]; // 기존 높이 저장
					map[ci][cj] = map[i][j] - 1; // 현재의 높이 -1로 깍아 최대한 많은 경로 찾을수 있게 해주기
					visit[i][j] = 1; // 방문체크
					search_way(ci, cj, idx + 1, 0);
					map[ci][cj] = tmp; // 다시 복구
					visit[i][j] = 0;
					flag = 1;
				}
			}
		}
		if (flag == 0) { // 더이상 이동 불가
			max_len = Math.max(max_len, idx); // 최대 길이 와 비교
			return;
		}
	}

	// 높은 위치 찾아서 스택에 저장
	private static void search_high() {
		int max_high = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) { // 가장 높은 위치 찾기
			for (int j = 0; j < N; j++) {
				max_high = Math.max(map[i][j], max_high);
			}
		}

		stack = new Stack<>();
		for (int i = 0; i < N; i++) { // 가장 높은 위치 스택에 저장
			for (int j = 0; j < N; j++) {
				if (map[i][j] == max_high)
					stack.push(new pos(i, j));
			}
		}
	}

}
