import java.io.*;
import java.util.*;

public class Main_S1_2468_안전영역_이상민_solved {
	
	static int N; // map의 크기
	static int [][] map; // map을 담을 int 2차원 배열
	static boolean [][] v; // 방문처리를 위한 boolean 2차원 배열
	static int [] di = {-1, 0, 1, 0}; //상 우 하 좌
	static int [] dj = { 0, 1, 0, -1};//상 우 하 좌
	//좌표를 표시하기 위한 클래스
	static class Point{ 
		int pi;
		int pj;
		public Point(int pi, int pj) {
			super();
			this.pi = pi;
			this.pj = pj;
		}	
	}
	
	public static void main(String[] args) throws Exception{
		
		System.setIn(new FileInputStream("res/input_bj_2468.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//input 및 초기화
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		v = new boolean[N][N];
		int min = Integer.MAX_VALUE; //최소 강수량
		int max = Integer.MIN_VALUE; //최대 강수량
		int sol = Integer.MIN_VALUE; //정답
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				min = Math.min(min, map[i][j]);
				max = Math.max(max, map[i][j]);
			}
		}
		
		int [] dSafeArea = new int[max+1];
		
		for (int d = min; d <= max; d++) {
			v = new boolean[N][N];
			int safeAreaCnt = 0;
			//잠기는 곳은 true로 변경 하여 탐색을 못하게 세팅
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j]<=d) {
						v[i][j] = true;
					}
				}
			}
			//탐색이 가능한 경우에만 bfs해주고 안전 지역 ++;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(!v[i][j]) {
						bfs(new Point(i,j));
						safeAreaCnt++;
					}
				}
			}
			dSafeArea[d] = safeAreaCnt;
		}
		//안전지역이 최대일 때 강수량 일 때 찾기
		for (int i = min; i <= max; i++) {
			sol = Math.max(sol, dSafeArea[i]);
		}
		
		if(max==1) { // 최대 높이가 1인 경우에 강수량이 0이면 아무곳도 잠기지 않으므로 안전지역은 1개
			System.out.println(1);	
		}else {
			System.out.println(sol);			
		}
		br.close();
	}
	//bfs로 같은 안전지역 탐색
	static void bfs(Point start) {
		ArrayDeque<Point> queue = new ArrayDeque<Point>();
		queue.offer(new Point(start.pi, start.pj));
		v[start.pi][start.pj] = true;
		
		while(!queue.isEmpty()) {
			Point current = queue.poll();
			for (int d = 0; d < 4; d++) {
				int ni = current.pi + di[d];
				int nj = current.pj + dj[d];
				if(ni>=0 && ni < N && nj>=0 && nj<N && !v[ni][nj]) {
					queue.offer(new Point(ni,nj));
					v[ni][nj] = true;
				}
			}
		}
		
	}
}
