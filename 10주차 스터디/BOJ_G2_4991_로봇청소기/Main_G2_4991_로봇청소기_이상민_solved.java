import java.util.*;
import java.io.*;

public class Main_G2_4991_로봇청소기_이상민_solved {
	/**
	 * 1. 최소거리를 구해야 하므로 BFS로 해결해야 되는 문제
	 * 2. 한번 간 칸은 다시 방문 X =>visited 배열로 해결
	 * 3. . = 깨끗한 칸, * =더러운칸, x = 가구, o = 로봇청소기 위치
	 * 4. 더러운칸의 위치를 모두 저장해놓고 BFS로 가장 가까운 위치 좌표를 찾아 이동 
	 * 5. 이동 후 다시 한번 더 모든 더러운 칸에 대해 최소거리를 찾기
	 * 6. 0 0 이 입력되면 끝
	 */
	static int W, H;
	static char[][] map;
	static int [][] visited;
	static int [][] adj;
	static boolean [] checked;
	static int sol;
	static int [] di = {-1, 0, 1, 0};
	static int [] dj = { 0, 1, 0,-1};
	static PriorityQueue<Point> dirtyScope = new PriorityQueue<>();
	static class Point implements Comparable<Point>{
		int ypos;
		int xpos;
		int level;
		public Point(int ypos, int xpos, int level) {
			super();
			this.ypos = ypos;
			this.xpos = xpos;
			this.level = level;
		}
		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.level, o.level);
		}
		@Override
		public String toString() {
			return "[" + ypos + ", " + xpos + ", level=" + level + "]";
		}
	}
	public static void main(String[] args) throws Exception {

		//System.setIn(new FileInputStream("res/input_bj_4991.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		//입력
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			if(W==0&&H==0) break;
			map = new char[H][W];
			visited = new int[H][W];
			sol = Integer.MAX_VALUE;
			for (int i = 0; i < H; i++) {
				Arrays.fill(visited[i], -1);
			}
			Point robot = null;
			int dirtyCnt = 0;
			for (int i = 0; i < H; i++) {
				String str = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = str.charAt(j);
					if(map[i][j]=='o') {
						robot = new Point(i,j,0);
						dirtyScope.offer(robot);
					}
					if(map[i][j]=='*') {
						dirtyCnt++;
					}
				}
			}
			//최초 탐색
			find(robot);
			//맵의 더러운 영역와 갈 수 있는 더러운 영역의 개수가 다르다면 -1 출력 후 다음 테스트케이스로
			if(dirtyCnt!=dirtyScope.size()-1) {
				dirtyScope.clear();
				sol = -1;
				sb.append(sol+"\n");
				continue;
			}
			
			int size = dirtyScope.size();
			//정점 정보를 저장할 배열
			Point[] vArr = new Point[size];
			int idx = 0;
			//큐에 있는 더러운 지역 배열에 옮겨주기
			while(!dirtyScope.isEmpty()) {
				vArr[idx++] = dirtyScope.poll();
			}			
			//모든 정점간의 거리 구하기
			adj = new int[size][size];
			for (int i = 0; i < size-1; i++) {
				for (int j = i+1; j < size; j++) {
					//bfs로 start정점에서 end종점까지 거리 구하기
					int distance = distance(vArr[i], vArr[j]);
					//인접행렬 생성
					adj[i][j] = distance;
					adj[j][i] = distance;
				}
			}
			//순열을 저장할 배열 선언
			int [] pick = new int[size];
			//방문체크를 위한 배열
			checked = new boolean[size];
			//무조건 로봇청소기의 첫 위치부터 시작해야하므로 1로 시작
			perm(1, size, pick);
			sb.append(sol+"\n");
		}
		System.out.println(sb.toString());
		br.close();
	}//end of main
	//모든 정점을 거치는 방법을 순열을 통해 구하기
	static void perm(int cnt, int size, int [] pick) {
		if(cnt==size) {
			int sum = 0;
			//순열에 뽑힌 순서대로 더러운 곳을 방문하여거 간 거리 합산
			for (int i = 0; i < size-1; i++) {
				sum += adj[pick[i]][pick[i+1]];
			}
			//최소값 갱신
			sol = Math.min(sol,sum);
			return;
		}
		//첫 시작은 로봇청소기이므로 배제 시켜주고 1부터 시작
		for (int i = 1; i < size; i++) {
			if(checked[i]) continue;
			checked[i] = true;
			pick[cnt] = i;
			perm(cnt+1, size, pick);
			checked[i] = false;			
		}
		
	}
	//정점간의 거리 구하기
	static int distance(Point start, Point end) {
		ArrayDeque<Point> queue = new ArrayDeque<Point>();
		boolean [][] visited = new boolean[H][W];
		visited[start.ypos][start.xpos] = true;
		start.level=0;
		queue.offer(start);
		while(!queue.isEmpty()) {
			Point curr = queue.poll();
			int ci = curr.ypos;
			int cj = curr.xpos;
			int cl = curr.level;
			for (int d = 0; d < 4; d++) {
				int ni = ci + di[d];
				int nj = cj + dj[d];
				if(checkRange(ni,nj)||map[ni][nj]=='x'||visited[ni][nj]) continue;
				if(ni==end.ypos&&nj==end.xpos) {
					return cl+1;
				}
				visited[ni][nj]=true;
				queue.offer(new Point(ni,nj,cl+1));
			}				
		}
		return -1;
	}
	
	//탐색하고 각 더러운 구역까지 갈 수 있는 거리를 마킹 후 큐에 집어넣기
	static void find(Point robot) {
		ArrayDeque<Point> queue = new ArrayDeque<Point>();
		queue.offer(robot);
		visited[robot.ypos][robot.xpos] = 0;
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			int ci = cur.ypos;
			int cj = cur.xpos;
			int cl = cur.level;
			
			for (int d = 0; d < 4; d++) {
				int ni = ci + di[d];
				int nj = cj + dj[d];
				if(checkRange(ni,nj)||map[ni][nj]=='x'||visited[ni][nj]>=0) continue;
				//더러운 곳 발견하면 
				if(map[ni][nj]=='*'){
					dirtyScope.add(new Point(ni,nj,cl+1));
				}
				queue.offer(new Point(ni,nj,cl+1));
				visited[ni][nj] = cl+1;
			}			
		}	
	}//end of bfs
	private static boolean checkRange(int ni, int nj) {
		if(ni<0||ni>=H||nj<0||nj>=W) return true;
		return false;
	}
	
}//end of class
