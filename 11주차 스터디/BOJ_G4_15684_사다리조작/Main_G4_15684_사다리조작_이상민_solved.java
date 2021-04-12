import java.util.*;
import java.io.*;

public class Main_G4_15684_사다리조작_이상민_solved {
	static int W, M, H; // 세로선의 개수 : W 가로선의 개수 : M 가로선을 놓을 수 있는 위치의 개수 H
	static int map[][];
	static int count; //사다리를 놓는 개수
	static boolean flag; //종료조건
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_bj_15684.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		W = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H+1][W+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int ypos = Integer.parseInt(st.nextToken());
			int xpos = Integer.parseInt(st.nextToken());		
			map[ypos][xpos] = 1; //1이면 오른쪽으로 사다리 놓기
			map[ypos][xpos+1] = 2; //2이면 왼쪽으로 사다리 놓기

		}
		
		/*for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}*/
		
		for (int i = 0; i <= 3; i++) {
			//count는 사다리를 놓는 갯수
			count = i;
			dfs(0);
			//따로 min처리를 해주지 않아도 낮은 count에서 flag가 바뀌면 종료하므로써 백트래킹
			if(flag) break;
		}
		//flag가 false면 -1 true면 위 for문의 break 시점의 count 출력
		System.out.println(flag?count:-1);
		br.close();
	}
	//cnt가 사다리를 놓는 갯수이 count와 같아지면 사다리를 다 놓은 경우이므로 return
	static void dfs(int cnt) {
		//flag가 이미 바뀌었다면 더 이상 진행할 필요 X
		if(flag) return;
		//사다리를 다 놓았다면
		if(cnt==count) {
			//check가 true면 사다리 조작 성공!, 더 이상 불필요한 dfs진행을  막기 위해 flag를 false 처리
			if(check()) flag = true;
			return;
		}
		for (int i = 1; i <= H; i++) {
			for (int j = 1; j < W; j++) {
				//현재 놓으려는 다리와 오른쪽 칸이 0일때만 사다리 놓기
				if(map[i][j]==0&&map[i][j+1]==0) {
					//사다리 놓아보기
					map[i][j] = 1;
					map[i][j+1] = 2;
					dfs(cnt+1);
					//놓았던 사다리 치우기
					map[i][j] = 0;
					map[i][j+1] = 0;
				}
			}
		}
		
	}
	private static boolean check() {
		for (int j = 0; j < W; j++) {
			//맨위 부터 시작
			int ni = 0;
			//검사하려는 세로열로 nj세팅
			int nj = j;
			//ni가 사다리 끝으로 갈 때까지
			while(ni<=H) {
				//현재 위치가 1이면 오른쪽으로 이동
				if(map[ni][nj]==1) nj++; 
				//현재 위치가 2이면 왼쪽으로 이동
				else if(map[ni][nj]==2) nj--;
				//무조건 아래로 한칸씩은 내려간다
				ni++;
			}
			//하나라도  nj가 j와 같지 않으면 사다리 조작 실패했으므로  false 반환
			if(nj!=j) return false;
		}
		//위 조건문을 통과했다는 것은 모든 세로열이 도착지열과 같으므로 true를 반환
		return true;
	}
}
