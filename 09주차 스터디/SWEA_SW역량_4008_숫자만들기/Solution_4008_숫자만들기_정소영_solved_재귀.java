import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4008_숫자만들기_정소영_solved_재귀 {
	static int N, max, min;
	static int[] numbers;
	static int[] operators = new int[4];
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/input_4008.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for (int i = 0; i < 4; i++) {
				operators[i] = Integer.parseInt(st.nextToken());
			}
			
			numbers = new int[N];
			st = new StringTokenizer(br.readLine()," ");
			for (int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			calculate(0,0,numbers[0]);
			
			
			
			
			
			System.out.println("#"+t+" "+(max - min));

		}
		br.close();
	}
	private static void calculate(int start, int cnt, int sum) {
		if(cnt==N-1) {
			max = Math.max(max, sum);
			min = Math.min(min, sum);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if(i==0 && operators[i]>0) {
				operators[i]--;
				calculate(start+1, cnt+1, sum + numbers[start+1]);
				operators[i]++;
			}
			if(i==1 && operators[i]>0) {
				operators[i]--;
				calculate(start+1, cnt+1, sum - numbers[start+1]);
				operators[i]++;
			}
			if(i==2 && operators[i]>0) {
				operators[i]--;
				calculate(start+1, cnt+1, sum * numbers[start+1]);
				operators[i]++;
			}
			if(i==3 && operators[i]>0) {
				operators[i]--;
				calculate(start+1, cnt+1, sum / numbers[start+1]);
				operators[i]++;
			}
		}
	}

}
