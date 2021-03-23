import java.util.*;
import java.io.*;

public class Main_S2_1012_회전하는큐_이상민_solved {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_1021.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 큐의 크기
		int M = sc.nextInt(); // 뽑아내려는 수의 갯수
		ArrayDeque<Integer> queue = new ArrayDeque<Integer>();

		int sol = 0;
		for (int i = 1; i <= N; i++) {
			queue.offer(i);
		}
		for (int i = 0; i < M; i++) {
			int num = sc.nextInt();
			int min = 0;
			int cnt = 0, rcnt = 0, lcnt = 0;
			for (int j = 0; j < queue.size(); j++) {
				if (queue.peek() == num) {
					//System.out.print("poll : " + queue.poll());
					queue.addLast(queue.pollFirst());
					rcnt = cnt;
				} else {
					queue.addLast(queue.pollFirst());
					cnt++;
				}
			}
			cnt = 0;
			for (int j = 0; j < queue.size(); j++) {
				if (queue.peek() == num) {
					// System.out.print("poll : " + queue.poll());
					queue.addFirst(queue.pollLast());
					lcnt = cnt;
				} else {
					queue.addFirst(queue.pollLast());
					cnt++;
				}
			}
			min = Math.min(lcnt, rcnt);
			if (queue.peek() == num) {
				queue.poll();
			} else {
				while (queue.peek() != num) {
					queue.offer(queue.poll());
				}
				queue.poll();
			}
			sol += min;
		}
		queue.clear();
		System.out.println(sol);

		sc.close();
	}// end of main
}// end of class
