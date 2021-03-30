import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Solution_5656_벽돌깨기_정소영_solved_구현 {
	static int N, W, H;
	static int[][] map, tmp, original;
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	static int result = 0, oriCnt = 0;
	static int[] choose;

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/input_5656.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			original = new int[H][W];
			tmp = new int[H][W];
			choose = new int[N];
			oriCnt = 0;
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine()," ");
				for (int j = 0; j < W; j++) {
					original[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			result = Integer.MAX_VALUE;
			f(0);
			
			
			System.out.println("#"+t+" "+result);
			
		}
		br.close();
	}
	
	
	
	private static void f(int cnt) {
		if(cnt==N) {
			arrCopy(original, map, W, H);
			for (int n = 0; n < N; n++) {
				int i = 0; 
				int j = choose[n];
				
				for (i = 0; i < H; i++) {
					if(map[i][j]!=0) {
						shoot(i,j, n);
						break;
					}
				}
			}
			
			
			// 벽돌 확인
			int count = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if(map[i][j]!=0) count++;
				}
			}
			result = Math.min(result, count);
			return;
			
		} else {
			for (int j = 0; j < W; j++) {
				choose[cnt] = j; 
				f(cnt+1);
			}

		}
	}
	
	private static void shoot(int ii, int jj, int n) {
		arrCopy(map, tmp, W, H);	// tmp에 map 저장
		
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(ii, jj));
		
		while(!q.isEmpty()) {
			Pair p = q.poll();
			int i = p.i;
			int j = p.j;
			int K = tmp[i][j];
			tmp[i][j] = 0;
			for (int l = 0; l < 4; l++) {
				int ni = i;
				int nj = j;
				for (int k = 1; k < K; k++) {
					ni = ni+di[l];
					nj = nj+dj[l];
					if(ni>=0 && ni<H && nj>=0 && nj<W) {
						q.add(new Pair(ni, nj));
					}
				}
			}
		}	//
		
		drop(tmp, map);	// 아래로 떨어뜨리기
		
	}



	private static void drop(int[][] src, int[][] des) {
		for (int j = 0; j < W; j++) {
			int[] htemp = new int[H];
			int idx = H-1;
			for (int i = H-1; i >= 0; i--) {
				if(src[i][j]!=0) {
					htemp[idx--] = src[i][j];
				}
			}
			for (int i = H-1; i >= 0; i--) {
				des[i][j] = htemp[i];
			}
		}
		
		
	}

	private static void show(int[][] src) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.print(src[i][j]+" ");
			}
			System.out.println();
		}
	}

	private static void arrCopy(int[][] src, int[][] dest, int w, int h) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				dest[i][j] = src[i][j];
			}
		}
		
	}
	
	private static class Pair{
		int i, j;

		public Pair(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		
	}

	
	

}
