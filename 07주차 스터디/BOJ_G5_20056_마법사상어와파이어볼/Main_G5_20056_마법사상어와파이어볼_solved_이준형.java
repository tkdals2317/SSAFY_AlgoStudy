import java.io.*;
import java.util.*;

public class Main_bj_20056_마법사상어와파이어볼_구미_4_이준형 {
	
	static class fire{	//파이어볼
		int m;	//질량
		int s;	//속력
		int d;	//방향
		int cnt;	//개수
		public fire(int m, int s, int d, int cnt) {
			super();
			this.m = m;
			this.s = s;
			this.d = d;
			this.cnt = cnt;
		}
	}
	
	static int[] di= {-1,-1,0,1,1,1,0,-1};	//방향벡터
	static int[] dj= {0,1,1,1,0,-1,-1,-1};
	
	static int N;	//배열 사이즈
	static fire[][] map;	//맵
	static fire[][] new_map;

	public static void main(String[] args) throws Exception{

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());	//파이어볼 개수
		int K=Integer.parseInt(st.nextToken());	//명령개수
		
		map=new fire[N+1][N+1];	//맵 배열 생성 0위치는 안씀
		for(int k=0;k<M;k++) {	
			st=new StringTokenizer(br.readLine());
			int i=Integer.parseInt(st.nextToken());
			int j=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			int s=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken());
			map[i][j]=new fire(m,s,d,1);	//파이어볼 넣기
		}
		
//		printmap();
		for(int k=0;k<K;k++) {	//K번만큼 이동
			move_fire();
//			printmap();
		}
		
		int ans=0;
		for(int i=1;i<=N;i++) {	//남아 있는 파이어볼 무게 계산
			for(int j=1;j<=N;j++) {
				if(map[i][j]!=null) {
					if(map[i][j].cnt==1)	//남은게 여러개인경우 무게 분할 계산
						ans+=map[i][j].m;
					else {
						ans+=(map[i][j].m/5)*4;
					}
				}
			}
		}
		
		System.out.println(ans);
		
		br.close();
	}

	//파이어볼 이동하는거
	private static void move_fire() {
		new_map=new fire[N+1][N+1];	//새 맵 생성
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(map[i][j]!=null) {	//맵에 항목이 있을경우
					if(map[i][j].cnt==1) {	//개수가 1개일때
						calc_pos(i,j);	//불 넣을 위치 계산해서 넣기
					}else {	//개수가 여러개 파이어볼 분리
						split_fire(i,j);
					}
				}
			}
		}
		deepcopy();
	}

	//불 4방향으로 분산
	private static void split_fire(int i, int j) {
		int m=map[i][j].m/5;	//질량
		int s=map[i][j].s/map[i][j].cnt;	//속도
		
		if(m==0)	//무게가 0 이되면 소멸
			return;
		
		int d=map[i][j].d;	//방향
		
		//방향 다 일치안하면 1357분할 아니면  0248분할
		int k=0;
		if(d==-1) k=1;
		else k=0;
		for(;k<8;k+=2) {	
			int ci=i+di[k]*s;	//이동할 위치(맵 넘어가면 처음 위치에서 시작할수 있도록)
			if(ci>0) 
				ci=(ci-1)%N+1;
			else 
				ci=(ci)%N+N;
			
			int cj=j+dj[k]*s;
			if(cj>0) 
				cj=(cj-1)%N+1;
			else 
				cj=(cj)%N+N;
			
			give_fire(ci,cj,m,s,k);	//파이어볼 넣기
		}
		
	}

	//파이어볼 넣을 위치 계산
	private static void calc_pos(int i, int j) {
		int m=map[i][j].m;	//질량
		int s=map[i][j].s;	//속도
		int d=map[i][j].d;	//방향
		
		int ci=i+di[d]*s;	//이동할 위치(맵 넘어가면 처음 위치에서 시작할수 있도록)
		if(ci>0) 
			ci=(ci-1)%N+1;
		else 
			ci=(ci)%N+N;
		
		int cj=j+dj[d]*s;
		if(cj>0) 
			cj=(cj-1)%N+1;
		else 
			cj=(cj)%N+N;
		
		give_fire(ci,cj,m,s,d);	//파이어볼 넣기
		
	}

	//파이어볼 넣기
	private static void give_fire(int i, int j, int m, int s, int d) {
		if(new_map[i][j]==null) {	//해당 위치가 비어있으면 만들어서 넣기
			new_map[i][j]=new fire(m,s,d,1);
		}
		else {	//이미 있으면 추가해주기
			new_map[i][j].m+=m;	//무게더하기
			new_map[i][j].s+=s;	//속도 더하기
			new_map[i][j].cnt++;	//개수 증가
			if(new_map[i][j].d%2!=d%2) {	//방향이 다를경우에만 -1로 방향 변경
				new_map[i][j].d=-1;
			}
		}
	}


	//배열 딥카피 새로운 배열 만든것을 기존것에 넣어주기
	private static void deepcopy() {
		map=new fire[N+1][N+1];
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(new_map[i][j]!=null) {
					map[i][j]=new_map[i][j];
				}
			}
		}
	}

	//맵 출력
	private static void printmap() {
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(map[i][j]==null) {
					System.out.print(0+",0 ");
				}
				else {
					System.out.print(map[i][j].m+","+map[i][j].cnt+" ");
				}
			}System.out.println();
		}System.out.println("=================");
	}
	
}
