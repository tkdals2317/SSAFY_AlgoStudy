import java.util.*;
import java.io.*;

public class Solution_SWEA_5656_벽돌깨기_이상민_solved {
	static int N, W, H; //구슬을 떨어뜨릴 횟수, 높이, 너비
	static int [][]map;
	static int min;
	static int [] pick; //떨어드릴 좌표를 저장할 배열
	static int [] di = {-1, 0, 1, 0};
	static int [] dj = { 0, 1, 0,-1};
	static class Block{
		int ypos;
		int xpos;
		int range;
		public Block(int ypos, int xpos, int range) {
			super();
			this.ypos = ypos;
			this.xpos = xpos;
			this.range = range;
		}
		@Override
		public String toString() {
			return "[" + ypos + ", " + xpos + ", range=" + range + "]";
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_swea_5656.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			//구현 시작
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			pick = new int[N];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			min = Integer.MAX_VALUE;
			perm(0);
			System.out.println("#"+tc+" "+min);
		}
		
		br.close();
	} //end of main
	//공을 쏠 좌표 설정
	static void perm(int cnt) {
		if(cnt==N) {
			int [][] temp = new int[H][W];
			deepcopy(map, temp);
			for (int i = 0; i < N; i++) {
				shoot(pick[i], temp);
				down(temp);
			}
			min = Math.min(min, count(temp));
			return;
		}		
		for (int j = 0; j < W; j++) {
			pick[cnt]=j;
			perm(cnt+1);
		}		
	}
	//벽돌이 부서지고 난 뒤 벽돌 아래로 잡아당기기
    static void down(int[][] map) { 
        for (int j = 0; j < W; j++) {
        	//한 열의 0을 제외한 정보를 저장할 리스트
        	ArrayList<Integer> status = new ArrayList<>();
            
            for (int i = H - 1; i >= 0; i--) {
                if(map[i][j] > 0) {
                	//0이 아닌 값들만 list에 추가
                	status.add(map[i][j]);
                	//저장한 값들은 0으로 초기화
                    map[i][j] = 0;
                }
            }
            //맨 밑에서 부터 시작한다
            int i = H-1;
            for (int s : status) {
            	//다시 차곡차곡 쌓아주기
                map[i--][j] = s;
            }
            status.clear();
        }
    }
    //벽돌 부시기
	static int[][] shoot(int j, int[][] temp) {
		//부서야 할 블록의 목록을 저장할 큐
		ArrayDeque<Block> queue = new ArrayDeque<>();
		
		for (int i = 0; i < H; i++) {
			//만약 쏠 위치의 블록의 범위가 1보다 클 경우 큐에 추가 
			if(temp[i][j]>1) {
				queue.offer(new Block(i,j,temp[i][j]));
				temp[i][j] = 0;
				break;
			//만약 쏠 위치의 블록의 범위가 1이라면 0으로 바꿔주고 리턴 
			}else if(temp[i][j]==1) {
				temp[i][j] = 0;
				return temp;
			}
		}
		while(!queue.isEmpty()) {
			Block cur = queue.poll();
			int ci = cur.ypos;
			int cj = cur.xpos;
			int cr = cur.range;
			//범위만큼
			for (int dis = 1; dis < cr; dis++) {
				//4방향으로
				for (int d = 0; d < 4; d++) {
					int ni = ci+dis*di[d];
					int nj = cj+dis*dj[d];
					if(ni>=0&&ni<H&&nj>=0&&nj<W&&temp[ni][nj]>0) {
						queue.offer(new Block(ni,nj,temp[ni][nj]));
						temp[ni][nj] = 0;
					}
				}
			}		
		}	
		return temp;
	}
	//배열 복사! 주소값이므로 return하지 않아도 복사가 된다
	static void deepcopy(int[][] map, int [][]temp){
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				temp[i][j] = map[i][j];
			}
		}
	}
	//남아있는 블록 수 세기
	static int count(int [][]map) {
		int res = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(map[i][j]>0) res++;
			}
		}
		return res;
	}
}
