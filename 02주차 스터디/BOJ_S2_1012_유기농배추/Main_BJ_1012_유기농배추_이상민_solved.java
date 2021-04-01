import java.io.*;
import java.util.StringTokenizer;

public class Main_BJ_1012_유기농배추_이상민_solved {
	static int [] di= {-1,0,1,0};
	static int [] dj= {0,1,0,-1};
	private static int N;
	private static int M;
	private static int[][] map;
	private static int cabbageCnt;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_1012.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			cabbageCnt = Integer.parseInt(st.nextToken());
			map = new int [N][M];
			int sol = 0;
			for (int i = 0; i < cabbageCnt; i++) {
				st = new StringTokenizer(br.readLine()," ");
				int ci = Integer.parseInt(st.nextToken());
				int cj = Integer.parseInt(st.nextToken());
				map[ci][cj] = 1;
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					//1일때만 검사 dfs 진행 
					if(map[i][j]==1) sol += dfs(i,j);
				}
			}
			
			System.out.println(sol);
			
		}//end of testcase
		br.close();
	}//end of main
	static int dfs(int ci, int cj) {
		map[ci][cj] = 2;
		for (int i = 0; i < 4; i++) {
			int ni = ci + di[i];
			int nj = cj + dj[i];
			if(ni>=0&&ni<N&&nj>=0&&nj<M&&map[ni][nj]==1) {
				dfs(ni,nj);
			}
		}
		return 1;
	}
}//end of class
