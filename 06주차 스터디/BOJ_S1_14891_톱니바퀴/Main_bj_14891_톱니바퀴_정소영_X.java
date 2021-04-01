import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main_bj_14891_톱니바퀴_정소영_X {
	static int al, ar, bl, br, cl, cr, dl, dr;
	static int[][] rlrl = new int[4][2];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Deque<Integer> a = new ArrayDeque<>();
		Deque<Integer> b = new ArrayDeque<>();
		Deque<Integer> c = new ArrayDeque<>();
		Deque<Integer> d = new ArrayDeque<>();

		// 양방향이 가능한 deque에 값 입력받음
		// 오른쪽 2번, 왼쪽 6번
		char str[];
		int cnt = 0;
		str = sc.next().toCharArray();
		for (int i = 0; i < 8; i++) {
			a.offer(str[i] - '0'); // 큐로 넣음
			if (i == 2) {
				rlrl[cnt][0] = a.peekLast();
			}
			if (i == 6) {
				rlrl[cnt++][1] = a.peekLast();
			}
		}
		str = sc.next().toCharArray();
		for (int i = 0; i < 8; i++) {
			b.offer(str[i] - '0'); // 큐로 넣음
			if (i == 2) {
				rlrl[cnt][0] = b.peekLast();
			}
			if (i == 6) {
				rlrl[cnt++][1] = b.peekLast();
			}
		}
		str = sc.next().toCharArray();
		for (int i = 0; i < 8; i++) {
			c.offer(str[i] - '0'); // 큐로 넣음
			if (i == 2) {
				rlrl[cnt][0] = c.peekLast();
			}
			if (i == 6) {
				rlrl[cnt++][1] = c.peekLast();
			}
		}
		str = sc.next().toCharArray();
		for (int i = 0; i < 8; i++) {
			d.offer(str[i] - '0'); // 큐로 넣음
			if (i == 2) {
				rlrl[cnt][0] = d.peekLast();
			}
			if (i == 6) {
				rlrl[cnt++][1] = d.peekLast();
			}
		}
		int n = sc.nextInt();
		// 입력 받기
		int dira, dirb, dirc, dird;
		for (int i = 0; i < n; i++) {
			int gearn = sc.nextInt(); // 움직일 기어 위치
			int dir = sc.nextInt(); // 움직일 방향 -> 1 시계, -1반시계
			if (gearn == 1) {
				dira = dir;
				// rlrlrlrl
				// right는 0, left는 1
				if (rlrl[0][0] != rlrl[1][1]) { // ns 극이 다를 경우
					if (dira == 1) {
						dirb = -1;
					} else {
						dirb = 1;
					}
					turn(a, dira);
					turn(b, dirb); // b 회전 끝
					turnrl(a, 0);
					turnrl(b, 1);
					// c 시작
					if (rlrl[1][0] != rlrl[2][1]) { // ns 극이 다를 경우
						if (dirb == 1) {
							dirc = -1;
						} else {
							dirc = 1;
						}
						turn(b, dirb); // b 회전 끝
						turn(c, dirc);
						turnrl(b, 1);
						turnrl(c, 2);

						if (rlrl[2][0] != rlrl[3][1]) {
							if (dirc == 1) {
								dird = -1;
							} else {
								dird = 1;
							}
							turn(c, dirc);
							turn(d, dird); // b 회전 끝
							turnrl(c, 2);
							turnrl(d, 3);
						} else {
							turn(c, dirc);
							turnrl(c, 2);
						}

					} else { // ns 극이 같을 경우
						// c는 안움직이고 끝
						turn(b, dirb); // b 회전 끝
						turnrl(b, 1);
					}

				} else { // b는 안움직이고 끝
					turn(a, dira);
					turnrl(a, 0);
				}
			}
		}
		int res = 0;
		if (a.pop() == 1) {
			res += 1;
		}
		if (b.pop() == 1) {
			res += 2;
		}
		if (c.pop() == 1) {
			res += 4;
		}
		if (d.pop() == 1) {
			res += 8;
		}

		System.out.println(res);
	}

	private static void turnrl(Deque<Integer> a, int k) {
		for (int i = 0; i < 8; i++) {
			if (i == 2) {
				rlrl[k][0] = a.peekFirst();
			}
			if (i == 6) {
				rlrl[k][1] = a.peekFirst();
			}
			a.offer(a.pop());
		}
	}

// 스택 : peek, pop, push
// 큐 : peek, poll, offer
	private static void turn(Deque<Integer> a, int d) {
		if (d == -1) { // 반시계
			a.offer(a.pop());
		} else if (d == 1) {
			a.push(a.poll());
		}
	}

}
