import java.util.Scanner;

public class Solution_d2_1945_간단한소인수분해 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int [] pa = {2,3,5,7,11};

		
		for(int tc = 0; tc < T; tc++) {
			int input = sc.nextInt();
			int [] sa = new int[5];
			for (int i = 0; i < 5; i++) {
				while(true) {
					int surplus = input%pa[i];
					if(surplus==0) {
						sa[i]++;
						input = input/pa[i];
					}
					else break;
				}
			}
			System.out.println("#"+ (tc+1)+" "+sa[0]+" "+sa[1]+" "+sa[2]+" "+sa[3]+" "+sa[4]);
		}
		sc.close();
	}

}
