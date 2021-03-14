import java.util.*;
import java.io.*;
public class Main_S1_7576_토마토_이상민_solved {
	static int M, N;
	static int [][] map;
	static boolean [][] v;
	static int[] di = {-1,0,1,0};
	static int[] dj = { 0,1,0,-1};
	
	static class Point{
		int pi;
		int pj;
		int time;	
		public Point(int pi, int pj, int time) {
			super();
			this.pi = pi;
			this.pj = pj;
			this.time = time;
		}
		@Override
		public String toString() {
			return "[" + pi + ", " + pj + ", " + time + "]";
		}		
	}
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_7576.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 6
		M = Integer.parseInt(st.nextToken()); // 4
		map = new int[M][N];
		v = new boolean[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		bfs();
	
		
		br.close();			
	}
	static void bfs() {
		//bfs를 구현하기 위한 queue
		ArrayDeque<Point> queue = new ArrayDeque<Point>();
		//몇일째인지 저장하기 위한 time 변수
		int time = 0;
		//토마토가 모두 익었는지 안익었는지 확인하기 위한 flag
		boolean flag = false;
		//동시에 시작하기 위해 처음에 익은 토마토 영역 queue에 삽입
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]==1) {
					queue.offer(new Point(i,j, time));
					v[i][j] = true;
				}
			}
		}
		while(!queue.isEmpty()) {
			Point current = queue.poll();
			//시간 갱신
			time = current.time;
			//4방 탐색
			for (int d = 0; d < di.length; d++) {
				int ni = current.pi + di[d];
				int nj = current.pj + dj[d];
				//범위 체크 및 방문 체크
				if(ni>=0 && ni<M && nj>=0 && nj < N && map[ni][nj]==0 && !v[ni][nj]) {
					//현재 시간에 1을 더하여 queue에 삽입
					queue.offer(new Point(ni,nj, current.time+1));
					//방문처리 poll하는 부분에서 하면 메모리 초과 나므로 주의~! 
					v[ni][nj] = true;
					map[ni][nj] = 1;
				}			
			}					
		}
		//안익은 토마토가 있다면 flag를 true로 변경
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]==0) {
					flag=true;
				}
			}
		}
		//토마토가 다익었다면 false -> time 출력, 안익은 토마토가 있다면  true -> -1 출력
		if(flag) {
			System.out.println(-1);
		}else {
			System.out.println(time);			
		}
		
	}
}
