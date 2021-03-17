package baekjun;
import java.io.*;
import java.util.*;

public class Main_bj_1012_유기농배추_구미_4_이준형2 {

	static int map[][];
	static int cnt,N,M;
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int T=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			M=Integer.parseInt(st.nextToken());
			N=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			
			map=new int[N][M];
			//배열에 입력
			for(int i=0;i<K;i++) {
				st=new StringTokenizer(br.readLine());
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				map[y][x]=1;
			}
			
			
			//초기화후 탐색
			cnt=0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(map[i][j]==1) {
						search(i,j);
						cnt++;
					}
				}
			}
			
//			for(int i=0;i<N;i++) {
//				for(int j=0;j<M;j++) {
//					System.out.print(map[i][j]+"\t");
//				}System.out.println();
//			}
			
			//답 출력
			System.out.println(cnt);
		}
		
		br.close();
	}

	//재귀 이어진부분 -1로
	static void search(int i, int j) {
		//자기자리 -1하고 시작
		map[i][j]=-1;
		
		for(int k=0;k<4;k++) {
			int ci=i+dx[k];
			int cj=j+dy[k];
			//이동가능한거 찾아서 이동
			if(ci>=0&&ci<N&&cj>=0&&cj<M&&map[ci][cj]==1) {
				search(ci,cj);
			}
		}
	}

}
