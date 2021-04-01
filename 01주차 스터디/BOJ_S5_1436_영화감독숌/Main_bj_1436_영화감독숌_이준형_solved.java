package baekjun;
import java.io.*;
import java.util.Scanner;

public class Main_bj_1436_영화감독숌 {

	public static void main(String[] args) throws Exception{

		System.setIn(new FileInputStream("res/input_bj_1436.txt"));
		Scanner sc=new Scanner(System.in);
	
		int end=sc.nextInt();
		int n=1;
		int num=666;
		while(true) {
			//666조건이 맞으면 n 증가
			if(true==check(num)) {
//				System.out.println(num);
				if(end==n)break;
				n++;
			}
			num++;
		}
		System.out.println(num);
		
		sc.close();
	}

	private static boolean check(int num) {
		int count=0;
		while(num>=1) {
			if(num%10==6) {
				count++;
			}
			else {
				count=0;
			}
			if(count>=3)return true;
			num/=10;
		}
		return false;
	}

}
