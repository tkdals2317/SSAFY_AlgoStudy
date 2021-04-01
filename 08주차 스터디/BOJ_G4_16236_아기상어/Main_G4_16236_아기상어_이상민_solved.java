import java.util.*;
import java.io.*;

public class Main_G4_16236_아기상어_이상민_solved {
	static int N; //공간의 크기(2 ≤ N ≤ 20)
	static int[][] map; 

	static int time = 0;
	static int [] di = {-1, 0, 0, 1};
	static int [] dj = { 0,-1, 1, 0};
	static Fish shark;
	static int sharkSize = 2;
	static int eatCnt;
	//잡아 먹을 수 있는 물고기를 저장할 priorityQueue 
	static PriorityQueue<Fish> pq = new PriorityQueue<Fish>();
	static class Fish implements Comparable<Fish>{
		int y;
		int x;
		int level;
		public Fish(int y, int x, int level) {
			super();
			this.y = y;
			this.x = x;
			this.level = level;
		}
		
		@Override
		public String toString() {
			return "[" + y + ", " + x + ", " + level + "]";
		}

		@Override
		public int compareTo(Fish o) {
			//가장 위에 있는 물고기가 먼저 들어간다
	        if (this.y > o.y)
	            return 1;
	        //만약 가장 위에 있는 물고기가 여러명이라면
	        else if (this.y == o.y) {
	        	//가장 왼쪽에 있는 물고기가 먼저 들어간다 
	            if (this.x > o.x)
	                return 1;
	            else
	                return -1;
	        } else {
	            return -1;
	        }
		}
	}
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_bj_16236.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		pq = new PriorityQueue<Fish>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==9) {
					shark = new Fish(i,j,0);
				}
			}
		}
		System.out.println(solve());
		br.close();
	}
	static int solve() {
		while(true) {
			//현재 위치에서 먹을 수 있는 물고기가 어디있나 보러 갈까?
			search();
			//먹을 수 있는 물고기가 없다면 그대로 time 출력
			if(pq.isEmpty()) {
				return time;
			}
			//pq에는 여러개가 들어 있지만 한마리만 뽑아낸다
			//가장 가까운 물고기 타겟으로 지정하기 위해 꺼낸다(pq로 인해 가장 가까운 물고기, 여러마리라면 가장 위에 있는, i값도 같다면 가장 왼쪽에 있는 물고기가 나온다)
			Fish target = pq.poll();
			//가장 가까운 물고기까지 가는데 걸리는 시간을 더해준다 
			time += target.level;
			//그 전에 있던 자리 0으로 바꿔주기
			map[shark.y][shark.x] = 0;
			shark.y = target.y;
			shark.x = target.x;
			map[shark.y][shark.x] = 9;
			eatCnt+=1;
			if(eatCnt == sharkSize) {
				sharkSize +=1;
				eatCnt =0;
			}
			pq.clear();
		}
	} // end of solve
	static void search() {
		//상어 위치를 넣어줄 큐
		ArrayDeque<Fish> queue = new ArrayDeque<>();
		boolean[][] v = new boolean[N][N];
		int distance = Integer.MAX_VALUE;
		queue.offer(new Fish(shark.y, shark.x, 0));
		v[shark.y][shark.x] = true;
		while(!queue.isEmpty()) {
			//현재 상어 위치 꺼내기
			Fish current = queue.poll();
			int ci = current.y;
			int cj = current.x;
			int clevel = current.level;
			
			for (int d = 0;d  < 4; d++) {
				int ni = ci + di[d];
				int nj = cj + dj[d];
				//범위 체크, 방문하지 않았고, 현재까지 간 거리가 방문하고자 하는 
				if(ni >= 0 && ni < N && nj >= 0 && nj < N && !v[ni][nj] && clevel < distance && map[ni][nj] <= sharkSize) {
					
					//현재 상어보다 크기가 작은 물고기일 경우에 
					if(map[ni][nj] > 0 && map[ni][nj] < sharkSize) {
						//최소거리 갱신
						distance = Math.min(distance, clevel+1);
						//잡을 수 있는 물고기 추가(pq의 정렬로 자동적으로 정렬됨)
						pq.offer(new Fish(ni,nj, clevel+1));
						//방문처리
						v[ni][nj] = true;
					}
					//map[ni][nj]가 0이거나 상어와 크기가 같을 경우 
					else if(map[ni][nj]==0||map[ni][nj]==sharkSize) {
						//갈 수 있는 위치를 추가
						queue.offer(new Fish(ni,nj,clevel+1));
						v[ni][nj] = true;
					}
				}//end of rangeCheck if	
			}//end of for dir
		} //end of while		
	} // end of search
} // end of class
