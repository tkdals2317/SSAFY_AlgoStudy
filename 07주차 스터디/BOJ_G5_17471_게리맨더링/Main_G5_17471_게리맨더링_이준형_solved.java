
import java.io.*;
import java.util.*;

public class Main_bj_17471_게리맨더링_구미_4_이준형 {

	static int N;
	static int[][] map; // 연결정보
	static int[] bae; // 지역 분리
	static int[] visit; // 방문
	static int[] people; // 인구수
	static int count_people;
	static int min_cha; // 최소차이

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		people = new int[N + 1]; // 인구수
		for (int i = 1; i <= N; i++)
			people[i] = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1]; // 연결정보
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			for (int s = 1; s <= size; s++) {
				int tmp = Integer.parseInt(st.nextToken());
				map[i][tmp] = 1; // 양방향 이어줌
				map[tmp][i] = 1;
			}
		}

		bae = new int[N + 1]; // 선거구 분리배열
		for (int i = 1; i <= N; i++) {
			bae[i] = i;
		}
		visit = new int[N + 1];

		min_cha = Integer.MAX_VALUE;
		// 선거구 나누기
		divide(1);

		if (min_cha == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min_cha);

		br.close();
	}

	// 선거구 나누기(조합)
	private static void divide(int idx) {
		if (idx > N) {
			// 시작
			start();
			return;
		}

		bae[idx] = 1;
		divide(idx + 1);
		bae[idx] = 2;
		divide(idx + 1);
	}

	// 선거구 분리 가능한지 조사하고 실행
	private static void start() {
		deepcopy();
		int[] first = new int[2]; // 선거구 이어지는거 시작점
		for (int i = 1; i <= N; i++) { // 1번선거구 출발지
			if (visit[i] == 1) {
				first[0] = i;
				break;
			}
		}
		for (int i = 1; i <= N; i++) { // 2번선거구 출발지
			if (visit[i] == 2) {
				first[1] = i;
				break;
			}
		}
		if (first[0] == 0 || first[1] == 0) // 출발지 하나라도 비면 반환(한개로 분리되는거 방지)
			return;
		
		count_people = 0;
		search(first[0], 1);
		int a = count_people;
		
		count_people = 0;
		search(first[1], 2);
		int b = count_people;


		for(int i=1;i<=N;i++) {
			if(visit[i]==1||visit[i]==2)
				return;
		}

		min_cha = Math.min(min_cha, Math.abs(a - b));

	}

	// 해당이어지는거 연결 dfs
	private static void search(int i, int num) {
		visit[i] = num + 2; // 방문체크
		count_people += people[i];
		for (int j = 1; j <= N; j++) {
			if (map[i][j] == 1 && visit[j] == num) {
				search(j, num);
			}
		}
	}

	// 배열복사
	private static void deepcopy() {
		for (int i = 1; i <= N; i++) {
			visit[i] = bae[i];
		}
	}

}
