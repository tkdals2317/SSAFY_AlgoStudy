import java.io.*;
import java.util.*;

public class Solution_SW역량_2117_홈방범서비스_구미_4_이준형 {

	static class pos{	//위치정보 클래스
		int i;
		int j;
		public pos(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}
	static int[] di= {0,0,1,-1};	//방향벡터
	static int[] dj= {1,-1,0,0};
	static int[][] map;	//맵
	static boolean[][] check;	//방문체크용
	static int N,M;	//맵크기,집 비용
	static int max_count;	//최대 집 가능수
	
	public static void main(String[] args) throws Exception {

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {	//테케만큼 반복
			st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			
			map=new int[N][N];
			for(int i=0;i<N;i++) {	//입력받기
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			max_count=0;	//초기화
			for(int i=0;i<N;i++) {	//배열돌면서 각 위치에서 bfs호출
				for(int j=0;j<N;j++) {
					search(i,j);
				}
			}
			System.out.println("#"+tc+" "+max_count);	//답출력
			
		}
		
		br.close();
	}

	// bfs 손해보지않고 서비스 가능한 최대 집 개수 굿하기
	private static void search(int gi, int gj) {
		ArrayDeque<pos> queue=new ArrayDeque<>();	//큐 선언
		check=new boolean[N][N];	//체크배열 선언

		int price=-1;	//초기 비용
		int house_count=0;	//집 개수
		check[gi][gj]=true;	//처음위치 방문
		if(map[gi][gj]==1) {	//처음위치 집 체크
			price+=M;
			house_count++;
		}
		queue.offer(new pos(gi, gj));	//큐에 넣기
		
		//처음상황체크
		if(price>=0)	//손해 보지 않으면 최대 주택수와 비교
			max_count=Math.max(max_count, house_count);
		
		int go=4;	//증가량
		int go_count=1;
		while(!queue.isEmpty()) {	//큐가 빌때까지 반복
			int size=queue.size();
			for(int s=0;s<size;s++) {	//사이즈만큼 반복
				pos tmp=queue.poll();	//하나 꺼내기
				for(int k=0;k<4;k++) {	//4방향 탐색
					int ci=tmp.i+di[k];
					int cj=tmp.j+dj[k];
					if(ci>=0&&ci<N&&cj>=0&&cj<N&&check[ci][cj]==false) {	//범위조건,방문조건
						check[ci][cj]=true;	//방문체크
						if(map[ci][cj]==1)	{//집 있으면 비용,집수 증가
							price+=M;
							house_count++;
						}
						queue.offer(new pos(ci,cj));
					}
				}
			}
			price-=(go*go_count);	//영역 증가
			go_count++;

			if(price>=0)	//손해 보지 않으면 최대 주택수와 비교
				max_count=Math.max(max_count, house_count);
			
		}
		
	}

}
