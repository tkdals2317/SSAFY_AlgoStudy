import java.io.*;
// 160ms
public class bj_10026_G5 {

	static int N, map[][];
	static int[][] dir = { { -1, 1, 0, 0 }, { 0, 0, -1, 1 } }; // 4방 탐색

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; ++i) {
			String s = br.readLine();
			for (int j = 0; j < N; ++j) {
				char ch = s.charAt(j);// R-0 G-1 B-2 로 int형으로 배열 생성
				map[i][j] = ch == 'R' ? 0 : (ch == 'G' ? 1 : 2);
			}
		}
		System.out.println(process());// 출력
	}

	static String process() {
		boolean[][] isVisited = new boolean[N][N];// 방문체크 배열
		int cnt = 0, rcnt = 0;// 일반 구역, 적록색약 구역
		for (int i = 0; i < N; ++i) {// 일반 구역 시작
			for (int j = 0; j < N; ++j) {
				if (!isVisited[i][j]) {// 미방문 구역만
					isVisited[i][j] = true;// 방문 체크 후 탐색
					dfs(false, map[i][j], i, j, isVisited);
					cnt++;// 구역 카운팅
				}
			}
		}
		isVisited = new boolean[N][N];
		for (int i = 0; i < N; ++i) {// 적록색약 구역 시작
			for (int j = 0; j < N; ++j) {
				if (!isVisited[i][j]) {
					isVisited[i][j] = true;
					dfs(true, map[i][j], i, j, isVisited);
					rcnt++;// 적록색약 구역 카운팅
				}
			}
		}
		return cnt + " " + rcnt; // 반환
	}

	static void dfs(boolean rgb, int val, int i, int j, boolean[][] isVisited) {
		for (int d = 0; d < 4; ++d) {// 4방 탐색
			int ni = i + dir[0][d];
			int nj = j + dir[1][d];
			if (isBoundary(ni, nj) && !isVisited[ni][nj]) {// 영역을 벗어나지 않고, 미방문일 때
				if (!rgb && val == map[ni][nj]) { // 일반 구역이고 같은 색일 때만
					isVisited[ni][nj] = true;
					dfs(rgb, val, ni, nj, isVisited);// 재탐색
				} else if (rgb && (val == 0 && map[ni][nj] == 1 || // 적록색약 구역이고 색-록은 같은 구역으로
						  		   val == 1 && map[ni][nj] == 0 || 
						  		   val == map[ni][nj])) {
					isVisited[ni][nj] = true;
					dfs(rgb, val, ni, nj, isVisited);// 재탐색
				}
			}
		}
	}

	static boolean isBoundary(int x, int y) { // 영역 안인지 판별 후 반환
		return 0 <= x && x < N && 0 <= y && y < N;
	}

}
