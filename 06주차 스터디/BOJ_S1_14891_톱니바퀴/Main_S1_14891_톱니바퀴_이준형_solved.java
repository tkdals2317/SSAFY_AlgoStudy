import java.io.*;
import java.util.*;

public class Main {

	static int[][] map;
	static int[][] new_map;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new int[5][8];
		for (int i = 1; i < 5; i++) { // 배열입력	12시 방향부터 시계방향으로 원소로 주어짐!!
			String str = br.readLine();
			for (int j = 0; j < 8; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

//		printmap();

		int K = Integer.parseInt(br.readLine());
		new_map = new int[5][8]; // 임시 복사를 위한 배열

		for (int i = 0; i < K; i++) { // K횟수 만큼 반복
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()); // 톱니바퀴 위치
			int spin = Integer.parseInt(st.nextToken()); // 회전방향 1 시계 -1 반시계

			spinning(num, spin); // 자기자신 입력

			if(num-1>0)	// 위로체크
				upward(num-1,spin,0);
			if(num+1<5)	// 아래로체크
				downward(num+1,spin,0);
			
			deepcopy();	//배열 복사

//			printmap();
		}
		
		int ans=0;	// 점수계산 12 시방향에 S있는거
		for(int i=1;i<5;i++) {
			if(map[i][0]==1) {
				ans+=Math.pow(2, i-1);
			}
		}
		System.out.println(ans);

		br.close();
	}

	//아래로 가면서 회전 처리 재귀
	private static void downward(int num, int spin, int flag) {
		if(flag==1||map[num][6]==map[num-1][2]) {	//극성 같은 경우 회전없이 그대로 입력
			flag=1;
			for(int i=0;i<8;i++) {
				new_map[num][i]=map[num][i];
			}
		}
		else {	//극성이 다른 경우 회전
			spin=-spin;	// 방향 전환
			spinning(num,spin);
		}
		if(num+1<5)	//더 진행할수 있는지 체크
			downward(num+1, spin, flag);
	}

	//위로 가면서 회전 처리 재귀
	private static void upward(int num, int spin, int flag) {
		if(flag==1||map[num][2]==map[num+1][6]) {	//극성 같은 경우 회전없이 그대로 입력
			flag=1;
			for(int i=0;i<8;i++) {
				new_map[num][i]=map[num][i];
			}
		}
		else {	//극성이 다른 경우 회전
			spin=-spin;	// 방향 전환
			spinning(num,spin);
		}
		if(num-1>0)	//더 진행할수 있는지 체크
			upward(num-1, spin, flag);
	}

	// 배열 복사
	private static void deepcopy() {
		for (int i = 1; i < 5; i++) {
			for (int j = 0; j < 8; j++) {
				map[i][j]=new_map[i][j];
			}
		}
	}

	// 배열 회전해서 입력
	private static void spinning(int num, int spin) {
		if (spin == 1) { // 시계회전
			int tmp = map[num][7];
			for (int i = 7; i > 0; i--) {
				new_map[num][i] = map[num][i - 1];
			}
			new_map[num][0] = tmp;
		} else { // 반시계회전
			int tmp = map[num][0];
			for (int i = 0; i < 7; i++) {
				new_map[num][i] = map[num][i + 1];
			}
			new_map[num][7] = tmp;
		}
	}

	// 배열 출력
	private static void printmap() {
		for (int i = 1; i < 5; i++) { // 배열입력
			for (int j = 0; j < 8; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println("============");
	}

}
