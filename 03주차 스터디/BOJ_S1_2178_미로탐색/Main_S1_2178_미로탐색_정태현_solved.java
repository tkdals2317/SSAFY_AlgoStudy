package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_S1_2178_solved {
	static int[] di = {-1, 0, 1, 0}; //상우하좌
	static int[] dj = {0, 1, 0, -1}; //상우하좌
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
		visited = new boolean[N][M]; //방문체크용
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				maze[i][j] = s.charAt(j) - '0';
			}
		}
		
		bfs(maze);
		
		System.out.println(maze[N-1][M-1]); //마지막 칸 출력
	}
	
	public static void bfs(int[][] maze) {
		Queue<Node> q = new LinkedList<Node>(); //방문한 노드 차례로
		//Node로 row, col 한번에 묶어서 관리
		q.offer(new Node(0, 0)); //첫칸 큐에 넣고 시작
		
		while(!q.isEmpty()) {
			Node node = q.poll(); //가야 할 칸을 poll해서 작업
			int row = node.row;
			int col = node.col;
			
			
			visited[row][col] = true; //방문체크
			for(int i=0; i<di.length; i++) {
				int nR = row + di[i]; //상우하좌
				int nC = col + dj[i];
				
				if(nR >=0 && nC >= 0 && nR < N && nC < M) { //격자바깥
					if(maze[nR][nC] == 1 && visited[nR][nC] == false) { //갈수있는가? 갔던곳이 아닌가?
						q.offer(new Node(nR, nC)); //방문할 수 있는 칸을 큐에
						visited[nR][nC] = true;
						maze[nR][nC] = maze[row][col] + 1; //현재칸 cnt수 = 이전 칸에서 한칸 이동 = 이전 칸 + 1
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
