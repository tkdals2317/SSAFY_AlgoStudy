import java.io.*;
import java.util.*;

public class Main_bj_2667_단지번호붙이기_구미_4_이준형2 {

	static int[] di = { 0, 0, 1, -1 };	//방향벡터
	static int[] dj = { 1, -1, 0, 0 };
	static int[][] map;
	static int size;	//배열크기
	static int count;	//집 개수

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		size = Integer.parseInt(br.readLine());

		map = new int[size][size];
		for (int i = 0; i < size; i++) { // 배열에 입력
			String str = br.readLine();
			for (int j = 0; j < size; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

//		printmap();

		count=2;
		for (int i = 0; i < size; i++) { // 배열에 입력
			for (int j = 0; j < size; j++) {
				if (map[i][j] == 1) {	//1이면 dfs 호출
					search(i, j);
					count++;
				}
			}
		}

//		printmap();

		int[] house = new int[count+1];
		for (int i = 0; i < size; i++) { // 배열에 입력
			for (int j = 0; j < size; j++) {
				if (map[i][j] !=0) {	//0아니면 맞는 항목 값 증가
					house[map[i][j]]++;
				}
			}
		}
		
		Arrays.sort(house);		//크기만큼 정렬
//		System.out.println(Arrays.toString(house));
		
		System.out.println(count-2);
		for (int i = 0; i < house.length; i++) {	//카운팅한 배열 출력
			if (house[i] != 0) {
				System.out.println(house[i]);
			}
		}
		
		br.close();
	}

	//DFS 이어진 개수 찾기
	private static void search(int i, int j) {
		map[i][j]=count;	//해당항목 값 번호로 변경
		for (int k = 0; k < 4; k++) {	//4방향 탐색
			int ci = i + di[k];
			int cj = j + dj[k];
			if (ci >= 0 && ci < size && cj >= 0 && cj < size && map[ci][cj] == 1) {	//탐색가능조건
				search(ci, cj);
			}
		}
	}

	// 배열 출력
	private static void printmap() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

}
