import java.util.Scanner;

// 1. 두 팀으로 나눈다 -> 조합(N/2)
// 2. 각 팀의 능력치를 합친 후 차이를 구한다. 
public class Main_bj_14889_스타트와링크_정소영_solved {
	static int N;
	static int[][] map;
	static int[] team, team2;
	static int total=0;
	static int res = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		map = new int[N][N];
		team = new int[N/2];
		team2 = new int[N/2];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				total += map[i][j];
			}
		}
		
		combi(0,0);
		System.out.println(res);
		sc.close();
	}
	
	static boolean[] check = new boolean[N];
	
	private static void combi(int cnt, int start) {
		if(cnt == N/2) {
			int cnt2=0;
			check = new boolean[N];
			for (int i = 0; i < N/2; i++) {
				check[team[i]] = true;
			}
			for (int i = 0; i < N; i++) {
				if(!check[i]) team2[cnt2++] = i;
			}

			temp=0;
			temp2=0;
			combi2(0,0);
			res = Math.min(res, Math.abs(temp-temp2));
			return;
		}
		
		for (int i = start; i < N; i++) {
			//isVisited[i]=true;
			team[cnt] = i;
			combi(cnt+1, i+1);
			//isVisited[i]=false;
		}
	}
	
	static int[] pair = new int[2];
	static int[] pair2 = new int[2];
	
	static int temp=0, temp2 = 0;
	private static void combi2(int cnt, int start) {
		if(cnt == 2) {
			temp += map[pair[0]][pair[1]];
			temp += map[pair[1]][pair[0]];
			temp2 += map[pair2[0]][pair2[1]];
			temp2 += map[pair2[1]][pair2[0]];
			return;
		}
		
		for (int i = start; i < N/2; i++) {
			pair[cnt] = team[i];
			pair2[cnt] = team2[i];
			combi2(cnt+1, i+1);
		}
		
	}
	


}
