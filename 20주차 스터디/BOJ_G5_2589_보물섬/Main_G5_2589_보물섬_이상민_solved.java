import java.util.*;
import java.io.*;

public class Main_G5_2589_보물섬_이상민_solved {
	static int M, N;

	static int[] di = {-1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0,-1 };

	static char[][] map;
	static int max = Integer.MIN_VALUE;
	static class Point {
		int ypos;
		int xpos;

		public Point(int ypos, int xpos) {
			super();
			this.ypos = ypos;
			this.xpos = xpos;
		}

		@Override
		public String toString() {
			return "Point [ypos=" + ypos + ", xpos=" + xpos + "]";
		}

	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2589.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[M][N];

		for (int i = 0; i < M; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				//땅일 때만 탐색 시작
				if (map[i][j] == 'L') {
					bfs(i, j);
				}
			}
		}
		
		System.out.println(max);
		
		br.close();
	}


	static void bfs(int ci, int cj) {
		ArrayDeque<Point> queue = new ArrayDeque<Point>();
		//왔던 길 다시 가선 안됨! 방문처리 배열
		boolean [][] visited = new boolean[M][N]; 
		//시작점 세팅
		queue.offer(new Point(ci, cj));
		visited[ci][cj] = true;
		//소요 시간을 측정할 변수
		int level = 1;
		while (!queue.isEmpty()) {
			//탐색할 땅에 소요된 시간을 기록하기 위해 큐에 들어온 사이즈만큼 반복문 돌리기
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Point curr = queue.poll();
				for (int d = 0; d < 4; d++) {
					int ni = curr.ypos+di[d];
					int nj = curr.xpos+dj[d];
					//범위 체크 && 땅인지 && 방문안한 곳인지
					if(ni>=0&&ni<M&&nj>=0&&nj<N&&map[ni][nj]=='L'&&!visited[ni][nj]) {
						//큐에 새로 추가
						queue.offer(new Point(ni,nj));
						visited[ni][nj]=true;
						//최대 소요 시간 갱신 
						max = Math.max(max, level);
					}
				}
			}
			//큐 사이즈만큼 돌았다면 소요시간 증가
			level+=1;
		}
	}

}
