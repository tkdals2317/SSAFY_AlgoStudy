import java.util.Scanner;

public class Main_G4_1493_박스채우기_변준형_solved {
	static int L, W, H, n, cube[];
	static boolean sf=true;

	public static void main(String[] args) {
		cube = new int[21];
		Scanner sc = new Scanner(System.in);
		L = sc.nextInt();
		W = sc.nextInt();
		H = sc.nextInt();
		n = sc.nextInt();
		int a, b;
		for (int i = 0; i < n; i++) {
			a = sc.nextInt();
			b = sc.nextInt();
			cube[a] = b;
		}
		int ans = func(L, W, H);
		if(sf)
			System.out.println(ans);
		else
			System.out.println("-1");
	}

	public static int func(int l, int w, int h) {
		if (l == 0 || w == 0 || h == 0)
			return 0;
		int k = l;
		if (w < k)
			k = w;
		if (h < k)
			k = h;

		int t = logA(k,2);
		do {					//	그냥 while문 썻을때 시간초과... 왜지...
			if (cube[t]==0)
				continue;
			cube[t]--;			//	현재 크기만큼의 큐브 한개를 사용 
			int T = (int) Math.pow(2, t);
			return func(l - T, T, h) + func(l, w - T, h) + func(T, T, h - T) + 1;	//	큐브를 계속 한개씩 사용하기 때문에 + 1
		} while (--t >= 0);						
		sf = false;
		return -1;
	}
	
	public static int logA(int x, int base) {
		return (int)(Math.log(x)/Math.log(base));
	}
}
