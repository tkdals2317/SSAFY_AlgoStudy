import java.util.*;
import java.io.*;

public class Main_BJ_G4_2573_빙산_이상민_solved {
	static int R, C;
	static int[][] map;
	static int[][] temp;
	static boolean[][] visited;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static int iceCnt = 0;
	static int T = 0;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2573.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		temp = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int time = 0;
		// print();
		while (true) {
			melt();
			deepcopy();
			// print();
			time++;
			int status = check();
			if (status == 2) { //만약 빙산이 다녹을때까지 분리되지 않는 경우
				time = 0;
				break;
			} else if (status == 1) { //빙산이 두개로 나뉜 경우
				break;
			}
			//빙산이 한개인 경우 계속 진행 (0일 경우)
		}
		System.out.println(time);
	}

	private static int check() {
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0 && !visited[i][j]) {
					// 빙산이 아직 한개일 경우 0을 반환
					// bfs로 탐색가능한 얼음의 갯수와 녹고난후 얼음의 갯수를 비교하여 같으면 한개의 빙산이라는 뜻
					if (bfs(i, j) == iceCnt) {
						return 0;
					// 빙산이 두개 이상으로 나뉜 경우 1을 반환
					}else {
						return 1;
					}
				}
			}
		}
		// 빙산이 다 녹을때까지 분리 되지 않을 경우 2를 반환
		return 2;
	}
	//빙산이 한개로 이루어져있는 지 확인하기 위한 bfs
	private static int bfs(int i, int j) {
		ArrayDeque<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] { i, j });
		visited[i][j] = true;
		int cnt = 1;
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int ci = curr[0];
			int cj = curr[1];
			
			for (int d = 0; d < 4; d++) {
				int ni = ci + dr[d];
				int nj = cj + dc[d];
				if (ni > 0 && ni < R && nj > 0 && nj < C && !visited[ni][nj] && map[ni][nj] > 0) {
					queue.offer(new int[] { ni, nj });
					visited[ni][nj] = true;
					//현재 얼음의 갯수 세기
					cnt++;
				}
			}
		}
		return cnt;
	}

	private static void melt() {
		iceCnt = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				int surfaceCnt = 0;
				if (map[i][j] > 0) {
					for (int d = 0; d < 4; d++) {
						//주변의 바다와 맞닿아있는 영역 갯수 새기
						if (map[i + dr[d]][j + dc[d]] == 0) {					
							surfaceCnt++;
						}
					}
					//빙산이 녹는 과정
					//0이하로 내려가면 0으로 처리
					if (map[i][j] - surfaceCnt <= 0) {
						temp[i][j] = 0;
					} else {
						temp[i][j] = map[i][j] - surfaceCnt;
						//녹고 난 후 얼음의 갯수 전역변수로 카운팅 후에 bfs로 빙산이 한개인지 검사할때 사용
						iceCnt++;
					}
				}
			}
		}
	}

	static void deepcopy() {
		// iceCnt = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = temp[i][j];
				/*
				 * if (map[i][j] > 0) { iceCnt++; }
				 */
			}
		}
	}

	static void print() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
