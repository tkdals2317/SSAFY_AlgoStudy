import java.io.*;
import java.util.*;

public class Solution_SW역량_5648_원자소멸시뮬레이션_구미_4_이준형 {

	static class atom { // 원자 클래스
		float i;
		float j;
		int vec;
		int power;

		public atom(float i, float j, int vec, int power) {
			this.i = i;
			this.j = j;
			this.vec = vec;
			this.power = power;
		}

		@Override
		public String toString() {
			return "atom [i=" + i + ", j=" + j + ", vec=" + vec + ", power=" + power + "]";
		}

	}

	static class pos { // 위치 클래스
		float i;
		float j;
		int count;

		public pos(float i, float j, int count) {
			this.i = i;
			this.j = j;
			this.count = count;
		}
	}

	static float[] di = { 0, 0, -(float)0.5, (float)0.5 }; // 방향벡터(상하좌우)
	static float[] dj = { (float)0.5, -(float)0.5, 0, 0 };

	static ArrayDeque<atom> atomque; // 원자큐
	static int powercount; // 원자방출에너지

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			atomque = new ArrayDeque<>();

			// 입력
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				atomque.offer(new atom(x, y, v, p));
			}

			powercount = 0; // 초기화

			// 원자 소멸 시작
			while (!atomque.isEmpty()) {

				moveatom();	// 원자이동

				// 중복검사 해서 배열에 저장
				int size = atomque.size();
				pos[] bae = new pos[size + 1]; // 원자 위치저장배열
				int pcount = 0; // 원자 배열인덱스
				
				for (int s = 0; s < size; s++) { // 큐의 사이즈만큼
					atom tmp = atomque.poll();
					boolean ans = check_exist(tmp, pcount, bae);
					if (ans == false) // 새로운 원소이면 인덱스 증가
						pcount++;
				}
				
				removefirst(bae);	// 처음중복된거 제거
			}

			System.out.println("#" + tc + " " + powercount); // 출력

		}

		br.close();
	}

	// 처음 중복 위치처리 해주기
	private static void removefirst(pos[] bae) {
		for (int k = 0; k < bae.length; k++) {
			if (bae[k] != null &&bae[k].count>1) {
				float i = bae[k].i;
				float j = bae[k].j;
				deleteatom(i, j);
			}
		}
	}

	// 원자이동
	private static void moveatom() {
		int size = atomque.size();
		for (int k = 0; k < size; k++) {
			atom tmp = atomque.poll();

			int vec = tmp.vec; // 방향
			int power = tmp.power;
			float ci = tmp.i + di[vec];
			float cj = tmp.j + dj[vec];
			
			if (ci >= -1000 && ci <= 1000 && cj >= -1000 && cj <= 1000) { // 범위조건
				atomque.offer(new atom(ci, cj, vec, power));
			}
			
		}
	}

	// 원자의 위치에 이미 존재하는지 검사
	private static boolean check_exist(atom tmp, int pcount, pos[] bae) {

		for (int k = 0; k < pcount; k++) { // 현재 인덱스 까지 검색
			if (bae[k] == null)
				break;
			if (bae[k].i == tmp.i && bae[k].j == tmp.j) { /// 같은위치 발견하면
				bae[k].count++;
				powercount += tmp.power;
				
				return true;
			}
		}
		// 중복된 위치 없으면
		bae[pcount] = new pos(tmp.i, tmp.j, 1); // 새로운 위치 추가
		atomque.offer(tmp); // 다시 원자 넣어주기

		return false;
	}

	// 중복된 해당 원자 처음꺼 제거
	private static void deleteatom(float i, float j) {
		int size = atomque.size();
		for (int k = 0; k < size; k++) {
			atom tmp = atomque.poll();
			if (tmp.i == i && tmp.j == j) { // 같은위치 아니면 다시 넣어줌	??? 이거 왜 != != 해서하면 조건 이상해지는지??
				powercount+=tmp.power;
			}else {
				atomque.offer(tmp);
			}
		}
	}

}
