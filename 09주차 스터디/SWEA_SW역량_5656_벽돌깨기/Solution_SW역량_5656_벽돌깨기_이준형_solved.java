import java.io.*;
import java.util.*;

public class Solution_SW역량_벽돌깨기_구미_4_이준형 {

	static int[] di = { 0, 0, 1, -1 }; // 4방향 벡터
	static int[] dj = { 1, -1, 0, 0 };
	static int N, W, H; // 구슬개수,가로,세로
	static int[] bae; // 구슬 배열
	static int[][] map_main; // 기존맵
	static int[][] map; // 맵
	static int min_ans; // 벽돌 최소 개수

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			map_main = new int[H][W];
			for (int i = 0; i < H; i++) { // 맵 입력
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map_main[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			bae = new int[N];	//구슬배열
			min_ans = Integer.MAX_VALUE;	//초기화
			choice(0); // 구슬위치 선택

			System.out.println("#" + tc + " " + min_ans);	// 벽돌 개수 최소로 남은거 출력

		}

		br.close();
	}

	// 구슬 위치 선택 dfs
	private static void choice(int count) {
		if (count == N) { // 구슬 가짓수 선택 완료
			map = new int[H][W];
			deepcopy(); // 기존 배열 복사
			for (int i = 0; i < N; i++) { // 선택된거 항목순으로 처리
				ball_down(bae[i]); // 공 떨어뜨리기
				map_down(); // 빈공간 맵 내리기
			}
			int ans = check_ans(); // 남은 벽돌 개수세기
			min_ans = Math.min(min_ans, ans); // 최소 답이랑 비교

			return;
		}
		for (int j = 0; j < W; j++) {
			bae[count] = j;
			choice(count + 1);

		}
	}

	// 공 떨어뜨리기
	private static void ball_down(int target) {
		for (int i = 0; i < H; i++) {
			if (map[i][target] != 0) { // 0이 아니면
				if (map[i][target] == 1) // 1은 그냥 지우기
					map[i][target] = 0;
				else { // 1보다 클 경우 폭발 찾기
					find_bomb(i, target);
				}
				return;
			}
		}
	}

	// 폭발찾기 dfs
	private static void find_bomb(int i, int j) {
		int size = map[i][j]; // 폭탄 크기
		map[i][j] = 0; // 폭탄 제거
		for (int k = 0; k < 4; k++) { // 4방향 탐색
			int ci = i;
			int cj = j;
			for (int s = 0; s < size-1; s++) {	//폭탄의 크기만큼
				ci += di[k];
				cj += dj[k];
				if (ci >= 0 && ci < H && cj >= 0 && cj < W) { // 범위 조건
					if (map[ci][cj] > 1) {	//1이상이면 다시 폭발 호출
						find_bomb(ci, cj);
						map[ci][cj]=0;
					}else if(map[ci][cj]==1) {	//1이면 제거
						map[ci][cj]=0;
					}
				}
			}
		}
	}

	// 맵 빈공간 내리기
	private static void map_down() {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		for (int j = 0; j < W; j++) { // 가로 다 탐색
			for (int i = H - 1; i >= 0; i--) { // 밑에서 부터 올라가면서 처리
				if (map[i][j] != 0) { // 0아니면 큐에 넣고 제거
					queue.offer(map[i][j]);
					map[i][j] = 0;
				}
			}
			int idx = H - 1;
			while (!queue.isEmpty()) { // 큐 빌때까지 밑에서 부터 채워넣기
				map[idx][j] = queue.poll();
				idx--;
			}
		}
	}

	// 남은 벽돌개수세기
	private static int check_ans() {
		int count = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] != 0)
					count++;
			}
		}
		return count;
	}

	// 배열복사
	private static void deepcopy() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				map[i][j] = map_main[i][j];
			}
		}
	}

}
