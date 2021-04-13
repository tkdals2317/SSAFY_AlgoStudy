import java.io.*;
import java.util.*;

public class Main_bj_3055_탈출_구미_4_이준형2 {

	static class pos {
		int i;
		int j;

		public pos(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}

	static int[] di = { 0, 0, 1, -1 };
	static int[] dj = { 1, -1, 0, 0 };

	static int R, C; // R행 C열
	static char[][] map;
	static int endi, endj;

	static ArrayDeque<pos> water; // 물
	static ArrayDeque<pos> dochi; // 고슴도치

	static int time_count;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		dochi = new ArrayDeque<>(); // 선언
		water = new ArrayDeque<>();

		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				char tmp = str.charAt(j);
				map[i][j] = tmp;
				if (tmp == 'D') { // 발견하면 넣기
					endi = i;
					endj = j;
				} else if (tmp == 'S') {
					dochi.offer(new pos(i, j));
//					map[i][j] = '.'; // 고슴도치 자리 비워주기
				} else if (tmp == '*') {
					water.offer(new pos(i, j));
				}
			}
		}

		int ans = play();
		
//		System.out.println(endi+" "+endj);
//		System.out.println(time_count);
		
		if (ans == -1)
			System.out.println("KAKTUS");
		else
			System.out.println(time_count);

		br.close();
	}

	private static int play() {
		time_count = 0; // 시간체크
		while (true) {
//			printmap();

			water_flow();
			int ans = dochi_move();
			if (ans != 0)
				return ans;
			time_count++;
		}
	}

	private static int dochi_move() {
		if (dochi.isEmpty()) // 이동못하면 불가능 반환
			return -1;
		int size = dochi.size();
		for (int s = 0; s < size; s++) {
			pos tmp = dochi.poll();
			if (tmp.i == endi && tmp.j == endj) // 마지막위치에 도달하면 끝
				return 1;
			for (int k = 0; k < 4; k++) {
				int ci = tmp.i + di[k];
				int cj = tmp.j + dj[k];
				if (ci >= 0 && ci < R && cj >= 0 && cj < C && (map[ci][cj] == '.' || map[ci][cj]=='D')) {
					dochi.offer(new pos(ci, cj));
					map[ci][cj]='S';
				}
			}
		}

		return 0;
	}

	// 풀퍼지는거
	private static void water_flow() {
		if (water.isEmpty()) // 비었으면 끝내기
			return;
		int size = water.size();
		for (int s = 0; s < size; s++) {
			pos tmp = water.poll();
			for (int k = 0; k < 4; k++) {
				int ci = tmp.i + di[k];
				int cj = tmp.j + dj[k];
				if (ci >= 0 && ci < R && cj >= 0 && cj < C && map[ci][cj] == '.') {
					map[ci][cj] = '*';
					water.offer(new pos(ci, cj));
				}
			}
		}
	}
	
	//맵출력
	private static void printmap() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j]);
			}System.out.println();
		}System.out.println("------------");
	}

}
