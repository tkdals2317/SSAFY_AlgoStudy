package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G2_4991_로봇청소기_정태현_solved {
	static class Pos {
		int row;
		int col;
		
		public Pos(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	static int[] dr = {-1, 0, 1, 0}; //상우하좌
	static int[] dc = {0, 1, 0, -1};
	static int w, h, ans;
	static char[][] arr;
	static int[][] d;
	static boolean[] isSelected;
	static ArrayList<Pos> dlist; //로봇 위치, 먼지리스트
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		while(true) {
			st = new StringTokenizer(br.readLine(), " ");
			w = Integer.parseInt(st.nextToken()); //가로
			h = Integer.parseInt(st.nextToken()); //세로
			if(w == 0 && h == 0) break; //종료
			
			arr = new char[h][w];
			dlist = new ArrayList<>();
			ans = 0;
			
			for (int i = 0; i < h; i++) {
				String str = br.readLine();
				for (int j = 0; j < w; j++) {
					arr[i][j] = str.charAt(j);
					if(arr[i][j] == 'o') { //청소기 위치 저장
						dlist.add(0, new Pos(i, j));
					} else if(arr[i][j] == '*') {
						dlist.add(new Pos(i, j));
					}
				}
			}
			
			int size = dlist.size();
			d = new int[size][size];
			isSelected = new boolean[size];
			
			for (int i = 0; i < size; i++) {
				if(bfs(i) == -1) {
					ans = -1;
					break;
				}
			}
			
			if(ans == -1) {
				System.out.println(ans);
				continue;
			} else {
				ans = Integer.MAX_VALUE;
				isSelected[0] = true;
				perm(0, 0, 0);
				System.out.println(ans);
			}
		}
	}
	private static void perm(int dt, int dis, int from) {
		if(dt == isSelected.length - 1) {
			ans = Math.min(ans, dis);
			return;
		}
		
		for (int i = 1; i < isSelected.length; i++) {
			if(!isSelected[i]) {
				isSelected[i] = true;
				perm(dt + 1, dis + d[from][i], i);
				isSelected[i] = false;
			}
		}
		
	}
	private static int bfs(int idx) {
		boolean[][] visited = new boolean[h][w];
		Queue<Pos> q = new LinkedList<>();
		
		Pos rb = dlist.get(idx);
		visited[rb.row][rb.col] = true;
		q.offer(rb);
		int cnt = 0, dirt = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			cnt++;
			
			for (int i = 0; i < size; i++) {
				Pos cur = q.poll();
				
				for (int j = 0; j < dr.length; j++) {
					int nr = cur.row + dr[j];
					int nc = cur.col + dc[j];
					
					if (nr >= h || nr < 0 || nc >= w || nc < 0 || visited[nr][nc] || arr[nr][nc] == 'x')
						continue;
					
					if (arr[nr][nc] == 'o' || arr[nr][nc] == '*') {
						// 현재 도착한 지점이 몇번 인덱스 지점인지 찾는다. 
						for (int to = 0; to < dlist.size(); ++to) {
							Pos end = dlist.get(to);
							if (end.row == nr && end.col == nc) {
								// 거리배열을 갱신한다. 
								d[idx][to] = cnt;
								d[to][idx] = cnt;
								dirt++;
							}
						}
					}
					visited[nr][nc] = true;
					q.offer(new Pos(nr, nc));
				}
			}
		}
		if(dirt != dlist.size() - 1) return -1;
		else return 0;
	}
}
