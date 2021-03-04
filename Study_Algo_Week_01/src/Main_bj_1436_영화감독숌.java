import java.util.Scanner;

public class Main_bj_1436_영화감독숌 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int num = 0;
		int cnt = 0;
		while(true) {
			if(Integer.toString(num).contains("666")) {
				cnt++;
			}
			if(cnt==N) {
				System.out.println(num);
				break;
			}
			num++;
		}
		sc.close();
	}
}
