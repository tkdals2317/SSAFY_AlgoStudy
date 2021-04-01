package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_S1_7576_토마토_정태현_solved {
	static int[] di = { 0, 0, -1, 1 }; //좌우하상
    static int[] dj = { -1, 1, 0, 0 };
	static int M, N, ans;
	static int[][] tom, cnt;
	static boolean[][] visited;
	static Queue<Pos> q;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		ans = 0;
		
		tom = new int[N][M];
		cnt = new int[N][M]; //토마토 익는 일수 체크용
		visited = new boolean[N][M];
		q = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				tom[i][j] = Integer.parseInt(st.nextToken());
				if(tom[i][j] == 1) {
					q.offer(new Pos(i, j));
					visited[i][j] = true;
				}
			}
		}
		
		bfs();
		
		System.out.println(ans);
	}
	private static void bfs() {
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for (int i = 0; i < di.length; i++) {
				int nr = cur.r + di[i];
				int nc = cur.c + dj[i];
				
				if(nr >= 0 && nc >= 0 && nr < N && nc < M && !visited[nr][nc]) {
					if(tom[nr][nc]==0 && cnt[nr][nc]==0) { //bfs 돌기위한 조건
						q.offer(new Pos(nr, nc));
						visited[nr][nc] = true;
						cnt[nr][nc] = cnt[cur.r][cur.c] + 1; //해당 칸 토마토가 익는 일수						
					} 
				}
			}
		}
		
		answer();
	}
	
	private static void answer() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				ans = Math.max(ans, cnt[i][j]);
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(tom[i][j] == 0 && cnt[i][j] == 0) ans = -1; //안익은게 있다
			}
		}
		
	}

}
class Pos {
	int r;
	int c;
	public Pos(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}
}