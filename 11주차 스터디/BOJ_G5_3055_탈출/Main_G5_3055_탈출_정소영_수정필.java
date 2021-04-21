import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G5_3055_탈출_정소영_수정필 {
	static int r, c;
	static char[][] map;
	static int[][] temp;
	static boolean[][] visited;
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	static int resulttime = -1;
	static int goseumtime = Integer.MAX_VALUE;
	static int emptycount = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		Queue<Pair> water = new LinkedList<Pair>();

		map = new char[r][c];
		temp = new int[r][c];

		int starti = 0, startj = 0;
		int endi = 0, endj = 0;
		for (int i = 0; i < r; i++) {
			String str = br.readLine();
			for (int j = 0; j < c; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'S') { // 고슴도치
					starti = i;
					startj = j;
				} else if (map[i][j] == 'D') { // 도착
					endi = i;
					endj = j;
				} else if (map[i][j] == '*') { // 물
					water.add(new Pair(i, j));
				}
			}
		}
		// . 빈 곳, * 물, x 돌, d 비버 굴, s 고슴도치

//		goseumtime = Integer.MAX_VALUE;
//		visited = new boolean[r][c];
//		visited[starti][startj] = true;
//		movegoseum(starti, startj, endi, endj, 0);
//		System.out.println(goseumtime);

		move(starti, startj, endi, endj, water, 1);
		if (resulttime != -1)
			System.out.println(resulttime);
		else
			System.out.println("KAKTUS");
		br.close();
	}

	private static void move(int i, int j, int endi, int endj, Queue<Pair> water, int watertime) {

		System.out.println("move 함수");

		// 1. 물이 퍼진다.
		int watersize = water.size();

		if (watersize == 0) {
			return;
		}

		for (int k = 0; k < watersize; k++) {
			Pair tmp = water.poll();
			int wi = tmp.i;
			int wj = tmp.j;

			for (int d = 0; d < 4; d++) {
				int nwi = wi + di[d];
				int nwj = wj + dj[d];
				if (nwi >= 0 && nwi < r && nwj >= 0 && nwj < c) {
					if (map[nwi][nwj] == '.') { // 빈 곳이면 물을 퍼뜨릴 수 있다.
						map[nwi][nwj] = '*';
						water.add(new Pair(nwi, nwj));
					}
				}
			}
		}

		// 2. 고슴도치가 한칸 이동해본다.
		goseumtime = Integer.MAX_VALUE;
		visited = new boolean[r][c];
		visited[i][j] = true;
		movegoseum(i, j, endi, endj, 0);

		System.out.println("고슴 : " + goseumtime + " 물타임 : " + watertime + " 물 사이즈 : " + water.size());

		show();

		// 3. 만약 고슴도치가 성공했을 때, 이동한 시간이 물 퍼진 시간보다 적으면 해당 시간 출력
		if (goseumtime < Integer.MAX_VALUE) {
			if (goseumtime <= watertime) {
				resulttime = watertime;
				return;
			}
		}

		move(i, j, endi, endj, water, watertime + 1);

	}
	
	private static void spreadwater(Queue<Pair> water) {
		int watersize = water.size();

		if (watersize == 0) {
			return;
		}

		for (int k = 0; k < watersize; k++) {
			Pair tmp = water.poll();
			int wi = tmp.i;
			int wj = tmp.j;

			for (int d = 0; d < 4; d++) {
				int nwi = wi + di[d];
				int nwj = wj + dj[d];
				if (nwi >= 0 && nwi < r && nwj >= 0 && nwj < c) {
					if (map[nwi][nwj] == '.') { // 빈 곳이면 물을 퍼뜨릴 수 있다.
						map[nwi][nwj] = '*';
						water.add(new Pair(nwi, nwj));
					}
				}
			}
		}
	}
	
	
	// 앞에 visited 배열 초기화
	private static void move2(int i, int j, int endi, int endj, Queue<Pair> water, int watertime) {
		
		spreadwater(water);
		
		
		
		// 1. 고슴도치 움직임
		
		for (int d = 0; d < 4; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if (ni >= 0 && ni < r && nj >= 0 && nj < c) {
				if (map[ni][nj] == '.' && !visited[ni][nj]) {
					visited[ni][nj] = true;
					move2(ni, nj, endi,endj, water,  watertime);
					visited[ni][nj] = false;
				}
			}
		}
		
		
		
		
	}


	private static void movegoseum(int i, int j, int endi, int endj, int time) {
		// dfs가 빠르겠지??

		if (map[i][j] == 'D') {
			goseumtime = Math.min(goseumtime, time);
			return;
		}

		// visited[i][j] = true;
		for (int d = 0; d < 4; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if (ni >= 0 && ni < r && nj >= 0 && nj < c) {
				if (map[ni][nj] == '.' && !visited[ni][nj]) {
					visited[ni][nj] = true;
					movegoseum(ni, nj, endi, endj, time + 1);
					visited[ni][nj] = false;
				} else if (map[ni][nj] == 'D') {
					movegoseum(ni, nj, endi, endj, time + 1);
				}
			}
		}

	}

	private static boolean checkPossible(int i, int j, int endi, int endj) {
		if (i == endi && Math.abs(j - endj) == 1) {
			return true;
		} else if (j == endj && Math.abs(i - endi) == 1) {
			return true;
		}

		return false;
	}

	static void show() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	static class Pair {
		int i;
		int j;

		public Pair(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

	}

}
