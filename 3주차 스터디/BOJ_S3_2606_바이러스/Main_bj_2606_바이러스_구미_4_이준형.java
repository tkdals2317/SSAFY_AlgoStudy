package baekjun;
import java.io.*;
import java.util.*;

public class Main_bj_2606_바이러스_구미_4_이준형 {

	static int count;
	static int[][] bae;
	static int T, flag;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());

		bae = new int[T][T];
		count = 1;

		// 쌍의수만큼 반복
		for (int tc = 0; tc < N; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			flag = 0;
			// 배열 탐색
			for (int i = 0; i < count; i++) {
				searchline(i, a, b);
				if (flag == 1)
					break;
			}

//			printbae();
		}

		// 같은요소 있으면 합치기
		plusbae();

//		printbae();

		// 1이 감염시킨 개수 찾기
		int answer = 0;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < T; j++) {
				if (bae[i][j] == 0)
					break;
				else if (bae[i][j] == 1) { // 1발견하면
					answer = i;
				}
			}
		}

		// 총 개수 찾기
		int ans_count = 0;
		for (int j = 1; j < T; j++) {
			if (bae[answer][j] != 0) {
				ans_count++;
			}
		}
		System.out.println(ans_count);

		br.close();
	}

	// 배열 같은 요소 있으면 합치기
	static void plusbae() {
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++) {
				check_line(i, j);
			}
		}
	}

	// 라인단위씩 체크
	static void check_line(int i, int j) {
		for (int k = 0; k < T; k++) {
			if (bae[i][k] == 0)
				break;
			for (int h = 0; h < T; h++) {
				if (bae[j][h] == 0)
					break;
				if (bae[i][k] == bae[j][h]) { // 같은 항목을 발견한다면
					int len_i = checklen(i);
					int len_j = checklen(i);
					givebae(len_i, len_j, i, j); // 배열에 뒤로부터 이어붙이기
					return;
				}

			}
		}

	}

	// 배열 중복제거해서 넣기
	private static void givebae(int len_i, int len_j, int i, int j) {

		int go = len_i;
		for (int k = 0; k < len_j; k++) {
			if(bae[j][k]==0)
				break;
			int ch = 0;
			for (int h = 0; h < len_i; h++) {
				if(bae[i][h]==0)
					break;
				if (bae[j][k] != bae[i][h]) {
					ch = 1;
				}
			}
			if (ch == 1) {
//				System.out.println("go는"+go);
				bae[i][go] = bae[j][k];
				go++;
			}
			bae[j][k]=0;
		}
		

	}

	// 0이 아닌곳까지의 길이 반환
	static int checklen(int i) {
		int len = 0;
		for (int k = 1; k < T; k++) {
			if (bae[i][k] != 0) {
				len++;
			}
		}
		return len;
	}

	// 배열 출력
	static void printbae() {
		for (int i = 0; i < T; i++) {
			for (int j = 0; j < T; j++) {
				System.out.print(bae[i][j]);
			}
			System.out.println();
		}
		System.out.println("================");

	}

	// 라인 탐색
	static void searchline(int i, int a, int b) {
		for (int j = 0; j < T; j++) {
			// 항목 0을 만났을 경우
			if (bae[i][j] == 0) {
				if (j == 0) { // 배열에 항목이 하나도 없을경우 새로 추가
					count++;
					bae[i][0] = a;
					bae[i][1] = b;
					flag = 1;
					return;
				} else
					break;
			}
			// a나 b를 만날 경우 배열에 나머지 추가
			if (bae[i][j] == a) {
				for (int k = j; k < T; k++) {
					if (bae[i][k] == b) { // 이미 있으면 멈춤
						flag = 1;
						return;
					}
					if (bae[i][k] == 0) {
						bae[i][k] = b;
						flag = 1;
						return;
					}
				}
			} else if (bae[i][j] == b) {
				for (int k = j; k < T; k++) {
					if (bae[i][k] == a) { // 이미 있으면 멈춤
						flag = 1;
						return;
					}
					if (bae[i][k] == 0) {
						bae[i][k] = a;
						flag = 1;
						return;
					}
				}
			}

		}

	}

}
