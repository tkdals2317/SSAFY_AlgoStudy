import java.util.*;
import java.io.*;

public class Main_BJ_G4_1719_택배_이상민 {
	static final int INF = 9999999;
	static int V, E;
	static int[][] distance;
	static int[][] roadMap;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_1719.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		System.out.println(V + " " + E);
		distance = new int[V + 1][V + 1];
		roadMap = new int[V + 1][V + 1];
		for (int i = 0; i <= V; i++) {
			Arrays.fill(distance[i], INF);
			// System.out.println(Arrays.toString(distance[i]));
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			distance[from][to] = weight;
			distance[to][from] = weight;
			roadMap[from][to] = from;
			roadMap[to][from] = to;
		}
		for (int i = 1; i <= V; i++) {
			System.out.println(Arrays.toString(distance[i]));
		}

		for (int k = 1; k <= V; k++) { // 경유지
			for (int i = 1; i <= V; i++) { // 출발지
				for (int j = 1; j <= V; j++) { // 목적지
					//경유지를 들리는 것이 바로 가는 것보다 짧다면
					if (distance[i][j] > distance[i][k] + distance[k][j]) {
						//경유지를 들러 가는 거리로 최소거리 갱신
						distance[i][j] = distance[i][k] + distance[k][j];
						//경유지를 저장
						roadMap[i][j] = roadMap[k][j];
					}
				}
			}
			print();
		}
		//결과 출력
		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				if (roadMap[i][j] == INF) {
					System.out.print("- ");
				} else if (i == j) {
					System.out.print("- ");
				} else {
					System.out.print(roadMap[j][i] + " ");
				}
				
			}
			System.out.println();
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
		System.out.println("=====================================");

	}
}
