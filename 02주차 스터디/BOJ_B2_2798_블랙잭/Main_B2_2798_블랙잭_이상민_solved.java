import java.util.*;
import java.io.*;

public class Main_B2_2798_블랙잭_이상민_solved {
	static int N;
	static int R = 3;
	static int BlackJack;
	static int totalCnt;
	static int [] card;	
	static int [] pick;	
	static boolean [] v;
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2798.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		BlackJack = Integer.parseInt(st.nextToken());
		StringTokenizer st2 = new StringTokenizer(br.readLine()," ");
		card = new int[N];
		pick = new int[R];
		v = new boolean[N];
		for (int i = 0; i < N; i++) {
			card[i] = Integer.parseInt(st2.nextToken());
		}
		comb(0,0);
		System.out.println("max "+max);
		br.close();
	}
	//무조~건 외우자
	static void comb(int cnt, int start) { //조합이 다 완성됬는지 확인하는 cnt, 
		int sum = 0;
		if(cnt == R) {
			for (int i = 0; i < R; i++) {
				sum += pick[i];
			}
			if(sum<=BlackJack) {				
				max = Math.max(max, sum);
				System.out.println(Arrays.toString(pick));
			}
			return;
		}
		for (int i = start; i < N; i++) { //처음부터 x, i는 시도하는 수
			pick[cnt] = card[i];
			comb(cnt+1, i+1); 
		}
	}
}
