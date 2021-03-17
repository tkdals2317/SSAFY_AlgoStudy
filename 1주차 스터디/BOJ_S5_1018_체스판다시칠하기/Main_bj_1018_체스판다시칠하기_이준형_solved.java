package baekjun;
import java.io.FileInputStream;
import java.util.Scanner;

public class Main_bj_1018_체스판다시칠하기 {
	static int x,y;
	static int len=8;
	static int[][]bae;
	static int[][]bae_check=new int[8][8];
	public static void main(String[] args) throws Exception{

		System.setIn(new FileInputStream("res/input_bj_1018.txt"));
		Scanner sc=new Scanner(System.in);
		
		x=sc.nextInt();
		y=sc.nextInt();
		
		bae=new int[x][y];
		// 배열 입력
		for(int i=0;i<x;i++) {
			String str=sc.next();
			for(int j=0;j<y;j++) {
				char tmp=str.charAt(j);
				if(tmp=='W')
					bae[i][j]=1;
				else
					bae[i][j]=0;
			}
		}
		// 체크용 배열 입력
		int a=1;
		for (int i = 0; i < len; i++) {
			if(a==1)a=0;
			else a=1;
			for (int j = 0; j < len; j++) {
				bae_check[i][j]=a;
				if(a==1)a=0;
				else a=1;
			}
		}
		
		// 탐색
		int min=Integer.MAX_VALUE;
		int tmp;
		for(int i=0;i<=x-len;i++) {
			for(int j=0;j<=y-len;j++) {
				tmp=search(i,j);
				if(min>tmp)
					min=tmp;
			}
		}
		System.out.println(min);
		sc.close();
	}

	// 바꿔야하는 개수 찾기
	private static int search(int a, int b) {
		int count1=0;
		int count2=0;
		for(int i=a;i<a+len;i++) {
			for(int j=b;j<b+len;j++) {
				if(bae[i][j]!=bae_check[i-a][j-b])
					count1++;
				else count2++;
			}
		}
		if(count1>count2)
			return count2;
		else
			return count1;
	}

}
