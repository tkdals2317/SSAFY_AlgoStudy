package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_S1_9205_solved {
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=0; tc<T; tc++) {
			N = Integer.parseInt(br.readLine()); //������ ����
			Pos[] p = new Pos[N + 2]; //������ N���� ���, ����
			int[] visited = new int[N + 2];
			boolean check = false;
			Queue<Pos> q = new LinkedList<Pos>(); //�ϳ��� ť��
			for(int i=0; i<N+2; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				p[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			q.offer(p[0]); //������ġ ���� ť��
			
			
			while(!q.isEmpty()) {
				Pos cur = q.poll();
				if(cur.equals(p[N+1])) { //���� ��ġ�ΰ�?
					check = true;
					break;
				}
				
				for(int i=1; i<N+2; i++) { //���� �������� �Ÿ��� 1000����
					if(Math.abs(cur.i - p[i].i) + Math.abs(cur.j - p[i].j) <= 1000 && visited[i]==0) {
						q.offer(p[i]);
						visited[i] = 1;
					}
				}
			}
			
			if(check) System.out.println("happy");
			else System.out.println("sad");
			
		}
	}
	
	static class Pos {
		int i, j;

		public Pos(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		
	}

}






