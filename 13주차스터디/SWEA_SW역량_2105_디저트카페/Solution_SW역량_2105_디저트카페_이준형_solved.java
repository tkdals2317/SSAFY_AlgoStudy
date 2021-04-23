import java.io.*;
import java.util.*;

public class Solution_SW역량_2105_디저트카페_구미_4_이준형 {

	static int[] di = { 1, 1, -1, -1 }; // 대각선 방향벡터
	static int[] dj = { 1, -1, -1, 1 };

	static int[][] map;
	static int N;
	static boolean[] bae; // 먹은 디저트 배열 1~100
	static boolean[] use; // 경로 이동 체크 배열
	static int max_ans; // 먹은 디저트 최고수
	static int si,sj;	//시작점

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			map = new int[N][N];
			for (int i = 0; i < N; i++) { // 배열 입력
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			bae = new boolean[101]; // 초기화
			max_ans = -1;
			use = new boolean[4];

			// 모든 위치에서 사각형 만들기 시작
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					si=i;	//시작점 받기
					sj=j;
					search(i, j, 0, 0);
				}
			}

			System.out.println("#" + tc + " " + max_ans);

		}

		br.close();
	}

	// 사각형 만들기 dfs
	private static void search(int i, int j, int k, int len) {
		for (int h = 0; h < 2; h++) {	//현재방향이동 & 다음방향 이동
			if(k+h>=4) {	//마지막 이동 시도
				if(i==si&&j==sj)	//시작점과 같으면
					max_ans = Math.max(max_ans, len); // 최대종류와 현재 길이 비교
				return;
			}
			int ci = i + di[k+h];
			int cj = j + dj[k+h];
			if (ci >= 0 && ci < N && cj >= 0 && cj < N && bae[map[ci][cj]] == false) { // 범위조건,안먹은 메뉴
				bae[map[ci][cj]] = true;
				search(ci, cj, k+h, len + 1);
				bae[map[ci][cj]] = false;
			}
		}
	}

}
