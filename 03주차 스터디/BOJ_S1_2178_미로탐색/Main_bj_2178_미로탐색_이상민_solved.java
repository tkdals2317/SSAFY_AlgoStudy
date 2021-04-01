import java.util.*;
import java.io.*;

public class Main_bj_2178_미로탐색_이상민_solved {
	static int[] di = {-1,0,+1,0}; //상 우 하 좌
	static int[] dj = {0,1,0,-1};
	private static int N;
	private static int M;
	private static int[][] miro;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2178.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		miro = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				miro[i][j] = str.charAt(j)-'0';
			}
		}		
		bfs(0, 0);
		br.close();
		
	}
	static class Point{
		int i, j, depth;
		Point(int i, int j, int depth){
			this.i = i;
			this.j = j;
			this.depth = depth;
		}
		@Override
		public String toString() {
			return "("+i + "," + j + ") d : " + depth;
		}
		
	}
	private static void bfs(int si, int sj) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(sj,sj,1));
		miro[si][sj] = 2;
		while(!queue.isEmpty()) {
			Point temp = queue.poll();
			int ci = temp.i;
			int cj = temp.j;
			int cDepth = temp.depth;

			for (int i = 0; i < 4; i++) {
				int ni = ci+di[i];
				int nj = cj+dj[i];

				if(ni>=0&&ni<N&&nj>=0&&nj<M&&miro[ni][nj]==1) {
					queue.offer(new Point(ni, nj, cDepth+1));
					miro[ni][nj]=2;
				}
			}
			
			if(miro[N-1][M-1]==2) {
				//마지막 종료지점에서 moveCount를 하나 더 증가 시켜준다
				System.out.println(cDepth+1);
				break;
			}
		}		
	}
}
