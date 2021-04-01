package swea;
import java.io.FileInputStream;
import java.util.Scanner;

public class Solution_d2_1945_간단한소인수분해_구미_4_이준형 {

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("res/input_d2_1945.txt"));
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int bae[] = { 2, 3, 5, 7, 11 };
		int bae_count[] = new int[5];

		for (int k = 1; k <= n; k++) {
			int num = sc.nextInt();
			int tmp=num;
			for(int i=0;i<=4;i++) {
				int count=0;
				while(true) {
					if(tmp%bae[i]==0) {
						tmp/=bae[i];
						count++;
					}
					else break;
				}
				bae_count[i]=count;
			}
			
			
			//출력
			System.out.print("#"+k+" ");
			for(int i=0;i<=4;i++) {
				System.out.print(bae_count[i]+" ");
			}System.out.println();
		}

		sc.close();
	}

}
