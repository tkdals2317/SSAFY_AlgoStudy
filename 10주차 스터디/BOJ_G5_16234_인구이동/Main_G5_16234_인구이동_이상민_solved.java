import java.util.*;
import java.io.*;
/**
국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선을 오늘 하루동안 연다.
위의 조건에 의해 열어야하는 국경선이 모두 열렸다면, 인구 이동을 시작한다.
국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으면, 그 나라를 오늘 하루 동안은 연합이라고 한다.
연합을 이루고 있는 각 칸의 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수)가 된다. 편의상 소수점은 버린다.
연합을 해체하고, 모든 국경선을 닫는다.

아이디어1
모든 국가에 대해 BFS를 돌려서 현재 자신국가와 가려고하는 국가의 인구수 차이가 L과 R사이면 방문
어레이리스트에 추가하고 그 리스트의 값의 평균으로 값을 바꿔줌 
위 과정을 할때마다 카운트 해주고 while로 돌다가 더이상 바뀌는 값이 없으면 loop 탈출
*/
public class Main_G5_16234_인구이동_이상민_solved {
	static int N, L, R;
	static int [][] map;
	static boolean [][] visited;
	static int [] di = {-1, 0, 1, 0};
	static int [] dj = { 0, 1, 0,-1};
	static class City{
		int ypos;
		int xpos;
		int popu;
		public City(int ypos, int xpos, int popu) {
			super();
			this.ypos = ypos;
			this.xpos = xpos;
			this.popu = popu;
		}
		@Override
		public String toString() {
			return "City [ypos=" + ypos + ", xpos=" + xpos + ", popu=" + popu + "]";
		}
	
	}
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_bj_16234.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	
		int sol = 0;
		while(true) {
			visited = new boolean[N][N];
			boolean canMove = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					//방문하지 않은 나라에 대해서만
					if(!visited[i][j]) {
						//인구수 이동이 있으면 true로 변경
						if(bfs(new int[] {i,j})) {							
							canMove = true;
						}
					}
				}
			}
			//인구수이동이 하나도 일어나지 않았다면 loop탈출
			if(!canMove) break;
			sol++;
			
		}
		System.out.println(sol);	
		
		br.close();
	}

	private static boolean bfs(int[] start) {
		//이동할 수 있는 지 없는지를 판단하는 boolean 변수
		boolean flag = false;
		ArrayDeque<int[]> queue = new ArrayDeque<int[]>();
		//이동할 수 있는 나라를 저장할 어레이 리스트
		ArrayList<City> cList = new ArrayList<City>();
		//시작 도시 리스트에 추가
		cList.add(new City(start[0],start[1],map[start[0]][start[1]]));
		//시작 도시 방문처리
		visited[start[0]][start[1]]= true;
		queue.offer(start);
		
		while(!queue.isEmpty()) {
			int [] curr = queue.poll();
			int ci = curr[0];
			int cj = curr[1];
			for (int d = 0; d < 4; d++) {
				int ni = ci+di[d];
				int nj = cj+dj[d];
				//범위 체크
				if(ni<0||ni>=N||nj<0||nj>=N||visited[ni][nj]) continue;
				//현재 나라와 가려고하는 나라의 인구수 차이
				int diff = Math.abs(map[ci][cj]-map[ni][nj]);
				//인구수 차이가 범위 안이라면
				if(L<=diff&&diff<=R) {
					//이동이 일어나므로 true로 변경
					flag = true;
					visited[ni][nj] = true;
					queue.offer(new int[] {ni, nj});
					//새로운 도시를 리스트에 추가
					cList.add(new City(ni,nj,map[ni][nj]));
				}
			}
		}
		int sum = 0;
		//이동가능한 나라들이 있다면
		if(flag) {
			//그 나라들의 인구수 합 구하기
			for (City city : cList) {
				sum += city.popu;
			}
			//인구수 평균
			int avg = sum/cList.size();
			//이동가능한 나라들의 인구수를 갱신
			for (City city : cList) {
				map[city.ypos][city.xpos] = avg;
			}
		}
		return flag;
	}
	
}
