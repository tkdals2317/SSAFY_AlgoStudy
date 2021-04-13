import java.io.*;
import java.util.*;

public class Main_bj_15684_사다리조작_구미_4_이준형 {

	static int N, M, H; // 세로선수,가로선수,가로선위치수
	static int[][] map; // 맵
	static int min_count;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H + 2][N + 1]; // 맵 출발,도착 으로 +2, 가로 1부터 +1
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1; // 선연결된거 표시
			map[a][b + 1] = 2;
		}

//		printmap();

		min_count = Integer.MAX_VALUE;
		// 선배치하는거 조합
		make_line(1, 1, 0);

		if (min_count == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min_count);

		br.close();
	}

	// 조합 가로선 만들기
	private static void make_line(int idx_i, int idx_j, int count) {
		// 조건체크
		if (check_line()) { // 다 자기 번호로 이어진것
			// 최소길이와 비교
			min_count = Math.min(min_count, count);
			return;
		}

		if (count > 3) { // 3개 넘으면 끝
			return;
		}
		if (idx_j > N) { // 세로선 범위 벗어나면
			idx_i++;
			idx_j = 1;
		}
		if (idx_i > H + 1) { // 종료조건
			return;
		}
		if (idx_j == 1) { // 가장왼쪽
			if (map[idx_i][0] == 0 && map[idx_i][1] == 0) {
				map[idx_i][0] = 1; // 선택
				map[idx_i][1] = 2;
				make_line(idx_i, idx_j + 1, count + 1);
				map[idx_i][0] = 0; // 선택x
				map[idx_i][1] = 0;
			}
			make_line(idx_i, idx_j + 1, count);
		} else if (idx_j == N) { // 가장 오른쪽
			if (map[idx_i][N - 1] == 0 && map[idx_i][N] == 0) {
				map[idx_i][N - 1] = 1; // 선택
				map[idx_i][N] = 2;
				make_line(idx_i, idx_j + 1, count + 1);
				map[idx_i][N - 1] = 0; // 선택x
				map[idx_i][N] = 0;
			}
			make_line(idx_i, idx_j + 1, count);
		} else { // 중앙에 있는거
			if ( map[idx_i][idx_j] == 0 && map[idx_i][idx_j + 1] == 0) {
				map[idx_i][idx_j] = 1; // 선택
				map[idx_i][idx_j + 1] = 2;
				make_line(idx_i, idx_j + 1, count + 1);
				map[idx_i][idx_j] = 0; // 선택x
				map[idx_i][idx_j + 1] = 0;
			}
			make_line(idx_i, idx_j + 1, count);
		}
	}

	// 줄타고 내려가서 자신위치에 도착하는지
	private static boolean check_line() {
		for (int k = 1; k <= N; k++) { // 모든 시작점
			int j = k;
			for (int go = 0; go < H + 1; go++) { // 길이만큼 반복
				j = go_line(go, j);
				map[go][j]=3;
				map[go][j]=0;
			}
			if (j != k) // 다른게 존재하면 끝
				return false;
		}
		return true;
	}

	// 줄타고 내려가기
	private static int go_line(int i, int j) {
		switch (map[i][j]) {
		case 1: // 1이면 오른쪽으로
			j++;
			break;
		case 2: // 2이면 왼쪽으로
			j--;
			break;
		}

		return j;
	}

	// 맵 출력
	private static void printmap() {
		for (int i = 0; i < H + 2; i++) {
			for (int j = 1; j < N + 1; j++) {
				System.out.print(map[i][j] + "  ");
			}
			System.out.println();
		}
		System.out.println("------------");
	}

}
