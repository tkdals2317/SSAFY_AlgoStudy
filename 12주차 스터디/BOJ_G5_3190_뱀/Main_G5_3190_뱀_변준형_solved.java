import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G5_3190_뱀_변준형_solved {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 맵 사이즈
		int k = Integer.parseInt(br.readLine()); // 사과 갯수
		boolean[][] apple = new boolean[n][n]; // 사과 위치
		StringTokenizer st;
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			apple[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = true;
		}
		int l = Integer.parseInt(br.readLine()); // 방향전환 횟수
		int[][] time = new int[l][2]; // 방향전환 데이터
		for (int i = 0; i < l; i++) {
			st = new StringTokenizer(br.readLine());
			time[i][0] = Integer.parseInt(st.nextToken());
			time[i][1] = st.nextToken().equals("L") ? -1 : 1;	//	L이면 -1, D면 1 
		}
		int x = 0; // 뱀 x 좌표
		int y = 0; // 뱀 y 좌표
		int dir = 0; // 방향
		int sec = 0; // 시간
		int index = 0; // 인덱스
		int dx[] = { 0, 1, 0, -1 };		//	우 하 좌 상 순서
		int dy[] = { 1, 0, -1, 0 };
		String c;
		Queue<String> q = new LinkedList<>();
		q.add(x + " " + y);
		while (true) {
			sec++;			//	초 증가
			x += dx[dir];	//	현재 방향으로 이동
			y += dy[dir];
			if (x < 0 || x >= n || y < 0 || y >= n)
				break;			//	범위 밖으로 나가면 종료
			c = x + " " + y;
			if (q.contains(c))	
				break;			//	뱀의 머리가 뱀의 몸통과 겹치면 종료
			q.add(c);	//	좌표 큐에 저장
			if (apple[x][y])
				apple[x][y] = false;	//	사과 데이터 없앰
			else
				q.remove();				//	사과 없으면 뱀의 꼬리 증발
			if (index < l && sec == time[index][0]) {
				dir += time[index++][1];	//	시간에 따라 방향 전환
				if (dir < 0)
					dir = 3;
				if (dir > 3)
					dir = 0;
			}
		}
		System.out.println(sec);
	}
}
