import java.util.Scanner;

public class Main_17386_선분교차1_정소영_수정필 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		long x1 = sc.nextLong();
		long y1 = sc.nextLong();
		long x2 = sc.nextLong();
		long y2 = sc.nextLong();
		
		long x3 = sc.nextLong();
		long y3 = sc.nextLong();
		long x4 = sc.nextLong();
		long y4 = sc.nextLong();
		
		long a = ccw(x1,y1,x2,y2,x3,y3);
		long b = ccw(x1,y1,x2,y2,x4,y4);
		long c = ccw(x3,y3,x4,y4,x1,y1);
		long d = ccw(x3,y3,x4,y4,x2,y2);
		
		
		if(a*b<=0 && c*d<=0) {
			System.out.println(1);
		}else System.out.println(0);
		
		sc.close();
		
	}

	private static long ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
		long cal = x1*(y2-y3)+x2*(y3-y1)+x3*(y1-y2);
		if(cal > 0) return 1;
		else if(cal<0) return -1;
		else return 0;
	}

}
