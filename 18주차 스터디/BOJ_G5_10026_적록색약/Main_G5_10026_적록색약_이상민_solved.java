import java.util.*;
import java.io.*;

public class Main_G5_10026_적록색약_이상민_solved {
	//맵의 크기
	static int N;
	//적록색약이 아닌 사람
	static char[][] mapAll;
	static boolean[][] visitedAll;
	//적록색약인 사람
	static char[][] mapRG;
	static boolean[][] visitedRG;
	//영역의 갯수
	static int cntAll = 0, cntRG=0;
	//4방 탐색을 위한 변수
	static int [] di = { -1, 0, 1, 0}; 
	static int [] dj = {  0, 1, 0,-1}; 
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_10026.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		//생성
		mapAll = new char[N][N];
		mapRG = new char[N][N];
		visitedAll = new boolean[N][N];
		visitedRG = new boolean[N][N];
		//입력
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				mapAll[i][j] = str.charAt(j);	
				//적록색약인 경우 G=>R로 바꿔서 배열에 저장
				if(str.charAt(j)=='G') {
					mapRG[i][j] = 'R';
					continue;
				}
				//B인 경우는 그냥 저장
				mapRG[i][j] = str.charAt(j);
			}
		}
		//적록색약이 아닌 사람의 영역 검사
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				//방문하지 않았다면
				if(!visitedAll[i][j]) {
					bfsAll(i, j);
					cntAll++;
				}
			}
		}
		//적록색약인 사람의 영역 검사
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visitedRG[i][j]) {
					bfsRG(i, j);
					cntRG++;
				}
			}
		}
		System.out.println(cntAll+" "+cntRG);
		
		br.close();
	}
	private static void bfsRG(int i, int j) {
		ArrayDeque<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] {i,j});
		visitedRG[i][j] = true;
		while(!queue.isEmpty()) {
			int [] curr = queue.poll();
			int ci = curr[0];
			int cj = curr[1];
			for(int d=0; d<4; d++) {
				int ni = ci + di[d];
				int nj = cj + dj[d];
				//범위 체크 & 방문 체크 & 현재 색깔과 다음색깔이 같을 경우
				if(ni>=0&&ni<N&&nj>=0&&nj<N&&!visitedRG[ni][nj]&&mapRG[ni][nj]==mapRG[ci][cj]) {
					queue.offer(new int[] {ni, nj});
					visitedRG[ni][nj] = true;
				}
			}
		}
	}
	private static void bfsAll(int i, int j) {
		ArrayDeque<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] {i,j});
		visitedAll[i][j] = true;
		while(!queue.isEmpty()) {
			int [] curr = queue.poll();
			int ci = curr[0];
			int cj = curr[1];
			for(int d=0; d<4; d++) {
				int ni = ci + di[d];
				int nj = cj + dj[d];
				//범위 체크 & 방문 체크 & 현재 색깔과 다음색깔이 같을 경우
				if(ni>=0&&ni<N&&nj>=0&&nj<N&&!visitedAll[ni][nj]&&mapAll[ni][nj]==mapAll[ci][cj]) {
					queue.offer(new int[] {ni, nj});
					visitedAll[ni][nj] = true;
				}
			}
		}
	}
}
