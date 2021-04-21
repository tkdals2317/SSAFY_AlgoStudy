import java.util.*;
import java.io.*;
/**
 * 뱀의 방향 변환 정보 : 정수 X와 문자 C
 * 게임 시작 시간으로부터 X초가 끝난 뒤에 왼쪽(C가 'L') 또는 오른쪽(C가 'D')로 90도 방향을 회전
 */
public class Main_G5_3190_뱀_이상민_solved {
	static int N, K, L; //N : 보드의 크기, K : 사과의 개수, L : 뱀의 방향 변환 횟수
	static int[][] map;
	private static int[] dx = {0, 1, 0, -1}; 
	private static int[] dy = {1, 0, -1, 0};

	static ArrayList<Point> snake;
	static class Point{
		int ypos;
		int xpos;
		public Point(int ypos, int xpos) {
			super();
			this.ypos = ypos;
			this.xpos = xpos;
		}
		@Override
		public String toString() {
			return "[" + ypos + ", " + xpos + "]";
		}
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_bj_3190.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			map[y][x] = 2;
		}
		//print();
		L = Integer.parseInt(br.readLine());
		int[][] dir = new int[L][2];
		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			String charDir = st.nextToken();
			dir[i][0] = time;
			dir[i][1] = (charDir.equals("L")) ? -1 : 1; 
			//System.out.println(Arrays.toString(dir[i]));
		}
		snake = new ArrayList<>(); 
		snake.add(new Point(0,0));


		System.out.println(bfs(0, 0, 0, dir));
		//몸 길어지게 하기
		//죽는 조건 : 벽에 박거나 아니면 자신의 몸에 데이는 경우
		br.close();
	}
	static int bfs(int cx, int cy, int cDir, int [][]dir) {
		int time = 0;
		int turn = 0;
		
		while(true) {
			time++;
			int ny = cy+dy[cDir];
			int nx = cx+dx[cDir];
			
			if(rangeCheck(ny,nx)) break;
			if(map[ny][nx]==2) {
				snake.add(new Point(ny,nx));
				map[ny][nx] = 0;
			}else {
				snake.add(new Point(ny,nx));
				snake.remove(0);
			}
			
			cy = ny; 
			cx = nx;
			
			if(turn < L) {
				if(time == dir[turn][0]) {
					cDir = nextDir(cDir, dir[turn][1]);
					turn++;
				}
			}
		}
		return time;
	}
	
	private static boolean rangeCheck(int ny, int nx) {
		if(ny== -1 || ny == N || nx == -1 || nx == N) return true;
		
		for (int i = 0; i < snake.size(); i++) {
			int ypos = snake.get(i).ypos;
			int xpos = snake.get(i).xpos;
			if(ypos == ny && xpos==nx) return true;
		}
		
		return false;
	}
	
	private static int dirchange(int sdir, int ndir) {
        //0 = 상, 1 = 우, 2 = 하, 3 = 좌
        if(ndir == 1) { //왼쪽
            if(sdir == 0) sdir = 3;
            else if(sdir == 1) sdir = 0;
            else if(sdir == 2) sdir = 1;
            else sdir = 2;
        } else { //오른쪽
            if(sdir == 0) sdir = 1;
            else if(sdir == 1) sdir = 2;
            else if(sdir == 2) sdir = 3;
            else sdir = 0;
        }

        return sdir;
    }
	//방향 설정
	static int nextDir(int current, int dir) {
		int next = (current+dir) % 4;
		if(next==-1) next = 3;
		return next;
	}
	
	static void print() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <=N; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
