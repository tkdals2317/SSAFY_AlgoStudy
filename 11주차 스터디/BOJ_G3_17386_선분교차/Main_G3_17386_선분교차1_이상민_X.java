import java.util.*;
import java.io.*;

public class Main_G3_17386_선분교차1_이상민_X {
	static int x1, y1, x2, y2, x3, y3, x4, y4;
	
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_bj_16234.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		x1 = Integer.parseInt(st.nextToken());
		y1 = Integer.parseInt(st.nextToken());
		x2 = Integer.parseInt(st.nextToken());
		y2 = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine()," ");
		x3 = Integer.parseInt(st.nextToken());
		y3 = Integer.parseInt(st.nextToken());
		x4 = Integer.parseInt(st.nextToken());
		y4 = Integer.parseInt(st.nextToken());
		
		int A = ccw(x1,y1,x2,y2,x3,y3);
		int B = ccw(x1,y1,x2,y2,x4,y4);
		int C = ccw(x3,y3,x4,y4,x1,y1);
		int D = ccw(x3,y3,x4,y4,x2,y2);
		if(A*B>=0) {
			System.out.println(0);
		}else {
			System.out.println(1);
		}
		
		br.close();
	}
	
	static int ccw(int x1, int y1, int x2, int y2, int x3, int y3) {
		return x1*(y2-y3)+x2*(y3-y1)+x3*(y1-y2);		
	}
	
}
