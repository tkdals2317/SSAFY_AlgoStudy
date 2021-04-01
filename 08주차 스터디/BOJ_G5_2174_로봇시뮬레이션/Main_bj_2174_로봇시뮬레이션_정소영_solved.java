import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main_bj_2174_로봇시뮬레이션_정소영_solved {
	static int A, B, N, M;
	static int[][] robots;
	static int[] di = {-1,0,1,0};	// 북동남왼
	static int[] dj = {0,1,0,-1};	
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		A = Integer.parseInt(st.nextToken());	// J
		B = Integer.parseInt(st.nextToken());	// I
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		robots = new int[N+1][3];
		map = new int[B+1][A+1];	
		for (int i = 1; i <= N; i++) {	// 1~N
			st = new StringTokenizer(br.readLine(), " ");
			robots[i][0] = Integer.parseInt(st.nextToken());	// J
			robots[i][1] = (B+1) - Integer.parseInt(st.nextToken());	// I
			map[robots[i][1]][robots[i][0]] = i;
			String dir = st.nextToken();
			if(dir.equals("N")) {	// 북동남왼
				robots[i][2] = 0;
			}else if(dir.equals("E")) {
				robots[i][2] = 1;
			}else if(dir.equals("S")) {
				robots[i][2] = 2;
			}else if(dir.equals("W")) {
				robots[i][2] = 3;
			}
		}
//		show();
//		System.out.println();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			// 명령을 내리는 로봇, 명령의 종류(위에 나와 있는), 명령의 반복 회수
			int rN = Integer.parseInt(st.nextToken());
			String order = st.nextToken();
			int cnt = Integer.parseInt(st.nextToken());
			move(rN, order, cnt);
		}
		System.out.println("OK");
	}

	private static void move(int rN, String order, int cnt) {
		if(order.equals("F")) {
			for (int i = 0; i < cnt; i++) {
				int pi = robots[rN][1];	// I
				int pj = robots[rN][0];	// J, A
				int pd = robots[rN][2];
				
				int ni = pi + di[pd];
				int nj = pj + dj[pd];
				//System.out.println(ni+" : "+nj);
				if(ni<1 || ni>B || nj<1 || nj>A) {
					System.out.println("Robot "+rN+" crashes into the wall");
					System.exit(0);
				}else if(map[ni][nj]!=0) {
					System.out.println("Robot "+rN+" crashes into robot "+map[ni][nj]);
					System.exit(0);
				}else {
					map[pi][pj] = 0;
					map[ni][nj] = rN;
					robots[rN][1] = ni;	// I
					robots[rN][0] = nj;
				}
//				show();
//				System.out.println();
			}
		} else if(order.equals("L")) {
			int tmp = cnt % 4;
			if(tmp>0) {
				robots[rN][2] = robots[rN][2] - tmp;
				if(robots[rN][2]<0) robots[rN][2] += 4;
			}
		} else {	// R
			int tmp = cnt % 4;
			if(tmp>0) {
				robots[rN][2] = robots[rN][2] + tmp;
				if(robots[rN][2]>3) robots[rN][2] -= 4;
			}
		}
		
	}
	
	private static void show() {
		for (int i = 0; i < B+1; i++) {
			for (int j = 0; j < A+1; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}

}
