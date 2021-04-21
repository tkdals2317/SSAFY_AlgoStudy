import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_bj_3190_뱀_정소영_덜품ㅎㅎ {
	
	static int N, K, L;
	static int[][] map;
	static int[][] dir;
	static int[] di = {0,1,0,-1};	// 우, 하, 좌, 상
	static int[] dj = {1,0,-1,0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		K = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine()," ");
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
		}
		
		L = Integer.parseInt(br.readLine());
		dir = new int[L][2];
		for (int l = 0; l < L; l++) {
			st = new StringTokenizer(br.readLine()," ");
			dir[l][0] = Integer.parseInt(st.nextToken());
			String tmp = st.nextToken();
			if(tmp.equals("D")) dir[l][1] = 1;	// 오른쪽
			else dir[l][1] = 0;	// 왼쪽
		}
		// 입력 완료
		
		move();
		
		br.close();
	}

	private static void move() {
		
		int i = 0; 	// 시작좌표 i
		int j = 0;	// 시작좌표 j
		int d = 0;	// 방향
		int len = 1;	// 뱀의 몸길이
		int time = 0;
		int dindex = 0;
		Queue<Snake> q = new LinkedList<>();
		q.add(new Snake(0,0,0));
		
		
		while(i>=0 && i<N && j>=0 && j<N) { // 경계값일때 계속 움직임
			
			// 1. 좌표 움직이기
			i += di[d];
			j += dj[d];
			
			// 2. 만약 사과를 만났을 뗴?
			if(map[i][j]==1) {	// 몸 길이 늘리기, 사과 표시 없애기
				
			} else { // 안 만났으면
				
			}
			
			// 3. 뱀의 방향 전환 할 때
			time++;
			if(time==dir[dindex][0]) {
				if(dir[dindex][1] == 1) {	// 오른쪽 방향 전환
					d = (d+1)%4;
				}else {	// 왼쪽 방향 전환
					d -= 1;
					if(d==-1) d = 3;
				}
			}
			
			
			
		}
		
		
		
		
		
		
		
	}
	
	static private class Snake{
		int i;
		int j;
		int dir;
		public Snake(int i, int j, int dir) {
			super();
			this.i = i;
			this.j = j;
			this.dir = dir;
		}
		
		
		
	}
}
