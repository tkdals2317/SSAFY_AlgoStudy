package algorithm.swea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution_D2_1945_간단한소인수분해_정태현_solved {
	static int[] dnums = {2,3,5,7,11};
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/D2_1945_input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		
		for(int tc=1; tc<=T; tc++) {
			int[] ans = new int[dnums.length];
			int num = sc.nextInt();
			for(int i=0; i<dnums.length; i++) {
				while(num % dnums[i] == 0) { //�������� ���� �ȴٴ� �� ���̻� �������ٴ� ��
					ans[i] += 1;
					num = num/dnums[i];
				}
			}
			System.out.print("#"+tc+" ");
			for(int j=0; j<dnums.length; j++) {
				System.out.print(ans[j]+" ");
			}
			System.out.println();
			
		}
		
		sc.close();
	}
}
