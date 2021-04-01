import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution_1952_수영장_정소영_solved {
	static int[] price = new int [4];
	static int[] plan = new int [14];
	static int[] res = new int[14];

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/input_1952.txt"));
		Scanner sc= new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			for (int i = 0; i < 4; i++) {
				price[i] = sc.nextInt();
			}
			
			for (int i = 0; i < 12; i++) {
				plan[i] = sc.nextInt();
			}
			
			
			plan[0] = plan[0]*price[0]<price[1] ? plan[0]*price[0]:price[1];
			dp();
			//System.out.println(Arrays.toString(plan));
			System.out.println("#"+t+" "+Math.min(plan[13], price[3]));
		}
	}

	private static void dp() {
		for (int i = 1; i < 3; i++) {
			plan[i] = (plan[i-1]+plan[i]*price[0])<(plan[i-1]+price[1]) ? (plan[i-1]+plan[i]*price[0]):(plan[i-1]+price[1]);
			plan[i] = plan[i]<price[2] ? plan[i] : price[2];
		}
		for (int i = 3; i < 12; i++) {
			plan[i] = (plan[i-1]+plan[i]*price[0])<(plan[i-1]+price[1]) ? (plan[i-1]+plan[i]*price[0]):(plan[i-1]+price[1]);
			plan[i] = plan[i]<plan[i-3]+price[2] ? plan[i] : plan[i-3]+price[2];
		}
		for (int i = 12; i < 14; i++) {
			plan[i] = plan[11]<plan[i-3]+price[2] ? plan[11] : plan[i-3]+price[2];
		}
	}

}
