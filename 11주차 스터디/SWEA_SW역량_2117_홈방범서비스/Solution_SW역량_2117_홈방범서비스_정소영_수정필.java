import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_2117_홈방범서비스_정소영_수정필 {
	static int M, N, cost;
	static int[][] map;
	
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input_2117.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			
			N = Integer.parseInt(st.nextToken());	// map의 크기
			M = Integer.parseInt(st.nextToken());	// 하나의 집이 지불할 수 있는 비용

			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine()," ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			System.out.println("#"+t+" "+counthouse());
		}
		
		
		br.close();
	}


	private static int counthouse() {
		
		int result = -1;
		
		// 1. 모든 구역에서 범위를 늘려가면서 확인
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				result = Math.max(spread(i,j), result);
			}
		}
		
		
		
		return result;
	}
	
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};


	private static int spread(int ii, int jj) {
		
		
		boolean[][] visited = new boolean[N][N];
		
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {ii,jj});
		
		int K = 1; // 서비스 영역 크기
		int cost = 0;	// 비용
		int housecount = 0;	// 하우스 갯수
		int result = 0;	// 출력값
		
		while(K<N) {
			
			int size = q.size();
			for (int k = 0; k < size; k++) {
				int[] temp = q.poll();
				int i = temp[0];
				int j = temp[1];
				visited[i][j] = true;
				if(map[i][j]==1) housecount++;
				
				for (int d = 0; d < 4; d++) {
					int ni = i + di[d];
					int nj = j + dj[d];
					
					if (ni >= 0 && ni < N && nj >= 0 && nj < N) {
						if (!visited[ni][nj]) {
							q.add(new int[] { ni, nj });
							
						}
					}

				}

			}
			cost = calcoast(K, housecount);
			K++;
			if(cost<0) continue;
			result = Math.max(result, cost);
		}
		
		
		
		return result;
	}


	private static int calcoast(int k, int housecount) {
		return housecount*M - (k*k + (k-1)*(k-1));
	}

}
