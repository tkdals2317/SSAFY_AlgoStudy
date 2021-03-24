import java.util.Scanner;

public class Main_bj_14888_연산자끼워넣기_정소영_solved {
	static int N, min=Integer.MAX_VALUE, max=Integer.MIN_VALUE;
	static int[] num;
	static int[] oper = new int[4];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		num = new int[N];
		for (int i = 0; i < N; i++) {
			num[i] = sc.nextInt();
		}
		for (int i = 0; i < 4; i++) {
			oper[i] = sc.nextInt();
		}
		dfs(num[0],1);
		System.out.println(max);;
		System.out.println(min);
		sc.close();
	}
	private static void dfs(int n, int index) {
		if(index==N) {
			max = Math.max(max, n);
			min = Math.min(min, n);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			
			if(oper[i]>0) {
				oper[i]--;
				
				if(i==0) {
					dfs(n+num[index],index+1);
				}else if(i==1) {
					dfs(n-num[index],index+1);
				}else if(i==2) {
					dfs(n*num[index],index+1);
				}else if(i==3) {
					dfs(n/num[index],index+1);
				}
				
				oper[i]++;
			}
			
			
		}
	}

}
