import java.util.*;
import java.io.*;

public class Main_G4_18223_민준이와마산그리고건우_이상민_플로이드와샬_시간초과 {
	static int V, E, P;
	static int[][] distance;
	static final int INF = 9999999;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_18223.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		distance = new int[V + 1][V + 1];

		for (int i = 1; i <= V; i++) {
			for(int j = 1; j <=V; j++) {
				if(i==j) continue;
				distance[i][j] = INF;
			}
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			distance[from][to] = weight;
			distance[to][from] = weight;
		}

		for (int k = 1; k <= V; k++) {
			for (int i = 1; i <= V; i++) {
				for (int j = 1; j <= V; j++) {
					if (distance[i][j] > distance[i][k] + distance[k][j]) {
						distance[i][j] = distance[i][k] + distance[k][j];
					}
				}
			}
		}
		//print();
		if(distance[1][V]==distance[1][P]+distance[P][V]) {
			System.out.println("SAVE HIM");
 		}else {
 			System.out.println("GOOD BYE");
 		}
		br.close();
	}
	private static void print() {
		for (int i = 1; i <= V; ++i) {
			for (int j = 1; j <= V; ++j) {
				System.out.print(distance[i][j] + "\t");
			}
			System.out.println();
		}
	}
}
