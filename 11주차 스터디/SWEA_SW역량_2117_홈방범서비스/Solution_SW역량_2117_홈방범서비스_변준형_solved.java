import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_SW역량_2117_홈방범서비스_변준형_solved {
	public static int Ans;
	public static int N;
	public static int M;
	public static int[][] map;
	public static boolean[][] visit;
	public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T;
		T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					visit = new boolean[N][N];
					BFS(new int[] { i, j });
				}
			}
			System.out.println("#" + test_case + " " + Ans);
			Ans = 0;
		} 
	}
	
    public static void BFS(int[] rc) {
        LinkedList<int[]> q = new LinkedList<>();
        visit[rc[0]][rc[1]]=true;
        q.add(new int[] {rc[0],rc[1]});
        int[] now;
        int qsize;
        int nr;
        int nc;
        int k=0;
        int earned=0;
        int h_cnt=0; 
        while(!q.isEmpty()) {
            qsize = q.size();
            k++;
            for (int i = 0; i < qsize; i++) {
 
                now = q.poll();
                if(map[now[0]][now[1]]==1) {
                    h_cnt++;
                    earned += M;
                }
 
                for (int d = 0; d < 4; d++) {
                    nr = now[0] + dir[d][0];
                    nc = now[1] + dir[d][1];
//                    경계 안이면
                    if(nr>=0 && nr<N && nc>=0 && nc<N && !visit[nr][nc]) {
                        visit[nr][nc]=true;
                        q.add(new int[] {nr,nc});
                    }
                }
            }
//            손해 안보는 경우
            if(k * k + (k - 1) * (k - 1)<=earned) {
                Ans = Math.max(Ans, h_cnt);
            }
        }
    }
}
