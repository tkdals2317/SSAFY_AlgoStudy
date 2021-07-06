package programmers;

public class Soulition_programmers_level2_컬러링북2 {
	static int cnt;
	static int[] answer;
	static int [][] map;
	static int [] dx= {0,1,0,-1};
	static int [] dy= {1,0,-1,0};
	static int N ,M;
	
	public static void main(String[] args) {
		int m = 6; 
		int n = 4; 
//		int[][] picture = {{1, 1, 1, 0}, {1, 1, 1, 0}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}};
		int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
		/////////////////////////////////////////////////////
		N=n;
		M=m;
		map = new int [M][N];
		for(int i=0; i<M; i++)
			for(int j=0; j<N; j++)
				map[i][j]=picture[i][j];
		answer = new int[2];
		for(int i=0; i<M; i++)
			for(int j=0; j<N; j++)
				if(map[i][j]>0) {
					cnt =0;
					answer[0]++;
					dfs(i,j,map, map[i][j]);
					answer[1]=Math.max(answer[1], cnt);
//					print(map);
				}
		System.out.println(answer[0]+" "+answer[1]);

	}
//	private static void print(int[][] map) {
//		for(int i=0; i<M; i++) {
//			System.out.println();
//			for(int j=0; j<N; j++)
//				System.out.print(map[i][j]+" ");
//		}
//		System.out.println();
//	}
	private static void dfs(int y, int x, int[][] map, int target) {
		map[y][x]=0;
		cnt++;
		for(int i=0; i<4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if(ny<0 || nx <0 || ny>=M || nx>=N) continue;
			if(map[ny][nx]==target) {
				map[ny][nx]=0;
				dfs(ny,nx,map, target);
			}
		}
	}
}
