import java.io.*;
import java.util.*;

public class Solution_SW역량_4013_특이한자석_구미_4_이준형 {

	static int[][] map; // 맵정보
	static int[] move; // 회전정보

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int K = Integer.parseInt(br.readLine());

			map = new int[5][8];
			for (int i = 1; i <= 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int k = 0; k < K; k++) {

				st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken());
				int rotate = Integer.parseInt(st.nextToken());
				move = new int[5]; // 초기화
				move[num] = rotate; // 처음 회전정보 넣어주기
				search(num, -1, 6, -rotate); // 위로 탐색
				search(num, 1, 2, -rotate); // 밑으로 탐색

				rollin(); // 회전하기

			}

			// 답계산
			int ans = calcans();

			System.out.println("#" + tc + " " + ans);

		}

	}
	
	// 재귀로 호출
	private static void search(int i, int go, int j, int rotate) {
		if (i+go < 1 || i+go > 4) // 종료조건
			return;
		if (map[i][j] != map[i + go][(j + 4) % 8]) { // 맡닿은 정보가 다르면
			move[i + go] = rotate;
			search(i + go, go, j, -rotate);
		}
	}
	
	// 톱니바퀴 돌리기
	private static void rollin() {
		for (int i = 1; i < 5; i++) {
			int first = map[i][0];
			int last = map[i][7];
			if (move[i] == 1) { // 시계방향
				for (int j = 7; j > 0; j--) {
					map[i][j] = map[i][j - 1];
				}
				map[i][0] = last;
			} else if (move[i] == -1) { // 시계반대방향
				for (int j = 0; j < 7; j++) {
					map[i][j] = map[i][j + 1];
				}
				map[i][7] = first;
			}
		}

	}

	//점수계산
	private static int calcans() {
		int ans = 0;
		int k = 1;
		for (int i = 1; i < 5; i++) {
			ans += (map[i][0] * k);
			k *= 2;
		}
		return ans;
	}
	
	//맵 출력
	private static void printmap() {
		System.out.println();
		for (int i = 1; i <= 4; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(map[i][j]);
			}System.out.println();
		}System.out.println("------------");
	}


}
