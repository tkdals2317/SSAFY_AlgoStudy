import java.io.*;
import java.util.*;

public class Main_bj_1916_최소비용구하기_구미_4_이준형2 {

	static int[][] map;
	static int N,M;
	static int minprice;
	static int end;
	
	static int[] dist;
	static boolean[] visit;
	

	//DFS로 푸니까 시간초과 남 다익스트라 사용해야할듯
	public static void main(String[] args) throws Exception{

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N=Integer.parseInt(br.readLine());
		M=Integer.parseInt(br.readLine());
		
		map=new int[N+1][N+1];
		for(int i=0;i<=N;i++) {	//-1로 초기화
			for(int j=0;j<=N;j++) {
				map[i][j]=-1;
			}
		}
		for(int k=0;k<M;k++) {
			st=new StringTokenizer(br.readLine());
			int i=Integer.parseInt(st.nextToken());
			int j=Integer.parseInt(st.nextToken());
			int num=Integer.parseInt(st.nextToken());
			map[i][j]=num;
		}
		
		st=new StringTokenizer(br.readLine());
		int start=Integer.parseInt(st.nextToken());
		end=Integer.parseInt(st.nextToken());
		
		dist=new int[N+1];
		visit=new boolean[N+1];
		
		Arrays.fill(dist,Integer.MAX_VALUE);
		
		dist[start]=0;
		visit[start]=true;
		
        //연결노드 distance갱신
        for(int i=1;i<=N;i++){
            if(!visit[i] && map[start][i]!=-1){
                dist[i] = map[start][i];
            }
        }
		
		int min=0, current=start;
		for(int i=1; i<=N; i++){
			//step1: 방문하지 않은 정점들 중 최소가중치의 정점 선택
			min=Integer.MAX_VALUE;
			for(int j=1; j<=N; j++){
				if(!visit[j] && min>dist[j]){
					min=dist[j];
					current=j;
				}
			}
			visit[current]=true; // 선택 정점 방문 처리
//			if(current==end) break; // 선택 정점이 도착정점이면 탈출.
				
			//step2: current정점을 경유지로 하여 갈수 있는 다른 방문하지 않은 정점들에 대한 처리
			for(int c=1; c<=N; c++){
				if(!visit[c] && map[current][c]!=-1 && dist[c]>min+map[current][c]){
					dist[c]=min+map[current][c];
				}
			}
		}
		
		System.out.println(dist[end]);
//		System.out.println(Arrays.toString(distance));
		br.close();
		
	}


}
