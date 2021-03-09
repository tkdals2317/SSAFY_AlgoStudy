import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_G4_17135_캐슬디펜스_이상민_X {
	static int N, M;
	static int D;
	static int[][] map;
	static int[][] tmap;
	static int [] pick;
	static ArrayList<int[]> targets;
	private static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_17135.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		tmap = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		pick = new int[3];
		comb(0,0);
		System.out.println(min);
		br.close();
	}
	static void comb(int cnt, int start) {
		if(cnt==3) {
			min  = Math.min(min, defence(pick));
			return;
		}
		for (int i = start; i <= M; i++) {
			pick[cnt] = i;
			comb(cnt+1, i+1);
		}
	}
	private static int defence(int[] pick) {
		int aCnt = checkEnenmy();
		int kill = 0;
		//System.out.println(cnt);
		while(aCnt!=0) {
			//공격대상 지정
			targets = new ArrayList<int[]>();
			for (int i = 0; i < 3; i++) {
				mDistance(pick[i]);
			}
			for (int i = 0; i < targets.size(); i++) {
				if(map[targets.get(i)[0]][targets.get(i)[1]]==1) {
					map[targets.get(i)[0]][targets.get(i)[1]] = 0;
					kill++;
				}
			}
			for (int i = 0; i < M; i++) {
				if(map[N-1][i]==1) {
					map[N-1][i]=0;
				}
			}
			for (int i = N-1; i >= 1 ; i--) {
				for (int j = 0; j < M; j++) {
					map[i][j] = map[i-1][j];
				}
			}
			aCnt = checkEnenmy();
		}
		return kill;
	}
	
	private static void mDistance(int position) {
		int min = Integer.MAX_VALUE;
		//System.out.println("현재 궁수 위치 :" + 6+ " "+position);
		int targetY = 0;
		int targetX = 0;
 		for (int i = N-1; i > N-1-D; i--) {
			for (int j = 0; j < M; j++) {			
				if(map[i][j]==1) {	
					int d = (Math.abs(N-i)+Math.abs(position-j));
					if(min>=d) {
						min = d;
						targetY = i;
						targetX = j;
						if(j<targetX) {
							min = d;
							targetY = i;
							targetX = j;
						}
					}
				}
			}
		}
 		int [] temp = new int[2];
 		temp[0] = targetY;
 		temp[1] = targetX;
 		targets.add(temp);
	}
	private static int checkEnenmy() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sum+=map[i][j];
			}
		}
		return sum;
	}
}
