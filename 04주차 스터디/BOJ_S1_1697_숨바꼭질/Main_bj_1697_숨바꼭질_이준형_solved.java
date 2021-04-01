package baekjun;
import java.io.*;
import java.util.*;

public class Main_bj_1697_숨바꼭질_구미_4_이준형3 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] bae = new int[100001];

		search(N, K, bae);

		br.close();
	}

	// 위치 찾는 BFS
	private static void search(int N, int K, int[] bae) {
		ArrayDeque<Integer> stack = new ArrayDeque<>(); // 스택
		int count = 0; // 몇번만에 찾았는지
		int x = N;
		stack.offer(x);
		while (!stack.isEmpty()) { // 스택이 빌때까지 반복
//			System.out.println(stack);
			int size = stack.size();
			for (int i = 0; i < size; i++) { // 스택 크기만큼 다 체크
				x = stack.poll();
				if (x == K) { // K찾으면 출력후 종료
					System.out.println(count);
					return;
				}
				if (x - 1 >= 0 && bae[x - 1] == 0) { // 가지치기 갔던곳은 방문 안하도록
					stack.offer(x - 1); // x-1이동
					bae[x-1]=1;
				}
				if (x * 2 <= 100000 && bae[x * 2] == 0) {
					stack.offer(x * 2); // x*2이동
					bae[x*2]=1;
				}
				if (x + 1 <= 100000 && bae[x + 1] == 0) {
					stack.offer(x + 1); // x+1이동
					bae[x+1]=1;
				}
			}
			count++;
		}

	}

}
