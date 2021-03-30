import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


// 방법 1 정말 완전탐색
// 방법 2 특강 방법(?) -> 다시 풀기


public class Solution_1949_등산로조성_정소영_solved_완전탐색 {
	static int N, K;
	static int[][] map, tmp;
	static boolean[][] visited;
	static int result;
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		System.setIn(new FileInputStream("res/input_1949.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			ArrayList<Pair> list = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			tmp = new int[N][N];
			
			int max = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine()," ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(max<map[i][j]) {
						max = map[i][j];
						list.clear();
						list.add(new Pair(i, j));
					}else if(max==map[i][j]) {
						list.add(new Pair(i, j));
					}
				}
			}
			result = 1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int d = 1; d <= K; d++) {
						map[i][j] -= d;	// 모든 위치에서 1~K만큼 깎아서 dfs 돌린다
						for (int k = 0; k < list.size(); k++) {
							visited = new boolean[N][N];
							dfs(list.get(k), 1);
						}
						map[i][j] += d;
					}
					
				}
			}
			
			System.out.println("#"+t+" "+result);
			
		}
		br.close();
	}
	
	private static void dfs(Pair pair, int cnt) {
		
		result = Math.max(result, cnt);
		
		int i = pair.i;
		int j = pair.j;
		int height = map[i][j];
		visited[i][j] = true;
		
		for (int k = 0; k < 4; k++) {
			int ni = i+di[k];
			int nj = j+dj[k];
			
			if(ni>=0 && ni<N && nj>=0 && nj<N) {
				int nheight = map[ni][nj];
				if(nheight<height) {
					dfs(new Pair(ni, nj), cnt+1);
				}
			}
		}
		
		
	}

	static class Pair{
		int i;
		int j;
		public Pair(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		
	}

}

