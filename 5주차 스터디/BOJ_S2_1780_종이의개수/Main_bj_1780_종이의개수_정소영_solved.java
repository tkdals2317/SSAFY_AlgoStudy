import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1780
public class Main_bj_1780_종이의개수_정소영_solved {
	static int N;
	static int[][] map;
	static int rone=0, rzero=0, rminus=0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		paper(0,0,N);
		System.out.println(rminus);
		System.out.println(rzero);
		System.out.println(rone);
		br.close();
	}
	private static void paper(int i, int j, int n) {
		int one=0;
		int zero=0;
		int minus=0;
		for (int k = 0; k < 3; k++) {
			for (int k2 = 0; k2 < 3; k2++) {
				for (int l = i; l < i+n/3; l++) {
					for (int l2 = j; l2 < j+n/3; l2++) {
						if(map[n/3*k+l][n/3*k2+l2]==1) {
							one++;
						}else if(map[n/3*k+l][n/3*k2+l2]==0) {
							zero++;
						}else {
							minus++;
						}
					}
				}
				if(n*n/9==one) {
					rone++;
				}else if(n*n/9==zero) {
					rzero++;
				}else if(n*n/9==minus) {
					rminus++;
				}else {
					paper(n/3*k, n/3*k2, n/3);
				}
				one=0;
				zero=0;
				minus=0;
				
			}
		}
	}

}
