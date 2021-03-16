import java.util.*;
import java.io.*;

public class Main_G5_20056_마법사상어와파이어볼_이상민_X {
	static class Fireball {
		int i;
		int j;
		int m;
		int speed;
		int dir;

		public Fireball(int i, int j, int m, int speed, int dir) {
			super();
			this.i = i;
			this.j = j;
			this.m = m;
			this.speed = speed;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Fireball [i=" + i + ", j=" + j + ", m=" + m + ", dir=" + dir + ", speed=" + speed + "]";
		}

	}

	static int N, M, K;
	static ArrayList<Fireball>[][] map;
	static ArrayList<Fireball> fires;
	static int[] di = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dj = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // map크기
		M = Integer.parseInt(st.nextToken()); // 파이어볼 갯수
		K = Integer.parseInt(st.nextToken()); // 이동명령 개수

		map = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<Fireball>();
			}
		}
		fires = new ArrayList<Fireball>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int fi = Integer.parseInt(st.nextToken()) - 1; // 파이어볼 i 좌표
			int fj = Integer.parseInt(st.nextToken()) - 1; // 파이어볼 j 좌표
			int fm = Integer.parseInt(st.nextToken()); // 파이어볼 질량
			int fs = Integer.parseInt(st.nextToken()); // 파이어볼 속도
			int fd = Integer.parseInt(st.nextToken()); // 파이어볼 방향
			fires.add(new Fireball(fi, fj, fm, fs, fd));
		}
		for (int i = 0; i < K; i++) {
			move();
		}
		int sol = 0;
		for (Fireball cur : fires) {
			sol += cur.m;
		}
		System.out.println(sol);

		br.close();
	}

	static void move() {
		// 파이어 볼 이동
		for (Fireball cur : fires) {
			// 격자의 행과 열은 1번부터 N번까지 번호가 매겨져 있고, 1번 행은 N번과 연결되어 있고, 1번 열은 N번 열과 연결되어 있다.
			int ni = (cur.i + N + di[cur.dir] * (cur.speed % N)) % N;
			int nj = (cur.j + N + dj[cur.dir] * (cur.speed % N)) % N;
			// 현재 파이어볼의 위치 갱신
			cur.i = ni;
			cur.j = nj;
			map[ni][nj].add(cur);
		}
		// 맵에 파이어볼이 두개 이상 겹친 경우
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].size() == 1)
					map[i][j].clear();
				if (map[i][j].size() == 0)
					continue;
				explosion(i, j);
				
			}
		}
	}
	static void explosion(int i, int j) {
		int mSum = 0; // 질량합
		int sSum = 0; // 속도합
		boolean dirIsEven;
		if ((map[i][j].get(0).dir % 2) == 0) {
			dirIsEven = true;
		}
		boolean even = map[i][j].get(0).dir % 2 == 0 ? true : false;
		boolean odd = map[i][j].get(0).dir % 2 == 1 ? true : false;

		for (Fireball cur : map[i][j]) {
			mSum += cur.m;
			sSum += cur.speed;
			even = even & cur.dir % 2 == 0 ? true : false;
			odd = odd & cur.dir % 2 == 1 ? true : false;
			fires.remove(cur);
		}
		// 질량 5로 나눠주기
		int newMass = mSum / 5;
		// 합쳐진 파이어볼 갯수 구하기
		int size = map[i][j].size();
		// map에서 파이어볼 없애기
		map[i][j].clear();

		if (newMass == 0) return;
		// 더해진 스피드 합쳐진 파이어볼 갯수 만큼 나눠주기
		int newSpeed = sSum / size;
		// 새롭게 나눠진 파이어볼 추가
		if (even | odd) {
			for (int k = 0; k < 8; k += 2) {
				fires.add(new Fireball(i, j, newMass, newSpeed, k));
			}
		} else {
			for (int k = 1; k < 8; k += 2) {
				fires.add(new Fireball(i, j, newMass, newSpeed, k));
			}
		}

	}
}
