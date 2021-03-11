package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.chrono.MinguoChronology;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_S1_2468_안전영역_정태현_solved {
	static int[] di = {-1, 1, 0, 0}; //�����¿�
	static int[] dj = {0, 0, -1, 1};
	static int[][] a;
	static int N;
	static int cnt = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		a = new int[N][N];
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(a[i][j], max);
//				min = Math.min(a[i][j], min);
				//�� �ȿ� ��� ������ min�� �ǹ̰� ����
			}
		}
		
		//5*5 �迭�� ���� 1�� �� ���� min 1�� �����Ͽ� 0�� ������ �ȵ�
		//�� �ȿ� ��� ����Ͽ� 0���� ����
		for (int i = 0; i <= max; i++) { 
			int tcnt = 0;
			sinkIsland(i); //0���� ���� ��� ĭ�� 0����
			boolean[][] visited = new boolean[N][N]; //�� ���̽� ���� ���� ����
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if(a[j][k]!=0 && !visited[j][k]) {
						dfs(j, k, i, visited);
						tcnt++; //�ѹ� dfs �� ���� ������ 1���� ��������
					}
				}
			}
			
			cnt = Math.max(cnt, tcnt);
		}
		
		System.out.println(cnt);
	}
	
	private static void sinkIsland(int base) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(a[i][j] <= base) {
					a[i][j] = 0;
				}
			}
		}
	}

	private static void dfs(int r, int c, int base, boolean[][] visited) {
		visited[r][c] = true; //�湮ó��
		
		for (int i = 0; i < di.length; i++) { //���
			int nr = r + di[i];
			int nc = c + dj[i];
			
			if(nr >= 0 && nc >= 0 && nr < N && nc < N && !visited[nr][nc]) {
				if(a[nr][nc] != 0) {
					dfs(nr, nc, base, visited);
				}
			}
		}
		
	}
	
}