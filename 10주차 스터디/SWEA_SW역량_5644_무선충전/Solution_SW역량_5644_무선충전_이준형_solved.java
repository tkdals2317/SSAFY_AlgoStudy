import java.io.*;
import java.util.*;

public class Solution_SW역량_5644_무선충전_구미_4_이준형 {

	static class battery implements Comparable<battery> { // 배터리
		int x; // 좌표
		int y;
		int c; // 거리
		int p; // 세기

		public battery(int x, int y, int c, int p) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}

		@Override
		public int compareTo(battery o) { // 배터리 세기 역순 정렬
			return -Integer.compare(p, o.p);
		}
	}

	static class pos { // 사람 위치
		int x;
		int y;

		public pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int[] dx = { 0, 0, 1, 0, -1 }; // 방향벡터
	static int[] dy = { 0, -1, 0, 1, 0 };

	static int C; // 충전기 개수
	static int count; // 충전량
	static PriorityQueue<battery> aque; // 배터리 저장 우선순위 큐
	static PriorityQueue<battery> bque;
	static battery[] bae; // 배터리 배열
	static pos A; // 사람 위치
	static pos B;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) { // 테케만큼 반복
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			int[] moveA = new int[M]; // 사람의 이동정보 배열에 입력
			int[] moveB = new int[M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				moveB[i] = Integer.parseInt(st.nextToken());
			}
			bae = new battery[C];
			for (int i = 0; i < C; i++) { // 배터리 정보 입력
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				bae[i] = new battery(x, y, c, p);
			}

			count = 0; // 초기화
			aque = new PriorityQueue<>(); // 배터리 저장 우선순위큐
			bque = new PriorityQueue<>();
			A = new pos(1, 1);
			B = new pos(10, 10);
			find_battery(); // 충전 가능한 배터리 찾기 //처음 위치 처리
			choose_battery(); // 사용할 배터리 선택
			for (int m = 0; m < M; m++) { // 시간만큼 반복
				aque.clear(); // 큐 비워주기
				bque.clear();
				A.x += dx[moveA[m]]; // 위치 이동
				A.y += dy[moveA[m]];
				B.x += dx[moveB[m]];
				B.y += dy[moveB[m]];
				find_battery(); // 충전 가능한 배터리 찾기
				choose_battery(); // 사용할 배터리 선택
			}
			System.out.println("#" + tc + " " + count); // 답 출력
		}

		br.close();
	}

	// 사용할 배터리 선택
	private static void choose_battery() {
		battery abat = aque.poll(); // 큐에서 하나씩 꺼내기
		battery bbat = bque.poll();

		if (abat == null && bbat == null) // 둘다 빈경우
			return;
		if (abat == null) { // 한쪽이 빈경우 나머지쪽을 사용
			count += bbat.p;
			return;
		}
		if (bbat == null) {
			count += abat.p;
			return;
		}
		if (abat.equals(bbat)) { // 두개가 같은 배터리인 경우 한번더 빼서 체크
			battery tmp1 = aque.poll();
			battery tmp2 = bque.poll();
			if (tmp1 == null && tmp2 == null) {	//양쪽 빈경우
				count += abat.p;
				return;
			}
			if (tmp1 == null) {		//한쪽이 빈경우
				count += abat.p + tmp2.p;
				return;
			}
			if (tmp2 == null) {
				count += bbat.p + tmp1.p;
				return;
			}
			if (abat.p + tmp2.p > bbat.p + tmp1.p) {	//다 인비면 크기 비교
				count += abat.p + tmp2.p;
				return;
			} else {
				count += bbat.p + tmp1.p;
				return;
			}
		}else {	//같은 배터리 아니면 두개 더하고 종료
			count+=abat.p+bbat.p;
			return;
		}
		
	}

	// 충전 가능한 배터리 찾기
	private static void find_battery() {
		for (int i = 0; i < C; i++) { // A,B 범위에 배터리 있으면 큐에 넣기
			if (Math.abs(bae[i].x - A.x) + Math.abs(bae[i].y - A.y) <= bae[i].c)
				aque.offer(bae[i]);
			if (Math.abs(bae[i].x - B.x) + Math.abs(bae[i].y - B.y) <= bae[i].c)
				bque.offer(bae[i]);
		}
	}

}
