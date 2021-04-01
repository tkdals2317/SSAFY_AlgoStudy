package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G5_2174_로봇시뮬레이션_정태현_왜안돼 {
	static int[] dr = {-1, 0, 1, 0}; //상우하좌
	static int[] dc = {0, 1, 0, -1};
	static int A, B, N, M;
	static boolean ok;
	static int[][] arr;
	static Queue<rob> q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        q = new LinkedList<>();
        int rnum = 1; //로봇번호용
        ok = true;
        
        st = new StringTokenizer(br.readLine(), " ");
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		arr = new int[B][A];
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int c = Integer.parseInt(st.nextToken()) - 1;
			int r = B - Integer.parseInt(st.nextToken());
			int dir = st.nextToken().charAt(0);
			if(dir == 'N') dir= 0; 
			else if(dir == 'E') dir= 1; 
			else if(dir == 'S') dir= 2; 
			else if(dir == 'W') dir= 3;
			
			q.offer(new rob(r, c, dir));
			arr[r][c] = rnum++; //로봇 넣고 번호체크
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int oN = Integer.parseInt(st.nextToken());
			char ord = st.nextToken().charAt(0);
			int cnt = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < q.size(); j++) {
				rob r = q.poll();
				if(arr[r.r][r.c] == oN) {
					order(r, ord, cnt);
				}
				q.offer(r);
			}
		}
		
		if(ok) System.out.println("OK");
    }
    
	private static void order(rob r, char ord, int cnt) {
		for (int i = 0; i < cnt; i++) {
			if(ord == 'L') {
				r.dir = (r.dir+3) % 4;
			}else if(ord == 'R') {
				r.dir = (r.dir+1) % 4;				
			}else if(ord == 'F') {
				int nr = r.r + dr[r.dir];
				int nc = r.c + dc[r.dir];
				
				if(nr < 0 || nr >= B || nc < 0 || nc >= A) { //격자바깥으로
					ok = false;
					System.out.println("Robot " + arr[r.r][r.c]+ " crashes into the wall");
					return;
				}
				
				if(arr[nr][nc] > 0) { //움직였는데 다른 로봇과 충돌
					ok = false;
					System.out.println("Robot " + arr[r.r][r.c]+ " crashes into robot " + arr[nr][nc]);
					return;
				}
				
				arr[nr][nc] = arr[r.r][r.c]; //옮겨졌다면
				arr[r.r][r.c] = 0; //원래 있던 위치 0
				r.r = nr;
				r.c = nc;
			}
		}
	}
}

class rob {
	int r;
	int c;
	int dir;
	
	public rob(int r, int c, int dir) {
		this.r = r;
		this.c = c;
		this.dir = dir;
	}
}
