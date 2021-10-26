package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Soulition_BOJ_21611_마법사상어와블리자드 {
	static int[][] map;
	static int[] rx = { 0, 0, 0, -1, 1 };
	static int[] ry = { 0, -1, 1, 0, 0 };
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	private static StringTokenizer st;
	private static BufferedReader br;
	private static int N, M;
	private static int answer = 0;
	private static List<Integer> list;
	private static ArrayList<Integer> list2;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			list = new ArrayList<Integer>();

			remove(d, s);
			move();
			boom();
			map = new int[N][N];
			change();
		}
		System.out.println(answer);
	}

	private static void change() {
		list2 = new ArrayList<Integer>();
		int cnt = 1;
		if (list.size() == 0)
			return;
		for (int i = 0; i < list.size() - 1; i++) {
			if (list.get(i) != list.get(i + 1)) {
				list2.add(cnt);
				list2.add(list.get(i));
				cnt = 1;
			} else {
				cnt++;
			}
		}
		list2.add(cnt);
		if (list.size() != 0)
			list2.add(list.get(list.size() - 1));

		fillMap();
	}

	private static void fillMap() {
		int ny = N / 2;
		int nx = N / 2;
		int dir = 0;
		int size = 1;
		int idx = 0;
		while (isValid(ny, nx)) {
			if (idx >= list2.size())
				break;
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < size; j++) {
					if (idx >= list2.size())
						break;
					ny = ny + dy[dir];
					nx = nx + dx[dir];

					if (!isValid(ny, nx))
						continue;
					map[ny][nx] = list2.get(idx);
					idx++;
				}
				dir = (dir + 1) % 4;
			}
			size++;
		}

	}

	private static void boom() {
		boolean flag = true;
		while (flag) {
			int idx = 0;
			int cnt = 1;
			flag = false;
			while (idx < list.size() - 1) {
				if (list.get(idx) != list.get(idx + 1)) {
					if (cnt > 3) {
						for (int i = 0; i < cnt; i++) {
							answer += list.get(idx + 1 - cnt);
							list.remove(idx + 1 - cnt);
						}
						idx -= cnt;
						flag = true;
						cnt = 0;
						idx++;
						continue;
					}
					cnt = 1;
				} else {
					cnt++;
				}
				idx++;
			}
			if (cnt > 3) {
				for (int i = 0; i < cnt; i++) {
					answer += list.get(idx + 1 - cnt);
					list.remove(idx + 1 - cnt);
				}
			}
		}
	}

	private static void move() {
		int ny = N / 2;
		int nx = N / 2;
		int dir = 0;
		int size = 1;
		while (isValid(ny, nx)) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < size; j++) {
					ny = ny + dy[dir];
					nx = nx + dx[dir];

					if (!isValid(ny, nx))
						continue;
					if (map[ny][nx] > 0) {
						list.add(map[ny][nx]);
					}
				}
				dir = (dir + 1) % 4;
			}
			size++;
		}

	}

	private static void remove(int d, int s) {
		int y = N / 2;
		int x = N / 2;

		for (int i = 0; i < s; i++) {
			y = y + ry[d];
			x = x + rx[d];

			if (isValid(y, x))
				map[y][x] = -1;
		}
	}

	private static boolean isValid(int y, int x) {
		return (y >= 0 && x >= 0 && y < N && x < N);
	}
}
