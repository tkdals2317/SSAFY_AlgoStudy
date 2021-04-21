import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main_G4_4485_녹색옷입은애가젤다지_변준형_solved {
	static PriorityQueue<Node> pq;
	static int distance[][];
	static int map[][];
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { 1, -1, 0, 0 };
	static ArrayList<Node>[] al;
	static int size;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testcase = 1;
		while (true) {
			size = sc.nextInt();
			if (size == 0)
				break;
			map = new int[size][size];
			distance = new int[size][size];
			pq = new PriorityQueue<>();
			for (int i = 0; i < size; i++) {
				for (int k = 0; k < size; k++) {
					map[i][k] = sc.nextInt();
					distance[i][k] = Integer.MAX_VALUE;
				}
			}
			distance[0][0] = 0;
			pq.add(new Node(0, 0, map[0][0]));
			dijkstra();
			System.out.println("Problem " + (testcase) + ": " + distance[size - 1][size - 1]);
			testcase++;
		}
	}

	public static void dijkstra() {
		while (!pq.isEmpty()) {
			int c_x = pq.peek().x;
			int c_y = pq.peek().y;
			int c_distance = pq.peek().distance;
			pq.remove();
			for (int i = 0; i < 4; i++) {
				int n_x = c_x + dx[i];
				int n_y = c_y + dy[i];
				if (n_x >= 0 && n_y >= 0 && n_x < size && n_y < size) 
				{
					if (distance[n_x][n_y] > c_distance + map[n_x][n_y]) 
					{
						distance[n_x][n_y] = c_distance + map[n_x][n_y];
						pq.add(new Node(n_x, n_y, distance[n_x][n_y]));
					}
				}
			}
		}
	}
	
	static class Node implements Comparable<Node> {
		int distance;
		int x, y;
		Node(int x, int y, int distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}
		@Override
		public int compareTo(Node o) {
			return this.distance <= o.distance ? -1 : 1;
		}
	}

}
