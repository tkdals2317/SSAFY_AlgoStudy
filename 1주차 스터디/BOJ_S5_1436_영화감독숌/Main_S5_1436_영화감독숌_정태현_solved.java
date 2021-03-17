package algorithm.boj;

import java.util.Scanner;

public class Main_S5_1436_영화감독숌_정태현_solved {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
 
		int num = 666;
		int index = 1;
        
		while(index != N) {
			num++;
			String str = Integer.toString(num); //�񱳸� ���� str�� ��ȯ
			if(str.contains("666")) { //666�� �����ϰ� �ִ��� �����ϴϱ�
				index++;
			}
		}
		System.out.println(num);
		
		sc.close();
	}
}
