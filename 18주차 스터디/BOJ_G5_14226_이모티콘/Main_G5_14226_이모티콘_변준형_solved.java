import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_G5_14226_이모티콘_변준형_solved {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();				//	이모티콘 수
		int[][] d = new int[n + 1][n + 1];	//	d[s][c]의 상태에 되기 까지 걸리는 시간(초)
		for (int i = 0; i <= n; i++) {
			Arrays.fill(d[i], -1);			//	-1로 초기화 : -1은 방문하기 전
		}
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(1);
		q.add(0);
		d[1][0] = 0;	// 초기값, 이모티콘 수는 1, 클립보드에는 0개, 걸리는 시간 0초
 		while (!q.isEmpty()) {
			int s = q.poll();
			int c = q.poll();
			if (d[s][s] == -1) {					// 복사하는 경우, 클립보드에 s를 복사한다.
				d[s][s] = d[s][c] + 1;
				q.add(s);
				q.add(s);
			}
			if (s + c <= n && d[s + c][c] == -1) {	// 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기를 하는 경우	
				d[s + c][c] = d[s][c] + 1;
				q.add(s + c);
				q.add(c);
			}
			if (s - 1 >= 0 && d[s - 1][c] == -1) {	// 화면에 이모티콘 하나를 삭제하는 경우
				d[s - 1][c] = d[s][c] + 1;
				q.add(s - 1);
				q.add(c);
			}
		}
		int time = -1;
		for (int i = 0; i <= n; i++) {		//	최솟값을 구하는 방법
			if (d[n][i] != -1) {
				if (time == -1 || time > d[n][i]) {
					time = d[n][i];
				}
			}
		}
		System.out.println(time);
		//System.out.println(Arrays.deepToString(d));
	}
}
