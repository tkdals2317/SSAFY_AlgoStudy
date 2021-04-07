package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G5_16234_인구이동_정태현_solved {
	static class Pos {
		int row;
		int col;
		
		public Pos(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	static int[] dr = {-1, 0, 1, 0}; //상우하좌
	static int[] dc = {0, 1, 0, -1};
	static int N, L, R, union;
	static int[][] arr;
	static int[][] UnionNum; //연합별로 숫자 부여, visited 역할
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		UnionNum = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");			
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = 0; //결과 출력용
		boolean ismovePop; //인구 이동 했는지 체크
		
		while(true) {
			ismovePop = false;
			union = 0; //구역 번호
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(UnionNum[i][j] == 0) { //첨엔 0, visit 하지 않음
						if(bfs(i, j)) ismovePop = true;
						//인구이동이 일어났다
					}
				}
			}
			
			if(ismovePop) ans++;
			else break;
			
			UnionNum = new int[N][N]; //새로 초기화
		}
		
		System.out.println(ans);
	}
	
	private static boolean bfs(int r ,int c) {
		Queue<Pos> q = new LinkedList<>();
		union++;
		int totalCnt = 1; //연합안의 지역 숫자
		int totalPop = arr[r][c]; //총 인구숫자
		q.offer(new Pos(r, c));
		UnionNum[r][c] = union; //연합번호
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for (int i = 0; i < dr.length; i++) {
				int nr = cur.row + dr[i];
				int nc = cur.col + dc[i];
				
				if(nr >= 0 && nc >= 0 && nr < N && nc < N && UnionNum[nr][nc]==0) { //Unionnum의 해당 칸이 0? 아직 구역지정되지 않음
					if(diffabs(arr[cur.row][cur.col], arr[nr][nc])) {
						UnionNum[nr][nc] = union; //구역번호 부여
						totalPop += arr[nr][nc];
						totalCnt++;
						q.offer(new Pos(nr, nc));
					}
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(UnionNum[i][j] == union) //같은 구역
					arr[i][j] = totalPop / totalCnt;
			}
		}
		
		if(totalCnt == 1) return false; //한번도 bfs 돌지 않았다. 이동없었다.
		
		return true;
	}

	private static boolean diffabs(int i, int j) {
		int diff = Math.abs(i - j);
		if(L<=diff && diff <= R) return true;
		
		return false;
	}
}
