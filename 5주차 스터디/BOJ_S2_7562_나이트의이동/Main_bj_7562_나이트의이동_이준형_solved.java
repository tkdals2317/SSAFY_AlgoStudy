package baekjun;
import java.io.*;
import java.util.*;

public class Main_bj_7562_나이트의이동_구미_4_이준형 {

	static class Pos { // 위치 저장하는 클래스
		int i;
		int j;

		public Pos() {
		}

		public Pos(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) { // 테케 만큼 반복
			int L = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int i = Integer.parseInt(st.nextToken()); // 시작점
			int j = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			int end_i = Integer.parseInt(st.nextToken()); // 끝나는점
			int end_j = Integer.parseInt(st.nextToken());

			search(i, j, end_i, end_j, L); // 함수 호출

		}

		br.close();
	}

	// bfs 몇번째 만에 위치 찾는지(맨하탄 거리)
	private static void search(int i, int j, int end_i, int end_j, int L) {

		int[] di = { -2, -2, -1, -1, 1, 1, 2, 2 }; // 방향벡터
		int[] dj = { 1, -1, 2, -2, 2, -2, 1, -1 };
		int[][] check = new int[L][L];

		Queue<Pos> queue = new ArrayDeque<>(); // 위치 저장할 스택
		int count = 1; // 몇번만에 목적지 도달하는지
		queue.offer(new Pos(i, j)); // 처음 위치 넣기
		check[i][j] = count;

		while (!queue.isEmpty()) { // 큐가 안비었으면 계속 반복
			int size = queue.size();
			for (int t = 0; t < size; t++) { // 처음 큐의 원소 개수 만큼 반복
				Pos pos = queue.poll();
//				System.out.println(pos_tmp.i + " " + pos_tmp.j);
				if (pos.i == end_i && pos.j == end_j) { // 목적지 도착하면 횟수 출력
					check[pos.i][pos.j] = count;
//					printbae(L,check);
					System.out.println(count - 1);
					queue.clear();
					return;
				}
				check[pos.i][pos.j] = count; // 방문체크

				for (int k = 0; k < 8; k++) { // 갈수있는곳 탐색
					int ci = pos.i + di[k];
					int cj = pos.j + dj[k];
					if (ci >= 0 && ci < L && cj >= 0 && cj < L && check[ci][cj] == 0) {// 나가지 않고 방문하지 않았으면
						queue.offer(new Pos(ci, cj)); // 큐에 넣기
						check[ci][cj]=count;
					}
				}
			}
//			printbae(L,check);
			count++; // 횟수 증가
		}
	}

	private static void printbae(int L, int[][] check) {
		for (int i = 0; i < L; i++) {
			for (int j = 0; j < L; j++) {
				System.out.print(check[i][j]);
			}
			System.out.println();
		}
		System.out.println("==============");
	}

}
