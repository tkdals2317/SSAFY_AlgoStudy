import java.io.*;
import java.util.*;

public class Main_bj_2468_안전영역_구미_4_이준형 {

	static int N; // 맵 크기
	static int[] di = { 1, -1, 0, 0 }; // 방향벡터
	static int[] dj = { 0, 0, 1, -1 };
	static int[][] map;
	static int[][] map_check;
	static int max_count; // 물에 안잠긴 영역의 최대

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		map = new int[N][N];
		for (int i = 0; i < N; i++) { // 맵 입력받기
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				map[i][j] = tmp;
				if (min > tmp)
					min = tmp; // 맵의 최소값 최대값 지정
				else if (max < tmp)
					max = tmp;
			}
		}

//		printmap();

		max_count = 1; // 값 초기화
		map_check = new int[N][N];

		for (int k = min; k < max; k++) { // 최소값에서 최대값 까지 조건 검색
			changemap(k); // 물에 잠긴 맵으로 변경,초기화
			
			int count=0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(map_check[i][j]==0) {	//물에 잠기지도 않았고 사용도 안됬으면 호출
						searchmap(i,j);	//dfs 호출
						count++;	//호출한 횟수만큼 증가
					}
				}
			}
			
//			printmap();
//			System.out.println(count);
			
			max_count=Math.max(max_count, count);	//크기비교
		}
		
		System.out.println(max_count);	//최대 개수 출력

		br.close();
	}

	//DFS로 영역 값 변경
	private static void searchmap(int i,int j) {
		map_check[i][j]=1;	//방문 체크
		for(int k=0;k<4;k++) {	//4방향탐색
			int ci=i+di[k];
			int cj=j+dj[k];
			if(ci>=0&&ci<N&&cj>=0&&cj<N&&map_check[ci][cj]==0) {
				searchmap(ci, cj);
			}
		}
	}

	// 물에 잠긴 영역 -1로 표시 아니면 0으로
	private static void changemap(int k) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]<=k) {	//해당 위치 값보다 작거나 같으면 체크 배열을 -1로 아니면 0으로
					map_check[i][j]=-1;
				}else {
					map_check[i][j]=0;
				}
			}
		}
	}

	// 맵 출력
	private static void printmap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map_check[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("========");
	}

}
