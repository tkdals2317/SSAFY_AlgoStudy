package algorithm.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_SW역량_2117_홈방범서비스_정태현 {
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int[] dr = {-1, 0, 1, 0}; //상우하좌
	static int[] dc = {0, 1, 0, -1};
	static int N, M, ans;
	static ArrayList<Pos> hlist;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); //N*N 배열
			M = Integer.parseInt(st.nextToken()); //집마다 낼수있는 요금
//			int[][] map = new int[N][N]; //필요없을듯, 그냥 2중포문으로 bfs진입
			hlist = new ArrayList<>(); //집 리스트
			ans = 0; //최대 집 개수 체크
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
//					map[i][j] = Integer.parseInt(st.nextToken());
					if(Integer.parseInt(st.nextToken()) == 1) 
						hlist.add(new Pos(i, j)); //집이면 집 리스트에 넣는다
				}
			}
			
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					house(i, j);
				}
			}
			
			System.out.println("#"+tc+" "+ans);
		}
		
	}
	private static void house(int row, int col) {
//		int maxN = 0;
//		for (int i = 0; i < hlist.size(); i++) { //가장 먼거리 찾자
//			int abs = Math.abs(hlist.get(i).r - row) + Math.abs(hlist.get(i).c - col);
//			maxN = Math.max(abs, maxN) + 1;
//		}
		
		for (int dist = 1; dist <= N+1; dist++) { //maxN의 범위까지 방범 서비스 범위 설정
			int cost = (dist * dist) + (dist-1)*(dist-1); //비용
			int homeCnt = 0; //범위 안에 집이 몇개 있는지 체크
			for (int j = 0; j < hlist.size(); j++) {
				if(Math.abs(hlist.get(j).r - row) + Math.abs(hlist.get(j).c - col) <= dist - 1) { //범위 내부에 있다
					homeCnt++;
				}
			}
			if(homeCnt*M - cost >= 0) { //손해를 보지 않는다
				ans = Math.max(ans, homeCnt);
			} //손해를 본다면 그냥 패스
		}
	}

}
