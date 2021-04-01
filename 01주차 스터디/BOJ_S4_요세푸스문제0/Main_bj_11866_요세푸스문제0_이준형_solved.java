package baekjun;
import java.io.FileInputStream;
import java.util.Scanner;

public class Main_bj_11866_요세푸스문제0 {

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("res/input_bj_11866.txt"));
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int k = sc.nextInt();

		int[] bae = new int[n + 1];

		for (int i = 1; i <= n; i++)
			bae[i] = i;

		int i = 1;
		int count = 0;
		int end=0;
		System.out.print("<");
		while (true) {
			if (bae[i] != 0) {
				count++;
				if (count == k) {
					System.out.print(bae[i]);
					bae[i] = 0;
					count = 0;
					end++;
					if(end==n)break;
					System.out.print(", ");
				}
			}
			i++;
			if (i == n + 1)
				i = 1;
		}
		System.out.print(">");
		sc.close();
	}

}
