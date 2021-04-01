package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_S2_7562_나이트의이동_solved {
	private static int[] di = {-2, -1, 1, 2, 2, 1, -1, -2};
    private static int[] dj = {1, 2, 2, 1, -1, -2, -2, -1};
    static int N, ans;
    static int dr, dc;
    static boolean[][] visited;
    static int[][] cnt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			visited = new boolean[N][N];
			st = new StringTokenizer(br.readLine(), " ");
			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			dr = Integer.parseInt(st.nextToken());
			dc = Integer.parseInt(st.nextToken());
			cnt = new int[N][N]; //횟수 출력용
			ans = 0; //결과 출력
			bfs(new Pos(sr, sc));
			System.out.println(ans);
		}
	}

	private static void bfs(Pos p) {
		Queue<Pos> q = new LinkedList<>();
		
		//시작점
		visited[p.row][p.col] = true;
		q.offer(p); 
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			if(cur.row == dr && cur.col == dc) {
				//도착했을 때 종료
				//제일 먼저 도착하면 종료 => 최소값
				ans = cnt[cur.row][cur.col];
				break;
			}
			
			for (int i = 0; i < di.length; i++) {//8번
				int nr = cur.row + di[i];
				int nc = cur.col + dj[i];
				
				//격자바깥X, visited 체크
				if(nr >= 0 && nc >= 0 && nr < N && nc < N && !visited[nr][nc]) {
					q.offer(new Pos(nr, nc));
					visited[nr][nc] = true;
					cnt[nr][nc] = cnt[cur.row][cur.col] + 1;
				}
			}
		}
	}
}

class Pos {
	int row;
	int col;
	
	public Pos(int row, int col) {
		this.row = row;
		this.col = col;
	}
}