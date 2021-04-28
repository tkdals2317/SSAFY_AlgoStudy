import java.util.*;
import java.io.*;

public class Main_G5_9019_DSLR_이상민_solved {
	static int A, B;
	static boolean[] visited;

	static class Calc {
		int num;
		String cmd;

		public Calc(int num, String cmd) {
			super();
			this.num = num;
			this.cmd = cmd;
		}

		@Override
		public String toString() {
			return "Calc [num=" + num + ", cmd=" + cmd + "]";
		}

	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_9019.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			visited = new boolean[10001];
			bfs();
		}
		//System.out.println(calcL(1000));
		//System.out.println(calcR(102));

		br.close();
	}

	static void bfs() {
		ArrayDeque<Calc> queue = new ArrayDeque<>();
		queue.offer(new Calc(A, ""));
		visited[A] = true;
		while (!queue.isEmpty()) {
			Calc curr = queue.poll();
			int cnum = curr.num;
			String cmd = curr.cmd;
			//System.out.println(cnum);
			if (cnum == B) {
				System.out.println(cmd);
				return;
			}
			int next = (cnum * 2>9999)?(cnum*2)%10000:(cnum*2);
			if (!visited[next]) {
				visited[next] = true;
				queue.add(new Calc(next, cmd + "D"));
			}
			next = (cnum-1<0)?9999:cnum-1;
			if (!visited[next]) {
				visited[next] = true;
				queue.add(new Calc(next, cmd + "S"));
			}
			next = (cnum%1000)*10+(cnum/1000);
			if (!visited[next]) {
				visited[next] = true;
				queue.add(new Calc(next, cmd + "L"));
			}
			next = (cnum%10)*1000+(cnum/10);
			if (!visited[next]) {
				visited[next] = true;
				queue.add(new Calc(next, cmd + "R"));
			}

		}
	}


}
