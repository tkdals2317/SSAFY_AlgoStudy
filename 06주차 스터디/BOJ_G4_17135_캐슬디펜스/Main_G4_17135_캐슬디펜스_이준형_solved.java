import java.io.*;
import java.util.*;

public class Main_bj_17135_캐슬디펜스_구미_4_이준형 {

	static class pos{	//좌표 클래스
		int i;
		int j;
		public pos(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}
	
	static int[] di= {0,-1,0};	//방향 벡터 좌상우 순서(좌측우선탐색)
	static int[] dj= {-1,0,1};
	static int[][] map;
	static int[][] map_main;
	static int N,M,D;	//맵 크기, 공격가능 거리
	static int kill,kill_max;	//궁수가 잡은 수
	static int[] arrow;	//궁수 배열
	
	public static void main(String[] args) throws Exception{

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		D=Integer.parseInt(st.nextToken());
		
		map_main=new int[N][M];	//제일 처음 맵
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map_main[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
//		deepcopy(map_main);
//		printmap();
		arrow=new int[M];	//궁수 배열 생성
		arrow_position(0,0);
		
		System.out.println(kill_max);	//최대 킬값 출력
		br.close();
	}

	//궁수의 배치 조합(combination)
	private static void arrow_position(int idx, int count) {
		if(count==3) {
//			System.out.println(Arrays.toString(arrow));
			map=new int[N][M];	//맵 생성	왜 이거 할때 새로 안만들어주면 오류가 발생하는지??????????????
			deepcopy1();	// map에 map_main딥카피
//			printmapmain();
//			printmap();
			
			kill=0;		//초기화
			shooting_arrow(N);
			kill_max=Math.max(kill_max, kill);	//최대값 비교
			return;
		}
		else if(idx>=M) {	//탈출조건
			return;
		}
		arrow[idx]=1;	//사용
		arrow_position(idx+1, count+1);
		arrow[idx]=0;	//사용x
		arrow_position(idx+1, count);
		
	}

	//화살쏘기
	private static void shooting_arrow(int play) {
		if(play==0)return;

//		printmap();

		//		System.out.println(play);		
		for(int k=0;k<M;k++) {		//궁수있으면 해당위치에서 bfs탐색
			if(arrow[k]==1) {
				search_close(k);
			}
		}
		calc_kill();
		map_down();
		shooting_arrow(play-1);
		
	}

	//가장 가까운거 찾아서 처리 BFS
	private static void search_close(int k) {
		
		int[][] tmp_map=new int[N][M];	//임시배열 생성
		deepcopy2(tmp_map);
		ArrayDeque<pos> queue=new ArrayDeque<>();	//큐 생성
		queue.offer(new pos(N-1,k));	//초기위치 넣기
		
		while(!queue.isEmpty()) {	//큐가 비지 않았을때동안 반복
			int size=queue.size();
			for(int i=0;i<size;i++) {
				pos p=queue.poll();
				if(tmp_map[p.i][p.j]==1||tmp_map[p.i][p.j]==-1) {
					map[p.i][p.j]=-1;	//실제 map을 변경
					return;
				}
				tmp_map[p.i][p.j]=2;	//방문 체크
				
				for(int d=0;d<3;d++) {	//좌상우 3방향 탐색
					int ci=p.i+di[d];
					int cj=p.j+dj[d];
					if(ci>=0&&ci<N&&cj>=0&&cj<M&&tmp_map[ci][cj]!=2) {	//bfs조건
						if(Math.abs(ci-N)+Math.abs(cj-k)<=D) {	//거리 조건
							queue.offer(new pos(ci, cj));
						}
					}
				}
			}
		}
		
	}

	//임시 배열 deepcopy생성
	private static void deepcopy2(int[][] tmp_map) {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				tmp_map[i][j]=map[i][j];
			}
		}
	}

	//맵을 한칸씩 밑으로 당기기
	private static void map_down() {
//		printmap();
		for(int i=N-1;i>0;i--) {
			map[i]=map[i-1];
		}
		map[0]=new int[M];
		
//		for(int j=0;j<M;j++) {	//제일위에꺼 0으로 만들어주기 왜 이렇게 하면 이상하게 되는거?????????????
//			map[0][j]=0;	
//		}
		
//		printmap();
	}

	//화살로 잡은거 처리
	private static void calc_kill() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]==-1) {	//잡은거 발견하면
					kill++;
					map[i][j]=0;	//제거
				}
			}
		}
	}

	//배열딥카피1 map을 가장 처음값으로
	private static void deepcopy1() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				map[i][j]=map_main[i][j];
			}
		}
	}

	//배열출력
	private static void printmap() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(map[i][j]);
			}System.out.println();
		}System.out.println("===========");
	}
	
	private static void printmapmain() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(map_main[i][j]);
			}System.out.println();
		}System.out.println("===========");
	}

}
