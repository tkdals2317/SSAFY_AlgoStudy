import java.util.Scanner;

public class Main_bj_11866_요세푸스문제0 {

	public static void main(String[] args) throws Exception{
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int [] ia = new int[N];
		boolean [] ba = new boolean[N];
	
		int index = K-1;
		for (int i = 0; i < N; i++) {
			ia[i] = i+1;
		}
		
		ba[index] = true;
		
		System.out.print("<"+ia[index]);
		for (int i = 0; i < N-1; i++) {
			int cnt = 0;
			while(cnt<K) {
				index++;
				if(index == N) index=0;
				if(ba[index]!=true) cnt++; 
			}
			ba[index]=true;
			System.out.print(", "+ia[index]);
		}
		System.out.println(">");				
		sc.close();
	}

}
