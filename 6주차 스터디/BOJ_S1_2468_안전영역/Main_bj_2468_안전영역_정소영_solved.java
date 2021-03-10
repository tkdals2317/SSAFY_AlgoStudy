import java.util.Scanner;
// 전형적인 dfs
public class Main_bj_2468_안전영역_정소영_solved {
	static int n;
	static int[][] map;
	static boolean[][] visited;
	static int[] di = {-1,1,0,0}; 	// 상하좌우
	static int[] dj = {0,0,-1,1}; 
	static int res=1;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		map = new int[n][n];
		
		boolean[] deep = new boolean[101];
		int cnt = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
				if (!deep[map[i][j]]) {
					deep[map[i][j]] = true;
					cnt++;	// map 내의 겹치지 않는 깊이들을 세기
				}
			}
		}
		// 입력 저장 끝-----------------
		
		int[] dres = new int[cnt];
		cnt = 0;
		for (int i = 0; i <= 100; i++) {
			if (deep[i]) {
				dres[cnt++] = i;
			}
		}
		// 깊이를 센 수를 이용해 dres를 만들어 해당 배열에 map 내에 있는 깊이를 저장
		
		int tmp;	// 검사할 깊이에서의 영역 갯수를 저장할 임시 변수
		for (int d = 0; d < cnt; d++) {	// 총 deep의 갯수만큼 dfs 실현
			visited = new boolean[n][n];	// visited 배열 선언 및 초기화
			int h = dres[d];	// 검사할 해당 깊이
			tmp = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(map[i][j]>h && visited[i][j] == false) {	// 잠기지 않는 부분 및 방문하지 않은 곳이라면
						rain(i, j, h);
						tmp++;
					}
				}
			}
			res = Math.max(res, tmp);
		}
		System.out.println(res);
		sc.close();
	}

	private static void rain(int i, int j, int h) {
		if(map[i][j]<=h || visited[i][j] == true) {
			return;	// 만약 해당 위치가 주어진 깊이보다 이하거나 방문한 곳일 경우 return
		}
		
		visited[i][j] = true;	// 방문 처리
		
		for (int k = 0; k < 4; k++) {
			int ni = i+di[k];
			int nj = j+dj[k];
			// 다음 위치를 ni, nj에 저장
			if(ni>=0 && ni<n && nj>=0 && nj<n) {
				rain(ni,nj,h);
			}
			
		}
		
	}

}

/*
 * 
5
6 8 2 6 2
3 2 3 4 6
6 7 3 3 2
7 2 5 3 6
8 9 5 2 7
 * 
 * 
5
 * 
 */
