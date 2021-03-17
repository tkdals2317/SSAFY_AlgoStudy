import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_bj_7576_토마토_정소영_x {
	static int M, N;
	static int map[][];
	static int temp[][];
	
	static boolean visited[][];
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		temp = new int[N][M];

		visited = new boolean[N][M];
		cnt=0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] =Integer.parseInt(st.nextToken()); 
				temp[i][j] = map[i][j];
				if(map[i][j] == 1 || map[i][j]==-1)	{
					cnt++;
				}
				if(map[i][j]==-1) visited[i][j] = true;	// 방문 금지
			}
		}
		int total=M*N;
		int res = 0;
		System.out.println(cnt);
		
		while(cnt<total) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j]==1 && visited[i][j]==false) {
						map[i][j] = 2;
						visited[i][j]=true;
						tomato(i,j); 
					}
				}
			}
			show();
			//System.out.println(cnt);
			res++;
		}
		System.out.println(res);
	}
	
	private static void show() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	private static void tomato(int i, int j) {
		for (int k = 0; k < 4; k++) {
			int ni = i+di[k];
			int nj = j+dj[k];
			
			if(ni>=0 && ni<M && nj>=0 && nj<N) {
				if(temp[ni][nj]==0 && visited[ni][nj] == false) {
					temp[ni][nj] = 1;
					visited[ni][nj] = true;
					cnt++;
				}
			}
		}
	}

}
