import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1697
public class Main_bj_1697_숨바꼭질_정소영_solved {

	static int N, K, res = Integer.MAX_VALUE;
	static int[] visited = new int[100002];

	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		bfs(N);
		sc.close();
	}

	public static void bfs(int i) {

		if (i == K) {
			System.out.println(0);
			return;
		}

		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(i);
		visited[i] = 1;

		int temp;
		int next;
		while (!q.isEmpty()) {
			temp = q.poll();
			// -1, +1, *2
			for (int j = 0; j < 3; j++) {
				if (j == 0) {
					next = temp - 1;
				} else if (j == 1) {
					next = temp + 1;
				} else {
					next = temp * 2;
				}
				if (next == K) {
					System.out.println(visited[temp]);
					return;
				}
				if (next >= 0 && next < 100002 && visited[next] == 0) {
					q.add(next);
					visited[next] = visited[temp] + 1;
				}
			}

		}
	}

}
