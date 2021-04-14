import java.util.*;
import java.io.*;
/**
Math.abs(Bx-bcArr[k].xpos)+Math.abs(By-bcArr[k].ypos)<=bcArr[k].area
*/
public class Solution_SW역량_2117_홈방범서비스_이상민_solved {
	static int N, M;
	static int [][] map;
	static int hCnt = 0;
	static ArrayList<int[]> houseList;
	static int total = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_swea_2117.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//입력
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			houseList = new ArrayList<int[]>();
			total = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					if(map[i][j]==1) houseList.add(new int[] {i,j});
				}
			}
			//System.out.println(houseList.size());
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					findHouse(i, j);
				}
			}
			//System.out.println(total);
			System.out.println("#"+tc+" "+total);
		}
		
		br.close();
	}
	static void findHouse(int ci, int cj) {
		for (int K = 1; K <= N+1; K++) {
			int cost = K * K + (K - 1) * (K - 1);
			int hCnt = 0;
			int hPrice = 0;
			//System.out.println("현재 위치 : ("+ci+", "+cj+") 범위 : "+K+" 운영비용 :"+ cost);
			for (int[] house : houseList) {
				if(Math.abs(cj-house[1])+Math.abs(ci-house[0])<=K-1) {
					hCnt++;
					hPrice +=M;	
					//System.out.print(Arrays.toString(house));
				}
			}
			if(hPrice-cost>=0) {
				//System.out.println("현재 위치 : ("+ci+", "+cj+") 범위 : "+K+" 운영비용 :"+ cost);
				//System.out.println("보안회사 이익 + : "+(hPrice-cost) + " 집 개수 :" + hCnt);
				total = Math.max(total, hCnt);
			}	
		}
	}


}
