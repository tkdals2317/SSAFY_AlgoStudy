import java.util.*;
import java.io.*;
public class Main_S1_14891_톱니바퀴_이상민_solved {
	static int K; //회전 횟수
	static int [][] gears;
	static boolean[] v;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14981.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		gears = new int[4][8];
		
		for (int i = 0; i < 4; i++) {
			String str = br.readLine();
			for (int j = 0; j < 8; j++) {
				gears[i][j] = str.charAt(j)-'0';
			}
		}
		K = Integer.parseInt(br.readLine()); //명령어 수
	
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");			
			int gear = Integer.parseInt(st.nextToken()); //회전 시킬 톱니바퀴
			int dir = Integer.parseInt(st.nextToken()); //회전 시킬 방향
			v = new boolean[4];
			rotate(gear-1, dir);
		}
		int sol = 0;
		for (int i = 0, score = 1; i < 4; i++, score*=2) {
			if(gears[i][0]==1) sol+=score;
		}
		System.out.println(sol);
		br.close();		
	}
	
	static void rotate(int current, int dir) {
		int cLeft = gears[current][6]; // 현재 기어의 왼쪽 맞물리는 곳 
		int cRight = gears[current][2];// 현재 기어의 오른쪽 맞물리는 곳 
		v[current] = true; // 중복해서 바뀌는 것을 방지하기 위한 방문처리
		if(dir==-1) { // 반시계 방향일 때 배열 왼쪽으로 shift
			int temp = gears[current][0];
			for (int i = 1; i < 8; i++) {
				gears[current][i-1] = gears[current][i];
			}
			gears[current][7] = temp;
		}else { // 시계 방향일 때 배열 오른쪽으로 shift
			int temp = gears[current][7];
			for (int i = 7; i > 0 ; i--) {
				gears[current][i] = gears[current][i-1];
			}
			gears[current][0] = temp;
		}
		//왼쪽 기어가 존재한다면
		if(current-1>=0) {
			//서로 다른 극이고 방문하지 않았다면 왼쪽기어 돌리기
			if(gears[current-1][2]!=cLeft&&!v[current-1]) {
				rotate(current-1, -1*dir);
			}
		}
		//오른쪽 기어가 존재한다면
		if(current+1<4) {
			//서로 다른 극이고 방문하지 않았다면 오른쪽 기어 돌리기
			if(gears[current+1][6]!=cRight&&!v[current+1]) {
				rotate(current+1, -1*dir);
			}
		}
	}
	static void print() {
		System.out.println();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(gears[i][j]);
			}
			System.out.println();
		}
	}
}
