package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G4_4485_녹옷입젤_정태현_solved {
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int[] dr = {-1, 0, 1, 0}; //상우하좌
	static int[] dc = {0, 1, 0, -1};
	static int N, K, L, turn;
	static int[][] map, info;
	static ArrayList<Pos> snake;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine()); //배열 크기
		K = Integer.parseInt(br.readLine()); //사과 개수
		map = new int[N][N];
		
		int tR, tC;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			tR = Integer.parseInt(st.nextToken());
			tC = Integer.parseInt(st.nextToken());
			map[tR - 1][tC - 1] = 2; //사과
		}
		L = Integer.parseInt(br.readLine());
		info = new int[L][2]; //방향 변환 정보
		
		for (int i = 0; i < L; i++) {
			String[] str = br.readLine().split(" ");
			info[i][0] = Integer.parseInt(str[0]);
			if(str[1].equals("L")) info[i][1] = 1;
			else info[i][1] = 2; //D(오른쪽)이면 2, L(왼쪽)이면 1
		}
		
		snake = new ArrayList<Pos>();
		turn = 0; //횟수
		snake(0, 0, 1); //처음엔 우방향
		
		System.out.println(turn);
	}
	
	private static void snake(int sR, int sC, int sdir) {
		int dcnt = 0; //방향 변동 횟수 체크용
		snake.add(new Pos(sR, sC)); //뱀 첫 시작점
		
		while(true) { //조건에 드는한 무한으로
			turn++;
			int nr = sR + dr[sdir];
			int nc = sC + dc[sdir]; //다음 머리가 이동할 지점
			
//			if(map[nr][nc] != 2) { //사과가 아니면 꼬리는 없애기
//				snake.remove(0); //첫번째가 꼬리일 것이니까				
//			}
			
			if(!ValidCheck(nr, nc)) break; //얘가 바로 끝지점
			
			if(map[nr][nc] == 2) {
				snake.add(new Pos(nr, nc)); //머리 일단 담칸에 넣고
				map[nr][nc] = 0; //개시이팔 이거때문에 3시간을 낭비했다 ㄹㅇ 모두들 참고하세요
			}else {
				snake.add(new Pos(nr, nc)); //머리 일단 담칸에 넣고
				snake.remove(0); //첫번째가 꼬리일 것이니까		
			}
			
			sR = nr; //다음 방향 바꿔주고
			sC = nc;
			if(turn == info[dcnt][0]) { //방향을 바꿔야 할 때이다
				sdir = dirchange(sdir, info[dcnt][1]);
				dcnt++;
				if(dcnt >= L) dcnt = 0; //L을 넘어가버리면 다 끝났으니 그냥 0으로 돌려놓는다
			}
		}
	}
	
	private static int dirchange(int sdir, int ndir) {
		//0 = 상, 1 = 우, 2 = 하, 3 = 좌
		if(ndir == 1) { //왼쪽
			if(sdir == 0) sdir = 3;
			else if(sdir == 1) sdir = 0;
			else if(sdir == 2) sdir = 1;
			else sdir = 2;
		} else { //오른쪽
			if(sdir == 0) sdir = 1;
			else if(sdir == 1) sdir = 2;
			else if(sdir == 2) sdir = 3;
			else sdir = 0;
		}
		
		return sdir;
	}

	private static boolean ValidCheck(int nr, int nc) {
		if(nr < 0 || nc < 0 || nr >= N || nc >= N) return false; //밖에 나가버렸다
		
		//맵 안에 있다면 몸통이랑 박는지 체크
		for (int i = 0; i < snake.size(); i++) {
			if(snake.get(i).r == nr && snake.get(i).c == nc) {
				return false; //몸통이랑 박네
			}
		}
		
		return true;
	}
}
