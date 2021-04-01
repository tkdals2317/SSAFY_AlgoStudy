import java.util.*;
import java.io.*;

public class Main_BJ_7571_점모으기_이상민_solved {
	static int N;
	static int M;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_7571.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1 = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st1.nextToken()); //방의 크기
		M = Integer.parseInt(st1.nextToken()); //점의 개수
		
		int [] pointi = new int[M];
		int [] pointj = new int[M];

		for (int i = 0; i < M; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine()," ");
			pointi[i] = Integer.parseInt(st2.nextToken());
			pointj[i] = Integer.parseInt(st2.nextToken());
		}
		Arrays.sort(pointi);
		Arrays.sort(pointj);
		
		int ai = pointi[M/2];
		int aj = pointj[M/2];
				
		System.out.println("기준 i : "+ai+", 기준 j :" + aj);
		int dsum = 0;
		for (int i = 0; i < M; i++) {
			dsum += Math.abs(ai-pointi[i])+Math.abs(aj-pointj[i]);
		}
		
		
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				for (int k = 0; k < M; k++) {					
//					 dsum += Math.abs(i-point[k][0])+Math.abs(j-point[k][1]);
//				}
//				min = Math.min(min, dsum);
//			}
//		}
		System.out.println(dsum);
		br.close();
	}

}
