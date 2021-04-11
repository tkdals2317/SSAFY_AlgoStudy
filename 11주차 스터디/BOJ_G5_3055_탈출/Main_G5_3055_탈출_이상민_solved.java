import java.util.*;
import java.io.*;

public class Main_G5_3055_탈출_이상민_solved {
	/**
	 * '.' = 비어있는 곳,  '*' = 물이 차있는 지역, 'X' = 돌, 'D' = 비버의 굴, 'S' = 고슴도치
	 */
	static int R, C;
	static char [][] map;
	static boolean [][][] visited;
	static int [] di = {-1, 0, 1, 0};
	static int [] dj = { 0, 1, 0,-1};
	static int sol = Integer.MAX_VALUE;
	static class Point{
		int ypos;
		int xpos;
		int level;
		boolean isWater;
		
		public Point(int ypos, int xpos, int level, boolean isWater) {
			super();
			this.ypos = ypos;
			this.xpos = xpos;
			this.level = level;
			this.isWater = isWater;
		}
		
		@Override
		public String toString() {
			return "[" + ypos + ", " + xpos + ", " + level + ", " + (isWater ? "water":"gosum") + "]";
		}
	}
	static ArrayDeque<Point> queue;
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_bj_3055.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		//visited[R][C][0] : 물맵
		//visited[R][C][1] : 고슴도치맵
		visited = new boolean[R][C][2];
		queue = new ArrayDeque<Point>();
		Point start = null;
		
		for (int i = 0; i < R; i++) {
			String row = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = row.charAt(j);
				//돌
				if(map[i][j]=='X') {
					//어차피 둘 다 못움직임
					//물 확장 맵에 true
					visited[i][j][0] = true;
					//비버 이동 맵에 true
					visited[i][j][1] = true;
				}
				//물
				if(map[i][j]=='*') {
					//큐에 물 영역 추가
					queue.offer(new Point(i,j,0,true));
					//둘다 못움직임
					visited[i][j][0] = true;
					visited[i][j][1] = true;
				}
				//고슴도치
				if(map[i][j]=='S') {
					start = new Point(i,j,0,false);
					//고슴도치 이동 맵에만 true 물은 고슴도치가 왔던 길을 갈 수 있다
					visited[i][j][1] = true;
				}
			}
		}
		boolean success = bfs(start);
		if(success) System.out.println(sol);
		else System.out.println("KAKTUS");
		
		br.close();
	}//end of main
	static boolean bfs(Point start) {
		boolean success = false;
		//처음에는 물이 먼저 들어가있는 상태에서 고슴도치 추가
		queue.offer(start);
		while(!queue.isEmpty()) {
			Point curr = queue.poll();
			int ci = curr.ypos;
			int cj = curr.xpos;
			int cl = curr.level;
			boolean cIsWater = curr.isWater;
			for (int d = 0; d < 4; d++) {
				int ni = ci + di[d];
				int nj = cj + dj[d];
				//물일때
				if(cIsWater) {
					//물맵만 범위체크
					if(ni<0||ni>=R||nj<0||nj>=C||visited[ni][nj][0]||map[ni][nj]=='D') continue;
					queue.offer(new Point(ni,nj,cl+1,cIsWater));
					//왔던길을 다시 안가기 위한 물맵 방문처리
					visited[ni][nj][0] = true;
					//고슴도치는 물이 생기는 곳에 갈 수 없으므로 방문처리
					visited[ni][nj][1] = true;
				}else { //고슴도치일때
					//물맵과 고슴도치맵 둘다 검사
					if(ni<0||ni>=R||nj<0||nj>=C||visited[ni][nj][0]||visited[ni][nj][1]) continue;
					//비버굴에 도착한다면
					if(map[ni][nj]=='D') {
						success = true;
						sol = cl+1;
						return success;
					}
					queue.offer(new Point(ni,nj,cl+1,cIsWater));
					//물은 고슴도치가 지나갔던 길을 갈 수 있으므로 고슴도치맵에만 방문처리
					visited[ni][nj][1] = true;
				}
			}	
		}
		return success;
	}	
}//end of class
