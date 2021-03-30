import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;


// Kruskal or Prim 전형적인 문제

public class Main_bj_1922_네트워크연결_정소영_solved_Prim {
	static int N, M;
	static int parents[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		//N = sc.nextInt();
		//M = sc.nextInt();
		int[][] adjMatrix = new int[N+1][N+1];
		boolean[] visited = new boolean[N+1];
		int[] minEdge = new int[N+1];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
//			int from = sc.nextInt();
//			int to = sc.nextInt();
//			int weight = sc.nextInt();
			adjMatrix[from][to] = weight;
			adjMatrix[to][from] = weight;
		}
		
		
		int result = 0;
		minEdge[0] = 0;
		minEdge[1] = 0;
		
		for (int c = 1; c <= N; c++) {
			int min = Integer.MAX_VALUE;
			int minVertex = 0;
			for (int i = 1; i <= N; i++) {
				if(!visited[i] && min > minEdge[i]) {
					min = minEdge[i];
					minVertex = i;
				}
			}
			
			result += min;
			visited[minVertex] = true;
			
			for (int i = 1; i <= N; i++) {
				if(!visited[i] && adjMatrix[minVertex][i]!=0 && minEdge[i]>adjMatrix[minVertex][i]) {
					minEdge[i] = adjMatrix[minVertex][i];
				}
			}
		}

		
		System.out.println(result);
		br.close();
		//sc.close();
	}
	
	

}
