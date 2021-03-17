package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_S1_2178_solved {
	static int[] di = {-1, 0, 1, 0}; //�������
	static int[] dj = {0, 1, 0, -1}; //�������
	static int[][] maze;
	static int N, M;
	static boolean[][] visited;
	static int cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maze = new int[N][M];
		visited = new boolean[N][M]; //�湮üũ��
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				maze[i][j] = s.charAt(j) - '0';
			}
		}
		
		bfs(maze);
		
		System.out.println(maze[N-1][M-1]); //������ ĭ ���
	}
	
	public static void bfs(int[][] maze) {
		Queue<Node> q = new LinkedList<Node>(); //�湮�� ��� ���ʷ�
		//Node�� row, col �ѹ��� ��� ����
		q.offer(new Node(0, 0)); //ùĭ ť�� �ְ� ����
		
		while(!q.isEmpty()) {
			Node node = q.poll(); //���� �� ĭ�� poll�ؼ� �۾�
			int row = node.row;
			int col = node.col;
			
			
			visited[row][col] = true; //�湮üũ
			for(int i=0; i<di.length; i++) {
				int nR = row + di[i]; //�������
				int nC = col + dj[i];
				
				if(nR >=0 && nC >= 0 && nR < N && nC < M) { //���ڹٱ�
					if(maze[nR][nC] == 1 && visited[nR][nC] == false) { //�����ִ°�? �������� �ƴѰ�?
						q.offer(new Node(nR, nC)); //�湮�� �� �ִ� ĭ�� ť��
						visited[nR][nC] = true;
						maze[nR][nC] = maze[row][col] + 1; //����ĭ cnt�� = ���� ĭ���� ��ĭ �̵� = ���� ĭ + 1
					}
				}
			}
			
		}
	}

}

class Node {
	int row;
	int col;
	
	public Node(int row, int col) {
		this.row = row;
		this.col = col;
	}
}
