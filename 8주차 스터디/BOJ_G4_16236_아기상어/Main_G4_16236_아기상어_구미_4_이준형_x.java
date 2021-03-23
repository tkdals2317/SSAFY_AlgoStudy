import java.io.*;
import java.util.*;

public class Main_bj_16236_아기상어_구미_4_이준형2 {

	static class pos { // 위치 클래스
		int i;
		int j;

		public pos(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}

	static int[] di = { -1, 0, 0, 1 }; // 방향벡터 상 좌 우 하 우선순위
	static int[] dj = { 0, -1, 1, 0 };

	static int N;
	static int[][] map;
	static int[][] map_main;
	static int shark_size; // 상어 크기
	static int shark_full; // 상어 배 차는거

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map_main = new int[N][N];
		pos shark_start = null;
		for (int i = 0; i < N; i++) { // 맵에 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				map_main[i][j] = tmp;
				if (tmp == 9) { // 상어의 시작위치
					shark_start = new pos(i, j);
				}
			}
		}

		map_main[shark_start.i][shark_start.j] = 0; // 처음위치 0으로 바꾸고 시작
		shark_size = 2; // 초기화
		shark_full = 0;
		int time = bfs(shark_start.i, shark_start.j);

		System.out.println(time); // 걸린 시간 출력

		br.close();
	}

	// bfs 상어 먹는거 반복
	private static int bfs(int get_i, int get_j) {
		int time = 0; // 몇초 흘렀는지 체크용

		ArrayDeque<pos> queue = new ArrayDeque<>(); // 상어 위치저장 큐
		queue.offer(new pos(get_i, get_j)); // 시작위치 대입
		
		deepcopy();

		while (!queue.isEmpty()) { // 큐가 비지 않을때동안 반복
			int size = queue.size();
//			System.out.println(size);

			boolean fish_check = can_eat(); // 먹을수 있는 물고기 남았는지 체크
			if (fish_check == false) {// 없으면 종료
				return time;
			}

			for (int t = 0; t < size; t++) {
				pos shark = queue.poll(); // 큐에있는거 빼서 사용

				if (map[shark.i][shark.j] != 0 && shark_size > map[shark.i][shark.j]&&map[shark.i][shark.j]!=-1) { // 상어가 물고기 먹을수 있는 조건
					shark_full++;
					if (shark_full == shark_size) { // 배 꽉차서 덩치 커지는거
						shark_size++;
						shark_full = 0; // 배 초기화
					}
					map_main[shark.i][shark.j] = 0; // 물고기제거
					queue.clear(); // 큐 비워주고 다시 새로 위치 넣어줌
					queue.offer(new pos(shark.i, shark.j));
					
					System.out.println("물고기 제거"+shark.i+" "+shark.j);
					System.out.println("시간"+time);
					deepcopy();	//맵 복사
					printmap();
					
					time--;	//해당 항목으로 종료시에는 시간 변함없게
					break;
				}

				for (int k = 0; k < 4; k++) { // 4방향 탐색 우선순위 상좌우하
					int ci = shark.i + di[k];
					int cj = shark.j + dj[k];
					if(map[shark.i][shark.j]==0)
						map[shark.i][shark.j]=-1;	//방문체크
					if (ci >= 0 && ci < N && cj >= 0 && cj < N && map[ci][cj] <= shark_size&&map[ci][cj]!=-1) { // 물고기가 상어보다 작거나 같으면 이동
						queue.offer(new pos(ci, cj));
					}
				}
			}
			time++; // 시간 증가
		}

		return time;
	}

	//배열딥카피
	private static void deepcopy() {
		map=new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j]=map_main[i][j];
			}
		}
	}


	// 먹을수 있는 물고기 남았는지 체크
	private static boolean can_eat() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map_main[i][j] != 0 && map_main[i][j] < shark_size) // 먹을수있는거 있으면 true
					return true;
			}
		}
		return false;
	}

	// 맵출력
	private static void printmap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("==============");
	}

}
