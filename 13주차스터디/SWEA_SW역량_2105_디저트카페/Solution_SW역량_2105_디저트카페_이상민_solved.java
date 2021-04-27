import java.util.*;
import java.io.*;

public class Solution_SW역량_2105_디저트카페_이상민_solved {
	static int N;
	static int [][] map;
	static boolean [][] visited;
	static boolean [] desert;
	static int [] dr = { 1, 1,-1,-1};
	static int [] dc = { 1,-1,-1, 1};
	static HashSet<Integer> set;
	
	static int max;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_swea_2105.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//입력
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			set = new HashSet<>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			max = -1;
			for (int i = 0; i < N-2; i++) {
				for (int j = 0; j < N-1; j++) {
					visited = new boolean[N][N];
					set.clear();
					set.add(map[i][j]);
                    visited[i][j] = true;
					dfs(i, j ,i, j, 0);
                    visited[i][j] = false;
                    set.remove(map[i][j]);
				}
			}
			System.out.println("#"+tc+" "+max);
		}
		br.close();
	}
	
	static void dfs(int sr, int sc, int cr, int cc, int curve) {
		//현재 방향으로부터 꺽을 수 있는 수까지 반복 
		for (int d = curve; d < 4; d++) {
			int nr = cr + dr[d];
			int nc = cc + dc[d];
			//사각형이 만들어지는 최소조건 : 3개 이상
			//도착지에 도달한다면 최대값 갱신
			if(set.size()>=3 && nr==sr && nc== sc) {
				max = Math.max(max, set.size());
				return;
			}
			//범위 체크 및 이미 방문한 가게면 continue;
			if(nr<0||nr>=N||nc<0||nc>=N||visited[nr][nc]) continue;
			//이미 한번 먹은 디저트 판단을 set의 contains메소드로 확인
			if(set.contains(map[nr][nc])) continue;
			//방문체크
			visited[nr][nc] = true;
			set.add(map[nr][nc]);
			dfs(sr, sc, nr, nc, d);
			//다른 길에서 검사할 수 있게 다시 되돌려 놓기
			set.remove(map[nr][nc]);
			visited[nr][nc] = false;			
		}
		
	}
	
}
