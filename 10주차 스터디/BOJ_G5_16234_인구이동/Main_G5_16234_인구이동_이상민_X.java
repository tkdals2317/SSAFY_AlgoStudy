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
어레이리스트에 추가하고 셋을 통해 중복 제거한 후 그 리스트의 값의 평균으로 값을 바꿔줌 

*/
public class Main_G5_16234_인구이동_이상민_X {
	static int N, L, R;
	static int [][] map;
	static boolean [][] visited;
	static int [] di = {-1, 0, 1, 0};
	static int [] dj = { 0, 1, 0,-1};
	static HashSet<City> citySet;
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
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + xpos;
			result = prime * result + ypos;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			City other = (City) obj;
			if (xpos != other.xpos)
				return false;
			if (ypos != other.ypos)
				return false;
			return true;
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
				//System.out.print(map[i][j]+" ");
			}
			//System.out.println();
		}
		/*citySet = new HashSet<City>();
		citySet.add(new City(0,0,50));
		citySet.add(new City(0,1,30));
		citySet.add(new City(0,0,50));
		City c1 = new City(0,0,50);
		City c2 = new City(0,1,40);
		System.out.println(c1.equals(c2));
		System.out.println(citySet);*/
		int sol = 0;
		while(true) {
			citySet = new HashSet<City>();
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(!visited[i][j]) bfs(new int[] {i,j});
				}
			}
			//System.out.println(citySet);
			//System.out.println(citySet.size());
			if(citySet.size()==0) {
				break;
			}
			int sum = 0;
			for(City c : citySet) {
				sum += c.popu;
			}
			int avg = sum/citySet.size();
			System.out.println("평균 :"+avg);
			
			for(City c : citySet) {
				map[c.ypos][c.xpos] = avg;
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(map[i][j]+" ");
				}
				System.out.println();
			}
			sol++;
		}
		System.out.println(sol);
		
		
		br.close();
	}
	private static void bfs(int[] start) {
		ArrayDeque<int[]> queue = new ArrayDeque<int[]>();
		visited[start[0]][start[1]]= true;
		queue.offer(start);
		//int total = map[start[0]][start[1]];
		//citySet.add(new City(start[0],start[1],map[start[0]][start[1]]));
		while(!queue.isEmpty()) {
			int [] curr = queue.poll();
			int ci = curr[0];
			int cj = curr[1];
			for (int d = 0; d < 4; d++) {
				int ni = ci+di[d];
				int nj = cj+dj[d];
				if(ni<0||ni>=N||nj<0||nj>=N||visited[ni][nj]) continue;
				int diff = Math.abs(map[ci][cj]-map[ni][nj]);
				if(L<=diff&&diff<=R) {
					citySet.add(new City(start[0],start[1],map[start[0]][start[1]]));
					visited[ni][nj] = true;
					queue.offer(new int[] {ni, nj});
					citySet.add(new City(ni,nj,map[ni][nj]));
				}
			}
		}
/*		int sum = 0;
		for (City c : citySet) {
			sum+=c.popu;
		}
		for (City c : citySet) {
			map[c.ypos][c.xpos] = sum/citySet.size();
		}*/
		
	}
	
}
