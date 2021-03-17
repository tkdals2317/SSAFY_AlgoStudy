package baekjun;
import java.io.*;
import java.util.*;

public class Main_bj_13458_시험감독_구미_4_이준형 {

	static long count;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] bae = new int[N];
		for (int i = 0; i < N; i++) { // 배열에 입력
			bae[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		count = 0; // 카운트 초기화
//		if (B > C) {
			calcBC(bae, B, C, N); // B>C 일때 호출
//		} else {
//			calcCB(bae, B, C, N); // B<=C 일때 호출
//		}

		System.out.print(count);

		br.close();
	}

//	//C가 B와 같거나 더클때
//	private static void calcCB(int[] bae, int B, int C, int N) {
//		for (int i = 0; i < N; i++) {
//			if (bae[i] % C == 0) {
//				count += (bae[i] / C);
//			} else
//				count += ((bae[i] / C) + 1);
//		}
//	}

	//B가 더클때
	private static void calcBC(int[] bae, int B, int C, int N) {
		for (int i = 0; i < N; i++) {
			if (bae[i] > B) {	//B보다 더 큰 값이면
				count++;
				if ((bae[i] - B) % C == 0) {	//나누어 떨어질때
					count += ((bae[i] - B) / C);
				} else {
					count += (((bae[i] - B) / C) + 1);
				}
			} else	//B보다 작거나 같으면 1증가
				count++;
		}

	}

}
