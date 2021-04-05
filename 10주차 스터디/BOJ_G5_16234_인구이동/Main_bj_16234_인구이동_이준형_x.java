import java.io.*;
import java.util.*;

public class Main_bj_16234_인구이동_구미_4_이준형 {

	static class pos { // 위치 클래스
		int i;
		int j;

		public pos(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}

	static int[][] map; // 맵
	static int[][] check; // 체크
	static int N, L, R; // 변수

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) { // 배열 입력
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int count = 0; // 이동 횟수
		check=new int[N][N];
		while (true) {
			int c_num = make_team(); // 연합 만들기
			boolean ans=people_move(c_num); // 인구 이동
			if(ans==true)
				count++; // 이동 횟수 증가
			else
				break;	//변화가 없으면 종료
		}

		System.out.println(count); // 이동횟수 출력

		br.close();
	}




	// 인구 이동
	private static boolean people_move(int c_num) {
		int[][] bae = new int[c_num][2]; // 연합수,연합별 인구수 저장

		boolean flag = false;

		for (int i = 0; i < N; i++) { // 체크맵 돌면서 연합수,연합별 인구수 저장
			for (int j = 0; j < N; j++) {
				bae[check[i][j]][0]++;
				bae[check[i][j]][1] += map[i][j];
			}
		}

		for (int i = 1; i < c_num; i++) {
			if (bae[i][0] != 0)
				bae[i][1] = bae[i][1] / bae[i][0]; // 인구수를 연합개수로 나누어 저장
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]!=bae[check[i][j]][1])	//인구수에 변화가 있으면 true
					flag=true;
				map[i][j] = bae[check[i][j]][1]; // 해당하는 연합 인구수 넣어주기
			}
		}
		return flag;
	}

	// 연합 만들기
	private static int make_team() {
		for(int i=0;i<N;i++) {	 // 체크 배열 초기화
			for(int j=0;j<N;j++) {
				check[i][j]=0;
			}
		}
		
		int num = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (check[i][j] == 0) { // 방문 안햇던 곳이면 bfs맵체크 호출
					check_map(i, j, num);
					num++;
				}
			}
		}
		return num;
	}

	// bfs로 체크맵 숫자 넣기
	private static void check_map(int get_i, int get_j, int num) {
		int[] di = { 0, 0, 1, -1 }; // 방향벡터
		int[] dj = { 1, -1, 0, 0 };
		
		ArrayDeque<pos> queue = new ArrayDeque<>(); // 큐 선언
		queue.offer(new pos(get_i, get_j)); // 큐 넣고 자기 위치 체크
		check[get_i][get_j] = num;

		while (!queue.isEmpty()) { // 큐가 빌때까지
				pos p = queue.poll();
				check[p.i][p.j] = num; // 색칠
				for (int k = 0; k < 4; k++) { // 4방향
					int ci = p.i + di[k];
					int cj = p.j + dj[k];
					if (ci >= 0 && ci < N && cj >= 0 && cj < N && check[ci][cj] == 0) { // 범위조건 방문조건
						if (Math.abs(map[p.i][p.j] - map[ci][cj])>=L&&Math.abs(map[p.i][p.j] - map[ci][cj])<=R) // 이동 가능한 지역이면 큐에 넣기
							queue.offer(new pos(ci, cj));
					}
				}
		}
	}

}
