import java.util.Scanner;

public class Main_bj_2960_에라토스테네스의체_정소영_solved {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		boolean[] num = new boolean[N+1];
		
		Eratos(N, K, num);
		sc.close();
	}

	private static void Eratos(int n, int k, boolean[] num) {
		int result = 0;
		for(int i=2;i<=n;i++) {
			for(int j=i;j<=n;j += i) {
				if(!num[j]) {
					num[j]=true;
					result++;
					if(result==k) {
						System.out.println(j);
						System.exit(0);
					}
				}
			}
		}
	}

}
