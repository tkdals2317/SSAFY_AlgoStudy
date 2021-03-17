package baekjun;
import java.io.*;
import java.util.*;

public class Main_bj_2178_미로탐색_구미_4_이준형_bfs {

	static int N, M;
	static int[][] map;
	static int[][] visit;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	// 위치 저장하는 클래스
	static class pos {
		int i;
		int j;

		pos(int ci, int cj) {
			i = ci;
			j = cj;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		visit=new int [N][M];
		search(0, 0);
		
		//도착점 까지의 최단 길이 출력
		System.out.println(visit[N-1][M-1]);
		
		br.close();
	}

	static void search(int i, int j) {
		Queue<pos> queue = new ArrayDeque<pos>();
		
		//제일 처음 큐에 항목 넣어줌
		queue.offer(new pos(i, j));
		visit[i][j]=1;
		
		// 큐가 빌때까지 반복
		while (!queue.isEmpty()) {
			//큐에서 꺼내어 i,j 저장
			pos temp=queue.poll();
			int gi=temp.i;
			int gj=temp.j;
			
//			System.out.println("=========");
//			printmap();
			
			//갈수 있는 전방향 큐에 넣기
			for (int k = 0; k < 4; k++) {
				int ci = gi + dx[k];
				int cj = gj + dy[k];
				if (ci >= 0 && ci < N && cj >= 0 && cj < M && map[ci][cj] == 1&&visit[ci][cj]==0) {
					queue.offer(new pos(ci, cj));
					visit[ci][cj]=visit[gi][gj]+1;
				}
			}
			
		}

	}

	static void printmap() {
		for (int i = 0; i < N ; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(visit[i][j]+"\t");
			}
			System.out.println();
		}
	}

}
