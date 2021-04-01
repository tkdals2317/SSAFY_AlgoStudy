import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/17144

public class Main_bj_17144_미세먼지안녕_정소영_X {
	static int R,C,T;
	static int[][] map;
	static int[] di = {-1,1,0,0}; // 상하좌우
	static int[] dj = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		int c1, c2;
		map = new int[R][C];
		for (int i = 0; i < C; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < R; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==-1) {
					c1=i;
				}
			}
		}
		for (int t = 0; t < T; t++) {
			for (int i = 0; i < C; i++) {
				for (int j = 0; j < R; j++) {
					if(map[i][j]!=0) {
						int tmp = map[i][j]/5;
						for (int d = 0; d < 4; d++) {
							int ni = i+di[d];
							int nj = j+dj[d];
							if(ni>=0 && ni<C && nj>=0 && nj<R && map[ni][nj]==0) {
								map[ni][nj] = tmp;
								map[i][j] -= tmp;
							}
						}
					}
						
				}
			}
		}
	}

}
