import java.util.*;

public class Main_S1_2447_별찍기10_이상민_solved {
	static char [][] map;
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int N = sc.nextInt();
		map = new char [N][N];
		sc.close();
		for(int i=0;i<N;i++) Arrays.fill(map[i],' ');

		divide(N,0,0);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void divide(int current, int ci, int cj) {
		if(current==1) {
			map[ci][cj]='*';
			return;
		}
		
		int dSize = current/3;
		
		//시간 초과				
		divide(dSize, ci,       cj);
		divide(dSize, ci,       cj+dSize*1);
		divide(dSize, ci,       cj+dSize*2);
		
		divide(dSize, ci+dSize*1, cj      );
		divide(dSize, ci+dSize*1, cj+dSize*2);
		
		divide(dSize, ci+dSize*2, cj      );
		divide(dSize, ci+dSize*2, cj+dSize*1);
		divide(dSize, ci+dSize*2, cj+dSize*2);				
	}

}
