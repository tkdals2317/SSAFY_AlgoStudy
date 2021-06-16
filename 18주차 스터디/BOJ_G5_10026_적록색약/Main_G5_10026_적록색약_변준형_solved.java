import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_G5_10026_적록색약_변준형_solved {
	static String[][] arr;
	static boolean[][] check;
	static int num = 0;
	static int[] changeX = { 1, -1, 0, 0 };
	static int[] changeY = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		num = Integer.parseInt(br.readLine());
		int max = 0;
		int cnt = 0;
		arr = new String[num][num]; // 입력받는 색 배열 초기화
		String str = "";
		// 입력받기
		for (int i = 0; i < num; i++) {
			str = br.readLine();
			for (int j = 0; j < num; j++) {
				arr[i][j] = Character.toString(str.charAt(j));
			}
		}
		check = new boolean[num][num];
		// 정상인이 본 구역 개수
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				if (check[i][j] != true) {
					str = arr[i][j];
					Count(i, j, str);
					cnt++;
				}
			}
		}
		System.out.print(cnt + " ");
		check = new boolean[num][num];
		cnt = 0;
		// 적록색약인 사람이 본 구역 개수
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				if (check[i][j] != true) {
					str = arr[i][j];
					Count(i, j, str);
					cnt++;
				}
			}
		}
		System.out.print(cnt);
	}

	private static void Count(int a, int b, String color) { // a : x좌표, b : y좌표, color : 색
		check[a][b] = true; // 방문 확인
		// 색 변경
		if ("R".equals(color)) {
			arr[a][b] = "G";
		}
		for (int i = 0; i < 4; i++) {
			int chX = a + changeX[i];
			int chY = b + changeY[i];
			if (chX < num && chY < num && chX >= 0 && chY >= 0) {
				if (check[chX][chY] != true && color.equals(arr[chX][chY])) {
					Count(chX, chY, color);
				}
			}
		}
	}
}