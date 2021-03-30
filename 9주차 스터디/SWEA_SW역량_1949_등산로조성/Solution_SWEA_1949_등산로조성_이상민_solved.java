import java.util.*;
import java.io.*;

public class Solution_SWEA_1949_등산로조성_이상민_solved {
	static int N, K; // 맵의 크기, 최대 공사 가능 깊이
	static int[][] map;
	static boolean[][] visited;
	static int[] di = { -1, 0, 1, 0 }; //4방 탐색
	static int[] dj = { 0, 1, 0, -1 };
	static int sol; //등산로의 최대길이
	static int top; //산의 가장 높은 봉우리의 높이

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_swea_1949.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			//지도의 한변의 길이, 최대 공사 가능 깊이 입력
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			//가장 높은 봉우리의 높이, 결과값을 최소값으로 초기화
			top = Integer.MIN_VALUE;
			sol = Integer.MIN_VALUE;
			//지도와 방문처리를 위한 지도 생성
			map = new int[N][N];
			visited = new boolean[N][N];
			//지도 정보 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					//가장 높은 봉우리의 높이 갱신
					top = Math.max(top, map[i][j]);
				}
			}
			//가장 높은 봉우리에서 dfs 탐색 시작
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == top)
						dfs(i, j, 1, 1);
				}
			}
			//결과 출력
			System.out.println("#"+tc+" "+sol);
		}
		br.close();
	}

	static void dfs(int ci, int cj, int cnt, int length) {
		//등산로 최대길이 갱신
		sol = Math.max(sol, length);
		//현재 위치 방문 처리
		visited[ci][cj] = true;
		//4방 탐색
		for (int d = 0; d < 4; d++) {
			int ni = ci + di[d];
			int nj = cj + dj[d];
			if (checkRange(ni, nj)||visited[ni][nj]) continue;		
			//현재 위치보다 다음 위치가 낮으면 탐색 진행
			if(map[ci][cj]>map[ni][nj]) {
				dfs(ni,nj, cnt, length+1);
			}else { //현재 위치보다 다음 위치가 높다면
				//다음 위치를 깎았을 경우 현재 위치보다 낮아지고 깍을 수 있는 횟수가 남아있으면
				if(map[ci][cj]>map[ni][nj]-K&&cnt>0){
					//다음 위치값을 임시변수에 저장
					int temp = map[ni][nj];
					//다음 위치를 현재위치의 높이 -1로 깍기
					map[ni][nj] = map[ci][cj]-1;
					//탐색 진행(공사 가능 횟수를 0으로 바꿔준다)
					dfs(ni,nj,0,length+1);
					//탐색이 끝났다면 깎은 높이를 원상복구
					map[ni][nj] = temp;
				}
			}			
		}
		//다른 등산로도 탐색이 가능하게끔 방문처리를 해제
		visited[ci][cj] = false;
		return;
	}
	//범위 체크
	static boolean checkRange(int ni, int nj) {
		if (ni < 0 || ni >= N || nj < 0 || nj >= N)
			return true;
		else
			return false;
	}
}
