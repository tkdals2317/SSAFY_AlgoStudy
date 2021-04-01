package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_S1_2667_단지번호붙이기_정태현_solved {
	static int[] di = {-1, 1, 0, 0}; //�����¿�
	static int[] dj = {0, 0, -1, 1};
	static int[][] town;
	static int N;
	static int ans, cnt;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		town = new int[N][N];
		visited = new boolean[N][N];
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				town[i][j] = str.charAt(j) - '0';
			}
		}
		
		findHouse(list); //�ϴ� ������ ã�� dfs
		
		Collections.sort(list);
		
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	private static void findHouse(ArrayList<Integer> list) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(town[i][j]==1 && visited[i][j]==false) {
					cnt = 1; //ù��° ������ ����
					dfs(i, j);
					list.add(cnt);
				}
			}
		}
	}
	private static void dfs(int r, int c) {
		visited[r][c] = true; //�湮ó��
		
		for (int i = 0; i < di.length; i++) { //���
			int nr = r + di[i];
			int nc = c + dj[i];
			
			if(nr >= 0 && nc >= 0 && nr < N && nc < N && !visited[nr][nc]) {
				if(town[nr][nc] == 1) {
					dfs(nr, nc);
					cnt++;
				}
			}
		}
		
	}
	
}