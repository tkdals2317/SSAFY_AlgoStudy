package baekjun;
import java.io.*;
import java.util.*;

//위치저장 클래스
class Pos{
	int x;
	int y;
	public Pos() {	}
	public Pos(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

public class Main_bj_9205_맥주마시면서걸어가기_구미_4_이준형 {
	
	static int[] dx;
	static int[] dy;
	static int[] visit;
	static int ans_x,ans_y;
	static int n;
	

	public static void main(String[] args) throws Exception{

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int T=Integer.parseInt(br.readLine());
		for(int tc=0;tc<T;tc++) {
			n=Integer.parseInt(br.readLine());
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			//시작위치
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			
			//편의점 위치 입력
			dx=new int[n];
			dy=new int[n];
			for(int k=0;k<n;k++) {
				st=new StringTokenizer(br.readLine());
				dx[k]=Integer.parseInt(st.nextToken());
				dy[k]=Integer.parseInt(st.nextToken());
			}
			//락페스티벌 위치
			st=new StringTokenizer(br.readLine());
			ans_x=Integer.parseInt(st.nextToken());
			ans_y=Integer.parseInt(st.nextToken());
			
			//탐색
			search(x,y);
			
		}
		
		br.close();
	}

	//BFS 탐색
	static void search(int x, int y) {
		Queue<Pos> queue=new ArrayDeque<Pos>();
		queue.offer(new Pos(x,y));
		visit=new int[n];
		
		while(!queue.isEmpty()) {
			Pos pos=queue.poll();
			int i=pos.x;
			int j=pos.y;
			
			//도착지점까지 갈수 있을경우
			if(dis(i,j,ans_x,ans_y)<=1000) {
				System.out.println("happy");
				return;
			}

			//편의점까지 가는거
			for(int k=0;k<n;k++) {
				if(dis(i,j,dx[k],dy[k])<=1000 && visit[k]==0) {
					visit[k]=1;		//방문한곳은 재방문 x
					queue.offer(new Pos(dx[k],dy[k]));
				}
			}
		}
		System.out.println("sad");
		
	}

	//거리계산
	static int dis(int i, int j, int x, int y) {
		return (Math.abs(i-x)+Math.abs(j-y));
	}

}
