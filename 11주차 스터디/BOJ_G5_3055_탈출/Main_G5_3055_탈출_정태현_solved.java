package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G5_3055_탈출_정태현_solved {
	static class Pos { //각 위치용 클래스
		int r;
		int c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int[] dr = {-1, 0, 1, 0}; //상우하좌
	static int[] dc = {0, 1, 0, -1};
	static int R, C;
	static int cnt, bvr, bvc; //고슴도치 움직인 횟수 카운트용, 비버집
	static boolean[][] hvisited, wvisited;
	static char[][] map;
	static Queue<Pos> hogq, waterq;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C]; //R행C열 맵
		hvisited = new boolean[R][C];
		wvisited = new boolean[R][C];
		hogq = new LinkedList<>(); //고슴도치 위치
		waterq = new LinkedList<>(); //물의 위치
		cnt = 0;
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == '*') { //물
					waterq.offer(new Pos(i, j));
				} else if(map[i][j] == 'S') { //고슴도치
					hogq.offer(new Pos(i, j));
				} else if(map[i][j] == 'D') { //비버 굴 위치
					bvr = i;
					bvc = j;
				}
			}
		}
		
		hogBfs();
		
		if(cnt == 0) System.out.println("KAKTUS");
		else System.out.println(cnt);
	}
	
	private static void hogBfs() {
		
		while(!hogq.isEmpty()) { //고슴큐로 bfs 돌자
			waterbfs(); //물 bfs부터 먼저 돌아준다. 그래야 고슴도치가 갈수있는지를 체크가능
			
			cnt++; //여기 위치 맞음?
			
			int size = hogq.size();
			for (int i = 0; i < size; i++) {
				Pos hcur = hogq.poll();
				
				for (int j = 0; j < dr.length; j++) {
					int nr = hcur.r + dr[j];
					int nc = hcur.c + dc[j];
					
					if(nr >= 0 && nc >= 0 && nr < R && nc < C) {
						if(nr == bvr && nc == bvc) return; //비버 집 도착
						
						if(!hvisited[nr][nc] && map[nr][nc] == '.') { //방문안했는가? 갈 수 있는가?
							hvisited[nr][nc] = true;
							map[nr][nc] = 'S'; //고슴도치 이동
							hogq.offer(new Pos(nr, nc));
						}
					}
				}
			}
		}
		
		cnt = 0; //73번째 줄 비버 집에서 return하지 못하고 여기서 return하면 cnt는 0, KAKTUS
		return;
	}

	private static void waterbfs() {
		
		int size = waterq.size();
		
		for (int i = 0; i < size; i++) {
			Pos wcur = waterq.poll();
			
			for (int j = 0; j < dr.length; j++) {
				int nr = wcur.r + dr[j];
				int nc = wcur.c + dc[j];
				
				if(nr >= 0 && nc >= 0 && nr < R && nc < C) {
					if(!wvisited[nr][nc] && map[nr][nc] != 'D' && map[nr][nc] != 'X') {
						map[nr][nc] = '*';
						waterq.offer(new Pos(nr, nc));
						wvisited[nr][nc] = true;
					}
				}
			}
		}
		
		return;
	}
}
