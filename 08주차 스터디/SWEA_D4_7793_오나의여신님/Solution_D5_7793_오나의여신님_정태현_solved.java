package algorithm.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
2
5 3
D*S
.X.
.X.
.X.
...
5 3
D*S
...
.X.
.X.
...

=>
#1 10
#2 GAME OVER
 */
public class Solution_D5_7793_오나의여신님_정태현_solved {
	static int[] di = {-1, 0, 1, 0}; //상우하좌
	static int[] dj = {0, 1, 0, -1};
	static int N, M, cnt;
	static char[][] arr;
	static boolean meet;
	static Queue<Pos> sq; //수연 큐
	static Queue<Pos> dq; //악마 큐
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr = new char[N][M];
			sq = new LinkedList<Pos>();
			dq = new LinkedList<Pos>();
			
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < M; j++) {
					arr[i][j] = str.charAt(j);
					if(arr[i][j] == 'S') sq.offer(new Pos(i, j));
					if(arr[i][j] == '*') dq.offer(new Pos(i, j));
				}
			}
			
			meet = false;
			cnt = 0;
			bfs();
			if(!meet) System.out.println("#" + tc + " " + "GAME OVER");
			else System.out.println("#" + tc + " " + (cnt+1));
		}
		

		
	}
	private static void bfs() {
		int size = 0;
		while(!sq.isEmpty()) {
			size = dq.size(); //악마부터
			for (int i = 0; i < size; i++) {
				Pos cur = dq.poll();
				for (int j = 0; j < di.length; j++) {
					int ni = cur.r + di[j];
					int nj = cur.c + dj[j];
					
					if((ni<0 || ni >=N || nj <0 || nj>= M)) continue;
					
					if(arr[ni][nj] == '.' || arr[ni][nj] == 'S') {
						//1초에 악마손아귀 수연이동 동시에 일어나기 때문에 종료하면 안됨
						arr[ni][nj] = '*'; //악손 할수 있으면 ㄱ
						dq.offer(new Pos(ni, nj));
					}
				}
			}
			
			size = sq.size();
			for (int i = 0; i < size; i++) {
				Pos cur = sq.poll();
				for (int j = 0; j < di.length; j++) {
					int ni = cur.r + di[j];
					int nj = cur.c + dj[j];
					
					if((ni<0 || ni >=N || nj <0 || nj>= M)) continue;
					
					if(arr[ni][nj] == 'D') {
						meet = true;
						return;
					}
					
					if(arr[ni][nj] == '.') {
						arr[ni][nj] = 'S';
						sq.offer(new Pos(ni, nj));
						cnt++;
					}
				}
			}
		}
		
	}
}
class Pos {
	int r, c;

	public Pos(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}
	
	
}