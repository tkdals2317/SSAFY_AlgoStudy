package baekjun;
import java.io.*;
import java.util.*;

public class Main_bj_17144_미세먼지안녕_구미_4_이준형 {

	static int[][] bae; // 전체 배열
	static int[][] new_bae;
	static int[] air; // 공기청정기 위치
	static int R, C; // 배열 사이즈
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());

		bae = new int[R][C]; // 배열 입력
		int air_cnt = 0;
		air = new int[2]; // 공기청정기 위치
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				bae[i][j] = tmp;
				if (tmp == -1) { // -1인경우 공기청정기 위치 넣기
					air[air_cnt] = i;
					air_cnt++;
				}
			}
		}

		printbae();

		for (int t = 0; t < T; t++) { // 시간만큼 반복

			new_bae = new int[R][C]; // 먼지확산 저장 배열
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (bae[i][j] >= 5) // 나눠질수 있으면
						spread(i, j); // 먼지 확산 호출
					else { // 아니면 그냥 더해주기
						new_bae[i][j] += bae[i][j];
					}
				}
			}

			deepcopy(); // 배열 복사
			printbae();

			spin1(air[0], 0); // 공기청정기1 작동
			spin2(air[1], 0); // 공기청정기2 작동
			
			printbae();
		}
		
		//먼지 개수 세기
		int ans=0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(bae[i][j]!=-1)
					ans+=bae[i][j];
			}
		}
		System.out.println(ans);
		
		br.close();
	}

	// 공기청정기 아래꺼
	private static void spin2(int i, int j) {
		i += 1; // 한칸 아래부터 시작
		int[] di = { 1, 0, -1, 0 }; // 여기선 다른 방향기준 적용
		int[] dj = { 0, 1, 0, -1 };
		for (int k = 0; k < 4; k++) { // 4방향
			while (true) {
				int ci = i + di[k];
				int cj = j + dj[k];
				if (ci >= air[1] && ci < R && cj >= 0 && cj < C && bae[ci][cj] != -1) {
					bae[i][j] = bae[ci][cj];
					i = ci;
					j = cj;
				} else
					break;
			}
		}
		bae[i][j] = 0; // 마지막 바람 불어서 생긴 곳

	}

	// 공기청정기 위에꺼
	private static void spin1(int i, int j) {
		i -= 1; // 위에 위치부터 시작
		for (int k = 0; k < 4; k++) { // 4방향
			while (true) {
				int ci = i + di[k];
				int cj = j + dj[k];
				if (ci >= 0 && ci <= air[0] && cj >= 0 && cj < C && bae[ci][cj] != -1) {
					bae[i][j] = bae[ci][cj];
					i = ci;
					j = cj;
				} else
					break;
			}
		}
		bae[i][j] = 0; // 마지막 바람 불어서 생긴 곳
	}

	// 배열 딥카피
	private static void deepcopy() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				bae[i][j] = new_bae[i][j];
			}
		}
	}

	// 먼지 확산
	private static void spread(int i, int j) {
		int num = bae[i][j] / 5; // 퍼지는양

		for (int k = 0; k < 4; k++) { // 4방향 탐색
			int ci = i + di[k];
			int cj = j + dj[k];
			if (ci >= 0 && ci < R && cj >= 0 && cj < C && bae[ci][cj] != -1) { // 범위조건, 공기청정기 아니면
				bae[i][j] -= num;
				new_bae[ci][cj] += num;
			}
		}

		new_bae[i][j] += bae[i][j];
	}

	// 배열 출력
	private static void printbae() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(bae[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("===============");
	}

}
