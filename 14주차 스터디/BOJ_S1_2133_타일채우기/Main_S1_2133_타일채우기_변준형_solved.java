import java.util.Arrays;
import java.util.Scanner;

public class Main_S1_2133_타일채우기_변준형_solved {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int result[] = new int[31];
		int sum = 0; // result[0] + result[2] + result[4] ..........

		result[0] = 1;
		result[2] = 3;
		sum = result[0] + result[2];

		int a = sc.nextInt();

		for (int i = 4; i <= a; i = i + 2) {
			result[i] = result[i - 2] + 2 * sum; // = 3result[i-2] + 2*sum[i-4]
			sum += result[i];
		}
		System.out.println(Arrays.toString(result));
		System.out.println(result[a]);
	}
}
