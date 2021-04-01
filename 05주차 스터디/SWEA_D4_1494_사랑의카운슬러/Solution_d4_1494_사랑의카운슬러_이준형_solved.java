package swea;
import java.io.*;
import java.util.*;

public class Solution_d4_1494_사랑의카운슬러_구미_4_이준형 {

	static class Pos { // 위치 클래스
		int i;
		int j;

		public Pos() {
		}

		public Pos(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}

	static int size; // 초기 지렁이 수
	static int[] check; // 체크배열
	static Pos[] pos; // 위치배열
	static long min_len;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			pos = new Pos[N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				pos[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			size = N; // 초기화
			check = new int[size];
			min_len=Long.MAX_VALUE;
			search(0,0, N, 1);
			
			System.out.println("#"+tc+" "+min_len);

		}

		br.close();
	}

	// 짧은 거리 찾기 dfs
	private static void search(int idx,int count, int N, int flag) { // 순서,사용개수,지렁이수,단계
		if (N == 2) { // 항목 두개만 남은 경우 탈출조건
//			printcheck();
			long tmp_len=calc_len(flag);			
//			System.out.println(tmp_len);
			min_len=Math.min(min_len, tmp_len);		//짧은 길이가 우선순위
			return;
		} else if (count == 2) {	//2가지를 고른경우
			search(0,0, N - 2, flag + 1);
		} 
		
		for (int i = 0; i < size; i++) {
			if (check[i] != 0) // 이미 사용된거면 다음꺼 찾기
				continue;
			check[i]  = flag; // 사용
			search(idx+1,count + 1, N, flag);
			check[i] = 0; // 사용x
		}
	}

	//체크배열 출력
	private static void printcheck() {
		for (int i = 0; i < size; i++) {
			System.out.print(check[i]);
		}System.out.println();
		System.out.println("====================");
	}

//	// 거리 계산
//	private static long calc_len(int flag) {
//		long x=0;
//		long y=0;
//		for (int k = 0; k < flag; k++) { // 단계별로 계산
////			System.out.println("실행");
//			Stack<Pos> stack = new Stack<>();
//			for (int z = 0; z < size; z++) {
//				if (check[z] == k) // 해당 단계 리스트 추가
//					stack.push(new Pos(pos[z].i, pos[z].j));
//			}
//			Pos p1=stack.pop();
//			Pos p2=stack.pop();
////			System.out.println(p1.i+" "+p2.i);
//			x += p1.i - p2.i;
//			y += p1.j - p2.j;
//			stack.clear();
//		}
////		System.out.println(x*x);
////		System.out.println(x+" "+y);
//		return (x*x+y*y);
//	}
	
	
	
	// 거리 계산
	private static long calc_len(int flag) {
		long x=0;
		long y=0;
		long ans=0;
		for (int k = 0; k < flag; k++) { // 단계별로 계산
//			System.out.println("실행");
			Stack<Pos> stack = new Stack<>();
			for (int z = 0; z < size; z++) {
				if (check[z] == k) // 해당 단계 리스트 추가
					stack.push(new Pos(pos[z].i, pos[z].j));
			}
			Pos p1=stack.pop();
			Pos p2=stack.pop();

			long x1=p1.i-p2.i;
			long y1=p1.j-p2.j;
			
			long x2=p2.i-p1.i;
			long y2=p2.j-p1.j;
			
			if((x1*x1+y1*y1)<(x2*x2+y2*y2)) {
				ans+=(x1*x1+y1*y1);
			}
			else {
				ans+=(x2*x2+y2*y2);
			}
			stack.clear();
		}
//		System.out.println(x*x);
//		System.out.println(x+" "+y);
		return ans;
	}

}
