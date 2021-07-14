package programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Soulition_programmers_level3_카드짝맞추기 {
	static ArrayList<int[]> orders= new ArrayList<>();// 순열로 짝 뽑는순서
	static int[] dy = { 1, -1, 0, 0 };
	static int[] dx = { 0, 0, 1, -1 };

	public static void main(String[] args) {
		int[][] board = { { 1, 0, 0, 3 }, { 2, 0, 0, 0 }, { 0, 0, 0, 2 }, { 3, 0, 1, 0 } };
		int y = 1;
		int x = 0;
		//////////////////////////////////////////////////////////////
		int answer = Integer.MAX_VALUE;
		int n = 0;
		for (int[] row : board)// 개수세기
			for (int e : row)
				if (e != 0)
					n++;

		n /= 2;// 짝 개수
		int[] temp = new int[n]; // 짝 개수 크기의 배열 생성
		for (int i = 0; i < n; i++)
			temp[i] = i + 1;

		permutation(0, n, new int[n], temp, new boolean[n]); // 순열로 카드 순서 

		for (int[] order : orders) {
			int total = 0;
			int[] point = new int[2];// 최초커서위치 (y,x)
			point[0] = y;
			point[1] = x;
			int[][] cBoard = new int[4][4];// grid 만들기
			cBoard = copy(cBoard, board);

			for (int card : order) {// 순열로 구한 순서만큼
				int cnt = 0;
				// 목표 카드 찾기
				cnt += bfs(cBoard, card, point) + 1;
				// 목표 카드 선택
				cBoard[point[0]][point[1]] = 0;

				// 짝 찾기
				cnt += bfs(cBoard, card, point) + 1;
				// 짝 찾기 성공
				cBoard[point[0]][point[1]] = 0;
				total += cnt;
			}
			answer = Math.min(total, answer);
		}

		System.out.println(answer);
	}

	private static int[][] copy(int[][] cBoard, int[][] board) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				cBoard[i][j] = board[i][j];
		return cBoard;
	}

	private static int bfs(int[][] board, int target, int[] point) { // 한번의 이동
		int y = point[0];
		int x = point[1];
		Queue<int[]> que = new LinkedList<>();
		boolean[][] visited = new boolean[4][4];

		que.offer(new int[] { y, x, 0 }); // y, x, 이동 횟수
		visited[y][x] = true;

		while (!que.isEmpty()) {
			int[] p = que.poll();
			if (board[p[0]][p[1]] == target) { // 같은 짝 찾으면 횟수 리턴
				point[0] = p[0];
				point[1] = p[1];
				return p[2];
			}
			
			// 4방 탐색
			for (int i = 0; i < 4; i++) {
				int ny = p[0] + dy[i];// direction 상하좌우 탐색
				int nx = p[1] + dx[i];// direction 상하좌우 탐색
				if (ny >= 0 && ny < 4 && nx >= 0 && nx < 4 && !visited[ny][nx]) {
					visited[ny][nx] = true;
					que.offer(new int[] { ny, nx, p[2] + 1 });
				}
			}

			// ctrl + 4방 탐색
			for (int d = 0; d < 4; d++) {
				int[] result = findCard(board, p[0], p[1], d);
				if (!visited[result[0]][result[1]]) {
					visited[result[0]][result[1]] = true;
					que.offer(new int[] { result[0], result[1], p[2] + 1 });
				}
			}
		}
		return 0;
	}

	private static int[] findCard(int[][] board, int y, int x, int d) { // ctrl 이동
		y += dy[d];
		x += dx[d];
		while (y >= 0 && y < 4 && x >= 0 && x < 4) { // 배열의 끝까지 탐색
			if (board[y][x] != 0) // 카드를 만나면 리턴 (가장 가까운 카드로 이동)
				return new int[] { y, x };
			y += dy[d];
			x += dx[d];
		}
		return new int[] { y - dy[d], x - dx[d] }; // 카드를 못만나면 마지막 좌표 리턴
	}

	private static void permutation(int dep, int n, int[] perm, int[] input,  boolean visited []) { // 순열로 탐색
        if(dep == n){
        	int[] temp = new int[n];
			System.arraycopy(perm, 0, temp, 0, n);
			orders.add(temp);
			return;
        }
        
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                visited[i] = true;
                perm[dep] = input[i];
                permutation( dep+1, n, perm, input, visited);
                visited[i] = false;
            }
        }

	}
}