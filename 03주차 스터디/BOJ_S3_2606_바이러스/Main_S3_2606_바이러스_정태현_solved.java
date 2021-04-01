package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S3_2606_solved {
	static boolean[][] Icheck;
	static boolean[] visited;
	static int N, con;
	static int cnt = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		con = Integer.parseInt(br.readLine());
		Icheck = new boolean[N+1][N+1]; //��ǻ�Ͱ� ���
		visited = new boolean[N+1]; //
		
		for(int i=0; i<con; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			Icheck[n1][n2] = true;
			Icheck[n2][n1] = true; //����� ��� ����
		}
		System.out.println(dfs(1)); //��ǻ�� 1�� ����� ��� ��ǻ�� �� üũ
	}
	
	public static int dfs(int c) {
		visited[c] = true;
		
		for(int i=1; i<=N; i++) {
			if(Icheck[c][i] && !visited[i]) { //����Ǿ��ְ�, �湮���� �ʾ����� go
				cnt++;
				dfs(i);
			}
		}
		
		return cnt;
	}

}
